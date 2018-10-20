package edu.depaul.se359.agilegame.Player;

/*
    The team manager class. This class is a singleton class to prevent multiple team manager instances.
    Team manager controls all aspect of the team and its players.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TeamManager {

    private static TeamManager instance = null;
    private final ArrayList<Team> teams = new ArrayList<>();

    private TeamManager() {}

    public static TeamManager getInstance() {

        if (instance == null) {
            instance = new TeamManager();
        }

        return instance;

    }

    public void promptForTeamsAndPlayers() {

        int numTeams = 0;
        int numPlayers = 0;
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        // prompt for number of teams
        try {

            System.out.println("How many teams do you need?");
            System.out.println("Please enter an integer greater than 1.");

            String command = console.readLine();

            do {

                try {

                    numTeams = Integer.parseInt(command);

                    if (numTeams <= 1) {

                        System.out.println("Please enter an integer greater than 1.");
                        command = console.readLine();

                    }

                } catch (Exception e) {

                    System.out.println("Please enter an integer greater than 1.");
                    command = console.readLine();

                }

            }  while(numTeams <= 1);

            // use loop to create teams
            for (int i = 0; i < numTeams; i++) {

                this.createTeam();

            }

            System.out.println(numTeams + " teams are created.\n");

            System.out.println("How many players per Team?");
            System.out.println("Please enter an integer greater than 3.");

            command = console.readLine();

            do {

                try {

                    numPlayers = Integer.parseInt(command);

                    if (numPlayers <= 2) {

                        System.out.println("Please enter an integer greater than 3.");
                        command = console.readLine();

                    }

                } catch (Exception e) {

                    System.out.println("Please enter an integer greater than 3.");
                    command = console.readLine();

                }

            }  while(numPlayers <= 2);

            System.out.println(numPlayers + " players for each team.\n");

            // prompt for number of player per team
            for (Team team : this.getTeams()) {

                // use loop to create player in current team
                // first two players will always be project manager and scrum master
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

        } catch(IOException x) {

            x.printStackTrace();

        }

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

    public Integer getNumberOfMostPlayers() {

        int num = 0;

        for (Team team : this.getTeams()) {

            if (team.getPlayerCount() > num) {
                num = team.getPlayerCount();
            }

        }

        return num;

    }

}
