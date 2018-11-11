package edu.depaul.se359.agilegame.Player;

/*
    The developer player class represents a player with role of a project manager.

    Author:     Ethan Lin
    Created on: October 11, 2018
 */

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Hand.Hand;
import java.util.ArrayList;

class ProductOwnerPlayer extends Player {

    private final int id = Player.id++;
    private final Role role = Role.PRODUCT_OWNER;

    ProductOwnerPlayer(int teamId) {
        this.teamId = teamId;
        this.ownHand = new Hand(teamId, id, role);
        this.playedCards = new ArrayList<>();
    }

    @Override
    public void playCard(int num) {

        Card card = this.ownHand.getCard(num);

        playedCards.add(card);
        this.ownHand.removeCard(card);

    }

    @Override
    public ArrayList<Card> getPlayedCards() {
        return this.playedCards;
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
