package edu.depaul.se359.agilegame;


import edu.depaul.se359.agilegame.Deck.Deck;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

/*
    Parse the JSON in Main.

    Author:     Jack Chai

 */

public class Game {

    public static void main(String[] args) throws IOException, ParseException
    {
        // JSON.Simple lib
        JSONParser parser = new JSONParser();

        // get the current project directory
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        File folder = new File(System.getProperty("user.dir") + "/src/main/resources/decks");
        File[] listOfFiles = folder.listFiles();

        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile())
            {
                // grab whole file
                JSONObject jsonObject = (JSONObject)parser.parse(
                        new InputStreamReader(Game.class.
                                getResourceAsStream("/decks/" + file.getName())));

                JSONArray jsonArray = (JSONArray) jsonObject.get("Cards");
                JSONObject currObj = (JSONObject) jsonArray.get(0);
                String cardType = (String) currObj.get("Type");
                //System.out.println(cardType);

                // loop file with array
                for (int i = 1; i < jsonArray.size(); ++i)
                {
                    currObj = (JSONObject) jsonArray.get(i);
                    String cardContent = (String) currObj.get("Content");
                    String cardDescription = (String) currObj.get("Description");
                    String cardEffect = (String) currObj.get("Effect");

                    Deck.AddCard(cardType, cardContent, cardDescription, cardEffect);
                }
            }
        }
        
        Deck.printAllDecks();
    }
}
