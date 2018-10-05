package edu.depaul.se359.agilegame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GamePlayState implements GameState {

    @Override
    public void doAction(GameStateContext ctx) {

        System.out.println("Game Plays");
        ctx.setState(this);
        this.gamePlayLoop();

    }

    @Override
    public String toString() {

        return "Play";

    }

    private void gamePlayLoop() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("Welcome to the [story name]!");
            System.out.println("Enter \"next\" to go to next turn. [Not Coded!]");
            System.out.println("Enter \"end\" to end the story.\n");

            // list the available stories in the game that user can play
            // read stories from a JSON file
            System.out.println("Display stories...");

            String command = console.readLine();

            do {

                // go to next turn code here

            }  while(!command.equalsIgnoreCase("end"));

            System.out.println("You end the story.");
            System.out.println("[Display score...]");

            GameManager.getInstance().endGame();

        } catch(IOException x) {

            x.printStackTrace();

        }

    }

}
