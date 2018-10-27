package edu.depaul.se359.agilegame.Gui;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.GameState.GameManager;
import edu.depaul.se359.agilegame.GameState.ProgressManager;
import edu.depaul.se359.agilegame.Hand.Hand;
import edu.depaul.se359.agilegame.Player.Role;
import edu.depaul.se359.agilegame.Player.TeamManager;
import edu.depaul.se359.agilegame.Utility.GameUtility;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class Gui extends Application {

    VBox vBox;
    Scene scene;
    int score = 0;
    GameManager game;
    TeamManager teams;
    ProgressManager progress;
    Text hands = new Text();
    SecondStage cards;



    Text team1Score = new Text("Team 1: " + score);
    Text team2Score = new Text("Team 2: " + score);
    ArrayList<Hand> team1Hand = new ArrayList<>();
    ArrayList<Hand> team2Hand = new ArrayList<>();
    ArrayList<Card> story;

    public void checkTeam(int n, Hand h){
        if(n == 1){
            team1Hand.add(h);
        }
        else{
            team2Hand.add(h);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GameUtility.parseJSONtoDecks();
        Deck.printAllDecks();
        story = Deck.getStoryDeck();
        primaryStage.setTitle("Agile Game");

        TextField textField = new TextField();
        Text text = new Text();
        text.setText("Welcome to Agile Game");

        text.setFont(Font.font ("Verdana", 25));
        //setting the position of the text
        text.setWrappingWidth(800);
        text.setX(100);
        text.setY(550);


        Group root = new Group(text);


        Button button1 = new Button("Start");
        Button button3 = new Button("Play Card");
        Button button4 = new Button("End Game");

        Text num = new Text("Enter card number you wish to select: ");
        Text team = new Text("Number of players per team: ");



        button1.setStyle("-fx-font-size: 2em;");
        button3.setStyle("-fx-font-size: 2em; -fx-border-color: #ff0000; -fx-border-width: 5px;");
        button4.setStyle("-fx-font-size: 2em;");

        num.setFont(Font.font ("Verdana", 20));
        team.setFont(Font.font ("Verdana", 20));

        TextField t1 = new TextField();
        //Add the method to start the game


        button1.setOnAction(action -> {
            text.setText("The Game has started");
            game = game.getInstance();
            teams = teams.getInstance();
            teams.setNumberOfTeams(2);
            teams.setNumberOfPlayers(Integer.parseInt(t1.getText()));
            teams.createTeamsAndPlayers();
            for(int i = 1; i< Integer.parseInt(t1.getText()); i++){
                for(int j = 1; j < 3; j++){
                    if(i == 1 ){
                        Hand h1 = new Hand(j, i, Role.PROJECT_MANAGER);
                        checkTeam(j,h1);
                    }
                    else if(i == 2){
                        Hand h2 = new Hand(j, i, Role.SCRUM_MASTER);
                        checkTeam(j, h2);
                    }
                    else{
                        Hand h3 = new Hand(j, i, Role.DEVELOPER);
                        checkTeam(j, h3);
                    }
                }
            }
            game.startGame();
            System.out.println("Num players: " + teams.getNumberOfPlayers());
            System.out.println("Num teams: " + teams.getNumberOfTeams());
            vBox.getChildren().remove(team);
            vBox.getChildren().remove(t1);
            vBox.getChildren().remove(button1);

            text.setText(Deck.getOneCard(story.get(0)));
        });

        //Add the method that will play the card selected
        button3.setOnAction(action -> {

            progress = ProgressManager.getInstance();
            progress.circulateTurns();
            System.out.println(progress.getCurrentTurnCount());
            cards = new SecondStage();
        //    hands.setText(Deck.getRoleDeck());

            System.out.println(textField.getText());
            Deck.printAllDecks();


        });

        //Add the method that ends the game
        button4.setOnAction(action -> {
            text.setText("Game has ended");
            if(game.getInstance() != null) {
                game.endGame();
            }
        });

        Text player = new Text("Player Hand: ");


        vBox = new VBox(team1Score, team2Score, team, t1, button1, num, textField, button3, button4, root);

        scene = new Scene(vBox, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Second stage that will contain the players' hand
    public class SecondStage extends Stage {
        Label x = new Label("Player Hand");
        Group root2 = new Group(hands);
        VBox y = new VBox(hands, root2);

        SecondStage(){
            y.getChildren().add(x);
            this.setScene(new Scene(y, 800, 800));
            this.show();
        }
    }

    public static void main(String[] args) {

        launch(args);
    }
}