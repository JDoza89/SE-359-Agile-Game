package edu.depaul.se359.agilegame.Utility;

import edu.depaul.se359.agilegame.Card.ChanceCard;
import edu.depaul.se359.agilegame.Card.RoleCard;
import edu.depaul.se359.agilegame.Card.StoryCard;
import edu.depaul.se359.agilegame.Deck.Deck;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GameUtilityTest {

    //file paths for the json files with card data for each type of card.
    private final String chanceDeckPath = System.getProperty("user.dir") + "/src/main/resources/decks/chanceDeck.json";
    private final String roleDeckPath = System.getProperty("user.dir") + "/src/main/resources/decks/roleDeck.json";
    private final String storyDeckPath = System.getProperty("user.dir") + "/src/main/resources/decks/storyDeck.json";
    private static JSONParser jsonParser;

    @BeforeAll
    //Before all tests in this class, create instance of JSON parser and calls the parseJSONtoDecks method to parse files into deck of cards.
    //Needed since all test methods below use the decks of cards created to test something.
    static void init() throws IOException, ParseException {
        jsonParser = new JSONParser();
        GameUtility.parseJSONtoDecks();
    }

    @AfterAll
    static void clearDecks()
    {
        Deck.getInstance().clearDecks();
    }

//    @Test
//    //Test Shuffle Card Function by checking length of the card deck is the same as before.  Can't really test shuffle functionality since
//    //small chance that any given shuffle will result in same order of cards returned as before.
//    void shuffleCards() {
//        ArrayList<ChanceCard> cards = Deck.getInstance().getChanceCards();
//        int expectedSize = cards.size();
//        GameUtility.shuffleCards(cards.toArray(new ChanceCard[cards.size()]));
//        int newSize = cards.size();
//        assertEquals(expectedSize,newSize);
//
//    }

    @Test
    //Test chance card deck to see if parsing method was able to successfully parse json file into cards with all the given attributes.
    void parseJSONChanceDeck() throws IOException, ParseException
    {
        //Get json data from file and store in JSON Object variable, then use JSON Array to store all the cards inside
        //the "Cards" key section of the file.
        Object obj = jsonParser.parse(new FileReader(chanceDeckPath));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray)jsonObject.get("Cards");
        ArrayList<ChanceCard> chanceCards = Deck.getInstance().getChanceCards();

        //check contents of list of chance cards to match what is in json file.
        for(int i =1;i < jsonArray.size(); i++)
        {
            JSONObject item = (JSONObject)jsonArray.get(i);
            assertEquals(item.get("ID"),chanceCards.get(i-1).getId());
            assertEquals(item.get("Role"),chanceCards.get(i-1).getRole());
            assertEquals(item.get("Content"),chanceCards.get(i-1).getContent());
            assertEquals(item.get("Description"),chanceCards.get(i-1).getDescription());
            assertEquals(item.get("Effect"),chanceCards.get(i-1).getEffect());
            assertEquals(item.get("Amount"),chanceCards.get(i-1).getAmount());
        }
    }

    @Test
    //Test role card deck to see if parsing method was able to successfully parse json file into cards with all the given attributes.
    void parseJSONRoleDeck() throws IOException, ParseException
    {
        //Get json data from file and store in JSON Object variable, then use JSON Array to store all the cards inside
        //the "Cards" key section of the file.
        Object obj = jsonParser.parse(new FileReader(roleDeckPath));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray)jsonObject.get("Cards");
        ArrayList<RoleCard> roleCards = Deck.getInstance().getRoleCards();

        //check contents of list of chance cards to match what is in json file.
        for(int i =1;i < jsonArray.size(); i++)
        {
            JSONObject item = (JSONObject)jsonArray.get(i);
            assertEquals(item.get("ID"),roleCards.get(i-1).getId());
            assertEquals(item.get("Role"),roleCards.get(i-1).getRole());
            assertEquals(item.get("Content"),roleCards.get(i-1).getContent());
            assertEquals(item.get("Description"),roleCards.get(i-1).getDescription());
            assertEquals(item.get("Effect"),roleCards.get(i-1).getEffect());
            assertEquals(item.get("Amount"),roleCards.get(i-1).getAmount());
        }
    }

    @Test
    //Test story card deck to see if parsing method was able to successfully parse json file into cards with all the given attributes.
    void parseJSONStoryDeck() throws IOException, ParseException
    {
        //Get json data from file and store in JSON Object variable, then use JSON Array to store all the cards inside
        //the "Cards" key section of the file.
        Object obj = jsonParser.parse(new FileReader(storyDeckPath));
        JSONObject jsonObject = (JSONObject) obj;
        JSONArray jsonArray = (JSONArray)jsonObject.get("Cards");
        ArrayList<StoryCard> storyCards = Deck.getInstance().getStoryCards();

        //check contents of list of chance cards to match what is in json file.
        for(int i =1;i < jsonArray.size(); i++)
        {
            JSONObject item = (JSONObject)jsonArray.get(i);
            assertEquals(item.get("ID"),storyCards .get(i-1).getId());
            assertEquals(item.get("Role"),storyCards .get(i-1).getRole());
            assertEquals(item.get("Content"),storyCards .get(i-1).getContent());
            assertEquals(item.get("Description"),storyCards .get(i-1).getDescription());
            assertEquals(item.get("Effect"),storyCards .get(i-1).getEffect());
            assertEquals(item.get("Amount"),storyCards .get(i-1).getAmount());
        }
    }
}