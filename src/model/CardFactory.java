package model;

import model.cards.*;

import controller.exceptions.WrongArgumentsException;

/**
 * classe che ha il compito di inizializzare le varie carte con i parametri necessari.
 * @author ann
 *
 */
public interface CardFactory 
{

	/**
	 * Metodo che prende in input il tipo di carta da creare e restituisce la carta istanziata
	 * @param type
	 * @return
	 * @throws WrongArgumentsException
	 */
	default Card createCard(Type type, String nameImage) throws WrongArgumentsException
	{
		//in base al tipo crea la carta specifica
		return switch(type) 
		{
		case CHANGE_COLOR_CARD -> new ChangeColorCard(nameImage);
		case DRAW_4_CARD -> new Draw4Card(nameImage);
		
		default -> 
			throw new WrongArgumentsException("Wrong arguments for the type: "+type.toString());
		};
		
	
	}
	/**
	 * metodo che prende in input il tipo della carta da creare e il colore di essa e restituisce la carta creata.
	 * @param type
	 * @return
	 * @throws WrongArgumentsException
	 */
	default Card createCard(Type type, Color color, String nameImage) throws WrongArgumentsException
	{
		//in base al tipo crea la carta specifica
		return switch(type) 
		{
		case DRAW_2_CARD -> new Draw2Card(color, nameImage);
		case SKIP_CARD -> new SkipCard(color, nameImage);
		case REVERSE_CARD -> new ReverseCard(color, nameImage);
		default -> 
			throw new WrongArgumentsException("Wrong arguments for the type: "+type.toString());
		};
		
	
	}
	/**
	 * Metodo che riceve in input il tipo di carta da inizializzare e gli altri input necessari
	 * @param type
	 * @return l'oggetto istanziato
	 */
	default Card createCard(Type type, Color color, int value, String nameImage) throws WrongArgumentsException
	{
		//in base al tipo crea la carta specifica
		return switch(type) 
		{
		case VALUE_CARD -> new ValueCard(color, value, nameImage);	
		case DRAW_2_CARD -> new Draw2Card(color, nameImage);

		default -> 
			throw new WrongArgumentsException("Wrong arguments for the type: "+type.toString());
		};
		
	
	}
	
	
	
}
