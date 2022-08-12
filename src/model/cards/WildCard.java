package model.cards;

import model.Card;
import model.Color;

/**
 * Carta generica con degli effetti particolari: +4 o cambia colore
 * @author ann
 *
 */
public abstract class WildCard extends Card
{
	public WildCard(String nameImage)
	{
		setImage(nameImage);
	}
	@Override
	public boolean isWildCard() {
		return true;
	}
	

}
