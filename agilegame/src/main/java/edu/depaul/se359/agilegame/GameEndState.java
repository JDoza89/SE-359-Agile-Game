package edu.depaul.se359.agilegame;

public class GameEndState implements GameState {

    @Override
    public void doAction(GameStateContext ctx) {

        System.out.println("Game Ended");
        ctx.setState(this);
        this.backToMainMenu();

    }

    @Override
    public String toString() {

        return "End";

    }

    private void backToMainMenu() {

        System.out.println("Would you like to play another [story]?");
        System.out.println("Enter \"quit\" to quit the game.\n");
        System.out.println("[List all available stories again...]");

    }

}
