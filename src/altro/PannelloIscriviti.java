package altro;

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
		setPreferredSize(new Dimension(100, 100));
		setVisible(true);
		
		Border bordoInterno = BorderFactory.createTitledBorder("Iscrizione");
		Border bordoEsterno = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border bordoFinale = BorderFactory.createCompoundBorder(bordoEsterno, bordoInterno);
		
//		setBorder(bordoFinale);
		
		titoloFinestra = new JLabel("Inserisci i tuoi dati per creare un account");
		
		labelNome = new JLabel("Nome");
		fieldNome = new JTextField(20);
		
		labelAvatar = new JLabel("Inserisci un avatar o usa quello di default!");
		buttonAvatar = new JButton();
		//avatarChooser = new JFileChooser();
		//avatarChooser.addChoosableFileFilter(new AvatarFilter());
		
//		buttonAvatar.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if(avatarChooser.showOpenDialog(PannelloIscriviti.this) == JFileChooser.APPROVE_OPTION)
//				{
//					
//				}
//			}
//		});
		
		
		GridBagConstraints gbc1 = new GridBagConstraints();

		//titolo finestra
		gbc1.gridx = 0;
		gbc1.gridy = 0;
		
		gbc1.weightx = 0.01;
		gbc1.weighty = 0.01;
		
		gbc1.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc1.insets = new Insets(0, 0, 0, 5);
		
		add(titoloFinestra, gbc1);
		
		//labelNome
		gbc1.gridx = 0;
		gbc1.gridy = 1;
		
		gbc1.weightx = 0.01;
		gbc1.weighty = 0.01;
		
		gbc1.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc1.insets = new Insets(0, 0, 0, 5);
		
		add(labelNome, gbc1);
		
		//fieldNome
		gbc1.gridx = 1;
		gbc1.gridy = 1;
		
		gbc1.weightx = 0.01;
		gbc1.weighty = 0.01;
		
		gbc1.anchor = GridBagConstraints.LINE_START; 	//allineamento
		gbc1.insets = new Insets(0, 0, 0, 5);
		
		add(fieldNome, gbc1);
		
		//labelAvatar
		gbc1.gridx = 0;
		gbc1.gridy = 2;
		
		gbc1.weightx = 0.01;
		gbc1.weighty = 0.01;
		
		gbc1.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc1.insets = new Insets(0, 0, 0, 5);
		
		add(labelAvatar, gbc1);
		
		//buttonAvatar
		gbc1.gridx = 0;
		gbc1.gridy = 3;
		
		gbc1.weightx = 0.01;
		gbc1.weighty = 0.01;
		
		gbc1.anchor = GridBagConstraints.LINE_END; 	//allineamento
		gbc1.insets = new Insets(0, 0, 0, 5);
		
		add(buttonAvatar, gbc1);
		
	}
}
