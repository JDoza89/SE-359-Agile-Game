package edu.depaul.se359.agilegame.Player;

/*
    The developer player class represents a player with role of a project manager.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

import edu.depaul.se359.agilegame.Hand.Hand;

class ProjectManagerPlayer extends Player {

    private final int id = Player.id++;
    private int teamId = 0;
    private final Role role = Role.PROJECT_MANAGER;

    ProjectManagerPlayer(int teamId) {
        this.teamId = teamId;
        this.ownHand = new Hand(teamId, id, role);
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

    @Override
    public Hand showHand() {
        return this.ownHand;
    }

    public Role getRole() {

        return this.role;

    }

}
