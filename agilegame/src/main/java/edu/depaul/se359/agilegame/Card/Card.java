package edu.depaul.se359.agilegame.Card;

//Abstract class that represents a standard card in the game. Has attribute content which gives a description of
//a card.
public abstract class Card
{
	private String content;
	
	public Card()
	{
		
	}
	
	public Card(String content)
	{
		this.content = content;
	}
	
	public String getContent()
	{
		return content;
	}
}