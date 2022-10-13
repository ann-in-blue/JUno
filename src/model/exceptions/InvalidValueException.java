package model.exceptions;

public class InvalidValueException  extends Exception
{
	private int correctValue;
	private int usedValue;
	
	public InvalidValueException(String message, int usedValue, int correctValue)
	{
		super(message);
		this.correctValue = correctValue;
		this.usedValue = usedValue;
	}
	

}
