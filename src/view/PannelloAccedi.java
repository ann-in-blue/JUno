package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;
import javax.swing.border.Border;

public class PannelloAccedi extends JPanel
{
	private JLabel labelNome;
	
	public PannelloAccedi() {
		
		setLayout(new GridBagLayout());
//		setSize(new Dimension(100, 100));
		setVisible(true);
		
		Border bordoInterno = BorderFactory.createTitledBorder("Accedi");
		Border bordoEsterno = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		
		setBorder(bordoFinale);
		
		labelNome = new JLabel("Nome");
		
		GridBagConstraints gbc2 = new GridBagConstraints();

		//Riga 1 labelNome
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		
		gbc2.weightx = 0.01;
		gbc2.weighty = 0.01;
		
		gbc2.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc2.insets = new Insets(0, 0, 0, 5);
		
		add(labelNome, gbc2);
		
	}

}
