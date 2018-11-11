package edu.depaul.se359.agilegame.GameState;

/*
    The progress manager class. This class is a singleton class to prevent multiple progress manager instances.
    Progress Manager controls all aspect of the game progress like player's turn, effect of cards, etc.

    Author:     Ethan Lin
    Created on: October 19, 2018
 */

import edu.depaul.se359.agilegame.Player.Player;
import edu.depaul.se359.agilegame.Player.Team;
import edu.depaul.se359.agilegame.Player.TeamManager;

import java.util.*;

public class ProgressManager {

    private static ProgressManager instance = null;
    private final LinkedList<Integer> orderedPlayerIds = new LinkedList<>();
    private int playerTurnCount = 0;
    private int currentTurnCount = 1;

    public static ProgressManager getInstance() {

        if (instance == null) {
            instance = new ProgressManager();
        }

        return instance;

    }

    public int circulateTurns() {

        // TODO: need to initialize orderedPlayerIds with 2 team's players' ID
        // get current turn player
        int playerId = this.orderedPlayerIds.get(this.playerTurnCount);
        Player player = TeamManager.getInstance().getPlayer(playerId);

        // save current game state to GameStateContext
        GameManager.getInstance().saveCurrentTurnPlayer(player.getId());
        GameManager.getInstance().saveCurrentTurn(this.currentTurnCount);

        // set turn to next player
        this.currentTurnCount++;
        this.playerTurnCount++;

        // reset playerTurnCount to 0 if reached to the end of the ordered players array
        if (this.playerTurnCount >= this.orderedPlayerIds.size()) {
            this.playerTurnCount = 0;
        }

        return playerId;

    }

    public int getCurrentTurnCount() {
        return this.currentTurnCount;
    }

    // function to determine player's turns from different team
    public void determinePlayerTurns() {

        // holds a list of team ids
        ArrayList<Integer> teamIds = new ArrayList<>();

        // holds number of players in team
        int numberOfPlayers = 0;

        // holds player ids in each team
        ArrayList<ArrayList<Integer>> playerIds = new ArrayList<>();

        // loop to get team ids from each available team
        // and add them to the teamIds array list
        for (Team team : TeamManager.getInstance().getTeams()) {

            teamIds.add(team.getId());

        }

        // loop through each team
        for (Integer teamId : teamIds) {

            // set array of players from the current team iteration
            ArrayList<Player> players = TeamManager.getInstance().getTeam(teamId).getAllPlayers();

            // set number of players in current team iteration
            numberOfPlayers = players.size();


            // create an empty ids array
            ArrayList<Integer> ids = new ArrayList<>();

            // loop through the current players in a team
            // and fill the empty ids array with player id
            for (int i = 0; i < numberOfPlayers; i++) {

                ids.add(players.get(i).getId());

            }

            // add the the ids to the playerIds array
            playerIds.add(ids);

        }

        // set index to 0 for ordering players id
        int index = 0;

        // loop through the playerIds array and ordered
        // them into one orderedPlayerIds array
        for (int j = 0; j < numberOfPlayers; j++) {

            for (ArrayList<Integer> playerId : playerIds) {

                orderedPlayerIds.add(index++, playerId.get(j));

            }

        }

    }

}
