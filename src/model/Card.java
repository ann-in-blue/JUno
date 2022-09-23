package model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Classe astratta che rappresenta una carta generica di Uno.
 * @author ann
 *
 */
public abstract class Card implements CardInterface
{
	private Image img;
	private Color color;
	private boolean isWildCard;	//serve????


	@Override
	public Color getColor()
	{
		return color;
	}
	
	@Override
	public Image getImage()
	{
		return img;
	}
	@Override
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	@Override
	public void setImage(String nameImage)
	{
		try {
			//carica l'immagine 		
			img = ImageIO.read(new File(nameImage));
			
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
	@Override
	public boolean isWildCard() {
		return false;
	}

	protected abstract int getValue();
}
