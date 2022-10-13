package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import controller.ControllerFilePlayersDatabase;
import controller.JUno;
import controller.JUno.*;
import view.PannelloAvatar;


public class PaginaInizialeEventListener implements ActionListener
{
	private String nickname;
	JUno jUno = new JUno();
	ControllerFilePlayersDatabase controllerFile = new ControllerFilePlayersDatabase();
	
	public PaginaInizialeEventListener(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * Metodo che riceve in input una stringa di testo che rappresenta il nickname inserito dall'utente nella pagina iniziale della view.
	 * Si occupa di controllare se il nickname è già presente nel database.
	 * Se lo è, è possibile iniziare una nuova partita, altrimenti è necessario prima inserire un avatar e salvare il nuovo giocatore nel database.
	 * @param nickname
	 */
	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			if(controllerFile.checkPlayerInFile(nickname))
			{
				//inizia partita
				System.out.println("nickname inserito è presente");
				jUno.startGame(nickname);
				
			}
			else {
				System.out.println("nickname non presente");

				//apri il menu per la scelta dell'avatar
				PannelloAvatar pannelloAvatar = new PannelloAvatar();
				
				JUno.view.add(pannelloAvatar);

				//crea un nuovo giocatore con quel nickname e salvalo su file
				
				
				//e poi inizia la partita
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	

}
