package edu.depaul.se359.agilegame.Player;

/*
    The team class represents a team and owns an array list of player objects, which can be any player type because of
    all player classes inherited the Player abstract class.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

import java.util.ArrayList;

public class Team {

    private static int teamCount = 1;
    private final int id;

    private final ArrayList<Player> players;

    public Team() {

        this.id = Team.teamCount++;
        this.players = new ArrayList<>();

    }

    public int getId() {

        return this.id;

    }

    public void addPlayer(Player player) {

        this.players.add(player);

    }

    public Player getPlayer(int playerId ) {

        Player playerToGet = null;

        for (Player player : this.players) {

            if (player.getId() == playerId) {

                playerToGet = player;
                break;

            }

        }

        return playerToGet;

    }

    public ArrayList<Player> getAllPlayers() {

        return this.players;

    }

    public int getPlayerCount() {

        return this.players.size();

    }

}
