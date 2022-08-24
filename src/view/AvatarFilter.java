package view;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import java.io.File;

public class AvatarFilter extends FileFilter
{

	@Override
	public boolean accept(File f) {
		
		if(f.isDirectory()) {
			return true;	//se è una directory deve essere posibile navigare al suo interno
		}
		String estensione = Utils.getExtension(f);
		
		if(estensione == null)
			return false;
		if(estensione.equals("png") || estensione.equals("jpeg"))
			return true;
		
		return false;
	}

	@Override
	public String getDescription() {

		return "Immagini (*.png o *.jpeg)";
	}

}
