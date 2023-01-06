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

import model.Card;

public class PannelloGiocatoreUmano extends PannelloGiocatore
{
	private ArrayList<JButton> cardButtons;
	private JButton buttonUno;


	public PannelloGiocatoreUmano(int larghezza, int altezza, String titolo) {
		super(larghezza, altezza, titolo);
		cardButtons = new ArrayList<JButton>();	
		buttonUno = new JButton("UNO!");
		this.add(buttonUno, BorderLayout.PAGE_END);

	}

	public void addButtonCard(Card card)
	{
		String image = card.getImage();
		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File(image));		
	
			JButton button = new JButton(new ImageIcon(myPicture));
			button.setSize(20,20);
			cardButtons.add(button);
			this.add(button);

	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

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
	

		
	public void refresh()
	{
		
		this.repaint();
		
	}
	

}
