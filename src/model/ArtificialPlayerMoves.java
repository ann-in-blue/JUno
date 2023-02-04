package model;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.exceptions.InvalidTurnException;

public class ArtificialPlayerMoves implements AIMoves
{
	
	protected Game g;
	
	public ArtificialPlayerMoves(Game g)
	{
		this.g = g;
	}

	
	/**
	 * Metodo che si occupa di scegliere la carta da giocare per i 3 giocatori artificiali.
	 * Grazie ad un numero casuale viene scelta la strategia per scegliere una delle carte presenti nei mazzi dei giocatori artificiali.
	 * @param id
	 * @return
	 * @throws InvalidTurnException 
	 */
	public Card playCardArtificial(int id) throws InvalidTurnException
	{
		//controllo se il turno è del giocatore che vuole giocare la carta
		g.checkInvalidTurn(g.getPlayersId()[id]);
				
		//carte in mano al giocatore artificiale
		ArrayList<Card> artificialPlayerCards = g.getPlayerDeck(g.getPlayersId()[id]);
		
		//numero random per la scelta della strategia di gioco
		Random rand = new Random();		
		
		int random = rand.nextInt(4);
		
		return switch(random) {
		case 0 -> chooseCard0(artificialPlayerCards, id);
		case 1-> chooseCard1(artificialPlayerCards, id);
		case 2 -> chooseCard2(artificialPlayerCards, id);
		default -> chooseCard0(artificialPlayerCards, id);
		};

	}
	/**
	 * Metodo che scorre la lista delle carte del giocatore artificiale e restituisce la prima carta +4 o cambiaColore che trova.
	 * Altrimenti chiama il metodo chooseCard1().
	 * @param artificialPlayerCards : array di carte in mano al giocatore artificiale
	 * @return la carta giocata dal giocatore artificiale
	 */
	public Card chooseCard0(ArrayList<Card> artificialPlayerCards, int id)
	{
		for (Card c: artificialPlayerCards)
		{
			if(c.getClass().getSimpleName().equals("Draw4Card"))	 
			{
				g.setValidColor(chooseColorArtificial());
				g.setValidValue(-1);
				
				//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
				//artificialPlayerCards.remove(c);
				g.getPlayersDecks().get(g.getCurrentPlayer()).remove(c);

				g.checkUno();
				g.isGameOver();
				g.getDiscardDeck().add(c);
				
				g.nextPlayerTurn();	//si passa il turno al giocatore successivo
				g.drawMultipleCardsMove(4);	//il giocatore successivo pesca 4 carte
				
				//messaggio a schermo
				JLabel message = new JLabel("Carta +4 giocata; \n Nuovo colore: " + g.getValidColor()+"!");
				JOptionPane.showMessageDialog(null, message);
				
				g.notifyObservers(c);

				return c;
			}
			else if(c.getClass().getSimpleName().equals("ChangeColorCard"))
			{				
				g.setValidColor(chooseColorArtificial());
				
				g.setValidValue(-1);	//si setta il valore di gioco a -1
				
				//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
				//artificialPlayerCards.remove(c);
				g.getPlayersDecks().get(g.getCurrentPlayer()).remove(c);

				g.checkUno();
				g.isGameOver();
				g.getDiscardDeck().add(c);
				
				g.nextPlayerTurn();	//si passa il turno al giocatore successivo
				
				//messaggio a schermo
				JLabel message = new JLabel("Carta changeColor giocata; \n Nuovo colore: " + g.getValidColor()+"!");
				JOptionPane.showMessageDialog(null, message);
				
				g.notifyObservers(c);
				
			
				return c;
			}
		}
		return chooseCard1(artificialPlayerCards, id);
	}
	
