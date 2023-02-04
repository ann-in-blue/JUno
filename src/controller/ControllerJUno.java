package controller;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.exceptions.InvalidCardException;
import controller.exceptions.InvalidColorException;
import controller.exceptions.InvalidTurnException;
import controller.exceptions.InvalidValueException;
import controller.exceptions.WrongArgumentsException;
import model.ArtificialPlayer;
import model.ArtificialPlayerMoves;
import model.Card;
import model.Colors;
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
import view.PannelloGiocatoreUmano;

/**
 * Classe principale del controller che si occupa di gestire le interazioni utente e 
 * aggiornare la view in base ai cambiamenti nella logica del gioco.
 * 
 * @author a-00
 *
 */
public class ControllerJUno implements Observer
{	
	
		/**
		 * Riferimento all'url del file che fa da database per i dati dei giocatori, in comune per tutti 
		 * e altri campi per la gestione dei dati degli utenti. Il nickname fa riferimento al giocatore umano.
		 */
		public static File file = new File("/home/a-00/git/JUno/fileData.txt");
		protected ControllerFilePlayersDatabase controllerDatabase = new ControllerFilePlayersDatabase();
		protected String nickname;

		/**
		 * Campi per salvare i riferimenti agli elementi della logica di gioco, tra cui la partita e i 4 giocatori.
		 */
		protected static Game game;
		protected ArtificialPlayerMoves ai;
		protected ArtificialPlayer bot1, bot2, bot3;
		protected Player humanPlayer;
		
		/**
		 * Riferimenti alle classi che compongono la view. 
		 * La classe FinestraHome è la finestra iniziale di gioco mentre la classe GraficaGioco rappresenta la partita vera e propria.
		 */
		protected static FinestraHome view;
		protected static GraficaGioco viewGioco;
				
		
		/**
		 * Costruttore della classe: crea un'istanza della view, del modello, e della classe che rappresenta le mosse dei giocatori artificiali.
		 */
		public ControllerJUno()
		{
			//creazione della view
			view = new FinestraHome();
			
			//creazione del modello
			game = new Game();
			
			//mosse dei giocatori
			ai = new ArtificialPlayerMoves(game);
			
		}

