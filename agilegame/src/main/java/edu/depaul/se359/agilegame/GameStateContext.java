package edu.depaul.se359.agilegame;

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
