package model;

public class WrongArgumentsException extends Exception
{

	public WrongArgumentsException(String errorMessage)
	{
		System.out.println(errorMessage);
	}
}
