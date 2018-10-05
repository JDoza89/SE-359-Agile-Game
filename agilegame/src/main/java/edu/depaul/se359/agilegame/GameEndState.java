package edu.depaul.se359.agilegame;

public class GameEndState implements GameState {

    @Override
    public void doAction(GameStateContext ctx) {

        System.out.println("Game Ended");
        ctx.setState(this);

    }

    @Override
    public String toString() {

        return "End";

    }

}
