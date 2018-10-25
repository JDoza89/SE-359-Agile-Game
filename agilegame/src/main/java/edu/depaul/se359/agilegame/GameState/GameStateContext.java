package edu.depaul.se359.agilegame.GameState;

/*
    The game state context class. Hold the current game state. Also get and set game state.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import edu.depaul.se359.agilegame.Player.Team;

class GameStateContext {

    private GameState currentState;
    private final Map<Integer, Integer> teamsMap = new HashMap<>();
    private int turnPlayerId = 0;
    private int currentTurn = 0;

    GameStateContext() {
        currentState = null;
    }

    void setState(GameState state) {
        currentState = state;
    }

    GameState getState() {
        return currentState;
    }

    void saveTeams(ArrayList<Team> teams) {
        for (Team team : teams) {
            this.teamsMap.put(team.getId(),team.getPlayerCount());
        }
    }

    Map getTeams() {
        return this.teamsMap;
    }

    void saveCurrentTurnPlayer(int id) {
        this.turnPlayerId = id;
    }

    int getCurrentTurnPlayer() {
        return this.turnPlayerId;
    }

    void saveCurrentTurn(int counter) {
        this.currentTurn = counter;
    }

    int getCurrentTurn() {
        return this.currentTurn;
    }

}
