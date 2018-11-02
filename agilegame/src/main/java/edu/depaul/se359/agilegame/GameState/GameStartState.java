package edu.depaul.se359.agilegame.GameState;

/*
    The game start state class. Carry out actions that will happen during game start state.
    Starts initial game loop for user inputs.

    Author:     Ethan Lin
    Created on: October 5, 2018
 */

import edu.depaul.se359.agilegame.Card.StoryCard;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.Game;
import edu.depaul.se359.agilegame.Utility.EffectManager;
import edu.depaul.se359.agilegame.Utility.GameUtility;

public class GameStartState implements GameState {

    @Override
    public void saveState(GameStateContext ctx) {

        ctx.setState(this);
        this.startGame();

    }

    private void startGame()
    {
        // shuffle all 3 decks
        GameUtility.shuffleAllDecks();
        Deck.printAllDecks();

        // assign story deck to each team
        EffectManager.assignStoryPointToEachTeam();

        GameManager.getInstance().playGame();
    }

    @Override
    public String toString() {
        return "Start";
    }

}
