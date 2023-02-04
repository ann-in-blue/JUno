package model.cards;

/**
 * Classe che rappresenta una carta +4:
 * valore -1;
 * @author a-00
 *
 */
public class Draw4Card extends WildCard
{
	

	public Draw4Card(String nameImage)
	{
		super(nameImage);
	}
	
	@Override
	public String toString()
	{
		return "draw4";
	}

	@Override
	public int getValue() {
		return -1;
	}
}

