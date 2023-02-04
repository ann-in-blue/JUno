package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class FinestraHome extends JFrame 
{	
	private PaginaIniziale paginaIniziale;
	
	public PaginaIniziale getPaginaIniziale() {
		return paginaIniziale;
	}

	/**
	 * Costruttore della finestra
	 */
	public FinestraHome() 	{
		
		super("JUno");
		/**
		 * Inizializzazione dei vari elementi della finestra
*/
		paginaIniziale = new PaginaIniziale();
	
		setLayout(new BorderLayout());
		setSize(1100, 600);
		setLocationRelativeTo(null);	//mette la finestra al centro dello schermo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		 // Aggiungo i componenti alla finestra
		add(paginaIniziale, BorderLayout.CENTER);
	}
}
