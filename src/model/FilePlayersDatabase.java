package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * classe che permette di salvare i dati dei giocatori in un file
 * @author a-00
 *
 */
public class FilePlayersDatabase
{
	
//	private ArrayList<Player> players;
	
//	public FilePlayersData()
//	{
//		this.players = new ArrayList<Player>();
//	}
//	public FilePlayersData(ArrayList<Player> players) {
//		super();
//		this.players = players;
//	}
//
//	/**
//	 * Metodo per aggiungere un giocatore nella lista.
//	 * @param player
//	 */
//	public void addPlayers(Player player)
//	{
//		players.add(player);
//	}
//	
//	/**
//	 * Metodo che restituisce l'array contenente i giocatori.
//	 */
//	public ArrayList<Player> getPlayers() {
//		return players;
//	}


	/**
	 * Metodo per salvare i dati di un giocatore sul file in input.
	 * Il nickname e l'avatar vengono inseriti dall'utente mentre gli altri dati vengono settati a 0.
	 */
	public void salvaSuFile(File file, String nickname, String avatar) throws IOException
	{
		
		try {
			/**
			 * Se il nickname non è presente sul file viene aggiunto
			 */
			if(!checkPlayerInFile(file, nickname))
			{
				/**
				 * Dal path del file, viene creato uno stream di output per scrivere dati sul file. Se il file non esiste viene creato.
				 */
				FileWriter fileWriter = new FileWriter(file, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				
				bufferedWriter.append(new Player(nickname, avatar).toString()+"\n");
		
				//chiusura risorse
				bufferedWriter.close();
				fileWriter.close();
				
			}else
				System.out.println("nickname già presente");
			
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo per verificare se un giocatore è presente in memoria e quindi se è salvato su file.
	 * @throws IOException 
	 */
	public boolean checkPlayerInFile(File file, String nickname) throws IOException
	{
		Path p = Path.of(file.getAbsolutePath());

		return Files.lines(p).anyMatch(x -> x.startsWith(nickname));
	}
	
	
	/**
	 * Metodo per caricare i dati di un giocatore da un file in input.
	 */
	public String caricaDaFile(File file, String nickname) throws IOException
	{
	
		//controllo se il giocatore è già salvato in memoria e in caso positivo recupero i suoi dati
		
		if(checkPlayerInFile(file, nickname))
		{
			try {	

				//leggiamo l'array di giocatori salvato su file e troviamo la stringa corrispondente al giocatore cercato
				List<String> p = Files.lines(Path.of(file.getAbsolutePath())).filter(x -> x.startsWith(nickname)).collect(Collectors.toList());
			
			return p.get(0);

			
		} catch (IOException e) {
			e.printStackTrace();
			}
		}
		else
		{
			//altrimenti so che non è presente su file e non restituisco nulla
			return "Errore: giocatore non trovato";	
		}
		return null;
		
	}
	
	public static void main(String[] args) throws IOException {
		
		File f = new File("/home/a-00/git/JUno/fileData.txt");
		FilePlayersDatabase fd = new FilePlayersDatabase();
		
//		fd.salvaSuFile(f, "yoda", "yoda.png");

		Player p1 = new Player(fd.caricaDaFile(f, "yoda"));

	}
	
}
