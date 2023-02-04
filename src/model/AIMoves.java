package model;

import java.util.ArrayList;

import controller.exceptions.InvalidTurnException;

/**
 * Interfaccia che contiene i metodi usati dai giocatori artificiali per scegliere la carta da giocare.
 * @author a-00
 *
 */
public interface AIMoves
{

	public Card playCardArtificial(int id) throws InvalidTurnException;
	public Card chooseCard0(ArrayList<Card> artificialPlayerCards, int id);
	public Card chooseCard1(ArrayList<Card> artificialPlayerCards, int id);
	public Card chooseCard2(ArrayList<Card> artificialPlayerCards, int id);
	
	public Colors chooseColorArtificial();
	public Card drawCard();
}
