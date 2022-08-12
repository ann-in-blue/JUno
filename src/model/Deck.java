package model;

import java.util.Random;


public class Deck implements CardFactory
{
	
	private Card[] deck;
	
	
	public Deck() throws WrongArgumentsException
	{
		this.deck = new Card[112];
		
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
	public Card distribuisciCard(int i)
	{
		if (i < deck.length)
			return deck[i++];
		return deck[0];
	}

	
}
