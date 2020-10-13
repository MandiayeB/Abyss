package org.abyss.controller;

import java.util.ArrayList;
import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

public class CardsUtils {
	
	public static List<Cards> getCardsGame() {
		
		ArrayList<Cards> cards = new ArrayList<>();
		
		Combattant c = new Combattant("test", null, 10, 5, "test");
		//
		//
		//
		
		cards.add(c);
		
		return cards;
		
		
	}

}
