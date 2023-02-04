package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.exceptions.InvalidCardException;
import controller.exceptions.InvalidColorException;
import controller.exceptions.InvalidTurnException;
import controller.exceptions.InvalidValueException;
import model.Card;

/**
 * Classe che implementa lìinterfaccia ActionListener e permette di eseguire le azioni specifiche dopo aver cliccato il bottone per passare il turno.
 * @author a-00
 *
 */
public class PassaTurnoEventListener implements ActionListener
{

	private ControllerJUno controllerJUno;
	
	public PassaTurnoEventListener(ControllerJUno controllerJUno)
	{
		this.controllerJUno = controllerJUno;
	}
	
	
	/**
	 * Metodo che contiene tutte le istruzioni da eseguire nel momento in cui l'utente clicca il bottone "Passa il turno".
	 * In particolare invoca il metodo implementato nel controller che si occupa di passare al giocare successivo senza giocare alcuna carta.
	 * Viene cliccato solo quando è il turno del giocatore umano quindi i controlli sul turno sono stati fatti in precedenza.
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		controllerJUno.passaTurno();
		
	}

}
