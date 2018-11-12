package edu.depaul.se359.agilegame.Gui;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.StoryCard;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.GameState.GameManager;
import edu.depaul.se359.agilegame.GameState.Phase;
import edu.depaul.se359.agilegame.GameState.ProgressManager;
import edu.depaul.se359.agilegame.Hand.Hand;
import edu.depaul.se359.agilegame.Player.Player;
import edu.depaul.se359.agilegame.Player.Team;
import edu.depaul.se359.agilegame.Player.TeamManager;
import edu.depaul.se359.agilegame.Utility.EffectManager;
import edu.depaul.se359.agilegame.Utility.GameUtility;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;


import java.util.ArrayList;

public class Gui extends Application {

    private GameManager gameManager;
    private TeamManager teamsManager;
    private ProgressManager progressManager;

    private int team1Total = 0;
    private int team2Total = 0;
    private Text hands;
    private SecondStage cards;
    private Player currPlayer;
    private Team currTeam;
    private int winner;

    private Text team1Score;
    private Text team2Score;
    private ArrayList<Hand> team1Hand = new ArrayList<>();
    private ArrayList<Hand> team2Hand = new ArrayList<>();
    private ArrayList<StoryCard> story;
    private ArrayList<Card> storyTeam1;
    private ArrayList<Card> storyTeam2;
    private Text team1 = new Text("Team 1 Stories: ");
    private Text team2 = new Text("Team 2 Stories: ");
    private int len;

    private Button btnStart;
    private Button btnPlay;
    private Button btnEnd;
    private Text team1Stories;
    private Text team2Stories;
    private Text txtCardNum;
    private Text txtNumOfTeam;
    private Text txtPlayer;
    private TextField tFieldNumOfPerTeam;
    private TextField tFieldCardNum;
    private Group root;
    private VBox vBox;
    private Scene scene;
    private Text player;
    private Text team;
    private SecondStage playerDeck;
    private int currPlayerID;
    private Label handLabel = new Label("Player Hand:");

    private Text playerDescription = new Text();
    private Label playerRole = new Label();

    private Label currentPhase = new Label();
    private Text phaseDescription = new Text();


    @Override
    public void start(Stage primaryStage) throws Exception {

        // parse json file to decks
        GameUtility.parseJSONtoDecks();

        // shuffle all 3 decks
        GameUtility.shuffleAllDecks();
        Deck.printAllDecks();

        story = Deck.getStoryDeck();

        setUpUIEnvironment(primaryStage);
        listenButtons();

    }

    //Second stage that will contain the players' hand
    public class SecondStage extends Stage {
        Group root2 = new Group(hands);
        VBox y = new VBox(team, player, txtCardNum, tFieldCardNum, btnPlay);
        ScrollPane scroll = new ScrollPane(y);

        SecondStage(){
            y.getChildren().add(playerRole);
            y.getChildren().add(playerDescription);
            y.getChildren().add(handLabel);
            y.getChildren().add(hands);
            y.getChildren().add(root2);
            this.setScene(new Scene(scroll, 820, 800));
            this.show();
        }

    }

    public static void main(String[] args) {

        launch(args);
    }

    private void listenButtons()
    {
        btnStartListen();
        btnPlayListen();
        btnEndListen();
    }

    private void setUpUIEnvironment(Stage primaryStage)
    {
        // link 3 Managers
        setUpManagers();

        // initialize txtGameState on screen
        setGameTexts();

        // initialize 3 game buttons on screen
        setGameButtons();

        // initialize 2 txtField for user input on screen
        setGameTextField();

        // initialize group object
        setGameGroup();

        // initial the game on screen
        setPrimaryStageOnScreen(primaryStage);
    }

    private void btnStartListen()
    {
        //Add the method to start the game
        btnStart.setOnAction(action -> {
            teamsManager.setNumberOfTeams(2);
            teamsManager.setNumberOfPlayers(Integer.parseInt(tFieldNumOfPerTeam.getText()));
            teamsManager.createTeamsAndPlayers();
            gameManager.startGame();

            updateScore();

            // get the first player & hand
            getPlayer();
            updateHand();
            
            cards = new SecondStage();
            cards.setX(400);
            vBox.getChildren().remove(txtNumOfTeam);
            vBox.getChildren().remove(tFieldNumOfPerTeam);
            vBox.getChildren().remove(btnStart);

            team1Stories.setText(teamsManager.getTeam(1).getStoryCards());
            team2Stories.setText(teamsManager.getTeam(2).getStoryCards());

            Phase tmpPhase = ProgressManager.getInstance().getCurrentPhase();
            currentPhase.setText(tmpPhase.getPhaseName());
            phaseDescription.setText(tmpPhase.getPhaseDescription());

/*
            team1Stories.setText(Deck.getInstance().getStory(0, len/2));
            team2Stories.setText(Deck.getInstance().getStory((len/2)+1, len));       */
        });

    }

