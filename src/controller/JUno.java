package controller;

import controller.exceptions.WrongArgumentsException;
import model.Game;
import view.PaginaIniziale;

/**
 * Classe che contiene il main del gioco.
 * @author a-00
 *
 */
public class JUno 
{	

	public static void main(String[] args) throws WrongArgumentsException {
		
		ControllerJUno controller = new ControllerJUno();
		Game game = new Game();
		//Si rende la view osservatrice del modello aggiungendola alla lista dei suoi osservatori, attraverso la classe controllerJUno che fa da ponte tra l'entità game e l'entità view.
		game.addObserver(controller);
		

		/**
		 * Aggancio dei listeners ai componenti della view della pagina iniziale: bottone per l'invio del nickname
		 */
		PaginaIniziale pagina = controller.view.getPaginaIniziale();
		pagina.getButtonNickname().addActionListener(new PaginaInizialeEventListener(pagina.getFieldNickname(),controller, game));		
		
	}

}
