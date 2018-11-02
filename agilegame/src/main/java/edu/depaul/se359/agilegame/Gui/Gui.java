package edu.depaul.se359.agilegame.Gui;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.StoryCard;
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

    private VBox vBox;
    private Scene scene;
    private int score = 0;
    private GameManager game;
    private TeamManager teams;
    private ProgressManager progress;
    private Text hands = new Text();
    private SecondStage cards;

    private Text team1Score = new Text("Team 1: " + score);
    private Text team2Score = new Text("Team 2: " + score);
    private ArrayList<Hand> team1Hand = new ArrayList<>();
    private ArrayList<Hand> team2Hand = new ArrayList<>();
    private ArrayList<StoryCard> story;

    private Button btnStart;
    private Button btnPlay;
    private Button btnEnd;
    private Text txtGameState;
    private Text txtCardNum;
    private Text txtNumOfTeam;
    private Text txtPlayer;
    private TextField tFieldNumOfPerTeam;
    private TextField tFieldCardNum;
    private Group root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // parse json file to decks
        GameUtility.parseJSONtoDecks();
        Deck.printAllDecks();

        story = Deck.getStoryDeck();
        //deck = Deck.getRoleDeck();

        // initialize txtGameState on screen
        setGameTexts();

        // initialize 3 game buttons on screen
        setGameButtons();

        // initialize 2 txtField for user input on screen
        setGameTextField();

        root = new Group(txtGameState);

        //Add the method to start the game
        btnStart.setOnAction(action -> {
            txtGameState.setText("The Game has started");
            game = GameManager.getInstance();
            teams = TeamManager.getInstance();
            teams.setNumberOfTeams(2);
            teams.setNumberOfPlayers(Integer.parseInt(tFieldNumOfPerTeam.getText()));
            teams.createTeamsAndPlayers();
            for(int i = 1; i< Integer.parseInt(tFieldNumOfPerTeam.getText()); i++){
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
            vBox.getChildren().remove(txtNumOfTeam);
            vBox.getChildren().remove(tFieldNumOfPerTeam);
            vBox.getChildren().remove(btnStart);

            txtGameState.setText(Deck.getOneCard(story.get(0)));
        });

        //Add the method that will play the card selected
        btnPlay.setOnAction(action -> {

            progress = ProgressManager.getInstance();
            progress.circulateTurns();
            System.out.println(progress.getCurrentTurnCount());
            cards = new SecondStage();
        //    hands.setText(Deck.getRoleDeck());

            System.out.println(tFieldCardNum.getText());
            Deck.printAllDecks();


        });

        //Add the method that ends the game
        btnEnd.setOnAction(action -> {
            txtGameState.setText("Game has ended");
            if(GameManager.getInstance() != null) {
                game.endGame();
            }
        });


        // initial the game on screen
        setPrimaryStageOnScreen(primaryStage);
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

    private void checkTeam(int n, Hand h) {

        if(n == 1){
            team1Hand.add(h);
        }
        else{
            team2Hand.add(h);
        }
    }

    private void setPrimaryStageOnScreen(Stage primaryStage)
    {
        vBox = new VBox(team1Score, team2Score, txtNumOfTeam,
                tFieldNumOfPerTeam, btnStart, txtCardNum,
                tFieldCardNum, btnPlay, btnEnd, root);

        scene = new Scene(vBox, 800, 800);

        // set the app title on top of the window
        primaryStage.setTitle("Agile Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setGameTexts()
    {

        txtGameState = new Text();

        txtGameState.setText("Welcome to Agile Game");
        txtGameState.setFont(Font.font ("Verdana", 25));

        // set the position of the text
        txtGameState.setWrappingWidth(800);
        txtGameState.setX(100);
        txtGameState.setY(550);

        txtCardNum = new Text("Enter card number you wish to select: ");
        txtCardNum.setFont(Font.font ("Verdana", 20));

        txtNumOfTeam = new Text("Number of players per team: ");
        txtNumOfTeam.setFont(Font.font ("Verdana", 20));

        txtPlayer = new Text("Player Hand: ");
    }

    private void setGameTextField()
    {
        tFieldNumOfPerTeam = new TextField();
        tFieldCardNum = new TextField();
    }

    private void setGameButtons()
    {
        btnStart = new Button("Start");
        btnPlay = new Button("Play Card");
        btnEnd = new Button("End Game");

        btnStart.setStyle("-fx-font-size: 2em;");
        btnPlay.setStyle("-fx-font-size: 2em; -fx-border-color: #ff0000; -fx-border-width: 5px;");
        btnEnd.setStyle("-fx-font-size: 2em;");
    }
}