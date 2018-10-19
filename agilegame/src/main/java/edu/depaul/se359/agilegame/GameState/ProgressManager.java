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
    private LinkedList<Integer> playerIds = new LinkedList<>();

    private ProgressManager() {

        // put each team and it first player in teams hash map
        for (Team team : TeamManager.getInstance().getTeams()) {

            for (Player player: team.getAllPlayers()) {

                playerIds.add(player.getId());

            }

        }

    }

    public static ProgressManager getInstance() {

        if (instance == null) {
            instance = new ProgressManager();
        }

        return instance;

    }

    public String startTurn() {

        String command = "";

        Player player = TeamManager.getInstance().getPlayer(this.playerIds.getFirst());

        System.out.println(TeamManager.getInstance().getTeams().size() + " teams are playing.");
        System.out.println("Team " + player.getTeamId() + " goes first.\n");

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        try {

            do {

                ListIterator<Integer> playersIterator = this.playerIds.listIterator();

                while (playersIterator.hasNext()) {

                    if ( !command.equalsIgnoreCase("end") ) {

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

                    } else {

                        break;

                    }

                }

            } while ( !command.equalsIgnoreCase("end") );

        } catch(IOException x) {

            x.printStackTrace();

        }

        return command;

    }

}
