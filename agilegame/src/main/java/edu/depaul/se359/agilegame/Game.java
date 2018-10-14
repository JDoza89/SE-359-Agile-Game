package edu.depaul.se359.agilegame;


import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.Utility.GameUtility;
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
        GameUtility.parseJSONtoDecks();

        Deck.printAllDecks();
    }
}
