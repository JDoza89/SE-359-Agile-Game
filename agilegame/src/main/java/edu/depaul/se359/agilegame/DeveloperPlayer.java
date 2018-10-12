package edu.depaul.se359.agilegame;

/*
    The developer player class represents a player with role of a developer.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

class DeveloperPlayer extends Player {

    private final int id = Player.id++;
    private final Role role = Role.DEVELOPER;

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
