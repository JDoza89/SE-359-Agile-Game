package edu.depaul.se359.agilegame.GameState;

import edu.depaul.se359.agilegame.Player.Player;
import edu.depaul.se359.agilegame.Player.Team;
import edu.depaul.se359.agilegame.Player.TeamManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ProgressManager {

    private static ProgressManager instance = null;
    private LinkedList<Integer> orderedPlayerIds = new LinkedList<>();

    private ProgressManager() {

        this.determinePlayerTurns();

    }

    public static ProgressManager getInstance() {

        if (instance == null) {
            instance = new ProgressManager();
        }

        return instance;

    }

    public String startTurn() {

        String command = "";

        Player player = TeamManager.getInstance().getPlayer(this.orderedPlayerIds.getFirst());

        System.out.println(TeamManager.getInstance().getTeams().size() + " teams are playing.");
        System.out.println("Team " + player.getTeamId() + " goes first.\n");

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {

            do {

                ListIterator<Integer> playersIterator = this.orderedPlayerIds.listIterator();

                while (playersIterator.hasNext()) {

                    if ( command.equalsIgnoreCase("end") ) { break; }

                    int id = playersIterator.next();
                    Player turnPlayer = TeamManager.getInstance().getPlayer(id);
                    int playerTeamId = turnPlayer.getTeamId();
                    String playerRole = turnPlayer.getRole().toString();

                    System.out.println("Team " + playerTeamId + " " + playerRole + "'s turn!");
                    System.out.println("[Team " + playerTeamId + " " + playerRole + " picks a card from the story deck...]\n");

                    // replace the following output lines with code to
                    // display current card ids and contents on player's hand
                    // and use a loop to allow player to pick card to play
                    System.out.println("[Display cards on " + turnPlayer.getRole().toString() + "'s hand...]\n");

                    System.out.println("Enter the card number to play. [Not coded yet; just press enter/return key to advance]");
                    System.out.println("Enter \"end\" to end the [scenario].\n");

                    System.out.println("[Wait for player input...]");
                    System.out.println("[Execute the card effect played by the player...]\n");

                    command = console.readLine();

                    // remove current player id from the orderedPlayerIds array
                    // add it back to the end of the array
                    int tempIdHolder = turnPlayer.getId();
                    playersIterator.remove();
                    playersIterator.add(tempIdHolder);

                }

            } while ( !command.equalsIgnoreCase("end") );

        } catch(IOException x) {

            x.printStackTrace();

        }

        return command;

    }

    // function to determine player's turns from different team
    private void determinePlayerTurns() {

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
        for ( int t = 0; t < teamIds.size(); t++ ) {

            // set array of players from the current team iteration
            ArrayList<Player> players = TeamManager.getInstance().getTeam(teamIds.get(t)).getAllPlayers();

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

            for (int k = 0; k < playerIds.size(); k++) {

                orderedPlayerIds.add(index++, playerIds.get(k).get(j));

            }

        }

    }

}
