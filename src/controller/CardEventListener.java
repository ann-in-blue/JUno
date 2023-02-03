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

public class CardEventListener implements ActionListener
{

	private Card card;
	private ControllerJUno controllerJUno;
	
	public CardEventListener(Card card, ControllerJUno controllerJUno)
	{
		this.card = card;
		this.controllerJUno = controllerJUno;
	}
	/**
	 * Metodo eseguito quando viene cliccata una carta in mano al giocatore umano.
	 * Si apre una finestra di conferma e viene giocata la carta selezionata.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{

			System.out.println(card);
			System.out.println(card.getColor());
			
			try {
				
					controllerJUno.game.playCard(controllerJUno.game.getCurrentPlayerName(), card);
				
					System.out.println("true check");
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
