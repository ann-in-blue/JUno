package model;

public class InvalidTurnException extends Exception
{		
	String player;

	public InvalidTurnException(String message, String player)
	{
		super(message);
		this.player = player;
	}

}
