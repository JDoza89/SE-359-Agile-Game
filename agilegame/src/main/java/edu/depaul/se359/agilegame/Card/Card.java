package edu.depaul.se359.agilegame.Card;

//Abstract class that represents a standard card in the game. Has attribute content which gives a description of
//a card.
public abstract class Card
{
	private String id;
	private String role;
	private String content;
	private String description;
	private String effect;
	private String amount;

	public Card()
	{

	}

	public Card(String content){
		this.content = content;
	}

	public Card(String id, String role, String content,
                String description, String effect, String amount)
	{
	    this.id = id;
	    this.role = role;
		this.content = content;
		this.description = description;
		this.effect = effect;
		this.amount = amount;
	}

	public Card(String content)
	{
		this.content = content;
	}

	public String getId() {return id;}

	public String getRole() {return role;}

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

	public String getAmount() {return amount;}

}