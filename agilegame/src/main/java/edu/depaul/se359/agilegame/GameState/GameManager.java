package edu.depaul.se359.agilegame.GameState;

/*
    The game manager class. This class is a singleton class to prevent multiple game manager instances.
    Game manager controls all aspect of the game.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

public class GameManager {

    private static GameManager instance = null;
    final private GameStateContext gameStateContext = new GameStateContext();

    private GameManager() {}

    public static GameManager getInstance() {

        if (instance == null) {
            instance = new GameManager();
        }

        return instance;

    }

    public String getGameState() {

        return gameStateContext.getState().toString();

    }

    public void startGame() {

        GameState startState = new GameStartState();
        startState.doAction(this.gameStateContext);

    }

    public void playGame() {

        GameState playState = new GamePlayState();
        playState.doAction(this.gameStateContext);

    }

    public void endGame() {

        GameState endState = new GameEndState();
        endState.doAction(this.gameStateContext);

    }

}
