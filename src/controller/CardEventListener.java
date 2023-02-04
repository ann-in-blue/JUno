package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.exceptions.InvalidCardException;
import controller.exceptions.InvalidColorException;
import controller.exceptions.InvalidTurnException;
import controller.exceptions.InvalidValueException;
import model.Card;
import model.Game;
import model.cards.ValueCard;

/**
 * Classe che implementa l'interfaccia ActionListener che permette di eseguire le istruzioni corrette in seguito al click di una carta in mano all'utente.
 * @author a-00
 *
 */
public class CardEventListener implements ActionListener
{

	/**
	 * Riferimento alla carta selezionata e al controller di gioco che si occupa di aggiornare la logica e la view.
	 */
	private Card card;
	private ControllerJUno controllerJUno;
	
	/**
	 * Costruttore con parametri.
	 * @param card
	 * @param controllerJUno
	 */
	public CardEventListener(Card card, ControllerJUno controllerJUno)
	{
		this.card = card;
		this.controllerJUno = controllerJUno;
	}
	
	/**
	 * Metodo eseguito quando viene cliccata una carta in mano al giocatore umano.
	 * Chiama il metodo playCard del controller che permette di verificare se la carta selezionata è compatibile con quella sul tavolo e in caso affermativo aggiorna la view con la nuova carta.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{

//			System.out.println(card);
			
			try {
			
				controllerJUno.game.playCard(controllerJUno.game.getCurrentPlayerName(), card);
				controllerJUno.updateView(card, GameState.PLAYING_CARD);
					
					
			} catch (InterruptedException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (InvalidTurnException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidColorException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidValueException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvalidCardException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
	}
//	}
	
	

}
