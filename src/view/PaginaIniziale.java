package view;


//import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
//import java.awt.Stroke;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.geom.AffineTransform;
//import java.awt.geom.Line2D;

import javax.swing.*;

import controller.PaginaInizialeEventListener;

/**
 * Classe che rappresenta la pagina iniziale del gioco 
 * @author a-00
 *
 */
public class PaginaIniziale extends JPanel
{
	private JLabel labelTitolo;
	private JLabel labelNickname;
	private JTextField fieldNickname;
	private JButton buttonNickname;
	private JButton btn;
		
	public PaginaIniziale()
	{		
		super();
		setSize(300, 300);

		setLayout(new GridBagLayout());
		setVisible(true);
		
		//inizializzazione delle componenti della pagina
		labelTitolo = new JLabel("JUno");
		labelTitolo.setFont(new Font("Arial", Font.PLAIN, 40));
		
		labelNickname = new JLabel("Inserisci un nickname...");
		labelNickname.setFont(new Font("Arial", Font.PLAIN, 20));
		
		fieldNickname = new JTextField(20);
		fieldNickname.requestFocusInWindow();
		
		buttonNickname = new JButton("Invia");
		buttonNickname.setBackground(Color.LIGHT_GRAY);
		buttonNickname.setFont(new Font("Arial", Font.PLAIN, 20));
		buttonNickname.setSize(new Dimension(30, 30));


		btn = new JButton();
		btn.setVisible(false);
		
		GridBagConstraints gbc = new GridBagConstraints();

		//bottone di utilità
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		add(new JPanel(), gbc);
		
		//titolo finestra
		gbc.gridx = 0;
		gbc.gridy = 1;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
//		gbc.anchor = GridBagConstraints.CENTER; 	//allineamento
//		
//		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(labelTitolo, gbc);
		
		//labelNickname
		gbc.gridx = 0;
		gbc.gridy = 2;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(labelNickname, gbc);
		
		//fieldNickname
		gbc.gridx = 0;
		gbc.gridy = 3;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(fieldNickname, gbc);
		
		//bottone invio
		gbc.gridx = 0;
		gbc.gridy = 4;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		
		
		add(buttonNickname, gbc);
		
		//bottone di utilità
		gbc.gridx = 0;
		gbc.gridy = 5;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.05;
		add(new JPanel(), gbc);
				
		
		//azioni da eseguire una volta cliccato il bottone "Invia"
//		buttonNickname.addActionListener(new PaginaInizialeEventListener(fieldNickname));			
//		buttonNickname.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				String text = fieldNickname.getText();
//				System.out.println(text);
//
//			}
//			
//		});
				

	}

	public JButton getButtonNickname() {
		return buttonNickname;
	}

	public JTextField getFieldNickname() {
		return fieldNickname;
	}
	
}
