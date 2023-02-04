package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Card;
import model.Game;

/**
 * Classe che implementa l'interfaccia ActionListener e definisce le istruzioni da eseguire una volta cliccata il mazzo coperto sul tavolo.
 * @author a-00
 *
 */
public class DeckEventListener implements ActionListener
{

	/**
	 * Riferimento al controller di gioco.
	 */
	private ControllerJUno controllerJUno;

	public DeckEventListener(ControllerJUno controllerJUno)
	{
		this.controllerJUno = controllerJUno;
	}

	/**
	 * Metodo che si occupa di eseguire le istruzioni necessarie dopo che il giocatore umano ha cliccato il mazzo per pescare una carta.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		//Recupero della carta in cima al mazzo
		Card card = ControllerJUno.game.drawMultipleCardsMove(1)[0];
		
		//se è il turno del giocatore umano aggiunge la carta al mazzo scoperto
		try {
			controllerJUno.updateView(card, GameState.DRAWING_CARD);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		


		
		
	}

}
