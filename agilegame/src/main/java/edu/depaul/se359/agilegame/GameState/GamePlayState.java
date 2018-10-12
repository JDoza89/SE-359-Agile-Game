package edu.depaul.se359.agilegame.GameState;

/*
    The game play state class. Carry out actions that will happen during game start state.
    Starts a story loop to play user selected story and for additional user input within this story loop.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

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

            System.out.println("Welcome to the [scenario name]!");
            System.out.println("Enter \"next\" to go to next turn. (not coded)");
            System.out.println("Enter \"end\" to end the story.\n");

            // list the available stories in the game that user can play
            // read stories from a JSON file
            System.out.println("Display stories...");

            String command = console.readLine();

            do {

                // go to next turn code here

            }  while(!command.equalsIgnoreCase("end"));

            System.out.println("You end the scenario.");
            System.out.println("[Display score...]");

            GameManager.getInstance().endGame();

        } catch(IOException x) {

            x.printStackTrace();

        }

    }

}
