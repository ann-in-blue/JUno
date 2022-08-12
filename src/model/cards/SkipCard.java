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

}
