package edu.depaul.se359.agilegame.Utility;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Player.Team;

/*
    EffectManager does all the calculation and update for each Team for its
    storyPoint

 */

public class EffectManager
{
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