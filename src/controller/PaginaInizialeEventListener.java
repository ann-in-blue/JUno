package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JTextField;

import controller.exceptions.InvalidTurnException;
import model.Game;

/**
 * Classe che contiene le istruzioni da eseguire quando viene inserito un nickname all'inizio della partita.
 * @author a-00
 *
 */
public class PaginaInizialeEventListener implements ActionListener
{
	/**
	 * Riferimenti agli elementi necessari per iniziare una partita.
	 */
	private String nickname;
	private JTextField field;
	private ControllerJUno jUno;
	Game game;
	ControllerFilePlayersDatabase controllerFile = new ControllerFilePlayersDatabase();
	
	public PaginaInizialeEventListener(JTextField field, ControllerJUno jUno, Game game) {
		this.field = field;
		this.jUno = jUno;
		this.game = game;
	}

	/**
	 * Metodo che riceve in input una stringa di testo che rappresenta il nickname inserito dall'utente nella pagina iniziale della view.
	 * Si occupa di controllare se il nickname è già presente nel database.
	 * Se lo è, è possibile iniziare una nuova partita, altrimenti è necessario prima inserire un avatar e salvare il nuovo giocatore nel database.
	 * @param nickname
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		nickname = field.getText();
		System.out.println(nickname);

		try {
			if(controllerFile.checkPlayerInFile(nickname))
			{
				//inizia partita
				System.out.println("nickname inserito è presente");
				jUno.startGame(nickname);

				
			}
			else {
				System.out.println("nickname non presente");
				jUno.startGameWithAvatarRequest(nickname);
				
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	

}
