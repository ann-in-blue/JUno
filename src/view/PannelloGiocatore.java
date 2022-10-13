package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

public class PannelloGiocatore extends JPanel{

//	private ImageIcon imageCard;
	private BufferedImage myPicture;
	private JLabel picLabel;
	
	public PannelloGiocatore(String imagePath, int larghezza, int altezza, String titolo, int offsetDx, int offsetSx)
	{
		try {
			//carica l'immagine 
//			imageCard= new ImageIcon("images/mazzo.png");
			myPicture = ImageIO.read(new File(imagePath));
			picLabel = new JLabel(new ImageIcon(myPicture));			
			picLabel.setSize(100, 70);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(larghezza, altezza));
		add(new JLabel(titolo));
		add(picLabel, BorderLayout.CENTER);

		
		Border bordoInterno = BorderFactory.createTitledBorder(titolo);
		Border bordoEsterno = BorderFactory.createEmptyBorder(5, offsetSx, 5, offsetDx);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		
		setBorder(bordoFinale);
	}
	
	
	public PannelloGiocatore(int larghezza, int altezza, String titolo, int offsetDx, int offsetSx)
	{
		setPreferredSize(new Dimension(larghezza, altezza));
		add(new JLabel(titolo));
		
		Border bordoInterno = BorderFactory.createTitledBorder(titolo);
		Border bordoEsterno = BorderFactory.createEmptyBorder(5, offsetSx, 5, offsetDx);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		
		setBorder(bordoFinale);
	}


	public BufferedImage getMyPicture() {
		return myPicture;
	}

	public void setMyPicture(BufferedImage myPicture) {
		this.myPicture = myPicture;
	}
	
	
	
}
