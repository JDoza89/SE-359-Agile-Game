package edu.depaul.se359.agilegame.Player;

/*
    An enum that contains available roles for a player.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

public enum Role {

    SCRUM_MASTER("SM"),
    PRODUCT_OWNER("PO"),
    DEVELOPER("DEV");

    private String title;

    Role(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return this.title;
    }

}
