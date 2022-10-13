package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import controller.listeners.PaginaInizialeEventListener;
import model.ArtificialPlayer;
import model.Game;
import model.Player;
import view.FinestraHome;
import view.GraficaGioco;
import view.PaginaIniziale.*;
import view.PannelloGiocatore;

public class JUno 
{
	
	public static Game game;
	public static FinestraHome view;
	public static File file = new File("/home/a-00/git/JUno/fileData.txt");
	ControllerFilePlayersDatabase controllerDatabase = new ControllerFilePlayersDatabase();
	private ArtificialPlayer bot1, bot2, bot3;
	private Player humanPlayer;
	
	public void startGame(String nickname)
	{
		/**
		 * Recupero dei dati dei 3 giocatori artificiali
		 */
		try {
			//inizializzo i tre giocatori con i dati recuperati dal file
			bot1 = new ArtificialPlayer(controllerDatabase.caricaDaFile("bot1"));
			bot2 = new ArtificialPlayer(controllerDatabase.caricaDaFile("bot2"));
			bot3 = new ArtificialPlayer(controllerDatabase.caricaDaFile("bot3"));

			//inizializzazione del giocatore umano
			humanPlayer = new Player(controllerDatabase.caricaDaFile(nickname));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//inizializzazione della lista dei 4 giocatori
		game.getPlayersId()[0] = nickname;
		game.getPlayersId()[1] = bot1.getNickname();
		game.getPlayersId()[2] = bot2.getNickname();
		game.getPlayersId()[3] = bot3.getNickname();

		//inizializzazione dei mazzi di gioco
		game.startGame();
		
		//creazione della view di gioco
		GraficaGioco viewGioco = new GraficaGioco(game.getPlayersId());
		
		//aggiunta alla view della carte del giocatore umano
		
		
		
	}
	public static Game getGame() {
		return game;
	}
	public static void setGame(Game game) {
		JUno.game = game;
	}
	public static FinestraHome getView() {
		return view;
	}
	public static void setView(FinestraHome view) {
		JUno.view = view;
	}
	public static File getFile() {
		return file;
	}
	public static void setFile(File file) {
		JUno.file = file;
	}
	
	/**
	 * Classe che contiene il main del gioco.
	 */
	public static void main(String[] args) {
		
		//creazione della view
		view = new FinestraHome();
		
		//creazione del modello
		game = new Game();
				
		//si rende la view osservatrice del modello aggiungendola alla lista dei suoi osservatori
		game.addObserver(view);
		
		
		//aggancio dei listeners ai componenti della view
	
	}

}
