package model.cards;

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
	protected int getValue() {
		return -1;
	}
}

