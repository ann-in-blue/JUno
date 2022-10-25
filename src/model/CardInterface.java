package model;

import java.awt.Image;

public interface CardInterface 
{
	
	public Color getColor();
	public String getImage();
	public boolean isWildCard();
	public void setColor(Color color);
	public String toString();
	public void setImage(String nameImage);

}
