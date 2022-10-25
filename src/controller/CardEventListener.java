package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.exceptions.InvalidCardException;
import controller.exceptions.InvalidColorException;
import controller.exceptions.InvalidTurnException;
import controller.exceptions.InvalidValueException;
import model.Card;
import model.Game;

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
	 * Si apre una finestra di conferma e vien giocata la carta selezionata.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		System.out.println(card);
		try {
			
			if(controllerJUno.game.playCard(controllerJUno.game.getCurrentPlayerName(), card))
			{
				controllerJUno.update(controllerJUno.game, card);
				
				for(int i = 1; i<4; i++)
				{
					//turno del giocatore artificiale
					Card card = controllerJUno.game.playCardArtificial(i);
					System.out.println("carta giocata ai:"+card);
					
					controllerJUno.update(controllerJUno.game, card);
					controllerJUno.viewGioco.getPannelloGiocatoreUmano().validate();
				}
			}
				
			
		} catch (InvalidTurnException | InvalidColorException | InvalidValueException | InvalidCardException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	

}
