package view;

import java.awt.*;

//import java.awt.event.*;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
//import java.util.ArrayList;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.Card;

public class GraficaGioco extends JFrame
{
	private JButton buttonDeck;
	private JButton buttonDiscardDeck;
	private JPanel panelDecks;
	private JLabel labelTurno;
//	private JButton buttonPassaTurno;
	private PannelloGiocatoreUmano pannelloGiocatoreUmano;
	private PannelloGiocatore pannelloGiocatore1;
	private PannelloGiocatore pannelloGiocatore2;
	private PannelloGiocatore pannelloGiocatore3;
		
	public PannelloGiocatore getPannelloGiocatore(int giocatore) {
		return switch(giocatore) {
		case 1 -> pannelloGiocatore1;
		case 2 -> pannelloGiocatore2;
		case 3 -> pannelloGiocatore3;
		default -> pannelloGiocatore1;
		};
				
	}


	//prova bottone immagine
	private BufferedImage buttonIcon;
	private BufferedImage iconDiscardDeck;
	
	public GraficaGioco(String[] players)
	{
		super("JUno");
				
		setLayout(new GridBagLayout());

		//prova
		try {
			buttonIcon = ImageIO.read(new File("images/card_back.png"));
			iconDiscardDeck = ImageIO.read(new File("images/card_back.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		panelDecks = new JPanel();
//		labelTurno = new JLabel("Turno di: ");
//		buttonPassaTurno = new JButton("Passa il turno");
		buttonDeck = new JButton(new ImageIcon(buttonIcon));
		buttonDeck.setContentAreaFilled(false);
		buttonDeck.setBackground(super.getBackground());
		
		
		buttonDiscardDeck = new JButton(new ImageIcon(iconDiscardDeck));
		buttonDiscardDeck.setContentAreaFilled(false);


		panelDecks.add(buttonDeck);
		panelDecks.add(buttonDiscardDeck);
//		panelDecks.add(labelTurno);
		//panelDecks.add(buttonPassaTurno);
		
		pannelloGiocatoreUmano = new PannelloGiocatoreUmano(800, 500, players[0]);
		
		pannelloGiocatore1 = new PannelloGiocatore("images/card_back_alt.png", 250, 200, players[1], 7);
		pannelloGiocatore2 = new PannelloGiocatore("images/card_back_alt.png", 250, 200, players[2], 7);
		pannelloGiocatore3 = new PannelloGiocatore("images/card_back_alt.png", 250, 200, players[3], 7);

		GridBagConstraints gbc = new GridBagConstraints();
		
		//pannello giocatore 3
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		gbc.fill = GridBagConstraints.VERTICAL;	//occupa tutto lo spazio disponibile in altezza e in larghezza.

//		gbc.anchor = GridBagConstraints.RELATIVE;
//		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(pannelloGiocatore2, gbc);
		
//pannello giocatore 4
		gbc.gridx = 2;	//mette il componente a destra di quello precedente
		gbc.gridy = 0;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
//		gbc.anchor = GridBagConstraints.LINE_START;
//		gbc.insets = new Insets(0, 0, 0, 5);

		add(pannelloGiocatore3, gbc);
		
		//pannello giocatore 2
		gbc.gridx = 0;	//inizio seconda riga
		gbc.gridy = 0;
		
		gbc.weightx = 0.1;
		gbc.weighty = 0.1;

		add(pannelloGiocatore1, gbc);
		
		//pannello giocatore 1
		gbc.gridx = 1;
		gbc.gridy = 2;
		
		gbc.weightx = 0.9;
		gbc.weighty = 0.9;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		add(pannelloGiocatoreUmano, gbc);
		
		//immagine
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.CENTER;
		
		add(panelDecks, gbc);
		

		setSize(1500, 1000);

		setLocationRelativeTo(null);	//mette la finestra al centro dello schermo
		
		//per far si che schiacciando il tasto chiudi si interrompa l esecuzione
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//per rendere visibile la finestra
		setVisible(true);
		
	}


	public JLabel getLabelTurno() {
		return labelTurno;
	}


	public void setLabelTurno(JLabel labelTurno) {
		this.labelTurno = labelTurno;
		panelDecks.add(labelTurno);

		
	}


	public JButton getButtonDeck() {
		return buttonDeck;
	}
	
	public PannelloGiocatoreUmano getPannelloGiocatoreUmano() {
		return pannelloGiocatoreUmano;
	}

	public void setPannelloGiocatoreUmano(PannelloGiocatoreUmano pannelloGiocatoreUmano) {
		this.pannelloGiocatoreUmano = pannelloGiocatoreUmano;
	}
	
	/**
	 * Metodo per cambiare la carta presente sul tavolo nel mazzo di scarto.
	 * @param card
	 */
	public void setDiscardButton(Card card)
	{
			String image = card.getImage();
			BufferedImage iconDiscardDeck;
			
			try {				
				panelDecks.remove(buttonDiscardDeck);
				iconDiscardDeck = ImageIO.read(new File(image));
				buttonDiscardDeck = new JButton(new ImageIcon(iconDiscardDeck));
//				buttonDiscardDeck.setIcon(new ImageIcon(iconDiscardDeck));
				buttonDiscardDeck.setMargin(null);
				buttonDiscardDeck.setContentAreaFilled(false);
				panelDecks.add(buttonDiscardDeck);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public JButton getButtonDiscardDeck() {
		return buttonDiscardDeck;
	}

	
	public void refresh()
	{
		
		this.repaint();
		
	}
}
