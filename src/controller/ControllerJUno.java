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
		protected ArtificialPlayerMoves ai;
		protected String nickname;
		protected ArtificialPlayer bot1, bot2, bot3;
		protected Player humanPlayer;
		
		//campi della view
		protected static FinestraHome view;
		protected static GraficaGioco viewGioco;
		
		protected static ViewObserver viewObserver;
		
		public boolean waitForInput;
		
		/**
		 * Costruttore della classe JUno: crea un'istanza della view, del modello, dell'osservatore del modello e aggancia i listeners ai vari componenti della view.
		 */
		public ControllerJUno()
		{
			//creazione della view
			view = new FinestraHome();
			
			//creazione del modello
			game = new Game();
			
			//mosse dei giocatori
			ai = new ArtificialPlayerMoves(game);
					
			//creazione dell'observer del gioco
			viewObserver = new ViewObserver();
			
			//si rende la view osservatrice del modello aggiungendola alla lista dei suoi osservatori, attraverso la classe ViewObserver che fa da ponte tra l'entità game e l'entità view.
			game.addObserver(viewObserver);
			
		}

		public void startGame(String nickname)
		{
			this.nickname= nickname;
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
//			viewGioco.setLabelTurno(new JLabel("Turno di: "+game.getCurrentPlayerName()));
			//aggiunta alla view della prima carta sul tavolo
			viewGioco.setDiscardButton(game.getDiscardDeck().get(0));
			changeColor(game.getValidColor());

			

			setTurn();
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
		
		/**
		 * Metodo run che permette la gestione dei turni
		 * @return
		 */
		
		public void setTurn()
		{
			//aggiunta alla view delle carte del giocatore umano
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
			viewGioco.getButtonUno().addActionListener(new ButtonUnoEventListener(game.getCurrentPlayerName(), game));
			viewGioco.getButtonDeck().addActionListener(new DeckEventListener(this));
			
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

		public void startArtificialPlayersTurn()
		{
			System.out.println("entro nei turni dei giocatori artificiali");
			//PROVA GESTIONE TURNI
			while(game.getCurrentPlayer() != 0)	
			{
				System.out.println("prova while");
			
				//fino a che non è il turno del giocatore umano fai giocare i giocatori artificiali
				try {
					playTurn();
					Thread.sleep(3000);

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidTurnException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
			
			
			System.out.println("update inizio \n" + arg);
			System.out.println(game.getPlayersDecks().get(game.getCurrentPlayer()));

				
//			if(game.getCurrentPlayer()==0)	//se è il turno del giocatore umano
//			{				
//				viewGioco.getPannelloGiocatoreUmano().refresh();
				
//				viewGioco.getPannelloGiocatoreUmano().removeAll(); // Pulisco il pannello
				game.getPlayersDecks().get(0).remove((Card)arg);
//				viewGioco.getPannelloGiocatoreUmano().setCardButtons(game.getPlayersDecks().get(0));	
				//deve mostrare solo le card del giocatore umano
//				setCardListenerOnCardButtons();
				setGameForNextTurn();

				viewGioco.setDiscardButton((Card)arg);
				changeColor(game.getValidColor());
				
				// Aggiungo i componenti
				viewGioco.getPannelloGiocatoreUmano().repaint();
				viewGioco.getPannelloGiocatoreUmano().validate();
				
				viewGioco.repaint();
				viewGioco.validate();
				viewGioco.refresh();
				
				try {
					Thread.sleep(2000);
					System.out.println("Aspetto prima di giocare il turno successivo");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				startArtificialPlayersTurn();

		}
				
				
		public void setGameForNextTurn()
		{
			viewGioco.setPannelloGiocatoreUmano(new PannelloGiocatoreUmano(800, 500, nickname, game.getPlayersDecks().get(0)));
			
			setCardListenerOnCardButtons();
			
			// Aggiungo i componenti
			viewGioco.getPannelloGiocatoreUmano().repaint();
			viewGioco.getPannelloGiocatoreUmano().validate();
			
			viewGioco.repaint();
			viewGioco.validate();
			viewGioco.refresh();
		}

		
		
		public void setCardListenerOnCardButtons()
		{
//			viewGioco.getPannelloGiocatoreUmano().setCardButtons(game.getPlayersDecks().get(0));	//deve mostrare solo le card del giocatore umano

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
		 * @param card
		 * @param state
		 * @throws InterruptedException 
		 */
		public void updateView(Card card, GameState state) throws InterruptedException
		{
			switch(state)
			{
			case DRAWING_CARD -> 
			{
				
		
			System.out.println("prendo una carta \n" + card);
			System.out.println(game.getPlayersDecks().get(game.getCurrentPlayer()));
//			
			//prova refresh
			System.out.println("prova refresh");
				
			int giocatore = game.getCurrentPlayer();
			System.out.println("giocatore corrente: "+ giocatore);
			
			
			if(giocatore == 0)	//se è il turno del giocatore umano
			{				
				viewGioco.getPannelloGiocatoreUmano().refresh();
				
				viewGioco.getPannelloGiocatoreUmano().removeAll(); // Pulisco il pannello

				setButtonPassaTurno();
				setGameForNextTurn();

				
				// Aggiungo i componenti
				viewGioco.getPannelloGiocatoreUmano().repaint();
				viewGioco.getPannelloGiocatoreUmano().validate();
				viewGioco.repaint();
				viewGioco.validate();
				viewGioco.refresh();
				}
			else
			{
				/**
				 * è il turno del giocatore artificiale e viene pescata una carta:
				 * - si incrementa il contatore delle carte rimaste da giocare
				 */
				viewGioco.getPannelloGiocatore(giocatore).removeAll();
			viewGioco.getPannelloGiocatore(giocatore).setRemainingCards(game.getPlayerDeckSize(game.getCurrentPlayerName()));
			
			System.out.println(" giocatore: "+ giocatore);
			System.out.println("remaining cards for player: "+ game.getPlayerDeckSize(game.getCurrentPlayerName()));
			System.out.println("current pla: "+game.getCurrentPlayerName());

			Thread.sleep(1000);

			viewGioco.getPannelloGiocatore(giocatore).repaint();
			viewGioco.getPannelloGiocatore(giocatore).validate();
			viewGioco.repaint();
			viewGioco.validate();
			viewGioco.refresh();

				
			}
			}
			case PLAYING_CARD ->
			{
			
				viewGioco.getButtonPassaTurno().setVisible(false);
				this.update(game, card);
				System.out.println("update fatto");
//				
			}
			case PLAYING_CARD_ARTIFICIAL ->
			{
				
				//se è il turno dei giocatori artificiali
					viewGioco.refresh();
					//viewGioco.removeAll();
					JLabel message = new JLabel("Giocatore artificiale " + game.getPreviousPlayer()+"\nCarta giocata: " + card +"!");
					JOptionPane.showMessageDialog(null, message);
					
					viewGioco.setDiscardButton(card);
					changeColor(game.getValidColor());
					
					//new jLabel con le carte rimanenti
					viewGioco.getPannelloGiocatore(game.getPreviousPlayer()).setRemainingCards(game.getPlayerDeckSize(game.getCurrentPlayerName()));
					System.out.println("remaining cards for player: "+ game.getPlayerDeckSize(game.getPreviousPlayerName()));
					System.out.println(game.getPlayerDeck(game.getPreviousPlayerName()));


					viewGioco.getPannelloGiocatore(game.getCurrentPlayer()).repaint();
					viewGioco.getPannelloGiocatore(game.getCurrentPlayer()).validate();
					
					//
					viewGioco.repaint();
					viewGioco.validate();
					viewGioco.refresh();
					Thread.sleep(1000);

			}
			
		}
	}
	
		/**
		 * Metodo che rende visibile il bottone per passare il turno e associa ad esso un action listener che permette all'utente
		 * di passare il turno al giocatore successivo nel caso in cui, dopo aver pescato una carta, non si ha nessuna carta da giocare.
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
			System.out.println();
			System.out.println("Passa turno cliccato");
			
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
					System.out.println("**"+"bot"+ game.getCurrentPlayer());

					//turno del giocatore artificiale
//					Card card = game.playCardArtificial(game.getCurrentPlayer());
					Card card = ai.playCardArtificial(game.getCurrentPlayer());

					if (!card.equals(null))
					{
						System.out.println("carta giocata al play turn artificial :"+card+ game.getCurrentPlayer());
						updateView(card, GameState.PLAYING_CARD_ARTIFICIAL);

					}
						
					
					
				} catch (InvalidTurnException e) {
					e.printStackTrace();
				}
			
		}
		
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

		public void waitForUserInput() {
			
			System.out.println("entro nel wait for user");
//			setTurn();
			setGameForNextTurn();

	
		}

		public boolean isWaitForInput() {
			return waitForInput;
		}

		public void setWaitForInput(boolean waitForInput) {
			this.waitForInput = waitForInput;
		}
}
