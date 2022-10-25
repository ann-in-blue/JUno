package controller.exceptions;

import model.Color;

public class InvalidColorException extends Exception
{
	private Color correctColor;
	private Color usedColor;
	
	public InvalidColorException(String message, Color usedColor, Color correctColor)
	{
		super(message);
		this.correctColor = correctColor;
		this.usedColor = usedColor;
	}

}
