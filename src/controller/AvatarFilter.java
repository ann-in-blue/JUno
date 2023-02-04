package controller;

import javax.swing.filechooser.FileFilter;

import view.Utils;

import java.io.File;

/**
 * Classe che permette di gestire le immagini che l'utente carica come avatar dell'account.
 * @author a-00
 *
 */
public class AvatarFilter extends FileFilter
{

	/**
	 * Metodo che verifica il corretto formato dell'immagine inserita.
	 */
	@Override
	public boolean accept(File f) {
		/**
		 * se è una directory deve essere possibile navigare al suo interno
		 */
		if(f.isDirectory()) {
			return true;	
		}
		String estensione = Utils.getExtension(f);
		
		if(estensione == null)
			return false;
		if(estensione.equals("png") || estensione.equals("jpeg"))
			return true;
		
		return false;
	}
	/**
	 * Metodo che restituisce il formato delle immagini accettate. 
	 */
	@Override
	public String getDescription() {

		return "Immagini (*.png o *.jpeg)";
	}

}
