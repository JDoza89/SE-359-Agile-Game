package edu.depaul.se359.agilegame;

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

    public int getPlayerCount() {

        return this.players.size();

    }

}
