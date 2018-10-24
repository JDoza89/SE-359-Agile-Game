package edu.depaul.se359.agilegame.Utility;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Player.Team;

public class EffectManager
{
    private static EffectManager instance = null;

    public static EffectManager getInstance() {

        if (instance == null) {
            instance = new EffectManager();
        }

        return instance;
    }


}
