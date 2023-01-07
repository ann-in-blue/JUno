package controller;

import controller.exceptions.WrongArgumentsException;
import model.Game;
import view.PaginaIniziale;

public class JUno 
{	
	
	/**
	 * Classe che contiene il main del gioco.
	 */
	public static void main(String[] args) throws WrongArgumentsException {
		
		ControllerJUno controller = new ControllerJUno();
		Game game = new Game();
		

		//aggancio dei listeners ai componenti della view
		
		//listener della pagina iniziale: bottone per l'invio del nickname
		PaginaIniziale pagina = controller.view.getPaginaIniziale();
		pagina.getButtonNickname().addActionListener(new PaginaInizialeEventListener(pagina.getFieldNickname(),controller, game));		
		
	}

}
