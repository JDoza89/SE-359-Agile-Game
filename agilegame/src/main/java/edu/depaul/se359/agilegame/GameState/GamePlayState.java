package edu.depaul.se359.agilegame.GameState;

/*
    The game play state class. Carry out actions that will happen during game start state.
    Starts a story loop to play user selected story and for additional user input within this story loop.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

public class GamePlayState implements GameState {

    @Override
    public void saveState(GameStateContext ctx) {
        ctx.setState(this);
        this.gamePlay();
    }

    private void gamePlay()
    {
        // int playerID = ProgressManager.getInstance().circulateTurns();


        // do something in game play if any
    }

    @Override
    public String toString() {
        return "Play";
    }

}
