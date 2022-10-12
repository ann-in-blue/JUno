package controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaginaInizialeEventListener implements ActionListener
{
	private String nickname;
	
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

		
	}
	

}
