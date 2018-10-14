package edu.depaul.se359.agilegame.Deck;

import edu.depaul.se359.agilegame.Card.ChanceCard;
import edu.depaul.se359.agilegame.Card.CharacterCard;
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
    private ArrayList<CharacterCard> m_characterDeck;

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
        m_characterDeck = new ArrayList<CharacterCard>();
    }

    public static void printAllDecks()
    {
        printChanceDeck();
        printStoryDeck();
        printCharacterDeck();
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

    private static void printCharacterDeck()
    {
        Deck d = Deck.getInstance();

        System.out.println();
        System.out.println("Here are the story cards: ");
        for (CharacterCard card: d.m_characterDeck)
        {
            System.out.println(card.getContent());
        }
    }

    public static void AddCard(String type,
                               String content, String description, String effect)
    {
        Deck d = Deck.getInstance();

        switch (type) {
            case "Chance":
                d.m_chanceDeck.add(new ChanceCard(content, description, effect));
                break;
            case "Story":
                d.m_storyDeck.add(new StoryCard(content, description, effect));
                break;
            case "Character":
                d.m_characterDeck.add(new CharacterCard(content, description, effect));
                break;
        }

    }


}
