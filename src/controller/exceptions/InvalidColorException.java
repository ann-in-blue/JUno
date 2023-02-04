package controller.exceptions;

import model.Colors;

public class InvalidColorException extends Exception
{
	private Colors correctColor;
	private Colors usedColor;
	
	public InvalidColorException(String message, Colors usedColor, Colors correctColor)
	{
		super(message);
		this.correctColor = correctColor;
		this.usedColor = usedColor;
	}

}