		/**
		 * Metodo che permette di inizializzare una partita una volta ricevuto il nickname del giocatore umano.
		 * Si occupa di inizializzare i giocatori artificiali e il giocatore umano con i dati recuperati dal file e di inserirli nella lista dei giocatori per la partita corrente.
		 * @param nickname
		 */
		public void startGame(String nickname)
		{
			this.nickname= nickname;
			/**
			 * Recupero dei dati dei 3 giocatori artificiali
			 */
			try {
				//inizializzo i tre giocatori artificiali
				bot1 = new ArtificialPlayer(controllerDatabase.caricaDaFile("bot1"));
				bot2 = new ArtificialPlayer(controllerDatabase.caricaDaFile("bot2"));
				bot3 = new ArtificialPlayer(controllerDatabase.caricaDaFile("bot3"));

				//inizializzazione del giocatore umano
				humanPlayer = new Player(controllerDatabase.caricaDaFile(nickname));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//inizializzazione della lista dei 4 giocatori
			game.getPlayersId()[0] = nickname;
			game.getPlayersId()[1] = bot1.getNickname();
			game.getPlayersId()[2] = bot2.getNickname();
			game.getPlayersId()[3] = bot3.getNickname();

			/**
			 * Inizializzazione dei mazzi di gioco e della prima carta sul tavolo
			 */
			game.startGame();
			
			/**
			 * Creazione della view di gioco.
			 */
			viewGioco = new GraficaGioco(game.getPlayersId());

			/**
			 * Aggiunta alla view della prima carta sul tavolo
			 */
			viewGioco.setDiscardButton(game.getDiscardDeck().get(0));
			changeColor(game.getValidColor());

			/**
			 * Aggiunta delle carte ai mazzi sul tavolo e aggancio dei listeners sulle varie carte.
			 */
			setTurn();
		}
	
		/**
		 * Metodo che permette di inserire un'immagine come avatar e creare un account nuovo per quel giocatore.
		 * Una volta salvati i dati sul file inizia la partita.
		 * @param nickname
		 */
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
		
		/**
		 * Metodo che permette la gestione dei turni, preparanto il tavolo con le carte del giocatore umano.
		 * Aggancia i listeners alle carte per permettere al giocatore di selezionare la carta durante il suo turno.
		 */
		
		public void setTurn()
		{
			//aggiunta alla view delle carte del giocatore umano
			for(int i=0; i< game.getPlayerDeckSize(nickname); i++)
			{

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
			viewGioco.getButtonUno().addActionListener(new ButtonUnoEventListener(game.getCurrentPlayerName(), game));
			viewGioco.getButtonDeck().addActionListener(new DeckEventListener(this));
			
		}
		
		/**
		 * Metodo che permette di avere un riferimento al campo game della classe.
		 * @return
		 */
		public static Game getGame() {
			return game;
		}
		/**
		 * Metodo che permette di avere un riferimento al campo view della classe.
		 * @return
		 */
		public static FinestraHome getView() {
			return view;
		}
		
		/**
		 * Metodo per settare la view del gioco.
		 * @param view
		 */
		public static void setView(FinestraHome view) {
			ControllerJUno.view = view;
		}
		
		//Metodi get e set per il file
		public static File getFile() {
			return file;
		}
		public static void setFile(File file) {
			ControllerJUno.file = file;
		}

		/**
		 * Metodo che permette di giocare i vari turni ai giocatori artificiali.
		 * In particolare viene eseguito dopo il turno del giocatore umano e permette di continuare la partita facendo scegliere la carta dei giocatori artificiali.
		 */
		public void startArtificialPlayersTurn()
		{
			//Fino al prossimo turno del giocatore umano, che ha indice pari a 0, giocano i giocatori artificiali
			while(game.getCurrentPlayer() != 0)	
			{			
				//fino a che non è il turno del giocatore umano fai giocare i giocatori artificiali
				try {
					playTurn();
					Thread.sleep(2000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidTurnException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//Una volta terminati i turni dei giocatori artificiali prepara il tavolo per l'utente e aspetta una sua mossa
			waitForUserInput();
		
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
			
			/**
			 * Viene rimossa la la carta giocata dal mazzo del giocatore umano.
			 */
				game.getPlayersDecks().get(0).remove((Card)arg);

				//Aggiornamento delle carte in tavola
				setGameForNextTurn();

				//aggiunta della carta giocata al mazzo di scarto sul tavolo e aggiornamento del colore della carta
				viewGioco.setDiscardButton((Card)arg);
				changeColor(game.getValidColor());
				
				refreshPanel();
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				/**
				 * Inizio turni dei giocatori artificiali.
				 */
				startArtificialPlayersTurn();

		}
				
				/**
				 * Metodo che aggiorna il pannello del giocatore umano dopo la fine dei turni di tutti i giocatori con le sue carte e aggiunge i listeners ad esse.
				 */
		public void setGameForNextTurn()
		{
			viewGioco.setPannelloGiocatoreUmano(new PannelloGiocatoreUmano(800, 500, nickname, game.getPlayersDecks().get(0)));
			
			setCardListenerOnCardButtons();
			refreshPanel();
		}

		
		/**
		 * Metodo che si occupa di aggiungere gli ActionListener a tutte le carte in mano al giocatore umano.
		 */
		public void setCardListenerOnCardButtons()
		{
			//aggiunta alla view della carte del giocatore umano
			for(int i=0; i< (game.getPlayersDecks().get(0)).size(); i++)
			{			
					/**
					 * Aggancio dei listeners sulle carte del giocatore umano:
					 * Per ogni carta presente nella mano del giocatore umano aggiungo un listener che permetterà di scegliere la carta da giocare.
					 */
				viewGioco.getPannelloGiocatoreUmano().getCardButtons().get(i).addActionListener(new CardEventListener(game.getPlayerDeck(humanPlayer.getNickname()).get(i), this));

			}
		}

		
		/**
		 * Metodo di appoggio in cui è possibile esplicitare lo stato in cui si trova il sistema per poter aggiornare la view in modo corretto.
		 * @param card: carta selezionata dal giocatore corrente
		 * @param state: stato del gioco che determina le varie istruzioni da eseguire
		 * @throws InterruptedException 
		 */
		public void updateView(Card card, GameState state) throws InterruptedException
		{
			switch(state)
			{
			//Caso in cui è stata pescata una carta
				case DRAWING_CARD -> 
				{
					int giocatore = game.getCurrentPlayer();
//					System.out.println("giocatore corrente: "+ giocatore);
					
					if(giocatore == 0)	//se è il turno del giocatore umano viene aggiunta la carta al tavolo
					{				
						
						viewGioco.getPannelloGiocatoreUmano().refresh();
						viewGioco.getPannelloGiocatoreUmano().removeAll(); // Pulisco il pannello
		
						/**
						 * Viene mostrato il tasto per poter passare il turno nel caso in cui non si ha nessuna carta disponibile da giocare dopo aver pescato.
						 */
						setButtonPassaTurno();
						setGameForNextTurn();
		
						refreshPanel();
						}
					else
					{
						/**
						 * Se è il turno del giocatore artificiale e viene pescata una carta:
						 * - si incrementa il contatore delle carte rimaste da giocare
						 */
						viewGioco.getPannelloGiocatore(giocatore).removeAll();
viewGioco.getPannelloGiocatore(giocatore).setRemainingCards(game.getPlayerDeckSize(game.getCurrentPlayerName()));
					Thread.sleep(1000);
					refreshPanel();
						
					}
				}
				case PLAYING_CARD ->
				{
					/**
					 * Caso in cui è stata giocata una carta dal giocatore umano.
					 * Si richiama il metodo precedente che aggiorna la view giocando la carta selezionata.
					 */
					viewGioco.getButtonPassaTurno().setVisible(false);
					this.update(game, card);
				}
				case PLAYING_CARD_ARTIFICIAL ->
				{
					
					/**
					 * Se è il turno dei giocatori artificiali
					 */
					viewGioco.refresh();
					JLabel message = new JLabel("Giocatore artificiale " + game.getPreviousPlayer()+"\nCarta giocata: " + card +"!");
					JOptionPane.showMessageDialog(null, message);
					
					viewGioco.setDiscardButton(card);
					changeColor(game.getValidColor());
					
					//new jLabel nella view con le carte rimanenti ai vari giocatori
					viewGioco.getPannelloGiocatore(game.getPreviousPlayer()).setRemainingCards(game.getPlayerDeckSize(game.getCurrentPlayerName()));
					
					refreshPanel();
					Thread.sleep(1000);
				}
		}
	}
	
		/**
		 * Metodo che rende visibile il bottone per passare il turno e associa ad esso un action listener che permette all'utente di passare il turno al giocatore successivo nel caso in cui, dopo aver pescato una carta, non si ha nessuna carta da giocare.
		 */
		public void setButtonPassaTurno()
		{
			viewGioco.getButtonPassaTurno().setVisible(true);
			viewGioco.getButtonPassaTurno().addActionListener(new PassaTurnoEventListener(this));
		}
		
		/**
		 * Metodo chiamato dopo aver cliccato il bottone "Passa Turno" che permette al giocatore umano di saltare il turno e passare al giocatore successivo.
		 * 
		 */
		public void passaTurno()
		{
//			System.out.println();
//			System.out.println("Passa turno cliccato");
			
			game.nextPlayerTurn();			
			viewGioco.getButtonPassaTurno().setVisible(false);

			startArtificialPlayersTurn();
		}

/**
 * Metodo che permette ai giocatori artificiali di giocare il loro turno
 * @throws InvalidTurnException
 * @throws InterruptedException
 */
		public void playTurn() throws InterruptedException, InvalidTurnException 
		{
				try {								
//					System.out.println("**"+"bot"+ game.getCurrentPlayer());

					//Metodo che permette, in modo casuale, di scegliere una carta da giocare per i giocatori artificiali.
					Card card = ai.playCardArtificial(game.getCurrentPlayer());

					if (!card.equals(null))
					{
					//Aggiornamento della view con la carta scelta.
						updateView(card, GameState.PLAYING_CARD_ARTIFICIAL);

					}					
				} catch (InvalidTurnException e) {
					e.printStackTrace();
				}
			
		}
		
		/**
		 * Metodo che permette di modificare il colore sul tasto uno ogni volta che il colore di gioco viene modificato.
		 * @param color
		 */
		public void changeColor(Colors color)
		{
			Color c = switch(color)
					{
					case RED -> Color.red;
					case BLUE -> Color.blue;
					case YELLOW -> Color.yellow;
					case GREEN -> Color.green;
					default -> Color.gray;
			
					};
			viewGioco.setGameColor(c);

		}
		
		/**
		 * Metodo che permette di aggiornare gli elementi nei pannelli.
		 */
		public void refreshPanel()
		{
			// Aggiungo i componenti al pannello del giocatore umano
			viewGioco.getPannelloGiocatoreUmano().repaint();
			viewGioco.getPannelloGiocatoreUmano().validate();
			
			viewGioco.repaint();
			viewGioco.validate();
			viewGioco.refresh();
		}
		
		
	/**
	 * Metodo che prepara la finestra di gioco per la mossa successiva dell'utente:
	 * 
	 */
		public void waitForUserInput() {
			
//			System.out.println("entro nel wait for user");
			setGameForNextTurn();
		}
}
