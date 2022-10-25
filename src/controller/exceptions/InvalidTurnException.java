package controller.exceptions;

public class InvalidTurnException extends Exception
{		
	 private String player;

	public InvalidTurnException(String message, String player)
	{
		super(message);
		this.player = player;
	}

	public String getPlayer() {
		return player;
	}


}
