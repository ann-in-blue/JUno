package model;

import java.awt.Font;

import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.exceptions.InvalidCardException;
import controller.exceptions.InvalidColorException;
import controller.exceptions.InvalidTurnException;
import controller.exceptions.InvalidValueException;
import controller.exceptions.WrongArgumentsException;
import model.cards.WildCard;

/**
 * Classe che rappresenta la logica del gioco.
 * @author a-00
 *
 */
public class Game extends Observable
{
	//campi per salvare i giocatori
	private int currentPlayer;
	private String[] playersId;
	
	//campi per i mazzi
	private Deck deck;
	private ArrayList<ArrayList<Card>> playersDecks;	//carte in mano ai giocatori
	private ArrayList<Card> discardDeck;	//mazzo di scarto
	
	/**
	 * Riferimenti ai valori e al volore valido da giocare.
	 * I valori cambiano ad ogni turno.
	 */
	private Colors validColor;
	private int validValue;
	
	public boolean direction;	//senso orario o senso antiorario
	public boolean isGameOver;	//campo che permette di capire quando termina il gioco.
	
	
	/**
	 * Costruttore della classe.
	 * Crea una lista dei giocatori di dimensione 4.
	 */
	public Game() 
	{
		playersId = new String[4];
		currentPlayer = 0;
	}
	
