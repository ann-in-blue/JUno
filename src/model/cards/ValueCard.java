package model.cards;

import model.Card;
import model.Colors;

/**
 * Classe che rappresenta una carta valore.
 * Ha un colore e un valore preciso.
 * @author a-00
 *
 */
public class ValueCard extends Card
{

	private final int value;
	
	public ValueCard(Colors color, int value, String nameImage)
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
	@Override
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
