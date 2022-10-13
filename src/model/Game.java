package model;

import java.awt.Font;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.exceptions.InvalidCardException;
import model.exceptions.InvalidColorException;
import model.exceptions.InvalidTurnException;
import model.exceptions.InvalidValueException;
import model.exceptions.WrongArgumentsException;


public class Game extends Observable
{
	//campi per salvare i giocatori
	private int currentPlayer;
	private String[] playersId;
	
	//campi per i mazzi
	private Deck deck;
	
	private ArrayList<ArrayList<Card>> playersDecks;	//carte in mano ai giocatori
	private ArrayList<Card> discardDeck;	//mazzo di scarto
	
	private Color validColor;
	private int validValue;
	
	boolean direction;	//senso orario o senso antiorario
	boolean isGameOver;	
	
	
	public Game() 
	{
		playersId = new String[4];
	}
	
	public void startGame()
	{
		try
		{
			deck = new Deck(112);	//creazione del mazzo
			deck.shuffle();	//shuffle del mazzo
			discardDeck = new ArrayList<Card>();	//mazzo di scarto
			
			currentPlayer = 0;
			direction = false;
			playersDecks =  new ArrayList<ArrayList<Card>>();
			
			for (int i = 0; i < playersId.length; i++)
				{
				//per ogni giocatore -> array di 7 carte iniziali
				ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.distribuisciCard(7)));
				playersDecks.add(hand);
			}
		}catch(WrongArgumentsException wae)
		{
			wae.printStackTrace();
		}
		
	}
	//GETTERS E SETTERS
	
	public String getCurrentPlayerName() {
		return playersId[currentPlayer];
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
	
	public ArrayList<ArrayList<Card>> getPlayerDecks() {
		return playersDecks;
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
	
	
	
	
	//metodo per iniziare la partita - SPOSTARE NELL CONTROLLER
	public void start(Game game)
	{
		Card card = deck.distribuisciCard();
		
		//caso in cui la prima carta è una "pesca 4" -> viene rimessa nel mazzo e si pesca la carta successiva
		if(card.getClass().getSimpleName().equals("Draw4Card"))	
		{
			start(game);	//ricominciamo pescando la carta successiva
		}
		
		//caso in cui esce una carta "cambia colore" -> il giocatore alla sinistra del mazziere sceglie il colore da giocare
		if(card.getClass().getSimpleName().equals("ChangeColorCard"))
		{
			currentPlayer = 1;
			Object[] possibleColors = {"Red", "Blue", "Green", "Yellow"};
			Object selectedColor = JOptionPane.showInputDialog(null,"Choose one color", "Input", JOptionPane.INFORMATION_MESSAGE, null, possibleColors, possibleColors[0]);
			
			setValidColor(switch((String)selectedColor)
					{
							case "Red" -> Color.RED;
							case "Blue" -> Color.BLUE;
							case "Green" -> Color.GREEN;
							case "Yellow" -> Color.YELLOW;
							default -> null;
					});
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
		
		//aggiungiamo la carta 
		discardDeck.add(card);
		
	}

	/**
	 * Metodo che blocca il giocatore corrente per un turno
	 */
	private void skipTurnMove() {
		
		if(isDirection())	//se direzione == true avanzo di 1 nella lista dei giocatori
			setCurrentPlayer((getCurrentPlayer() + 1) % getPlayersId().length);		//una volta arrivata alla fine della lista torno all'inizio
		
		else if (!isDirection())
		{
			setCurrentPlayer((getCurrentPlayer() - 1) % getPlayersId().length);
			if(currentPlayer == -1)	//controllo nel caso in cui con il modulo sia uscito come risultato -1
				currentPlayer = playersId.length - 1;
		}
		
		JLabel message = new JLabel(playersId[currentPlayer] + "was skipped!");
		message.setFont(new Font("Arial", Font.BOLD, 48));
		JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * Metodo che permette di cambiare direzione di gioco grazie alla carta "Reverse Card"
	 */
	public void reverseCardMove()
	{
		JLabel message = new JLabel("Game direction changed by " + playersId[currentPlayer]+"!");
		message.setFont(new Font("Arial", Font.BOLD, 48));
		JOptionPane.showMessageDialog(null, message);
		
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

		
	}
	
	/**
	 * Metodo che permette di pescare n carte
	 * @param n
	 */
	private void drawMultipleCardsMove(int n) {
		
		try {
			//il giocatore corrente pesca n carte, le quali vengono aggiunte al suo mazzo
			for (int i = 0; i<n; i++)
				{
					if(!getDeck().isEmpty())		//se il mazzo NON è terminato pesca una carta
						getPlayersDecks().get(getCurrentPlayer()).add(deck.distribuisciCard());
					
					//altrimenti prendi il mazzo di scarto, mescola e pesca da quel mazzo
					else {
						
						setDecktoDiscardDeck();					
						deck.shuffle();
						getPlayersDecks().get(getCurrentPlayer()).add(deck.distribuisciCard());
					}
	
				}
			JOptionPane.showMessageDialog(null, playersId[currentPlayer] + "drew "+ n+" cards!");

		} catch (WrongArgumentsException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<ArrayList<Card>> getPlayersDecks() {
		return playersDecks;
	}

	public void setPlayersDecks(ArrayList<ArrayList<Card>> playersDecks) {
		this.playersDecks = playersDecks;
	}

	public Color getValidColor() {
		return validColor;
	}

	public void setValidColor(Color validColor) {
		this.validColor = validColor;
	}

	public int getValidValue() {
		return validValue;
	}

	public void setValidValue(int validValue) {
		this.validValue = validValue;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	public boolean isDirection() {
		return direction;
	}

	public void setDirection(boolean direction) {
		this.direction = direction;
	}
	
	//METODI PER IL CONTROLLO DELLE MOSSE DI GIOCO
	public boolean isValidCardToPlay(Card card)
	{
		//se il colore è valido oppure se il valore della carta è lo stesso
		return card.getColor().equals(validColor) || card.getValue() == validValue;
		
		
	}

	public void checkInvalidTurn(String player) throws InvalidTurnException
	{
		if (getPlayerIndex(player) == currentPlayer)
			throw new InvalidTurnException("It is not "+ player + " turn!", player);
		//se l'indice del giocatore in input corrisponde all'indice del giocatore corrente il turno è valido, altrimenti non è il turno del giocatore in input.
	}
	
	/**
	 * Metodo per giocare la carta scelta
	 * @throws InvalidCardException 
	 */
	public void playCard(String player, Card card, Color color) throws InvalidTurnException, InvalidColorException, InvalidValueException, InvalidCardException
	{
		//controllo se il turno è del giocatore che vuole vuole giocare la carta
		checkInvalidTurn(player);
		
		//carte in mano al giocatore selezionato
		ArrayList<Card> playerCards = getPlayerDeck(player);
		
		//caso in cui la carta NON è valida -> nel senso che non ha stesso colore oppure stesso valore
		if(!isValidCardToPlay(card))
		{
			//3 casi possibili
			
			//caso 1: carta +4 -> la carta può essere giocata
			if(card.getClass().getSimpleName().equals("Draw4Card"))	 
			{
				setValidColor(switch(chooseColorInput(player))
						{
								case "Red" -> Color.RED;
								case "Blue" -> Color.BLUE;
								case "Green" -> Color.GREEN;
								case "Yellow" -> Color.YELLOW;
								default -> null;
						});
				setValidValue(-1);
				nextPlayerTurn();	//si passa il turno al giocatore successivo
				drawMultipleCardsMove(4);	//il giocatore successivo pesca 4 carte
				
			}
			//caso 2: carta "cambia colore" -> è una carta valida da giocare
			else if(card.getClass().getSimpleName().equals("ChangeColorCard"))
			{				
				setValidColor(switch(chooseColorInput(player))
						{
								case "Red" -> Color.RED;
								case "Blue" -> Color.BLUE;
								case "Green" -> Color.GREEN;
								case "Yellow" -> Color.YELLOW;
								default -> null;
						});
				setValidValue(-1);	//si setta il valore di gioco a -1
				nextPlayerTurn();	//si passa il turno al giocatore successivo
			}
			//caso 3: la carta non è compatibile e non può essere giocata
			else
			{
				JLabel message = new JLabel("Invalid move: card played: "+ card.getValue() +" "+ card.getColor() + " with valid data (" + validValue +", "+validColor+")\n Choose another card!");
				message.setFont(new Font("Arial", Font.BOLD, 50));
				JOptionPane.showMessageDialog(null, message);
				throw new InvalidCardException(message.getText());
			}
			
		}
		
		//caso in cui il colore è valido ed è una carta +2
		else if (card.getColor().equals(validColor) && card.getClass().getSimpleName().equals("Draw2Card"))
		{
			setValidValue(-1);	//la carta +2 non ha valore quindi viene cambiato il valore di gioco
			nextPlayerTurn();	//si passa al giocatore successivo
			drawMultipleCardsMove(2);	//il giocatore successivo pesca 2 carte
			
		}
		
		//caso in cui il colore è valido ed è una carta skip
		else if (card.getColor().equals(validColor) && card.getClass().getSimpleName().equals("SkipCard"))
		{
			setValidValue(-1);	
			skipTurnMove();	//il giocatore successivo viene bloccato			
			nextPlayerTurn();	//si passa il turno al giocatore successivo a quello bloccato

			
		}
		
		//caso in cui il colore è valido e la carta è una "Reverse card"
		else if(card.getColor().equals(validColor) && card.getClass().getSimpleName().equals("ReverseCard"))
		{
			setValidValue(-1);
			reverseCardMove();	//si cambia la direzione di gioco e si passa il turno al giocatore successivo
		}
		
		//caso in cui la carta è una carta valore
		else if(card.getClass().getSimpleName().equals("ValueCard") && card.getValue() == validValue)
		{
			setValidColor(card.getColor());	//cambia il colore della partita
			nextPlayerTurn();
		}
		
		//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
		playerCards.remove(card);
		discardDeck.add(card);
		
		//se rimane solo una carta nel mazzo è necessario dire "UNO"
		if(getPlayerDeckSize(player) == 1)
			JOptionPane.showMessageDialog(null, "UNO!!!" + playersId[currentPlayer]);
		
		
		//controllo se il mazzo del giocatore è finito -> in quel caso finisce la partita
		if(playerCards.isEmpty())
			isGameOver();
		
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
	 * Metodo per passare il turno al giocatore successivo
	 */
	public void nextPlayerTurn()
	{
		if(isDirection())	//se direzione == true avanzo di 1 nella lista dei giocatori
			setCurrentPlayer((getCurrentPlayer() + 1) % getPlayersId().length);		//una volta arrivata alla fine della lista torno all'inizio
		else
		{
			setCurrentPlayer((getCurrentPlayer() - 1) % getPlayersId().length);
			if(currentPlayer == -1)
				currentPlayer = playersId.length - 1;
		}
		
	}
	
	/**
	 * Metodo per verificare se un mazzo è vuoto e quindi se la partita è finita
	 * @return true se è vuoto, false altrimenti
	 */
	public void isGameOver()
	{
		isGameOver = true;
		JOptionPane.showMessageDialog(null, "The Game is Over: " + playersId[currentPlayer] + "is the winner!",
											"Game Over",JOptionPane.INFORMATION_MESSAGE);
		
	}
}
