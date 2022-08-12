package view;

import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.Border;

public class PannelloGiocatore extends JPanel{

	PannelloGiocatore(int larghezza, int altezza, String titolo, int offsetDx, int offsetSx)
	{
		setPreferredSize(new Dimension(larghezza, altezza));
		
		Border bordoInterno = BorderFactory.createTitledBorder(titolo);
		Border bordoEsterno = BorderFactory.createEmptyBorder(5, offsetSx, 5, offsetDx);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		
		setBorder(bordoFinale);
	}
}
