package model.cards;

import model.Card;
import model.Color;

/**
 * Classe che rappresenta la carta "blocca il gioctore successivo".
 * @author ann
 *
 */
public class SkipCard extends Card
{

	public SkipCard(Color color, String nameImage)
	{
		setColor(color);
		setImage(nameImage);
	}
	
	@Override
	public String toString()
	{
		return getColor().toString().toLowerCase() + "SKIP";
	}

	@Override
	protected int getValue() {
		return -1;
	}

}
