package edu.depaul.se359.agilegame.Deck;

import edu.depaul.se359.agilegame.Card.ChanceCard;
import edu.depaul.se359.agilegame.Card.StoryCard;

import java.util.ArrayList;

/*
    Deck class stores all difference decks of cards. It is singleton.

    Author:     Jack Chai
 */

public class Deck
{
    private ArrayList<ChanceCard> m_chanceDeck;
    private ArrayList<StoryCard> m_storyDeck;

    private static Deck instance = null;

    public static Deck getInstance()
    {
        if (instance == null)
        {
            instance = new Deck();
        }
        return instance;
    }

    private Deck()
    {
        m_chanceDeck = new ArrayList<ChanceCard>();
        m_storyDeck = new ArrayList<StoryCard>();
    }

    public static void printAllDecks()
    {
        printChanceDeck();
        printStoryDeck();
    }

    private static void printChanceDeck()
    {
        Deck d = Deck.getInstance();

        System.out.println();
        System.out.println("Here are the chance cards: ");
        for (ChanceCard card: d.m_chanceDeck)
        {
            System.out.println(card.getContent());
        }
    }

    private static void printStoryDeck()
    {
        Deck d = Deck.getInstance();

        System.out.println();
        System.out.println("Here are the story cards: ");
        for (StoryCard card: d.m_storyDeck)
        {
            System.out.println(card.getContent());
        }
    }

    public static void AddCard(String type, String content)
    {
        Deck d = Deck.getInstance();

        if (type.equals("Chance"))
        {
            d.m_chanceDeck.add(new ChanceCard(content));
        }
        else if (type.equals("Story"))
        {
            d.m_storyDeck.add(new StoryCard(content));
        }
        else
        {
            assert false;
        }
    }


}
