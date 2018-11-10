package edu.depaul.se359.agilegame.Player;

/*
    An abstract player class that is inherited by other player type classes.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Hand.Hand;
import edu.depaul.se359.agilegame.Utility.GameUtility;

import java.util.ArrayList;
import java.util.Map;

public abstract class Player
{

    static int id = 1;
    int teamId = 0;
    Hand ownHand;
    ArrayList<Card> playedCards;
    private Map<Role, String[]> roleDescriptions = GameUtility.getRoleDescriptions();

    public abstract int getTeamId();
    public abstract int getId();
    public abstract Role getRole();
    public abstract Hand showHand();
    public abstract void playCard(int num);
    public abstract ArrayList<Card> getPlayedCards();

    public String[] getNameAndDescription(){
        return roleDescriptions.get(getRole());
    }

    public String getName(){
        return getNameAndDescription()[0];
    }

    public String getDescription(){
        return getNameAndDescription()[1];
    }
}
