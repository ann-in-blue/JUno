package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;

import controller.exceptions.WrongArgumentsException;
import model.ArtificialPlayer;
import model.Card;
import model.Color;
import model.Deck;
import model.Game;
import model.Player;
import model.Type;
import model.cards.Draw2Card;
import view.FinestraHome;
import view.GraficaGioco;
import view.PaginaIniziale;
import view.PannelloAvatar;
import view.PaginaIniziale.*;
import view.PannelloGiocatore;


public class ControllerJUno implements Observer
{	
	
		/**
		 * url del file che fa da database per i dati dei giocatori, in comune per tutti 
		 * e altri campi per la gestione dei dati 
		 */
		public static File file = new File("/home/a-00/git/JUno/fileData.txt");
		protected ControllerFilePlayersDatabase controllerDatabase = new ControllerFilePlayersDatabase();

		//campi del model
		protected static Game game;
		protected ArtificialPlayer bot1, bot2, bot3;
		protected Player humanPlayer;
		
		//campi della view
		protected static FinestraHome view;
		protected static GraficaGioco viewGioco;
		
		protected static ViewObserver viewObserver;
		
		/**
		 * Costruttore della classe JUno: crea un'istanza della view, del modello, dell'osservatore del modello e aggancia i listeners ai vari componenti della view.
		 */
		public ControllerJUno()
		{
			//creazione della view
			view = new FinestraHome();
			
			//creazione del modello
			game = new Game();
					
			//creazione dell'observer del gioco
			viewObserver = new ViewObserver();
			
			//si rende la view osservatrice del modello aggiungendola alla lista dei suoi osservatori, attraverso la classe ViewObserver che fa da ponte tra l'entità game e l'entità view.
			game.addObserver(viewObserver);
			
		}

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

			//inizializzazione dei mazzi di gioco e della prima carta sul tavolo
			game.startGame();
			
			//creazione della view di gioco
			viewGioco = new GraficaGioco(game.getPlayersId());
			viewGioco.setLabelTurno(new JLabel("Turno di: "+game.getCurrentPlayerName()));

			//aggiunta alla view della prima carta sul tavolo
			viewGioco.setDiscardButton(game.getDiscardDeck().get(0));
			
			//aggiunta alla view della carte del giocatore umano
			for(int i=0; i< game.getPlayerDeckSize(nickname); i++)
			{
//				System.out.println(game.getPlayerDeckSize(nickname));
//				System.out.println(game.getPlayerDeck(nickname).get(i));
				
				//per ogni carta in mano al giocatore umano aggiungo l'immagine alla view
				viewGioco.getPannelloGiocatoreUmano().addButtonCard(game.getPlayerDeck(nickname).get(i));
			
				/**
				 * Aggancio dei listeners sulle carte del giocatore umano:
				 * Per ogni carta presente nella mano del giocatore umano aggiungo un listener che permetterà di scegliere la carta da giocare.
				 */
				viewGioco.getPannelloGiocatoreUmano().getCardButtons().get(i).addActionListener(new CardEventListener(game.getPlayerDeck(nickname).get(i), this));

			}
			/**
			 * Aggiunta di un action listener sul bottone "Uno" e sul mazzo coperto
			 */
			viewGioco.getPannelloGiocatoreUmano().getButtonUno().addActionListener(new ButtonUnoEventListener(game.getCurrentPlayerName(), game));
			viewGioco.getButtonDeck().addActionListener(new DeckEventListener());
			
		}
		public void startGameWithAvatarRequest(String nickname)
		{
			//apri il menu per la scelta dell'avatar
			PannelloAvatar pannelloAvatar = new PannelloAvatar();
			view.add(pannelloAvatar);
			String avatar = pannelloAvatar.getAvatarInput();
			//crea un nuovo giocatore con quel nickname e salvalo su file
			try {
				controllerDatabase.salvaSuFile(nickname, avatar);
				startGame(nickname);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		public static Game getGame() {
			return game;
		}
		public static void setGame(Game game) {
			ControllerJUno.game = game;
		}
		public static FinestraHome getView() {
			return view;
		}
		public static void setView(FinestraHome view) {
			ControllerJUno.view = view;
		}
		public static File getFile() {
			return file;
		}
		public static void setFile(File file) {
			ControllerJUno.file = file;
		}

		/**
		 * Metodo che permette, dopo aver ricevuto una notifica dal modello, di aggiornare la view in base all'argomento in input.
		 * La view può essere aggiornata in vari modi, in relazione agli elementi del modello che sono cambiati e anche al tipo di giocatore che ha giocato il suo turno.
		 * 
		 * Nel caso del giocatore umano cambiano sia le carte nel suo mazzo sia la carta presente nel mazzo di scarto sul tavolo.
		 * Nel caso dei giocatori artificiali cambia solo la carta nel mazzo di scarto.
		 * 
		 */
		@Override
		public void update(Observable o, Object arg) {
			System.out.println(arg);

			viewGioco.setDiscardButton(game.getDiscardDeck().get(game.getDiscardDeck().size()-1));
			viewGioco.getPannelloGiocatoreUmano().validate();
			ArrayList<Card> hand = game.getPlayerDeck(game.getPlayersId()[0]);
			viewGioco.getPannelloGiocatoreUmano().getCardButtons().clear();
			for(Card c : hand)
			viewGioco.getPannelloGiocatoreUmano().addButtonCard(c);

		}

}
