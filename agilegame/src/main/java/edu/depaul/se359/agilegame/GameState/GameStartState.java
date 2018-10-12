package edu.depaul.se359.agilegame.GameState;

/*
    The game start state class. Carry out actions that will happen during game start state.
    Starts initial game loop for user inputs.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import edu.depaul.se359.agilegame.Player.TeamManager;

public class GameStartState implements GameState {

    @Override
    public void doAction(GameStateContext ctx) {

        System.out.println("Game Starts");
        ctx.setState(this);
        this.startGameLoop();

    }

    private void startGameLoop() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Welcome to [game name]!\n");
        System.out.println("Let's setup teams and players per team.\n");

        // prompt for teams and players per team
        TeamManager.getInstance().promptForTeamsAndPlayers();

        // save number of teams and players per team to game state context
        GameManager.getInstance().saveTeams(TeamManager.getInstance().getTeams());

        System.out.println("Thank you!\n");

        try {

            System.out.println("Please enter the [scenario] number that you would like to play.");
            System.out.println("Enter \"quit\" to quit the game.\n");

            // list the available stories in the game that user can play
            // read stories from a JSON file
            System.out.println("[List available scenarios to play. For now use \"1\" for demo.]");

            String command = "";

            do {

                if (command.equalsIgnoreCase("1")) {

                    // store user's choice some for the game play state class to access
                    System.out.println("Awesome! You selected 1 for [scenario name].\n");

                    GameManager.getInstance().playGame();

                }

                command = console.readLine();

            }  while(!command.equalsIgnoreCase("quit"));

            System.out.println("You quit the game. Good bye!");

        } catch(IOException x) {

            x.printStackTrace();

        }

    }

    @Override
    public String toString() {

        return "Start";

    }

}
