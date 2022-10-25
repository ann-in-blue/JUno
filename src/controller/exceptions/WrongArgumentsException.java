package controller.exceptions;

public class WrongArgumentsException extends Exception
{

	public WrongArgumentsException(String errorMessage)
	{
		System.out.println(errorMessage);
	}
}
