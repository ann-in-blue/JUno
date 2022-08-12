package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Grafica1 extends JFrame 
{
	private Image image;
	private PannelloGiocatore pannelloGiocatore1;
	private PannelloGiocatore pannelloGiocatore2;
	private PannelloGiocatore pannelloGiocatore3;
	private PannelloGiocatore pannelloGiocatore4;

	
	public Grafica1()
	{
		super("JUno");
//		try {
//			//carica l'immagine 
//			image= ImageIO.read(new File("images/table.jpg"));
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
		
//		setLayout(new BorderLayout());
		setLayout(new GridBagLayout());

		pannelloGiocatore1 = new PannelloGiocatore(450, 150, "Player 1", 5,5);
		pannelloGiocatore2 = new PannelloGiocatore(250, 100, "Player 2", 5, 5);
		pannelloGiocatore3 = new PannelloGiocatore(450, 150, "Player 3", 5,5);
		pannelloGiocatore4 = new PannelloGiocatore(250, 100, "Player 4", 5, 5);

//		add(pannelloGiocatore1, BorderLayout.SOUTH);
//		add(pannelloGiocatore2, BorderLayout.LINE_START);
		//add(pannelloGiocatore3, BorderLayout.NORTH);
//		add(pannelloGiocatore4, BorderLayout.LINE_END);

//
		GridBagConstraints gbc = new GridBagConstraints();
		
		//pannello giocatore 3
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
//		gbc.anchor = GridBagConstraints.RELATIVE;
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(pannelloGiocatore3, gbc);
		
//pannello giocatore 4
		gbc.gridx = 2;	//mette il componente a destra di quello precedente
		gbc.gridy = 1;
		
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.fill = GridBagConstraints.BOTH;	//occupa tutto lo spazio disponibile in altezza e in larghezza.
//		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(0, 0, 0, 5);

		add(pannelloGiocatore4, gbc);
		
		//pannello giocatore 2
		gbc.gridx = 0;	//inizio seconda riga
		gbc.gridy = 1;
		
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
//		gbc.gridwidth = 2;
//		gbc.gridheight = 1;
//		gbc.anchor = GridBagConstraints.LINE_END;
		gbc.fill = GridBagConstraints.BOTH;	//occupa tutto lo spazio disponibile in altezza e in larghezza.
		add(pannelloGiocatore2, gbc);
		
		//pannello giocatore 1
		gbc.gridx = 1;
		gbc.gridy = 2;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
//		gbc.anchor = GridBagConstraints.LINE_START;

		add(pannelloGiocatore1, gbc);
		

		setSize(1000, 700);

		setLocationRelativeTo(null);	//mette la finestra al centro dello schermo
		
		//per far si che schiacciando il tasto chiudi si interrompa l esecuzione
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//per rendere visibile la finestra
		setVisible(true);
		
	}
	//ridisegnamo la finestra
//		@Override
//		public void paint(Graphics g)
//		{
//			Graphics2D g2d=(Graphics2D)g;
//			//versione evoluta di Graphics
//			
//			AffineTransform identity = new AffineTransform();
//			AffineTransform trans = new AffineTransform();
//			trans.setTransform(identity);
//			g2d.drawImage(image, trans, this);
//			
//			
//			
//		}


}
