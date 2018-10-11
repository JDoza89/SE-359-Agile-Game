package edu.depaul.se359.agilegame;

/*
    The application's starting point (a.k.a., the main class)
 */

public class Game {

    public static void main(String[] args) {

        // start the game when program runs
        //GameManager.getInstance().startGame();

        Team teamOne = new Team();
        Player scrumMaster = new ScrumMasterPlayer();

        System.out.println(teamOne.getId());
        System.out.println(scrumMaster.getId());


        TeamManager.getInstance().addTeam(teamOne);
        TeamManager.getInstance().addPlayerToTeam(teamOne.getId(),scrumMaster);
        System.out.println(TeamManager.getInstance().getPlayer(teamOne.getId(),scrumMaster.getId()));



        Team teamTwo = new Team();
        Player developer = new DeveloperPlayer();

        System.out.println(teamTwo.getId());
        System.out.println(developer.getId());

        TeamManager.getInstance().addTeam(teamTwo);
        TeamManager.getInstance().addPlayerToTeam(teamTwo.getId(),developer);
        System.out.println(TeamManager.getInstance().getPlayer(teamTwo.getId(),developer.getId()));

    }

}
