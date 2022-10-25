package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeckEventListener implements ActionListener
{

	/**
	 * Metodo che si occupa di eseguire le istruzioni necessarie dopo che il giocatore umano ha cliccato il mazzo per pescare una carta.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		ControllerJUno.game.drawMultipleCardsMove(1);
		
		
	}

}
