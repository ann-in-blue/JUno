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

	/**
	 * Costruttore della finestra
	 */
	public FinestraHome() 	{
		super("JUno");
		//setLayout(new GridBagLayout());
		setLayout(new BorderLayout());
		setSize(1200, 700);
		setLocationRelativeTo(null);	//mette la finestra al centro dello schermo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		/**
		 * Inizializzazione dei vari elementi della finestra
		 */
		btn = new JButton();
		btn.setPreferredSize(new Dimension(0, 400));
		btn.setBackground(Color.black);
		
		//pannello per i bottoni
		panel = new JPanel();
		
		//bottone accedi
		bottoneAccedi = new JButton("Accedi");
		bottoneAccedi.setPreferredSize(new Dimension(200,100));
		bottoneAccedi.setBackground(Color.red);
		bottoneAccedi.setFont(new Font("Arial", Font.PLAIN, 35));
		
		//bottone iscriviti
		bottoneIscriviti = new JButton("Iscriviti");
		bottoneIscriviti.setBackground(Color.lightGray);
		bottoneIscriviti.setPreferredSize(new Dimension(200,100));
		bottoneIscriviti.setFont(new Font("Arial", Font.PLAIN, 25));

		//aggiunta dei bottoni al pannello
		panel.add(btn);
		panel.add(bottoneAccedi, BorderLayout.CENTER);
		panel.add(bottoneIscriviti, BorderLayout.CENTER);
		
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
		this.add(pannelloAccedi, BorderLayout.PAGE_START);
		this.add(panel);
		this.add(pannelloIscriviti);
//		GridBagConstraints gbc = new GridBagConstraints();
//		
//		//prima colonna: pannello accedi
//		gbc.gridx = 0;
//		gbc.gridy = 0;
//		
//		gbc.weightx = 0.01;
//		gbc.weighty = 0.01;
//		gbc.anchor = GridBagConstraints.LINE_START; 	//allineamento
//	
//		add(pannelloAccedi, gbc);
////		
//		//panel
//		gbc.gridx = 1;
//		gbc.gridy = 0;
////		gbc.anchor = GridBagConstraints.CENTER; 	//allineamento
//		gbc.weightx = 0.01;
//		gbc.weighty = 0.01;
//		gbc.fill = GridBagConstraints.BOTH;
//
//		add(panel, gbc);
//		//add(btn, gbc);
//		
////		//seconda colonna: bottone accedi
////		gbc.gridx = 1;
////		gbc.gridy = 1;
////		
////		gbc.weightx = 0.01;
////		gbc.weighty = 0.01;
////		
//		//gbc.anchor = GridBagConstraints.PAGE_START; 	//allineamento
////		gbc.ipadx = 30;
////		gbc.ipady = 30;
////		add(bottoneAccedi, gbc);
//		
////		//terza colonna: bottone iscriviti
////		gbc.gridx = 2;
////		gbc.gridy = 0;
////		
////		gbc.weightx = 0.01;
////		gbc.weighty = 0.01;
////
////		gbc.anchor = GridBagConstraints.LINE_END;
////		add(bottoneIscriviti, gbc);
////		
//		//quarta colonna: pannello iscriviti
//		gbc.gridx = 0;
//		gbc.gridy = 1;
//		
//		gbc.weightx = 0.1;
//		gbc.weighty = 0.1;
//		gbc.anchor = GridBagConstraints.LINE_END; 	//allineamento
//
//		add(pannelloIscriviti, gbc);
//		
	}
}
