package view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class FinestraHome extends JFrame
{	
	//bottoni accedi e iscriviti
	private JButton bottoneAccedi;
	private JButton bottoneIscriviti;
	private JButton btn;
	
	private PannelloAccedi pannelloAccedi;
	private PannelloIscriviti pannelloIscriviti;
	
	private JPanel panel;
	private Image image;

	/**
	 * Costruttore della finestra
	 */
	public FinestraHome()
	{
		super("JUno");
		setLayout(new GridLayout());
		//setLayout(new BorderLayout());
		setSize(1200, 700);
		setLocationRelativeTo(null);	//mette la finestra al centro dello schermo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		/**
		 * Inizializzazione dei vari elementi della finestra
		 */
		btn = new JButton();
		btn.setPreferredSize(new Dimension(0, 400));
		btn.setBackground(null);
		
		//pannello per i bottoni
		panel = new JPanel();
		
		//bottone accedi
		bottoneAccedi = new JButton("Accedi");
		bottoneAccedi.setPreferredSize(new Dimension(270,100));
		bottoneAccedi.setBackground(Color.red);
		bottoneAccedi.setFont(new Font("Arial", Font.PLAIN, 35));
		
		//bottone iscriviti
		bottoneIscriviti = new JButton("Iscriviti");
		bottoneIscriviti.setBackground(Color.lightGray);
		bottoneIscriviti.setPreferredSize(new Dimension(200,70));
		bottoneIscriviti.setFont(new Font("Arial", Font.PLAIN, 25));

		//aggiunta dei bottoni al pannello
		panel.add(btn);
		panel.add(bottoneAccedi, BorderLayout.CENTER);
		panel.add(bottoneIscriviti);
		
		//inizializzazione pannelli
		pannelloAccedi = new PannelloAccedi();
		pannelloIscriviti = new PannelloIscriviti();
		

		//azioni da eseguire una volta cliccati i due bottoni "Accedi" e "iscriviti"
		
		bottoneAccedi.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pannelloAccedi.setVisible(true);
				
			}
		});
		bottoneIscriviti.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pannelloIscriviti.setVisible(true);
				
			}
		});
		
		
		/**
		 * Aggiungo i componenti alla finestra tramite un grid layout
		 */

		GridBagConstraints gbc = new GridBagConstraints();
		
		//prima colonna: pannello accedi
		gbc.gridx = 0;
		gbc.gridy = 0;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(pannelloAccedi, gbc);
		
		//panel
		gbc.gridx = 1;
		gbc.gridy = 1;
		
		gbc.weightx = 0.01;
		gbc.weighty = 0.01;
		//gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(0, 0, 0, 5);
		
		add(panel, gbc);
		
//		//seconda colonna: bottone accedi
//		gbc.gridx = 1;
//		gbc.gridy = 1;
//		
//		gbc.weightx = 0.01;
//		gbc.weighty = 0.01;
//		
//		
//		//gbc.fill = GridBagConstraints.NONE;
//
//		//gbc.anchor = GridBagConstraints.PAGE_START; 	//allineamento
//		gbc.ipadx = 30;
//		gbc.ipady = 30;
//		add(bottoneAccedi, gbc);
//		
//		//terza colonna: bottone iscriviti
//		gbc.gridx = 2;
//		gbc.gridy = 0;
//		
//		gbc.weightx = 0.01;
//		gbc.weighty = 0.01;
//
//		gbc.anchor = GridBagConstraints.LINE_END;
//		gbc.insets = new Insets(0, 0, 0, 0);
//		add(bottoneIscriviti, gbc);
//		
		//quarta colonna: pannello iscriviti
		gbc.gridx = 2;
		gbc.gridy = 0;
		
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		
		add(pannelloIscriviti, gbc);
		
	}
}
