package edu.depaul.se359.agilegame;


import edu.depaul.se359.agilegame.GameState.GameManager;
import org.json.simple.parser.*;
import java.io.IOException;

public class Game {

    public static void main(String[] args) throws IOException, ParseException {

        GameManager.getInstance().startGame();

    }

}
