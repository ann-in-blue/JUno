package model.cards;

import model.Card;
import model.Color;

public class Draw2Card extends Card
{
	public Draw2Card(Color color, String nameImage)
	{
		setColor(color);
		setImage(nameImage);

	}
	@Override
	public String toString()
	{
		return getColor().toString().toLowerCase() + "+2";
	}
	@Override
	public int getValue() {
		return -1;
	}

}
