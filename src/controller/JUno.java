package controller;

import model.Card;
import model.Color;
import model.Game;
import model.cards.ValueCard;

public class JUno 
{
	/**
	 * Classe che contiene il main del gioco.
	 */
	public static void main(String[] args) {
		//DA INPUT LISTA GIOCATORI
		//Game game = new Game({"Ann", "Yoda"});
		Card card = new ValueCard(Color.BLUE, 0, "./images/blue0.png");
		System.out.println(card.getClass().getSimpleName());

		
	}

}
