package edu.depaul.se359.agilegame.Hand;

import edu.depaul.se359.agilegame.Card.PlayerCard;
import edu.depaul.se359.agilegame.Player.Role;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Hand{
    private int team;
    private int player;

    private ArrayList<PlayerCard> hand;

    public Hand(int playerTeam, int playerNumber, Role playerRole){
        team = playerTeam;
        player = playerNumber;
        hand = new ArrayList<>();
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
    public PlayerCard removeCard(int cardPosition){
        return hand.remove(cardPosition - 1);
    }

    public String getHand(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hand.size(); i++){
            sb.append(i+1);
            sb.append(". ");
            sb.append(hand.get(i).getContent());
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
