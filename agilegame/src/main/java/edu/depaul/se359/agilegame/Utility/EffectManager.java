package edu.depaul.se359.agilegame.Utility;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.StoryCard;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.Player.Team;
import edu.depaul.se359.agilegame.Player.TeamManager;

import java.util.ArrayList;

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
            case "addStory":
                team.setStoryPoint(sign, score);
                break;
        }
    }

    // assume we only have 2 team
    public static void assignStoryPointToEachTeam()
    {
        ArrayList<Team> teams = TeamManager.getInstance().getTeams();
        int teamSz = teams.size();

        assert teamSz == 2;

        ArrayList<StoryCard> storyDeck = Deck.getStoryCards();
        int deckSz = storyDeck.size();

        for (int i = 0; i < deckSz; ++i)
        {
            StoryCard currCard = storyDeck.get(i);
            String amount = currCard.getAmount();
            String sign = amount.substring(0, 1);
            int score = Integer.parseInt(amount.substring(1));

            if (i % 2 == 0) teams.get(0).setStoryPoint(sign, score);
            else teams.get(1).setStoryPoint(sign, score);
        }
    }
}