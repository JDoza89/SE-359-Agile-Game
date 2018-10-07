package deck;

import card.AbstractCard;
import card.ChanceCard;
import card.StoryCard;

import java.util.ArrayList;
import java.util.Stack;

/*
    Deck class stores all difference decks of cards.

    Author:     Jack Chai
 */

public class Deck
{
    private ArrayList<ChanceCard> m_chanceDeck;
    private ArrayList<StoryCard> m_storyDeck;

    public Deck()
    {
        m_chanceDeck = new ArrayList<ChanceCard>();
        m_storyDeck = new ArrayList<StoryCard>();
    }

    public void printChanceDeck()
    {
        System.out.println();
        System.out.println("Here are the chance cards: ");
        for (ChanceCard card: m_chanceDeck)
        {
            System.out.println(card.m_content);
        }
    }

    public void printStoryDeck()
    {
        System.out.println();
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
