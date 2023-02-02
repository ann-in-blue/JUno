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
					
//					controllerJUno.update(ControllerJUno.game, card);
//					controllerJUno.updateView(card, GameState.PLAYING_CARD);
					controllerJUno.updateView(card, GameState.PLAYING_CARD);
//					notify();

//					Thread.sleep(3000);
					
//					while(controllerJUno.game.getCurrentPlayer() != 0)	
//					{
//						//fino a che non è il turno del giocatore umano fai giocare i giocatori artificiali
//						controllerJUno.playTurn();
//						Thread.sleep(2000);
//
//					}
					
					
				
//			} catch (InvalidTurnException | InvalidColorException | InvalidValueException | InvalidCardException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			
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
