package controller;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.listeners.PaginaInizialeEventListener;
import model.Game;
import view.FinestraHome;
import view.PaginaIniziale.*;

public class JUno 
{
	/**
	 * Classe che contiene il main del gioco.
	 */
	public static void main(String[] args) {
		
		//creazione della view
		FinestraHome view = new FinestraHome();
		
		//creazione del modello
		Game game = new Game();
				
		//si rende la view osservatrice del modello aggiungendola alla lista dei suoi osservatori
		game.addObserver(view);
		
		
		//aggancio dei listeners ai componenti della view
	
	}

}
