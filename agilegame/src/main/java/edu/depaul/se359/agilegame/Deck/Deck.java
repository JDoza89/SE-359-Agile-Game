package edu.depaul.se359.agilegame.Deck;

import edu.depaul.se359.agilegame.Card.Card;
import edu.depaul.se359.agilegame.Card.ChanceCard;
import edu.depaul.se359.agilegame.Card.RoleCard;
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
    private ArrayList<RoleCard> m_roleDeck;

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
        m_chanceDeck = new ArrayList<>();
        m_storyDeck = new ArrayList<>();
        m_roleDeck = new ArrayList<>();
    }

    public static void printAllDecks()
    {
        printChanceDeck();
        printStoryDeck();
        printRoleDeck();
    }

    private static void printChanceDeck()
    {
        Deck d = Deck.getInstance();

        cleanPrint("Chance", "START");

        for (ChanceCard card: d.m_chanceDeck)
        {
            printOneCard(card);
        }

        cleanPrint("Chance", "END");
    }

    private static void printStoryDeck()
    {
        Deck d = Deck.getInstance();

        cleanPrint("Story", "START");

        for (StoryCard card: d.m_storyDeck)
        {
            printOneCard(card);
        }

        cleanPrint("Story", "END");
    }

    private static void printRoleDeck()
    {
        Deck d = Deck.getInstance();

        cleanPrint("Role", "START");

        for (RoleCard card: d.m_roleDeck)
        {
            printOneCard(card);
        }

        cleanPrint("Role", "END");

    }

    public static void AddCard(String type,
                               String id, String role, String content,
                               String description, String effect, String amount)
    {
        Deck d = Deck.getInstance();

        switch (type) {
            case "Chance":
                d.m_chanceDeck.add(new ChanceCard(id, role, content,
                        description, effect, amount));
                break;
            case "Story":
                d.m_storyDeck.add(new StoryCard(id, role, content,
                        description, effect, amount));
                break;
            case "Role":
                d.m_roleDeck.add(new RoleCard(id, role, content,
                        description, effect, amount));
                break;
        }

    }

    private static void cleanPrint(String type, String pos)
    {
        System.out.println("//----------------------------------------------");
        System.out.println(pos + " - " + type + " cards");
        System.out.println("//----------------------------------------------");
        System.out.println();
    }

    private static void printOneCard(Card card)
    {
        System.out.println("ID: " + card.getId());
        System.out.println("Role: " + card.getRole());
        System.out.println("Content: " + card.getContent());
        System.out.println("Description: " + card.getDescription());
        System.out.println("Effect: " + card.getEffect());
        System.out.println("Amount: " + card.getAmount());
        System.out.println();
    }
}
