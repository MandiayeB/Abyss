package org.abyss.controller;

import java.util.ArrayList;
import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

import javafx.scene.image.Image;

public class CardsUtils {

	public static List<Cards> getCardsGame() {

		ArrayList<Cards> cards = new ArrayList<>();
		Combattant dragon1 = new Combattant("DragonDestruction",
				new Image("/resources/Images/eau1.png"), 100, 0, "dragon");
		Combattant neos = new Combattant("Neos", new Image("/resources/Images/eau2.png"), 100, 0, "monstre");
		Combattant dragon2 = new Combattant("DragonUltime", 
				new Image("/resources/Images/eau3.png"), 50, 0, "dragon");
		Combattant dragon3 = new Combattant("DragonBlanc", new Image("/resources/Images/ice1.png"), 50, 0, "dragon");
		Combattant exodia = new Combattant("Exodia", new Image("/resources/Images/ice2.png"), 100, 0, "monstre");
		DoubleSpell sort1 = new DoubleSpell("sort1", new Image("/resources/Images/lightsort1.png"), "test");

		cards.add(dragon1);
		cards.add(neos);
		cards.add(dragon2);
		cards.add(dragon3);
		cards.add(exodia);
		cards.add(sort1);
		

		for (int i = 0; i < cards.size(); i++) {
			
			int remplace = (int) (Math.random() * (cards.size() - 0));
			Cards save = cards.get(i); //save sert a stocker le i
			cards.set(i,cards.get(remplace)); // Je remplace la carte "i" par la carte "r" grace a set
			cards.set(remplace, save); // Je remplace la carte replace par la carte save (i)
			
		}
		
		return cards;

	}
	
	public static List<Cards> fillBoard(int number) {
		
		List<Cards> liste = new ArrayList<>();
		
		for (int i = 0; i < number; i++) {
			
			liste.add(null);
			
		}
		
		return liste;
	}
	
}
