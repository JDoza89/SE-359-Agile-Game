package edu.depaul.se359.agilegame.Card;

//Abstract class that represents a standard card in the game. Has attribute content which gives a description of
//a card.
public abstract class Card
{
	private String content;
	private String description;
	private String effect;
	
	public Card()
	{
		
	}
	
	public Card(String content, String description, String effect)
	{
		this.content = content;
		this.description = description;
		this.effect = effect;
	}
	
	public String getContent()
	{
		return content;
	}

    public String getDescription()
    {
        return description;
    }

    public String getEffect()
    {
        return effect;
    }

}