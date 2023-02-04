package model.cards;

import model.Card;
import model.Colors;

/**
 * Classe che rappresenta la carta +2.
 * Valore -1;
 * @author a-00
 *
 */
public class Draw2Card extends Card
{
	public Draw2Card(Colors color, String nameImage)
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