    private void btnPlayListen()
    {

        //Add the method that will play the card selected
        btnPlay.setOnAction(action -> {

            // get the player's choice
            int cardIndex = Integer.parseInt(tFieldCardNum.getText());

            // get the player's cards
            Hand playerCards = currPlayer.showHand();

            // get the player's chosen card
            Card chosenCard = playerCards.getCard(cardIndex);

            currPlayer.playCard(cardIndex);
            // do the back-end effect, which is adding or reducing score
            EffectManager.doEffect(currTeam, chosenCard);

            updateScore();

            System.out.println(progressManager.getCurrentTurnCount());

            //    hands.setText(Deck.getRoleDeck());
            System.out.println(tFieldCardNum.getText());

            // get the next player & hand
            getPlayer();
            updateHand();

            Phase tmpPhase = ProgressManager.getInstance().getCurrentPhase();
            currentPhase.setText(tmpPhase.getPhaseName());
            phaseDescription.setText(tmpPhase.getPhaseDescription());
        });
    }

    private void btnEndListen()
    {
        //Add the method that ends the game
        btnEnd.setOnAction(action -> {
            team1Stories.setText("Game has ended");
            if(GameManager.getInstance() != null) {
                gameManager.endGame();
            }
            vBox.getChildren().remove(team1Stories);
            vBox.getChildren().remove(team2Stories);

        });
    }

    private void getPlayer(){
        currPlayerID = progressManager.circulateTurns();
        // get the current player
        currPlayer = TeamManager.getInstance().getPlayer(currPlayerID);

        // get the current team from this player
        currTeam = TeamManager.getInstance().getTeam(currPlayer.getTeamId());
        team.setText("Team: " + currPlayer.getTeamId());

    }

    private void updateHand(){
        //update Current player and Team IDs
        player.setText("Player: " + currPlayerID);
        playerRole.setText("You are a " + currPlayer.getName());
        playerDescription.setText(currPlayer.getDescription() + "\n\n");
        hands.setText(currPlayer.showHand().getHand());
    }

    private void updateScore(){
        //update Score after card is played (display)
        team1Total = teamsManager.getTeam(1).getStoryPoint();
        team2Total = teamsManager.getTeam(2).getStoryPoint();
        if(team1Total <= 0){
            team1Total = 0;
            winner = 1;
            this.win();
        }
        if(team2Total <= 0) {
            team2Total = 0;
            winner = 2;
            this.win();
        }
        team1Score.setText("Team 1: " + String.valueOf(team1Total));
        team2Score.setText("Team 2: " + String.valueOf(team2Total));
        this.checkScore();
    }

    private void checkScore(){
        if(team1Total > team2Total){
            team1Score.setFill(Color.BLUE);
            team2Score.setFill(Color.RED);
        }
        else if(team2Total > team1Total){
            team2Score.setFill(Color.BLUE);
            team1Score.setFill(Color.RED);
        }
    }

    private void win(){
        cards.close();
        vBox.getChildren().remove(team2Stories);
        vBox.getChildren().remove(team2);
        vBox.getChildren().remove(team1Stories);
        vBox.getChildren().remove(team1);
        vBox.getChildren().remove(phaseDescription);
        currentPhase.setText("Team " + winner + " is the winner!\nThe rest of you are fired.");
    }
    private void checkTeam(int n, Hand h) {

        if(n == 1){
            team1Hand.add(h);
        }
        else{
            team2Hand.add(h);
        }
    }

    private void setGameTexts()
    {

        team1Stories = new Text();
        team2Stories = new Text();
        player = new Text();
        team = new Text();
        hands = new Text();

        team1Score = new Text("Team 1: " + team1Total);
        team2Score = new Text("Team 2: " + team2Total);

        team1.setFont(Font.font ("Verdana", 25));
        team2.setFont(Font.font ("Verdana", 25));
        currentPhase.setText("Welcome to Agile Game");
        currentPhase.setFont(Font.font ("Verdana", 20));
        team1Stories.setFont(Font.font ("Verdana", 20));
        team2Stories.setFont(Font.font ("Verdana", 20));
        player.setFont(Font.font ("Verdana", 20));
        team.setFont(Font.font ("Verdana", 20));
        hands.setFont(Font.font ("Verdana", 20));
        playerRole.setFont(Font.font ("Verdana", 20));
        handLabel.setFont(Font.font ("Verdana", 25));

        // set the position of the text
        team1Stories.setWrappingWidth(800);

        team2Stories.setWrappingWidth(800);

        hands.setWrappingWidth(800);

        txtCardNum = new Text("Enter card number you wish to select: ");
        txtCardNum.setFont(Font.font ("Verdana", 20));

        txtNumOfTeam = new Text("Number of players per team: ");
        txtNumOfTeam.setFont(Font.font ("Verdana", 20));


        txtPlayer = new Text("Player Hand: ");
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

    private void setGameTextField()
    {
        tFieldNumOfPerTeam = new TextField();
        tFieldCardNum = new TextField();
    }

    private void setGameGroup()
    {
        root = new Group(team1Stories);
    }

    private void setPrimaryStageOnScreen(Stage primaryStage)
    {
        vBox = new VBox(team1Score, team2Score, txtNumOfTeam,
                tFieldNumOfPerTeam, btnStart, btnEnd, currentPhase, phaseDescription,
                team1, team1Stories, team2, team2Stories, root);
        ScrollPane scroll = new ScrollPane(vBox);
        scene = new Scene(scroll, 820, 800);

        // set the app title on top of the window
        primaryStage.setTitle("Agile Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setUpManagers()
    {
        gameManager = GameManager.getInstance();
        teamsManager = TeamManager.getInstance();
        progressManager = ProgressManager.getInstance();
    }

}