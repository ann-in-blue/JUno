package model.cards;

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
	protected int getValue() {
		return -1;
	}
}
