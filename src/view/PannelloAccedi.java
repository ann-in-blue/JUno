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
		setPreferredSize(new Dimension(150, 100));
		setVisible(false);
		
		Border bordoInterno = BorderFactory.createTitledBorder("Accedi");
		Border bordoEsterno = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		
		setBorder(bordoFinale);
		
		labelNome = new JLabel("Nome");
		
		GridBagConstraints gbc = new GridBagConstraints();

		//Riga 1 labelNome
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
		gbc.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(labelNome, gbc);
		
	}

}
