package model.cards;

/**
 * Carta cambia colore: colore da scegliere e valore -1;
 * @author a-00
 *
 */
public class ChangeColorCard extends WildCard
{

	public ChangeColorCard(String nameImage) {
		super(nameImage);
	}
	

	@Override
	public String toString() {
		return "changeColor";
	}


	@Override
	public int getValue() {
		return -1;
	}
}
