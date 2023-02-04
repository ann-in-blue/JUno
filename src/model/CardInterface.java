package model;

import java.awt.Image;

public interface CardInterface 
{
	
	public Colors getColor();
	public String getImage();
	public boolean isWildCard();
	public void setColor(Colors color);
	public String toString();
	public void setImage(String nameImage);

}
