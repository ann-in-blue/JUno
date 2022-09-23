package model.cards;

import model.Card;
import model.Color;

public class ValueCard extends Card
{

	private final int value;
	
	public ValueCard(Color color, int value, String nameImage)
	{
		this.value = value;
		setColor(color);
		setImage(nameImage);
	}
	@Override
	public boolean isWildCard()
	{
		return false;
		
	}

	public int getValue()
	{
		return value;
	}
	@Override
	public String toString()
	{
		return this.getColor().toString().toLowerCase() + value;
	}
}
