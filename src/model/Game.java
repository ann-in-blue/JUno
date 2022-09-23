package model;

import java.awt.Font;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
public class Game 
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
	
	boolean isGameOver;	//DA VEDERE
	
	
	public Game(String[] playersId) throws WrongArgumentsException
	{
		deck = new Deck(112);	//creazione del mazzo
		deck.shuffle();	//shuffle del mazzo
		discardDeck = new ArrayList<Card>();	//mazzo di scarto
		
		this.playersId = playersId;
		currentPlayer = 0;
		direction = false;
		playersDecks =  new ArrayList<ArrayList<Card>>();
		
		for (int i = 0; i < playersId.length; i++)
		{
			//per ogni giocatore -> array di 7 carte iniziali
			ArrayList<Card> hand = new ArrayList<Card>(Arrays.asList(deck.distribuisciCard(7)));
			playersDecks.add(hand);
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
			JLabel message = new JLabel(playersId[currentPlayer] + " was skipped!");
			message.setFont(new Font("Arial", Font.BOLD, 48));
			JOptionPane.showMessageDialog(null, message);
			
			setValidColor(card.getColor());
			setValidValue(-1);	//non c'è nessun valore valido in questo caso
			
			if(isDirection())	//se direzione == true avanzo di 1 nella lista dei giocatori
				setCurrentPlayer((currentPlayer + 1) % playersId.length);		//una volta arrivata alla fine della lista torno all'inizio
			else
				setCurrentPlayer((currentPlayer - 1) % playersId.length);
			
		}
		
		//caso in cui esce una carta "reverse card"
		if(card.getClass().getSimpleName().equals("ReverseCard"))
		{
			JLabel message = new JLabel("Game direction changed!");
			message.setFont(new Font("Arial", Font.BOLD, 48));
			JOptionPane.showMessageDialog(null, message);
			
			//se la direzione è true diventa false e viceversa
			if(isDirection())
			{
				setCurrentPlayer((currentPlayer + 1) % playersId.length);
				setDirection(false);
			} else {
				setCurrentPlayer((currentPlayer - 1) % playersId.length);
				setDirection(true);
			}
			
			setValidColor(card.getColor());
			setValidValue(-1);	//non c'è nessun valore valido in questo caso
		}
			
			
		//caso in cui esce una carta "Draw2" -> il giocatore alla sinistra del mazzierempesca due carte e salta un giro
		if(card.getClass().getSimpleName().equals("Draw2Card"))
		{
			setCurrentPlayer(++currentPlayer);
			setValidColor(card.getColor());
			setValidValue(-1);
			
			//DA IMPLEMENTARE 
			draw2Cards();
			skipTurn();
			
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
	private void skipTurn() {
		
		if(isDirection())	//se direzione == true avanzo di 1 nella lista dei giocatori
			setCurrentPlayer((getCurrentPlayer() + 1) % getPlayersId().length);		//una volta arrivata alla fine della lista torno all'inizio
		
		else
			setCurrentPlayer((getCurrentPlayer() - 1) % getPlayersId().length);
		
	}
	
	private void draw2Cards() {
		
		try {
			//il giocatore corrente pesca 2 carte, le quali vengono aggiunte al suo mazzo
			getPlayersDecks().get(getCurrentPlayer()).addAll(Arrays.asList(deck.distribuisciCard(2)));
			
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
