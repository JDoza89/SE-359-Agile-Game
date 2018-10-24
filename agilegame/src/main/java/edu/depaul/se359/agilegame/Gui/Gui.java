package edu.depaul.se359.agilegame.GUI;

import edu.depaul.se359.agilegame.GameState.GameManager;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Gui extends Application {

    GameManager game;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Agile Game");

        TextField textField = new TextField();
        Text text = new Text();
        text.setText("Hello how are you");

        text.setFont(Font.font ("Verdana", 25));
        //setting the position of the text
        text.setWrappingWidth(300);
        text.setX(100);
        text.setY(550);

        //Creating a Group object
        Group root = new Group(text);


        Button button1 = new Button("Start");
        Button button3 = new Button("Play Card");
        Button button4 = new Button("End Game");

        Text num = new Text("Enter card number you wish to select: ");
        Text team = new Text("How many Players per Team?");
        Text team1 = new Text("Team 1: ");
        Text team2 = new Text("Team 2: ");


        button1.setStyle("-fx-font-size: 2em;");
        button3.setStyle("-fx-font-size: 2em; -fx-border-color: #ff0000; -fx-border-width: 5px;");
        button4.setStyle("-fx-font-size: 2em;");

        num.setFont(Font.font ("Verdana", 20));
        team.setFont(Font.font ("Verdana", 20));
        team1.setFont(Font.font ("Verdana", 20));
        team2.setFont(Font.font ("Verdana", 20));
        team2.setX(400);

        TextField t1 = new TextField();
        TextField t2 = new TextField();
        //Add the method to start the game
        game = game.getInstance();

        button1.setOnAction(action -> {
            text.setText("The Game has started");

            //game.startGame();
        });

        //Add the method that will play the card selected
        button3.setOnAction(action -> {
            System.out.println(textField.getText());
        });

        //Add the method that ends the game
        button4.setOnAction(action -> {
            text.setText("Game has ended");
            //game.endGame();
        });

        VBox vbox = new VBox(team, team1, t1, team2, t2, button1, num, textField, button3, button4, root);


        Scene scene = new Scene(vbox, 800, 800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}