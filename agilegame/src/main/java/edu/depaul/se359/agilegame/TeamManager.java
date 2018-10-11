package edu.depaul.se359.agilegame;

import java.util.ArrayList;

public class TeamManager {

    private static TeamManager instance = null;
    private ArrayList<Team> teams = new ArrayList<>();

    private TeamManager() {}

    public static TeamManager getInstance() {

        if (instance == null) {
            instance = new TeamManager();
        }

        return instance;

    }

    public void addTeam(Team team) {

        this.teams.add(team);

    }

    public Team getTeam(int teamId) {

        Team teamToGet = null;

        for (Team team : this.teams) {

            if (team.getId() == teamId) {

                teamToGet = team;
                break;

            }

        }

        return teamToGet;

    }

    public ArrayList<Team> getTeams() {

        return this.teams;

    }

    public void addPlayerToTeam( int teamId, Player player) {

        this.getTeam(teamId).addPlayer(player);

    }

    public Player getPlayer(int teamId, int playerId) {

        return this.getTeam(teamId).getPlayer(playerId);

    }

}
