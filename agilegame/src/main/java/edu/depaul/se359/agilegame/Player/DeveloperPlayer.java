package edu.depaul.se359.agilegame.Player;

/*
    The developer player class represents a player with role of a developer.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

class DeveloperPlayer extends Player {

    private final int id = Player.id++;
    private int teamId = 0;
    private final Role role = Role.DEVELOPER;

    DeveloperPlayer(int teamId) {
        this.teamId = teamId;
    }

    @Override
    public void doAction() {

    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public int getTeamId() {
        return this.teamId;
    }

    public Role getRole() {

        return this.role;

    }

}
