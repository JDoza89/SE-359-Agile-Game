package edu.depaul.se359.agilegame;

/*
    The interface for the various game states. Each game state must carry out an action.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

interface GameState {

    void doAction(GameStateContext ctx);

}
