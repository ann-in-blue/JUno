package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.exceptions.InvalidTurnException;
import model.Game;

public class ButtonUnoEventListener implements ActionListener
{

	private String currentPlayerName;	//riferimento al giocatore a cui spetta il turno
	private Game game;	//riferimento alla partita in corso
	
	public ButtonUnoEventListener(String currentPlayerName, Game game)
	{
		this.currentPlayerName = currentPlayerName;
		this.game = game;
	}
	
	/**
	 * Metodo eseguito quando viene cliccato il bottone UNO da parte del giocatore umano.
	 * Viene fatto un controllo per verificare se è il turno del giocatore umano e poi viene verificato che abbia effettivamente una carta sola nel mazzo.
	 * In quel caso viene stampato a schermo un messaggio.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try {
			game.checkInvalidTurn(currentPlayerName);
			//se rimane solo una carta nel mazzo è necessario dire "UNO"
			if(game.getPlayerDeckSize(currentPlayerName) == 1)
				JOptionPane.showMessageDialog(null, "UNO!!!" + game.getPlayersId()[game.getCurrentPlayer()]);
			
		} catch (InvalidTurnException e1) {
			e1.printStackTrace();
		}
		
	}
	

}
