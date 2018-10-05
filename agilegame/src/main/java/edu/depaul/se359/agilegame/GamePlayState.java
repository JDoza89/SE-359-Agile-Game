package edu.depaul.se359.agilegame;

public class GamePlayState implements GameState {

    @Override
    public void doAction(GameStateContext ctx) {

        System.out.println("Game Plays");
        ctx.setState(this);

    }

    @Override
    public String toString() {

        return "Play";

    }

}
