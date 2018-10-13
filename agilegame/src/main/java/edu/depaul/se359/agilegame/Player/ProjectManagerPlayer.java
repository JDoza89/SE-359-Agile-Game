package edu.depaul.se359.agilegame.Player;

/*
    The developer player class represents a player with role of a project manager.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

class ProjectManagerPlayer extends Player {

    private final int id = Player.id++;
    private final Role role = Role.PROJECT_MANAGER;

    @Override
    public void doAction() {

    }

    @Override
    public int getId() {
        return this.id;
    }

    public Role getPlayerRole() {

        return this.role;

    }

}
