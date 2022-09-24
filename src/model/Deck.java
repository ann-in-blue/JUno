package model;

import java.lang.ModuleLayer.Controller;
import java.util.Random;
import model.Game.*;
import javax.swing.ImageIcon;


public class Deck implements CardFactory
{
	
	Card[] deck;
	protected int currentCard;
	
	
	public Deck(int numeroCarte) throws WrongArgumentsException
	{
		this.deck = new Card[numeroCarte];
		currentCard = 0;
		int ind = 0;

		for (Color color : Color.values())
		{
			//indice per scorrere il mazzo
			//primo for che va da 0 a 9
			for (int i = 0; i < 10; i++)
			{
				deck[i] = createCard(Type.VALUE_CARD, color, i, "images/"+color.name().toLowerCase()+i+".png");
				//System.out.println(color.name()+" "+ i+ " "+"images/"+color.name().toLowerCase()+i+".png");
				ind++;
			}
			
			//secondo for che va da 1 a 9
			for (int j = 1; j < 10; j++)
			{
				deck[ind] = createCard(Type.VALUE_CARD, color, j, "images/"+color.name().toLowerCase()+j+".png");
//				System.out.println(color.name()+" "+ j+ " "+"images/"+color.name().toLowerCase()+j+".png");
				ind++; //incremento l'indice
			}

			for (int i = 0; i < 2; i++)
			{
				//2 carte pesca 2
				deck[ind++] = createCard(Type.DRAW_2_CARD, color, "images/"+color.name().toLowerCase()+"+2.png");
				//2 carte cambia giro
				deck[ind++] = createCard(Type.REVERSE_CARD, color, "images/"+color.name().toLowerCase()+"REVERSE.png");
				//2 carte blocc un turno
				deck[ind++] = createCard(Type.SKIP_CARD, color, "images/"+color.name().toLowerCase()+"SKIP.png");
			}
			
		}
		//4 carte pesca 4 e 4 carte cambia colore
		for (int i = 0; i<3; i++)
		{
			createCard(Type.CHANGE_COLOR_CARD, "images/changeColor.png");
			createCard(Type.DRAW_4_CARD, "images/draw4.png");
		}
		
		
	}
	
	public Card[] getDeck()
	{
		return deck;
	}
	
	
	public void shuffle()
	{
		Random rand = new Random();
		
		for (int i = 0; i < deck.length; i++)
		{
			int randomSwap = rand.nextInt(deck.length);
			
			/**
			 * utilizzo una carta random come appoggio per fare lo swap tra due carte.
			 */
			Card temp = deck[randomSwap];
			deck[randomSwap] = deck[i];
			deck[i] = temp;
		}
		
	}
	/**
	 * metodo che distribuisce la prossima carta del mazzo.
	 * @param i
	 * @return
	 */
	public Card distribuisciCard()
	{
		if (currentCard < deck.length)
			return deck[currentCard++];
		
		//se il mazzo è finito si mischia di nuovo il mazzo e si continua a giocare
		else {
			shuffle();
			currentCard = 0;
			return deck[currentCard];
		}
	}
	public int getCurrentCard() {
		return currentCard;
	}

	public void setCurrentCard(int currentCard) {
		this.currentCard = currentCard;
	}

	public void setDeck(Card[] deck) {
		this.deck = deck;
	}

	/**
	 * metodo che distribuisce la prossima carta del mazzo.
	 * @param i
	 * @return
	 */
	public ImageIcon mostraCard()
	{
		if (currentCard < deck.length)
			//return deck[currentCard++];
			return new ImageIcon(deck[currentCard++].toString() + ".png");
		
		//se il mazzo è finito si mischia di nuovo il mazzo e si continua a giocare -> DA FARE, mischiare mazzo senza le carte in mano ai giocatori
		else {
			shuffle();
			currentCard = 0;
			return new ImageIcon(deck[currentCard++].toString() + ".png");
			//return deck[currentCard];
		}
	}
	
	/**
	 * Metodo che permette di distribuire più carte insieme (esempio con un +2, +4).
	 * Il numero in input è il numero di carte da distribuire.
	 * @throws WrongArgumentsException 
	 */
	public Card[] distribuisciCard(int n) throws WrongArgumentsException
	{
		if (n < 0)
			throw new WrongArgumentsException("Il numero di carte da distribuire non è valido");
		
		Card[] res = new Card[n];
		for(int i = 0; i< n; i++)
			res[i] = distribuisciCard();
				
		return res;
	}
	
	
	/**
	 * Metodo che restituisce true se il mazzo è vuoto, false altrimenti
	 * @return
	 */
	public boolean isEmpty()
	{
		return this.deck.length == 0;
	}

	
}
