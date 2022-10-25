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
	private String image;
	private Color color;


	@Override
	public Color getColor()
	{
		return color;
	}
	
	@Override
	public String getImage()
	{
		return image;
	}
	@Override
	public void setColor(Color color)
	{
		this.color = color;
	}
	
	@Override
	public void setImage(String image)
	{
		this.image = image;
	}
	@Override
	public boolean isWildCard() {
		return false;
	}

	protected abstract int getValue();
}
