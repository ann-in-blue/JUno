package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.exceptions.InvalidTurnException;

public class ButtonPassaIlTurnoEventListener implements ActionListener
{
	
	private ControllerJUno controllerJUno;


	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			System.out.println("Passa il turno");
			
			controllerJUno.playTurn();
			
		} catch (InvalidTurnException | InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
