package edu.depaul.se359.agilegame.GameState;

/*
    The game end state class. Carry out actions that will happen during game start state.
    No loop because the initial (game start) loop is still active.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

public class GameEndState implements GameState {

    @Override
    public void doAction(GameStateContext ctx) {

        System.out.println("Game Ended!\n");
        ctx.setState(this);
        this.backToMainMenu();

    }

    @Override
    public String toString() {

        return "End";

    }

    private void backToMainMenu() {

        System.out.println("Would you like to play another [scenario]?");
        System.out.println("Enter \"quit\" to quit the game.\n");
        System.out.println("[List all available scenarios again...]");

    }

}