	/**
	 * Metodo che permette, in base alle carte in mano al giocatore artificiale, di giocare una carta dello stesso colore di quella presente sul tavolo di gioco.
	 * Se non c'è una carta dello stesso colore richiama il metodo chooseCard2() che sceglierà in base al valore della carta sul tavolo.
	 * 
	 * @param artificialPlayerCards
	 * @return carta giocata dal giocatore artificiale
	 */
	public Card chooseCard1(ArrayList<Card> artificialPlayerCards, int id)
	{
		for (Card c: artificialPlayerCards)
		{
			if(!c.getClass().getSimpleName().equals("Draw4Card") ||c.getClass().getSimpleName().equals("ChangeColorCard"))
			{
				
//				if (c.getColor().equals(validColor))
//				{
					//Se il colore è valido posso avere una carta +2, una carta valore, una carta SKIP o una carta REVERSE 
					
					//se il colore è valido e la carta è una carta +2:
					
					if(c.getClass().getSimpleName().equals("Draw2Card") && c.getColor().equals(g.getValidColor()))
					{
						g.setValidValue(-1);	//la carta +2 non ha valore quindi viene cambiato il valore di gioco
						//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
	//					artificialPlayerCards.remove(c);
						g.getPlayersDecks().get(g.getCurrentPlayer()).remove(c);
	
						g.getDiscardDeck().add(c);
						g.checkUno();
						g.isGameOver();
						
						g.nextPlayerTurn();	//si passa al giocatore successivo
						g.drawMultipleCardsMove(2);	//il giocatore successivo pesca 2 carte
						
						return c;
					}
					
					//caso in cui il colore è valido ed è una carta SKIP
					else if(c.getClass().getSimpleName().equals("SkipCard") && c.getColor().equals(g.getValidColor()))
					{
						g.setValidValue(-1);	
						//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
	//					artificialPlayerCards.remove(c);
						g.getPlayersDecks().get(g.getCurrentPlayer()).remove(c);
	
						g.checkUno();
						g.isGameOver();
						g.getDiscardDeck().add(c);
						
						g.skipTurnMove();	//il giocatore successivo viene bloccato			
						g.nextPlayerTurn();	//si passa il turno al giocatore successivo a quello bloccato
						
						g.notifyObservers(c);
						
						return c;
	
					}
					
					//caso in cui il colore è valido e la carta è una "Reverse card"
					else if(c.getClass().getSimpleName().equals("ReverseCard") && c.getColor().equals(g.getValidColor()))
					{
						g.setValidValue(-1);
						
						//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
	//					artificialPlayerCards.remove(c);
						g.getPlayersDecks().get(g.getCurrentPlayer()).remove(c);
	
						g.checkUno();
						g.isGameOver();
						g.getDiscardDeck().add(c);
						
						g.reverseCardMove();	//si cambia la direzione di gioco e si passa il turno al giocatore successivo
						
						g.notifyObservers(c);
	
						return c;
						
					}
//				}	
			}
		}
		return chooseCard2(artificialPlayerCards, id);
	}
	
	/**
	 * Metodo che permette al giocatore artificiale di giocare una carta dello stesso valore della carta presente sul tavolo di gioco.
	 * Nel caso non ce ne fosse nessuna richiama il metodo chooseFirstAvailableCard().
	 * @param artificialPlayerCards
	 * @return carta giocata dal giocatore artificiale
	 */
	public Card chooseCard2(ArrayList<Card> artificialPlayerCards, int id)
	{
		for (Card c: artificialPlayerCards)
		{
			if(c.getClass().getSimpleName().equals("ValueCard") && (c.getValue() == g.getValidValue() || c.getColor().equals(g.getValidColor())))
			{
				g.setValidColor(c.getColor());	//cambia il colore della partita
				g.setValidValue(c.getValue());
				//togliamo la carta dal mazzo del giocatore che l'ha giocata e la mettiamo nel mazzo di scarto
//				artificialPlayerCards.remove(c);
				g.getPlayersDecks().get(g.getCurrentPlayer()).remove(c);

				g.checkUno();
				g.isGameOver();
				g.getDiscardDeck().add(c);
				g.nextPlayerTurn();
				
				g.notifyObservers(c);

				return c;
			}
		}
		drawCard();
		System.out.println("\nNessuna carta utile da giocare. Pesco:\n");
		JOptionPane.showMessageDialog(null, "CARTA PESCATA ","carta pescata",JOptionPane.INFORMATION_MESSAGE);

		g.nextPlayerTurn();	//si passa il turno al giocatore successivo a quello bloccato
		return g.getDiscardDeckTopCard();

	}
	

	/**
	 * Metodo che permette al giocatore artificiale di pescare una carta dal mazzo.
	 * @return carta pescata dal mazzo
	 */
	public Card drawCard()
	{
		return g.drawMultipleCardsMove(1)[0];
	}
	/**
	 * Metodo che permette ai giocatori artificiali, grazie ad un numero random, di selezionare uno dei 4 colori da giocare.
	 * @return colore random
	 */
	public Colors chooseColorArtificial()
	{
		Random rand = new Random();		
		
		int random = rand.nextInt(5);
		
		return switch(random) {
		case 0 -> Colors.BLUE;
		case 1-> Colors.GREEN;
		case 2 -> Colors.RED;
		case 3 -> Colors.YELLOW;
		default -> Colors.BLUE;
		};

	}
	
}
