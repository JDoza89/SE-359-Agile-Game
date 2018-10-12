package edu.depaul.se359.agilegame;

/*
    An abstract player class that is inherited by other player type classes.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

abstract class Player {

    static int id = 1;

    abstract int getId();
    abstract void doAction();

}
