package model;

import java.awt.Image;

/**
 * Interfaccia che contiene i vari metodi collegati alle carte.

 * @param nameImage
 */
public interface CardInterface 
{
	
	public Colors getColor();
	public String getImage();
	public boolean isWildCard();
	public void setColor(Colors color);
	public String toString();
	public void setImage(String nameImage);

}
