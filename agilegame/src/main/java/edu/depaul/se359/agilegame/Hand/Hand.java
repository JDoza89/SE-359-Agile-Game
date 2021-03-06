package edu.depaul.se359.agilegame.Hand;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.PlayerCard;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.Player.Role;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Hand {
    private int team;
    private int player;
    private Role role;
    private ArrayList<Card> hand;

    public Hand(int playerTeam, int playerNumber, Role playerRole) {
        team = playerTeam;
        player = playerNumber;
        role = playerRole;
        hand = this.assignRoleCards();
    }

    public Hand(JSONObject serializedHand){
        team = (Integer) serializedHand.get("Team");
        player = (Integer) serializedHand.get("Player");
        hand = new ArrayList<>();

        JSONArray jsonHand = (JSONArray) serializedHand.get("Hand");
        for (Object card : jsonHand) {
            hand.add(new PlayerCard((String) card));
        }
    }

    public ArrayList<Card> assignRoleCards() {

        ArrayList<Card> cards = new ArrayList<>();

        for (Card card : Deck.getRoleDeck() ) {

            if ( (this.role.toString().equalsIgnoreCase(card.getRole()))) {
                cards.add(card);
            }
        }

        return cards;

    }

    public int getTeam() {
        return team;
    }

    public int getPlayer() {
        return player;
    }

    public void addCard(PlayerCard card){
        hand.add(card);
    }

    // Assumed this would be passed in as player input (i.e. the player plays card 2 (position 1)
    public Card removeCard(Card card){
        if(hand.contains(card))
        {
            hand.remove(card);
        }
        return card;
    }

    public Card getCard(int num) {
        return hand.get(num);
    }

    public String getHand(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hand.size(); i++){
            sb.append(i);
            sb.append(". \n");
            sb.append(hand.get(i).getContent() + "\n-Description: " + hand.get(i).getDescription() + "\n-Amount: " + hand.get(i).getAmount());
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString(){
        // IntelliJ assures me this is faster than using a StringBuilder
        return "Team Number:    " + team + "\n" +
                "Player Number: " + player + "\n" +
                "Hand:\n" +
                getHand();
    }

    public JSONObject toJSON(){
        JSONObject obj = new JSONObject();
        obj.put("Team", team);
        obj.put("Player", player);

        JSONArray jsonHand = new JSONArray();
        jsonHand.addAll(hand);

        obj.put("Hand", jsonHand);
        return obj;
    }
}
