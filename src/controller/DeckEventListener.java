package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Card;
import model.Game;

public class DeckEventListener implements ActionListener
{

	private ControllerJUno controllerJUno;
//	public Game game;
	public DeckEventListener(ControllerJUno controllerJUno)
	{
//		this.card = card;
		this.controllerJUno = controllerJUno;
	}

	/**
	 * Metodo che si occupa di eseguire le istruzioni necessarie dopo che il giocatore umano ha cliccato il mazzo per pescare una carta.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		Card card = ControllerJUno.game.drawMultipleCardsMove(1)[0];
		System.out.println("Carta pescata: "+ card);
		
		//se è il turno del giocatore umano fai vedere la carta

//		controllerJUno.update(controllerJUno.game, card);
//		controllerJUno.game.getPlayersDecks().get(controllerJUno.game.getCurrentPlayer()).add(card);
		controllerJUno.updateView(card, GameState.DRAWING_CARD);
		



		
		
	}

}
