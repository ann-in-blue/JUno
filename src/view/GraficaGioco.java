package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GraficaGioco extends JFrame 
{
	private JButton buttonDeck;
	private JButton buttonDiscardDeck;
	private JPanel panelDecks;
	private PannelloGiocatore pannelloGiocatore1;
	private PannelloGiocatore pannelloGiocatore2;
	private PannelloGiocatore pannelloGiocatore3;
	private PannelloGiocatore pannelloGiocatore4;
	private ArrayList<JButton> cardButtons;

	
	//prova bottone immagine
	BufferedImage buttonIcon;
	
	public GraficaGioco(String[] players)
	{
		super("JUno");
				
		setLayout(new GridBagLayout());

		//prova
		try {
			buttonIcon = ImageIO.read(new File("small/card_back.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		panelDecks = new JPanel();
		buttonDeck = new JButton(new ImageIcon(buttonIcon));
//		buttonDeck.setSize(new Dimension(50,50));
		buttonDiscardDeck = new JButton();
		
		panelDecks.add(buttonDeck);
		panelDecks.add(buttonDiscardDeck);

		pannelloGiocatore1 = new PannelloGiocatore(450, 150, players[0], 5, 5);
		
		pannelloGiocatore2 = new PannelloGiocatore("images/cartaCopert.png", 250, 100, players[1], 5, 5);
		pannelloGiocatore3 = new PannelloGiocatore("images/cartaCopert.png", 450, 150, players[2], 5, 5);
		pannelloGiocatore4 = new PannelloGiocatore("images/cartaCopert.png", 250, 100, players[3], 5, 5);

		GridBagConstraints gbc = new GridBagConstraints();
		
		//pannello giocatore 3
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
//		gbc.anchor = GridBagConstraints.RELATIVE;
//		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(pannelloGiocatore3, gbc);
		
//pannello giocatore 4
		gbc.gridx = 2;	//mette il componente a destra di quello precedente
		gbc.gridy = 1;
		
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
//		gbc.fill = GridBagConstraints.BOTH;	//occupa tutto lo spazio disponibile in altezza e in larghezza.
//		gbc.anchor = GridBagConstraints.LINE_START;
//		gbc.insets = new Insets(0, 0, 0, 5);

		add(pannelloGiocatore4, gbc);
		
		//pannello giocatore 2
		gbc.gridx = 0;	//inizio seconda riga
		gbc.gridy = 1;
		
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		add(pannelloGiocatore2, gbc);
		
		//pannello giocatore 1
		gbc.gridx = 1;
		gbc.gridy = 2;
		
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
//		gbc.anchor = GridBagConstraints.LINE_START;

		add(pannelloGiocatore1, gbc);
		
		//immagine
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
//		gbc.fill = GridBagConstraints.BOTH;
//				gbc.anchor = GridBagConstraints.RELATIVE;
//		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(panelDecks, gbc);
		

		setSize(1200, 700);

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
