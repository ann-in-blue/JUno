package controller;

import java.io.IOException;

import model.FilePlayersDatabase;


/**
 * Classe che permette di accedere al database (in questo caso consiste in un file di testo) e  
 * recuperare/scrivere i dati di un giocatore.
 * @author a-00
 *
 */
public class ControllerFilePlayersDatabase 
{
	/**
	 * Riferimento al database che si trova nel modello.
	 */
	FilePlayersDatabase fileData = new FilePlayersDatabase();
	
	/**
	 * Metodo che prende in input un nickname e il path dell'avatar e lo aggiunge al file.
	 * @param nickname
	 * @param avatar
	 * @throws IOException 
	 */
	public void salvaSuFile(String nickname, String avatar) throws IOException
	{
		fileData.salvaSuFile(ControllerJUno.file, nickname, avatar);
	}

	/**
	 * Metodo che recupera i dati del giocatore identificato con il nickname inserito in input.
	 * @param nickname
	 * @return la stringa contenente i dati del giocatore cercato.
	 * @throws IOException
	 */
	public String caricaDaFile(String nickname) throws IOException
	{
		return fileData.caricaDaFile(ControllerJUno.file, nickname);
	}
	
	/**
	 * Metodo usato all'inizio del gioco per verificare se il giocatore possiede già un account o se è necessario crearne uno nuovo.
	 * @param nickname
	 * @return
	 * @throws IOException
	 */
	public boolean checkPlayerInFile(String nickname) throws IOException
	{
		return fileData.checkPlayerInFile(ControllerJUno.file, nickname);
	}
}
