package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class PannelloGiocatore extends JPanel{

	private BufferedImage myPicture;
	private JLabel picLabel;
	private JLabel nickname;
	private JLabel remainingCards;
	
	public void setRemainingCards(int intRemainingCards) {
		this.remove(remainingCards);
		this.remainingCards = new JLabel("Cards for : "+ nickname + intRemainingCards);
		this.add(remainingCards);
		
	}


	public PannelloGiocatore(String imagePath, int larghezza, int altezza, String titolo, int numOfCards)
	{
		try {
			//carica l'immagine 
			myPicture = ImageIO.read(new File(imagePath));
			picLabel = new JLabel(new ImageIcon(myPicture));			
//			picLabel.setSize(50, 50);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		//nome del giocatore
		nickname = new JLabel(titolo);
		nickname.setFont(new Font("Arial", Font.PLAIN, 35));
		remainingCards = new JLabel("Remaining cards: "+ numOfCards);
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(larghezza, altezza));
		add(nickname, BorderLayout.PAGE_START);
		add(picLabel, BorderLayout.CENTER);
		add(remainingCards, BorderLayout.PAGE_END);

	}
	
	
	public PannelloGiocatore(int larghezza, int altezza, String titolo)
	{
		setPreferredSize(new Dimension(larghezza, altezza));
		add(new JLabel(titolo), BorderLayout.PAGE_START);

	}


	public BufferedImage getMyPicture() {
		return myPicture;
	}

	public void setMyPicture(BufferedImage myPicture) {
		this.myPicture = myPicture;
	}
	
	
	
}
