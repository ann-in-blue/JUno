package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.Border;

public class PannelloIscriviti extends JPanel {

	private JLabel labelNome;
	private JLabel titoloFinestra;
	private JTextField fieldNome;
	private JLabel labelAvatar;
	private JButton buttonAvatar;
	private JFileChooser avatarChooser;
	
	
	public PannelloIscriviti() {
		
		setLayout(new GridBagLayout());
		setPreferredSize(new Dimension(150, 100));
		//setVisible(false);
		
		Border bordoInterno = BorderFactory.createTitledBorder("Iscrizione");
		Border bordoEsterno = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		
		setBorder(bordoFinale);
		
		titoloFinestra = new JLabel("Inserisci i tuoi dati per creare un account");
		
		labelNome = new JLabel("Nome");
		fieldNome = new JTextField();
		
		labelAvatar = new JLabel("Inserisci un avatar o usa quello di default!");
		buttonAvatar = new JButton();
		avatarChooser = new JFileChooser();
		avatarChooser.addChoosableFileFilter(new AvatarFilter());
		
//		buttonAvatar.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(avatarChooser.showOpenDialog(PannelloIscriviti.this) == JFileChooser.APPROVE_OPTION)
//				{
//					
//				}
//			}
//		});
		
		
		GridBagConstraints gbc = new GridBagConstraints();

		//titolo finestra
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
		gbc.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(titoloFinestra, gbc);
		
		//labelNome
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
		gbc.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(labelNome, gbc);
		
		//fieldNome
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
		gbc.anchor = GridBagConstraints.LINE_START; 	//allineamento
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(fieldNome, gbc);
		
		//labelAvatar
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
		gbc.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(labelAvatar, gbc);
		
		//buttonAvatar
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
		gbc.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(buttonAvatar, gbc);
		
	}
}
