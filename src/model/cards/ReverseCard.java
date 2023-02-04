package model.cards;

import model.Card;
import model.Colors;

/**
 * Classe che rappresenta la carta "cambia giro" e modifica la direzione di gioco.
 * @author ann
 *
 */
public class ReverseCard extends Card
{

	public ReverseCard(Colors color, String nameImage)
	{
		setColor(color);
		setImage(nameImage);
	}
	
	@Override
	public String toString()
	{
		return getColor().toString().toLowerCase() + "REVERSE";
	}

	@Override
	public int getValue() {
		return -1;
	}
}
