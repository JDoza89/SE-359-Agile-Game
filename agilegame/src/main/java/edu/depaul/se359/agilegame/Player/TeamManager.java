package edu.depaul.se359.agilegame.Player;

/*
    The team manager class. This class is a singleton class to prevent multiple team manager instances.
    Team manager controls all aspect of the team and its players.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

import java.util.ArrayList;

import edu.depaul.se359.agilegame.Game;
import edu.depaul.se359.agilegame.GameState.GameManager;

public class TeamManager {

    private static TeamManager instance = null;
    private int numTeams = 0;
    private int numPlayers = 0;
    private final ArrayList<Team> teams = new ArrayList<>();


    private TeamManager() {}

    public static TeamManager getInstance() {

        if (instance == null) {
            instance = new TeamManager();
        }

        return instance;

    }

    // create all teams and players in each team
    public void createTeamsAndPlayers() {

        // use loop to create teams
        for (int i = 0; i < numTeams; i++) {
            this.createTeam();
        }

        // use loop to create player in each team
        // first two players will always be project manager and scrum master
        for (Team team : this.getTeams()) {

            for (int i = 0; i < numPlayers; i++) {

                switch (i) {
                    case 0:
                        this.createPlayer(team.getId(), Role.PROJECT_MANAGER);
                        break;
                    case 1:
                        this.createPlayer(team.getId(), Role.SCRUM_MASTER);
                        break;
                    default:
                        this.createPlayer(team.getId(), Role.DEVELOPER);
                        break;
                }

            }

        }

        // save teams to the game state context
        GameManager.getInstance().saveTeams(this.getTeams());


    }

    private void createTeam() {
        this.addTeam(new Team());
    }

    private void addTeam(Team team) {
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

    public void getID(){
        Team t;
        for(int i =0; i< this.teams.size(); i++)
        {   t = this.teams.get(i);
            System.out.println(t.getId());
        }
    }

    public ArrayList<Team> getTeams() {
        return this.teams;
    }

    private void createPlayer(int teamId, Role type) {

        Player player = null;

        switch (type) {

            case DEVELOPER:
                player = new DeveloperPlayer(teamId);
                break;
            case SCRUM_MASTER:
                player = new ScrumMasterPlayer(teamId);
                break;
            case PROJECT_MANAGER:
                player = new ProjectManagerPlayer(teamId);
                break;

        }

        this.addPlayer(teamId, player);

    }

    private void addPlayer( int teamId, Player player) {
        this.getTeam(teamId).addPlayer(player);
    }

    public Player getPlayer(int playerId) {

        Player targetPlayer = null;
        Boolean found = false;

        for(Team team : this.getTeams()) {

            if ( found ) { break; }

            for (Player player : team.getAllPlayers()) {

                if ( found ) { break; }

                if (player.getId() == playerId) {

                    targetPlayer = player;
                    found = true;

                }

            }

        }

        return targetPlayer;

    }

    public void setNumberOfTeams(int num) {
        this.numTeams = num;
    }

    public int getNumberOfTeams() {
        return this.numTeams;
    }

    public void setNumberOfPlayers(int num) {
        this.numPlayers = num;
    }

    public int getNumberOfPlayers() {
        return this.numPlayers;
    }

}
