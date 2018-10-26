package edu.depaul.se359.agilegame.GameState;

/*
    The game manager class. This class is a singleton class to prevent multiple game manager instances.
    Game manager controls all aspect of the game.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

import java.util.ArrayList;
import java.util.Map;

import edu.depaul.se359.agilegame.Player.Team;

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
        startState.saveState(this.gameStateContext);
        System.out.println("game started");
    }

    public void playGame() {
        GameState playState = new GamePlayState();
        playState.saveState(this.gameStateContext);
    }

    public void endGame() {
        GameState endState = new GameEndState();
        endState.saveState(this.gameStateContext);
    }

    public GameStateContext gameContext() {
        return this.gameStateContext;
    }

    public void saveTeams(ArrayList<Team> teams) {
        this.gameStateContext.saveTeams(teams);
    }

    public Map getTeamsInContext() {
        return this.gameStateContext.getTeams();
    }

    public void saveCurrentTurn(int counter) {
        this.gameStateContext.saveCurrentTurn(counter);
    }

    public int getCurrentTurnInContext() {
        return this.gameStateContext.getCurrentTurn();
    }

    void saveCurrentTurnPlayer(int id) {
        this.gameStateContext.saveCurrentTurnPlayer(id);
    }

    public int getCurrentTurnPlayerInContext() {
        return this.gameStateContext.getCurrentTurnPlayer();
    }

}
