package Deck;

import Card.AbstractCard;
import Card.ChanceCard;
import Card.StoryCard;

import java.util.ArrayList;
import java.util.Stack;

public class Deck
{
    private ArrayList<ChanceCard> m_chanceDeck;
    private ArrayList<StoryCard> m_storyDeck;

    public Deck()
    {
        m_chanceDeck = new ArrayList<>();
        m_storyDeck = new ArrayList<>();
    }

    public void printChanceDeck()
    {
        System.out.println("Here are the chance cards: ");
        for (ChanceCard card: m_chanceDeck)
        {
            System.out.println(card.m_content);
        }
    }

    public void printStoryDeck()
    {
        System.out.println("Here are the story cards: ");
        for (StoryCard card: m_storyDeck)
        {
            System.out.println(card.m_content);
        }
    }

    public void AddCard(String type, String content)
    {
        if (type.equals("Chance"))
        {
            m_chanceDeck.add(new ChanceCard(content));
        } else if (type.equals("Story"))
        {
            m_storyDeck.add(new StoryCard(content));
        } else
        {
            assert false;
        }
    }


}
