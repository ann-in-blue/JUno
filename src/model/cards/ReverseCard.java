package model.cards;

import model.Card;
import model.Color;

/**
 * Classe che rappresenta la carta "cambia giro" e modifica la direzione di gioco.
 * @author ann
 *
 */
public class ReverseCard extends Card
{

	public ReverseCard(Color color, String nameImage)
	{
		setColor(color);
		setImage(nameImage);
	}
}
