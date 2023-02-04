package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.exceptions.InvalidTurnException;
import model.Game;

/**
 * Classe che implementa l'interfaccia ActionListener che permette di eseguire le istruzioni specifiche
 *  in caso venga cliccato il tasto UNO.
 * 
 * @author a-00
 *
 */
public class ButtonUnoEventListener implements ActionListener
{

	/**
	 * Riferimento al giocatore a cui spetta il turno
	 */
	private String currentPlayerName;	
	/**
	 * Rifermento alla partita in corso
	 */
	private Game game;	
	
	
	/**
	 * Costruttore della classe.
	 * @param currentPlayerName
	 * @param game
	 */
	public ButtonUnoEventListener(String currentPlayerName, Game game)
	{
		this.currentPlayerName = currentPlayerName;
		this.game = game;
	}
	
	/**
	 * Metodo eseguito quando viene cliccato il bottone UNO da parte del giocatore umano.
	 * Viene fatto un controllo per verificare se è il turno del giocatore umano e poi viene verificato che abbia effettivamente una carta sola nel mazzo.
	 * In quel caso viene stampato a schermo un messaggio.
	 * Nel caso in cui il giocatore abbia più di 1 carta viene stampato un messaggio di errore.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		try {
			/**
			 * Si controlla che sia effettivamente il turno dell'utente.
			 */
			game.checkInvalidTurn(currentPlayerName);

			if(game.getPlayerDeckSize(currentPlayerName) == 1)
			{
				JOptionPane.showMessageDialog(null, "UNO!!!" + game.getPlayersId()[game.getCurrentPlayer()]);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Non è possibile chiamare UNO: numero di carte sbagliato");

			}
			
		} catch (InvalidTurnException e1) {
			e1.printStackTrace();
		}
		
	}
	

}