	/**
	 * Metodo che permette di iniziare una partita:
	 * crea il mazzo, inizializza i vari elementi e setta la prima carta sul tavolo.
	 */
	public void startGame()
	{
		try
		{
			deck = new Deck(108);	//creazione del mazzo
			deck.shuffle();	//shuffle del mazzo
			discardDeck = new ArrayList<Card>();	//mazzo di scarto
			
			direction = false;
			playersDecks =  new ArrayList<ArrayList<Card>>();
			
			for (int i = 0; i < playersId.length; i++)
				{
				//per ogni giocatore -> array di 7 carte iniziali
				ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.distribuisciCard(7)));
				
				for(Card c: hand)
				{
					playersDecks.add(i, hand);
				}
			}
			setFirstCard();
			
		}catch(WrongArgumentsException wae)
		{
			wae.printStackTrace();
		}
		
	}

	
	public String getCurrentPlayerName() {
		return playersId[currentPlayer];
	}
	
	/**
	 * Metodo che restituisce l'indice del giocatore del turno precedente.
	 * @return
	 */
	public int getPreviousPlayer()
	{
		if(isDirection())	//se direzione == true vado indietro di 1 nella lista dei giocatori
		{
			if (getCurrentPlayer() > 0)	//posso decrementare tranquillamente di 1
				return getCurrentPlayer() - 1;
			else return getPlayersId().length - 1;
						}
		
		else {
			if(getCurrentPlayer() < 3)
				return getCurrentPlayer() + 1;
			else
				return 0;
		}
	}

	/**
	 * Metodo che restituisce il nome del giocatore del turno precedente.
	 * @return
	 */
	public String getPreviousPlayerName()
	{
		return playersId[getPreviousPlayer()];
	}
	/**
	 * Dato un indice restituisce il nome del giocatore corrispondente in lista.
	 * @param index
	 * @return
	 */
	public String getPlayerName(int index)
	{
		return playersId[index];
	}
	public int getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	public String[] getPlayersId() {
		return playersId;
	}
	public void setPlayersId(String[] playersId) {
		this.playersId = playersId;
	}
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * Metodo che, dato in input il nome di un giocatore, restituisce l'indice corrispondente nella lista dei giocatori
	 */
	public int getPlayerIndex(String player)
	{
		return Arrays.asList(playersId).indexOf(player);
	}
	
	/**
	 * Metodo che restituisce il mazzo del giocatore dato in input
	 * @return
	 */
	public ArrayList<Card> getPlayerDeck(String player) 
	{
		//partendo dal nome del giocatore trovo l'indice corrispondente nella lista dei giocatori
		int index = getPlayerIndex(player);
		//restituisco il mazzo corrispondente all'indice calcolato
		return playersDecks.get(index);
	}
	/**
	 * Metodo che, dato in input un giocatore, restituisce la lunghezza del suo mazzo
	 * @param pid
	 * @return
	 */
	public int getPlayerDeckSize(String pid)
	{
		return getPlayerDeck(pid).size();
	}
	
	
	public void setPlayerDecks(ArrayList<ArrayList<Card>> playersDecks) {
		this.playersDecks = playersDecks;
	}
	public ArrayList<Card> getDiscardDeck() {
		return discardDeck;
	}
	public void setDiscardDeck(ArrayList<Card> discardDeck) {
		this.discardDeck = discardDeck;
	}
	/**
	 * Metodo che restituisce la carta in cima al mazzo di scarto
	 * @return
	 */
	public Card getDiscardDeckTopCard()
	{
		return discardDeck.get(discardDeck.size()-1);
	}
	
	/**
	 * Metodo per cambiare il mazzo di riferimento con il mazzo di scarto
	 * @throws WrongArgumentsException 
	 */
	public void setDecktoDiscardDeck() throws WrongArgumentsException {
					
		Deck nuovoDeck = new Deck(discardDeck.size());	
		//nuovo mazzo di appoggio di dimensione minore di quello iniziale

		for(int i = 0; i < discardDeck.size(); i++)
		{	
			//aggiungo le carte presenti nel mazzo di scarto nel mazzo di appoggio 
			nuovoDeck.deck[i] = discardDeck.get(i);
		}
		//cambio il riferimento del mazzo in uso con quello di appoggio
		deck = nuovoDeck;
	}
	
	
	
	
	/**
	 * metodo per iniziare la partita scegliendo la prima carta.
	 * Come da regolamento alcune carte non possono essere giocate all'inizio e questo metodo permette di verificare che queste regole siano verificate.
	 */
	public void setFirstCard()
	{
		Card card = deck.distribuisciCard();
		
		//caso in cui la prima carta è una "pesca 4" -> viene rimessa nel mazzo e si pesca la carta successiva
		if(card.getClass().getSimpleName().equals("Draw4Card"))	
		{
			setFirstCard();	//ricominciamo pescando la carta successiva
		}
		
		//caso in cui esce una carta "cambia colore" -> il giocatore alla sinistra del mazziere sceglie il colore da giocare
		if(card.getClass().getSimpleName().equals("ChangeColorCard"))
		{			
			//rallentiamo l'esecuzione in modo da far caricare prima la pagina di gioco e poi aprire la finestra per la scelta del colore
			new Timer().schedule(new TimerTask(){
				@Override 
				public void run(){
					Object[] possibleColors = {"Red", "Blue", "Green", "Yellow"};
					Object selectedColor = JOptionPane.showInputDialog(null,"Choose one color", "Input", JOptionPane.INFORMATION_MESSAGE, null, possibleColors, possibleColors[0]);
					
					setValidColor(switch((String)selectedColor)
					{
							case "Red" -> Colors.RED;
							case "Blue" -> Colors.BLUE;
							case "Green" -> Colors.GREEN;
							case "Yellow" -> Colors.YELLOW;
							default -> null;
					});
				}
				},2000); 	
		}
		
		//caso in cui esce una carta Skip
		if(card.getClass().getSimpleName().equals("SkipCard"))
		{
			setValidColor(card.getColor());
			setValidValue(-1);	//non c'è nessun valore valido in questo caso
			
			skipTurnMove();
			
		}
		
		//caso in cui esce una carta "reverse card"
		if(card.getClass().getSimpleName().equals("ReverseCard"))
		{			
			reverseCardMove();
			
			setValidColor(card.getColor());
			setValidValue(-1);	//non c'è nessun valore valido in questo caso
		}
			
			
		//caso in cui esce una carta "Draw2" -> il giocatore alla sinistra del mazzierempesca due carte e salta un giro
		if(card.getClass().getSimpleName().equals("Draw2Card"))
		{
			setCurrentPlayer(++currentPlayer);
			setValidColor(card.getColor());
			setValidValue(-1);
			
			drawMultipleCardsMove(2);
			skipTurnMove();
			
		}
		
		//caso in cui esce una carta valore -> inizia il gioco in modo ordinario
		setValidColor(card.getColor());
		setValidValue(card.getValue());
		
		//aggiungiamo la carta al mazzo di scarto
		discardDeck.add(card);
		notifyObservers(card);
		
	}

	/**
	 * Metodo che blocca il giocatore corrente per un turno seguendo gli effetti della carta SKIP.
	 */
	public void skipTurnMove() {
		
		if(isDirection())	//se direzione == true avanzo di 1 nella lista dei giocatori
			setCurrentPlayer((getCurrentPlayer() + 1) % getPlayersId().length);		//una volta arrivata alla fine della lista torno all'inizio
		
		else if (!isDirection())
		{
			setCurrentPlayer((getCurrentPlayer() - 1) % getPlayersId().length);
			if(currentPlayer == -1)	//controllo nel caso in cui con il modulo sia uscito come risultato -1
				currentPlayer = playersId.length - 1;
		}
		
		/**
		 * Inseriamo una pausa di 5 secondi per far si che, nel caso in cui la carta SKIP fosse pescata come prima carta, venga prima creata la view e poi il messaggio a schermo
		 */
		new Timer().schedule(new TimerTask(){
			@Override 
			public void run(){
			
				JLabel message = new JLabel(playersId[currentPlayer] + " was skipped!");
				System.out.println(message);
				JOptionPane.showMessageDialog(null, message);
				}
			},1000);
		
	}
	
	/**
	 * Metodo che permette di cambiare direzione di gioco grazie alla carta "Reverse Card".
	 */
	public void reverseCardMove()
	{
		
		//se la direzione è true diventa false e viceversa
		if(isDirection())
		{
			setCurrentPlayer((currentPlayer + 1) % playersId.length);
			setDirection(false);
			
		} else if(!isDirection())
		{
			setCurrentPlayer((currentPlayer - 1) % playersId.length);
			if(currentPlayer == -1)
				currentPlayer = playersId.length - 1;
			
			setDirection(true);
		}
		
		new Timer().schedule(new TimerTask(){
			@Override 
			public void run(){
			
				JLabel message = new JLabel("Game direction changed by " + playersId[currentPlayer]+"!");
//					message.setFont(new Font("Arial", Font.BOLD, 30));
		JOptionPane.showMessageDialog(null, message);
				System.out.println(message);

			}
			},1000);		
		
	}
	
	/**
	 * Metodo che permette di pescare n carte dal mazzo di gioco
	 * @param n 
	 */
	public Card[] drawMultipleCardsMove(int n) {
		
		Card[] cards = new Card[n];
		try {
			//il giocatore corrente pesca n carte, le quali vengono aggiunte al suo mazzo
			for (int i = 0; i<n; i++)
				{
					if(!getDeck().isEmpty())		
					{
						//se il mazzo NON è terminato pesca una carta
						Card card = deck.distribuisciCard();
						getPlayersDecks().get(getCurrentPlayer()).add(card);
						cards[i] = card;

					}
					
					//altrimenti prendi il mazzo di scarto, mescola e pesca da quel mazzo
					else {
						
						setDecktoDiscardDeck();					
						deck.shuffle();
						Card card = deck.distribuisciCard();
						getPlayersDecks().get(getCurrentPlayer()).add(card);
						cards[i] = card;
					}
	
				}			
			new Timer().schedule(new TimerTask(){
				@Override 
				public void run(){
				
					JOptionPane.showMessageDialog(null, playersId[currentPlayer] + " drew "+ n+" cards!");
					}
				},1000);
			
			/**
			 * Se il giocatore che ha pescato le carte è il giocatore umano comunichiamo alla view, attraverso il controller che vanno aggiunte n carte al mazzo del giocatore.
			 */
			if(getCurrentPlayer()==0)
				notifyObservers(cards);

			return cards;
			

		} catch (WrongArgumentsException e) {
			e.printStackTrace();
		}
		return cards;
	}
	
	
	public ArrayList<ArrayList<Card>> getPlayersDecks() {
		return playersDecks;
	}

	public Colors getValidColor() {
		return validColor;
	}

	public void setValidColor(Colors validColor) {
		this.validColor = validColor;
	}

	public int getValidValue() {
		return validValue;
	}

	public void setValidValue(int validValue) {
		this.validValue = validValue;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	
	/**
	 * Metodo verifica se è il turno del giocatore in input.
	 * Se l'indice del giocatore in input corrisponde all'indice del giocatore corrente il turno è valido, altrimenti non è il turno del giocatore in input e viene creato un messaggio a schermo per l'utente.
	 * @param player
	 * @throws InvalidTurnException
	 */
	public void checkInvalidTurn(String player) throws InvalidTurnException
	{
		if (getPlayerIndex(player) != currentPlayer)
		{
			//messaggio a schermo per l'utente
			JOptionPane.showMessageDialog(null, "It is not "+ player + " turn!");
		}
	
	}
	
	/**
	 * Metodo per giocare la carta scelta, prima verifica se la carta è compatibile e poi la gioca, passando eventualmente al giocatore successivo.
	 * @throws InvalidCardException 
	 */
	public boolean playCard(String player, Card card) throws InvalidTurnException, InvalidColorException, InvalidValueException, InvalidCardException
	{
		System.out.println("metodo play card");
		//controllo se il turno è del giocatore che vuole giocare la carta
		checkInvalidTurn(player);
				
		//caso 1: carta +4 -> la carta può essere giocata indipendentemente dai valori validValue e validColor
		if(card.getClass().getSimpleName().equals("Draw4Card"))	 
		{
			System.out.println("entro nel +4 play card");

			setValidColor(switch(chooseColorInput(player))
					{
							case "Red" -> Colors.RED;
							case "Blue" -> Colors.BLUE;
							case "Green" -> Colors.GREEN;
							case "Yellow" -> Colors.YELLOW;
							default -> null;
					});
			setValidValue(-1);
			
			//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
			getPlayersDecks().get(getCurrentPlayer()).remove(card);
			
			checkUno();
			isGameOver();
			discardDeck.add(card);
			
			nextPlayerTurn();	//si passa il turno al giocatore successivo
			drawMultipleCardsMove(4);	//il giocatore successivo pesca 4 carte
			
			return true;
			
		}
		//caso 2: carta "cambia colore" -> è una carta valida da giocare
		else if(card.getClass().getSimpleName().equals("ChangeColorCard"))
		{				
			System.out.println("entro nel change color play card");

			setValidColor(switch(chooseColorInput(player))
					{
							case "Red" -> Colors.RED;
							case "Blue" -> Colors.BLUE;
							case "Green" -> Colors.GREEN;
							case "Yellow" -> Colors.YELLOW;
							default -> null;
					});
			setValidValue(-1);	//si setta il valore di gioco a -1
			
			//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
			getPlayersDecks().get(getCurrentPlayer()).remove(card);

			checkUno();
			isGameOver();
			discardDeck.add(card);
			
			nextPlayerTurn();	//si passa il turno al giocatore successivo
			notifyObservers(card);
			
			return true;
		}
		

		//caso in cui la carta è una carta valore
		else if(card.getClass().getSimpleName().equals("ValueCard") && (card.getValue() == validValue || card.getColor().equals(validColor)))
		{
			System.out.println("entro nella carta valore play card");

			setValidColor(card.getColor());	//cambia il colore della partita
			setValidValue(card.getValue());
			//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
			getPlayersDecks().get(getCurrentPlayer()).remove(card);

			checkUno();
			isGameOver();
			discardDeck.add(card);
			
			nextPlayerTurn();
			notifyObservers(card);

			return true;
		}
		//caso 3: caso in cui il colore è valido
		else if (card.getColor().equals(validColor))
		{
			System.out.println("entro nel colore valido play card");

			//Se il colore è valido posso avere una carta +2, una carta valore, una carta SKIP o una carta REVERSE 
			
			//caso 3.1: il colore è valido e la carta è una carta +2
			
			if(card.getClass().getSimpleName().equals("Draw2Card"))
			{
				System.out.println("entro nel colore valido con +2");

				setValidValue(-1);	//la carta +2 non ha valore quindi viene cambiato il valore di gioco
				//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
//				playerCards.remove(card);
				getPlayersDecks().get(getCurrentPlayer()).remove(card);

				discardDeck.add(card);
				
				checkUno();
				isGameOver();
				
				nextPlayerTurn();	//si passa al giocatore successivo
				drawMultipleCardsMove(2);	//il giocatore successivo pesca 2 carte
				
				return true;
			}
			
			//caso 3.2: il colore è valido ed è una carta SKIP
			else if(card.getClass().getSimpleName().equals("SkipCard"))
			{
				System.out.println("entro nel colore valido skip");

				setValidValue(-1);	
				//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
				getPlayersDecks().get(getCurrentPlayer()).remove(card);

				checkUno();
				isGameOver();
				discardDeck.add(card);
				
				skipTurnMove();	//il giocatore successivo viene bloccato			
				nextPlayerTurn();	//si passa il turno al giocatore successivo a quello bloccato
				notifyObservers(card);
				
				return true;

			}
			
			//caso 3.3: in cui il colore è valido e la carta è una "Reverse card"
			else if(card.getClass().getSimpleName().equals("ReverseCard"))
			{
				System.out.println("entro nel colore valido reverse");

				setValidValue(-1);
				
				//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
				getPlayersDecks().get(getCurrentPlayer()).remove(card);

				checkUno();
				isGameOver();
				discardDeck.add(card);
				
				reverseCardMove();	//si cambia la direzione di gioco e si passa il turno al giocatore successivo				
				notifyObservers(card);
				return true;
				
			}
			
		}
		
		//caso 4: la carta non è compatibile e non può essere giocata
		else
		{
			JLabel message = new JLabel("Invalid move: Choose another card!");
			System.out.println(message +" "+validValue +" "+ validColor);
			message.setFont(new Font("Arial", Font.BOLD, 30));
			JOptionPane.showMessageDialog(null, message);		
			
			throw new InvalidCardException(message.getText());
		}
		return false;

		
	}
	/**
	 * Metodo che verifica se uno dei giocatori è arrivato ad una sola carta in mano e in caso scrive un messaggio a schermo per avvertire gli altri.
	 */
	public void checkUno()
	{
		if(getCurrentPlayer() !=0)
		{
			//se rimane solo una carta nel mazzo è necessario dire "UNO"
			if(getPlayerDeckSize(getCurrentPlayerName()) == 1)
				JOptionPane.showMessageDialog(null, "UNO!!!" + playersId[currentPlayer]);
		}
				
	}
	
	/**
	 * Metodo per la scelta del colore da input. Restituisce il colore scelto sotto forma di stringa
	 */
	public String chooseColorInput(String player)
	{
		Object[] possibleColors = {"Red", "Blue", "Green", "Yellow"};
		Object selectedColor = JOptionPane.showInputDialog(null,"Choose one color", "Input", JOptionPane.INFORMATION_MESSAGE, null, possibleColors, possibleColors[0]);
		return (String)selectedColor;
	}
	
	/**
	 * Metodo per passare il turno al giocatore successivo, incrementando/decrementando l'indice del giocatore corrente in base alla direzione di gioco.
	 */
	public void nextPlayerTurn()
	{
		System.out.println("giocatore corrente iniziale:" + getCurrentPlayer());
		if(isDirection())	//se direzione == true avanzo di 1 nella lista dei giocatori
		{
			setCurrentPlayer((getCurrentPlayer() + 1) % getPlayersId().length);		//una volta arrivata alla fine della lista torno all'inizio
			notifyObservers(getCurrentPlayer());
		}
		
		else
		{
			setCurrentPlayer((getCurrentPlayer() - 1) % getPlayersId().length);
			if(currentPlayer == -1)
				currentPlayer = playersId.length - 1;
		}
		
		System.out.println("giocatore corrente finale:" + getCurrentPlayer());

	}
	
	
	/**
	 * Metodo per finire la partita e annunciare il vincitore
	 */
	public void isGameOver()
	{
		//controllo se il mazzo del giocatore è finito -> in quel caso finisce la partita
		if(getPlayerDeckSize(getCurrentPlayerName())==0)
		{
			isGameOver = true;
			JOptionPane.showMessageDialog(null, "THE GAME IS OVER: " + playersId[currentPlayer].toUpperCase() + "\n IS THE WINNER!","Game Over",JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);

		}
		
		
	}
}
