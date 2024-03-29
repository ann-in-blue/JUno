package view;

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.AvatarFilter;

/**
 * Classe che rappresenta il pannello usato per inserire in input l'avatar di un giocatore.
 * @author a-00
 *
 */
public class PannelloAvatar extends JPanel{

	private JLabel labelAvatar;
	private JButton buttonAvatar;
	private JFileChooser avatarChooser;
	
	public PannelloAvatar()
	{
		setLayout(new BorderLayout());
		labelAvatar = new JLabel("Inserisci un avatar o usa quello di default!");
		buttonAvatar = new JButton();
		
		avatarChooser = new JFileChooser();
		avatarChooser.addChoosableFileFilter(new AvatarFilter());
				
		buttonAvatar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getAvatarInput();
			}
		});
		
		add(labelAvatar);
		add(buttonAvatar);
		add(avatarChooser);
		
		
		
		
}
	/**
	 * Metodo per fornire in input il file che contiene l'avatar
	 * @return
	 */
	public String getAvatarInput()
		{
			if(avatarChooser.showOpenDialog(avatarChooser) == JFileChooser.APPROVE_OPTION)
			{
				return avatarChooser.getSelectedFile().getName();
			}
			else
				//avatar di default
				return "images/image.png";
		}
}
