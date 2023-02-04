package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.MatteBorder;

import model.Card;

/**
 * Classe che contiene gli elementi utilizzati dal giocatore umano per giocare la sua partita.
 * Contiene i bottoni con le carte scoperte.
 * @author a-00
 *
 */
public class PannelloGiocatoreUmano extends PannelloGiocatore
{
	private ArrayList<JButton> cardButtons;
	private JButton buttonUno;

	/**
	 * costruttore con dimensioni e titolo del giocatore
	 * @param larghezza
	 * @param altezza
	 * @param titolo
	 */
	public PannelloGiocatoreUmano(int larghezza, int altezza, String titolo) {
		super(larghezza, altezza, titolo);
		cardButtons = new ArrayList<JButton>();	
		buttonUno = new JButton("UNO!");
		buttonUno.setContentAreaFilled(false);

	}
	/**
	 * Costruttore con le dimensioni e le carte in mano al giocatore fornite in input.
	 * @param larghezza
	 * @param altezza
	 * @param titolo
	 * @param cards
	 */	
	public PannelloGiocatoreUmano(int larghezza, int altezza, String titolo, ArrayList<Card> cards) {
		super(larghezza, altezza, titolo);
		cardButtons = new ArrayList<JButton>();	
		setCardButtons(cards);
		buttonUno = new JButton("UNO!");
		buttonUno.setContentAreaFilled(false);

	}
	/**
	 * Metodo che permette di aggiungere una carta nel mazzo del giocatore.
	 * @param card
	 */
	public void addButtonCard(Card card)
	{
		String image = card.getImage();
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File(image));		
	
			JButton button = new JButton(new ImageIcon(myPicture));
			button.setContentAreaFilled(false);
			button.setMargin(null);
			cardButtons.add(button);
			this.add(button);

	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Metodo che permette, fornendo una lista di carte in input, di sostituire la lista di carte precedentemente in mano al giocatore.
	 * @param cardButtons
	 */
	public void setCardButtons(ArrayList<Card> cardButtons) {
		
		for(Card c: cardButtons)
		{
			this.addButtonCard(c);
		}
	}

	public ArrayList<JButton> getCardButtons() {
		return cardButtons;
	}
	public JButton getButtonUno() {
		return buttonUno;
	}
	
	/**
	 * Metodo per aggiornare la view
	 */
	public void refresh()
	{
		
		this.repaint();
		
	}
	

}
