package com.company;

import Card.ChanceCard;
import Card.StoryCard;
import Deck.Deck;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IOException, ParseException
    {
        // JSON.Simple lib
        JSONParser parser = new JSONParser();

        // get the current project directory
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        // grab whole file
        JSONObject jsonObject = (JSONObject)parser.parse(
                new FileReader("asset/JSONSample.json"));
        //System.out.println(jsonObject);

        Deck d = new Deck();

        // loop file with array
        JSONArray jsonArray = (JSONArray) jsonObject.get("Cards");
        for (Object array : jsonArray)
        {
            JSONObject currObj = (JSONObject)array;
            String cardType = (String) currObj.get("Type");
            String cardContent = (String) currObj.get("Content");

            d.AddCard(cardType, cardContent);

//            System.out.println(cardType);
//            System.out.println(cardContent);
        }

        d.printChanceDeck();
        d.printStoryDeck();
    }
}
