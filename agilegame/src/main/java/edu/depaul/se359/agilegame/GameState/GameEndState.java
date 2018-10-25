package edu.depaul.se359.agilegame.GameState;

/*
    The game end state class. Carry out actions that will happen during game start state.
    No loop because the initial (game start) loop is still active.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

public class GameEndState implements GameState {

    @Override
    public void saveState(GameStateContext ctx) {
        ctx.setState(this);

        // do something here after the game ended (like displaying the score)

    }

    @Override
    public String toString() {
        return "End";
    }

}
