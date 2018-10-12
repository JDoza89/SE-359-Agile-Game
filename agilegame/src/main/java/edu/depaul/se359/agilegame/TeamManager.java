package edu.depaul.se359.agilegame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
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

            // prompt for number of player per team
            for (Team team : this.getTeams()) {

                int numPlayers = 0;

                System.out.println("How many players in Team " + team.getId() + "?");
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

                }  while(numPlayers <= 1);

                System.out.println(numPlayers + " players in Team " + team.getId() + ".\n");

                // use loop to create play in current team
                // first two player will always be project manager and scrum master
                for (int i = 0; i < numPlayers; i++) {

                    switch (i) {
                        case 0:
                            this.createPlayer(team.getId(), PlayerType.PROJECT_MANAGER);
                            break;
                        case 1:
                            this.createPlayer(team.getId(), PlayerType.SCRUM_MASTER);
                            break;
                        default:
                            this.createPlayer(team.getId(), PlayerType.DEVELOPER);
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

    private void createPlayer(int teamId, PlayerType type) {

        Player player = null;

        switch (type) {

            case DEVELOPER:
                player = new DeveloperPlayer();
                break;
            case SCRUM_MASTER:
                player = new ScrumMasterPlayer();
                break;
            case PROJECT_MANAGER:
                player = new ProjectManagerPlayer();
                break;

        }

        this.addPlayer(teamId, player);

    }

    private void addPlayer( int teamId, Player player) {

        this.getTeam(teamId).addPlayer(player);

    }

    public Player getPlayer(int teamId, int playerId) {

        return this.getTeam(teamId).getPlayer(playerId);

    }

}
