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

public class GameStartState implements GameState {

    @Override
    public void doAction(GameStateContext ctx) {

        System.out.println("Game Starts");
        ctx.setState(this);
        this.startGameLoop();

    }

    private void startGameLoop() {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {

            System.out.println("Welcome to [game name]! Please enter the [story] number that you would like to play.");
            System.out.println("Enter \"quit\" to quit the game.\n");

            // list the available stories in the game that user can play
            // read stories from a JSON file
            System.out.println("[List available stories to play. For now use \"1\" for demo purposes.]");

            String command = console.readLine();

            do {

                if (command.equalsIgnoreCase("1")) {

                    // store user's choice some for the game play state class to access
                    System.out.println("Awesome! You selected 1 for [story name].\n");

                    command = "";
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
