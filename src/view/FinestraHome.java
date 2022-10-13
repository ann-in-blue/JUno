package view;

import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class FinestraHome extends JFrame implements Observer
{	
	
	/**
	 * Costruttore della finestra
	 */
	public FinestraHome() 	{
		
		super("JUno");
		/**
		 * Inizializzazione dei vari elementi della finestra
*/
		PaginaIniziale paginaIniziale = new PaginaIniziale();
	
		setLayout(new BorderLayout());
		setSize(1200, 700);
		setLocationRelativeTo(null);	//mette la finestra al centro dello schermo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		
		 // Aggiungo i componenti alla finestra
		 
		add(paginaIniziale, BorderLayout.CENTER);
//		pack();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	//ridisegnamo la finestra
		@Override
		public void paint(Graphics g)
		{
//			Graphics2D g2d=(Graphics2D)g;			
			
			
		}
		
		public void refresh()
		{
			this.repaint();
			
		}
}
