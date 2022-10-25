package controller;

import java.util.Observable;
import java.util.Observer;

import model.Card;

public class ViewObserver implements Observer
{

	/**
	 * Metodo che permette, dopo aver ricevuto una notifica dal modello, di aggiornare la view in base all'argomento in input.
	 * La view può essere aggiornata in vari modi, in relazione agli elementi del modello che sono cambiati e anche al tipo di giocatore che ha giocato il suo turno.
	 * 
	 * Nel caso del giocatore umano cambiano sia le carte nel suo mazzo sia la carta presente nel mazzo di scarto sul tavolo.
	 * Nel caso dei giocatori artificiali cambia solo la carta nel mazzo di scarto.
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {

		if(arg.getClass().equals(Card.class))
		{
			
		}
	}

}
