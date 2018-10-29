package edu.depaul.se359.agilegame.Gui;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.GameState.GameManager;
import edu.depaul.se359.agilegame.GameState.ProgressManager;
import edu.depaul.se359.agilegame.Hand.Hand;
import edu.depaul.se359.agilegame.Player.Player;
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
    TextField textField;
    Button button3;
    Text player = new Text("Player 1 : ");



    Text team1Score = new Text("Team 1: " + score);
    Text team2Score = new Text("Team 2: " + score);
    ArrayList<Hand> team1Hand = new ArrayList<>();
    ArrayList<Hand> team2Hand = new ArrayList<>();
    ArrayList<Card> storyTeam1;
    ArrayList<Card> storyTeam2;

    //ArrayList<Card> deck;

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
        GameUtility.shuffleCards();
        //Deck.printAllDecks();
        int len = Deck.getStoryDeck().size()-1;
        ArrayList<Card> temp = new ArrayList<Card>(Deck.getStoryDeck().subList(0,len/2));
        storyTeam1 = temp;
        temp = new ArrayList<Card>(Deck.getStoryDeck().subList((len/2)+1, len));
        storyTeam2 = temp;
        //deck = Deck.getRoleDeck();


        primaryStage.setTitle("Agile Game");

        textField = new TextField();
        Text text = new Text();
        Text text2 = new Text();
        text.setText("Welcome to Agile Game");

        //text.setFont(Font.font ("Verdana", 25));
        //setting the position of the text
        text.setWrappingWidth(800);
        text.setX(100);
        text.setY(550);


        Group root = new Group(text);


        Button button1 = new Button("Start");
        button3 = new Button("Play Card");
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
        hands.setFont(Font.font ("Verdana", 25));
        hands.setWrappingWidth(800);

        player.setFont(Font.font ("Verdana", 30));

        Text team1 = new Text("Team 1 Stories: ");
        Text team2 = new Text("Team 2 Stories: ");
        text.setWrappingWidth(800);
        text2.setWrappingWidth(800);
        text.setFont(Font.font ("Verdana", 20));
        text2.setFont(Font.font ("Verdana", 20));


        button1.setOnAction(action -> {
            text.setText("The Game has started");
            game = game.getInstance();
            teams = teams.getInstance();
            teams.setNumberOfTeams(2);
            teams.setNumberOfPlayers(Integer.parseInt(t1.getText()));
            teams.createTeamsAndPlayers();

            game.startGame();
            Player players = TeamManager.getInstance().getPlayer(1);
            hands.setText(players.showHand().getHand());
            cards = new SecondStage();
            System.out.println("Num players: " + teams.getNumberOfPlayers());
            System.out.println("Num teams: " + teams.getNumberOfTeams());
            vBox.getChildren().remove(team);
            vBox.getChildren().remove(t1);
            vBox.getChildren().remove(button1);

            text.setText(Deck.getInstance().getStory(0, len/2));
            text2.setText(Deck.getInstance().getStory((len/2)+1, len));
        });

        //Add the method that will play the card selected
        button3.setOnAction(action -> {

            progress = ProgressManager.getInstance();
            int turn = progress.getCurrentTurnCount();
            Player players = TeamManager.getInstance().getPlayer(progress.getCurrentTurnCount());
            progress.circulateTurns();
            player.setText("Player: " + turn);
            hands.setText(players.showHand().getHand());

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
            vBox.getChildren().remove(text2);
        });

        Text player = new Text("Player Hand: ");


        vBox = new VBox(team1Score, team2Score, team, t1, button1, num, button4, team1, text, team2, text2, root);

        scene = new Scene(vBox, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //Second stage that will contain the players' hand
    public class SecondStage extends Stage {
        Label x = new Label("Player Hand");
        Group root2 = new Group(hands);

        VBox y = new VBox(player, textField, button3, hands, root2);

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