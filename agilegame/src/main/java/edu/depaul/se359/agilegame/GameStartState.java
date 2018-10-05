package edu.depaul.se359.agilegame;

public class GameStartState implements GameState {

    @Override
    public void doAction(GameStateContext ctx) {

        System.out.println("Game Starts");
        ctx.setState(this);

    }

    @Override
    public String toString() {

        return "Start";

    }

}
