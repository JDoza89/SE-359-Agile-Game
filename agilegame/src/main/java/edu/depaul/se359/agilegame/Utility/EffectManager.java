package edu.depaul.se359.agilegame.Utility;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.StoryCard;
import edu.depaul.se359.agilegame.Deck.Deck;
import edu.depaul.se359.agilegame.Player.Team;
import edu.depaul.se359.agilegame.Player.TeamManager;

import java.util.ArrayList;

/*
    EffectManager does all the calculation and
    update for each Team for its storyPoint

 */

public class EffectManager
{
    // effect is based on the overall team score
    // only do work in team not in each player
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

    // first step for the game: Assign storyCard to each team
    public static void assignStoryPointToEachTeam()
    {
        // get the list of the team
        ArrayList<Team> teams = TeamManager.getInstance().getTeams();

        // assume the # of team is 2
        assert teams.size() == 2;

        // get the storyDeck
        ArrayList<StoryCard> storyDeck = Deck.getStoryDeck();
        int deckSz = storyDeck.size();

        // loop through the story deck
        for (int i = 0; i < deckSz; ++i)
        {
            StoryCard currCard = storyDeck.get(i);
            String amount = currCard.getAmount();
            String sign = amount.substring(0, 1);
            int score = Integer.parseInt(amount.substring(1));

            // assign even index of card to team 0, odd to team 1
            if (i % 2 == 0) teams.get(0).setStoryPoint(sign, score);
            else teams.get(1).setStoryPoint(sign, score);
        }
    }
}