package edu.depaul.se359.agilegame.Utility;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.ChanceCard;
import edu.depaul.se359.agilegame.Card.RoleCard;
import edu.depaul.se359.agilegame.Card.StoryCard;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.Game;
import edu.depaul.se359.agilegame.Player.Role;
import edu.depaul.se359.agilegame.Player.Team;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Utility class responsible for providing utility functions for agile game such as shuffling cards in deck.
public final class GameUtility
{
    // JSON.Simple lib
    private static JSONParser parser = new JSONParser();

    public static void shuffleAllDecks()
    {
        shuffleCards(Deck.getRoleDeck());
        shuffleCards(Deck.getStoryDeck());
        shuffleCards(Deck.getChanceDeck());
    }
    //Wrapper function that calls private shuffleDeck function by casting cards to ArrayList<? super Card>.
    public static void shuffleCards(ArrayList<? extends Card> deck)
    {
        shuffleDeck((ArrayList<? super Card>) deck);
    }

    //Shuffles card for a given deck of cards,uses Knuth algorithm.
    private static void shuffleDeck(ArrayList<? super Card> deck)
    {
        int deckSize = deck.size();
        for(int i=0; i < deckSize; i++)
        {
            int randomIndex = i + (int)(Math.random() *(deckSize-i));
            Card temp = (Card) deck.get(i);
            deck.set(i, (Card)deck.get(randomIndex));
            deck.set(randomIndex, temp);
        }
    }

    public static void parseJSONtoDecks() throws IOException, ParseException {
        String[] fileNames = {"chanceDeck","roleDeck","storyDeck"};

        if (fileNames.length > 0) {

            for (String name : fileNames) {
                parseJSONtoDecks(name);
            }

        }
    }

    private static void parseJSONtoDecks(String fileName) throws IOException, ParseException {

	    // grab whole file
        JSONObject jsonObject = (JSONObject)parser.parse(
                new InputStreamReader(Game.class.
                        getResourceAsStream("/decks/" + fileName + ".json")));

        JSONArray jsonArray = (JSONArray) jsonObject.get("Cards");
        JSONObject currObj = (JSONObject) jsonArray.get(0);
        String cardType = (String) currObj.get("Type");
        //System.out.println(cardType);

        // loop file with array
        for (int i = 1; i < jsonArray.size(); ++i)
        {
            currObj = (JSONObject) jsonArray.get(i);
            String cardID = (String) currObj.get("ID");
            String cardRole = (String) currObj.get("Role");
            String cardContent = (String) currObj.get("Content");
            String cardDescription = (String) currObj.get("Description");
            String cardEffect = (String) currObj.get("Effect");
            String cardAmount = (String) currObj.get("Amount");

            Deck.AddCard(cardType,
                         cardID, cardRole, cardContent,
                         cardDescription, cardEffect, cardAmount);
        }
    }

    public static Map<Role, String[]> getRoleDescriptions() {
        HashMap<Role, String[]> roleMap = new HashMap<>();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(
                    new InputStreamReader(Game.class.getResourceAsStream("/roles/scrum.json")));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Roles");

            for (Object roleObject : jsonArray) {
                JSONObject currObj = (JSONObject) roleObject;
                String roleName = (String) currObj.get("Name");
                String roleDescription = (String) currObj.get("Description");
                Role role = Role.DEVELOPER;
                if (roleName.equalsIgnoreCase("Product Owner")) {
                    role = Role.PRODUCT_OWNER;
                } else if (roleName.equalsIgnoreCase("Scrum Master")) {
                    role = Role.SCRUM_MASTER;
                }

                roleMap.put(role, new String[]{roleName, roleDescription});
            }
        } catch (Exception e){
            System.out.println("Unable to pare the role json. Setting default vales. See bellow for the error:");
            System.out.println(e.getMessage());

            for (Role role: Role.values()) {
                roleMap.put(role, new String[] {role.toString(), "<-Trouble Parsing Values->\n This role is key to making SCRUM work!"});
            }
        }
        return roleMap;
    }

    public static void doEffect(Card card, Team team)
    {
        String effectType = card.getEffect();
        String amount = card.getAmount();

        doEffect(effectType, amount, team);
    }

    private static void doEffect(String effectType, String amount, Team team)
    {
        String sign = amount.substring(0, 1);
        int score = Integer.parseInt(amount.substring(1));

        switch (effectType)
        {
            case "pointChange":
                team.setStoryPoint(sign, score);
                break;
        }
    }
}