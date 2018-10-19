import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class gui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Agile Game");

        TextField textField = new TextField();


        Button button1 = new Button("Start");
        Button button2 = new Button("Enter Card Number: ");
        Button button3 = new Button("Play Card");
        Button button4 = new Button("End Game");

        button1.setStyle("-fx-font-size: 2em;");
        button2.setStyle("-fx-border-color: #ff0000; -fx-border-width: 5px;");
        button3.setStyle("-fx-font-size: 2em; -fx-border-color: #ff0000; -fx-border-width: 5px;");
        button4.setStyle("-fx-font-size: 2em;");

        //Add the method to start the game
        button1.setOnAction(action -> {
            System.out.println("The game has started");
        });

        //Add the method that will play the card selected
        button3.setOnAction(action -> {
            System.out.println(textField.getText());
        });

        //Add the method that ends the game
        button4.setOnAction(action -> {
            System.out.println("The game has ended");
        });

        HBox hbox = new HBox(button1, button2, textField, button3, button4);


        Scene scene = new Scene(hbox, 800, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}