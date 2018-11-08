package edu.depaul.se359.agilegame.Gui;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.StoryCard;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.GameState.GameManager;
import edu.depaul.se359.agilegame.GameState.ProgressManager;
import edu.depaul.se359.agilegame.Hand.Hand;
import edu.depaul.se359.agilegame.Player.Player;
import edu.depaul.se359.agilegame.Player.Role;
import edu.depaul.se359.agilegame.Player.Team;
import edu.depaul.se359.agilegame.Player.TeamManager;
import edu.depaul.se359.agilegame.Utility.EffectManager;
import edu.depaul.se359.agilegame.Utility.GameUtility;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class Gui extends Application {

    private GameManager game;
    private TeamManager teams;
    private ProgressManager progress;

    private int team1Total = 0;
    private int team2Total = 0;
    private Text hands;
    private SecondStage cards;

    private Text team1Score = new Text("Team 1: " + team1Total);
    private Text team2Score = new Text("Team 2: " + team2Total);
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
    @Override
    public void start(Stage primaryStage) throws Exception {

        // parse json file to decks
        GameUtility.parseJSONtoDecks();
        Deck.printAllDecks();

        story = Deck.getStoryDeck();
        //deck = Deck.getRoleDeck();
        len = Deck.getStoryDeck().size()-1;
        ArrayList<Card> temp = new ArrayList<Card>(Deck.getStoryDeck().subList(0,len/2));
        storyTeam1 = temp;
        team1Total = Deck.getInstance().getTotal();
        updateTotal(1, team1Total);
        temp = new ArrayList<Card>(Deck.getStoryDeck().subList((len/2)+1, len));
        storyTeam2 = temp;
        team2Total = Deck.getInstance().getTotal();
        updateTotal(2, team2Total);

        setUpUIEnvironment(primaryStage);

        listenButtons();

    }

    //Second stage that will contain the players' hand
    public class SecondStage extends Stage {
        Label x = new Label("Player Hand");
        Group root2 = new Group(hands);
        VBox y = new VBox(team, player, txtCardNum,
                tFieldCardNum, btnPlay, hands, root2);

        SecondStage(){
            y.getChildren().add(x);
            this.setScene(new Scene(y, 800, 800));
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
            team1Stories.setText("The Game has started");
            teams.setNumberOfTeams(2);
            teams.setNumberOfPlayers(Integer.parseInt(tFieldNumOfPerTeam.getText()));
            teams.createTeamsAndPlayers();
            game.startGame();
            System.out.println("Num players: " + teams.getNumberOfPlayers());
            System.out.println("Num teams: " + teams.getNumberOfTeams());
            playerDeck = new SecondStage();
            vBox.getChildren().remove(txtNumOfTeam);
            vBox.getChildren().remove(tFieldNumOfPerTeam);
            vBox.getChildren().remove(btnStart);


            team1Stories.setText(Deck.getInstance().getStory(0, len/2));
            team2Stories.setText(Deck.getInstance().getStory((len/2)+1, len));        });
    }

    private void btnPlayListen()
    {
        //Add the method that will play the card selected
        btnPlay.setOnAction(action -> {

            int currPlayerID = progress.circulateTurns();

            // get the current player
            Player currPlayer = TeamManager.getInstance().getPlayer(currPlayerID);

            // get the current team from this player
            Team currTeam = TeamManager.getInstance().getTeam(currPlayer.getTeamId());
            team.setText("Team: " + currPlayer.getTeamId());
            // get the player's choice
            // TODO: either the index in the player's hand object
            // TODO: or the with the card ID
          //  int cardIndex = Integer.parseInt(tFieldCardNum.getText());

            // get the player's cards
            ArrayList<Card> playerCards = currPlayer.getPlayedCards();

            // get the player's chosen card
          //  Card chosenCard = playerCards.get(cardIndex);

            // do the back-end effect, which is adding or reducing score
          //  EffectManager.doEffect(currTeam, chosenCard);

            System.out.println(progress.getCurrentTurnCount());

            //    hands.setText(Deck.getRoleDeck());

            player.setText("Player: " + currPlayerID);
            hands.setText(currPlayer.showHand().getHand());

            System.out.println(tFieldCardNum.getText());
        });
    }

    private void btnEndListen()
    {
        //Add the method that ends the game
        btnEnd.setOnAction(action -> {
            team1Stories.setText("Game has ended");
            if(GameManager.getInstance() != null) {
                game.endGame();
            }
            vBox.getChildren().remove(team1Stories);
            vBox.getChildren().remove(team2Stories);

        });
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

        team1.setFont(Font.font ("Verdana", 25));
        team2.setFont(Font.font ("Verdana", 25));
        team1Stories.setText("Welcome to Agile Game");
        team1Stories.setFont(Font.font ("Verdana", 20));
        team2Stories.setFont(Font.font ("Verdana", 20));
        player.setFont(Font.font ("Verdana", 20));
        team.setFont(Font.font ("Verdana", 20));
        hands.setFont(Font.font ("Verdana", 25));

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
                tFieldNumOfPerTeam, btnStart, btnEnd, team1, team1Stories, team2, team2Stories, root);

        scene = new Scene(vBox, 800, 800);

        // set the app title on top of the window
        primaryStage.setTitle("Agile Game");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setUpManagers()
    {
        game = GameManager.getInstance();
        teams = TeamManager.getInstance();
        progress = ProgressManager.getInstance();
    }

    private void updateTotal(int team, int total){
        if(team == 1){
            team1Score.setText("Team 1: " + total);
        }
        else if(team == 2){
            team2Score.setText("Team 2: " + total);
        }
        else{
            System.out.println("Please select team 1 or 2");
        }
    }
}