package edu.depaul.se359.agilegame.GameState;

/*
    The game state context class. Hold the current game state. Also get and set game state.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

class GameStateContext {

    private GameState currentState;

    GameStateContext() {

        currentState = null;
    }

    void setState(GameState state) {

        currentState = state;

    }

    GameState getState() {

        return currentState;

    }

}
