package edu.depaul.se359.agilegame.Player;

/*
    An abstract player class that is inherited by other player type classes.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

import edu.depaul.se359.agilegame.Hand.Hand;

public abstract class Player
{

    static int id = 1;
    int teamId = 0;
    Hand ownHand;

    public abstract int getTeamId();
    public abstract int getId();
    public abstract Role getRole();
    public abstract Hand showHand();
    abstract void doAction();

}
