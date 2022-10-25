package controller;

import java.io.IOException;

import model.FilePlayersDatabase;

public class ControllerFilePlayersDatabase 
{
	FilePlayersDatabase fileData = new FilePlayersDatabase();
	
	/**
	 * Metodo che prende in input un nickname e il path dell'avatar e lo aggiunge sul file
	 * @param nickname
	 * @param avatar
	 * @throws IOException 
	 */
	public void salvaSuFile(String nickname, String avatar) throws IOException
	{
		fileData.salvaSuFile(ControllerJUno.file, nickname, avatar);
	}

	public String caricaDaFile(String nickname) throws IOException
	{
		return fileData.caricaDaFile(ControllerJUno.file, nickname);
	}
	
	public boolean checkPlayerInFile(String nickname) throws IOException
	{
		return fileData.checkPlayerInFile(ControllerJUno.file, nickname);
	}
}
