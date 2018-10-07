package edu.depaul.se359.agilegame.Utility;

import edu.depaul.se359.agilegame.Card.Card;

//Utility class responsible for providing utility functions for agile game such as shuffling cards in deck.
public final class GameUtility
{
	//Shuffles card for a given deck of cards,uses Knuth algorithm.
	public static void shuffleCards(Card[] deck)
	{
		int deckSize = deck.length;
		for(int i=0; i < deckSize; i++)
		{
			int randomIndex = i + (int)(Math.random() *(deckSize-i));
			Card temp = deck[i];
			deck[i] = deck[randomIndex];
			deck[randomIndex] = temp;
		}
	}
		
}