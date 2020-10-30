package org.abyss.controller;

import java.util.ArrayList;
import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

import javafx.scene.image.Image;

public class CardsUtils {

	public static List<Cards> getCardsGame() {

		ArrayList<Cards> cards = new ArrayList<>();

		Combattant c = new Combattant("test", new Image("/resources/Images/yugi.jpg"), 10, 5, "eau");
		Combattant c1 = new Combattant("Guts", new Image("/resources/Images/yugi1.jpg"), 100, 20, "eau");
		Combattant m = new Combattant("Mandiaye", new Image("/resources/Images/yugi2.jpg"), 50, 20, "glace");
		Combattant t = new Combattant("Takoo", new Image("/resources/Images/yugi3.jpg"), 100, 20, "glace");
		Combattant sessa = new Combattant("Sessa", new Image("/resources/Images/yugi4.jpg"), 100, 20, "eau");
		cards.add(c);
		cards.add(c1);
		cards.add(t);
		cards.add(sessa);
		cards.add(m);
		

		for (int i = 0; i < cards.size(); i++) {
			
			int remplace = (int) (Math.random() * (cards.size() - 0));
			Cards save = cards.get(i); //save sert a stocker le i
			cards.set(i,cards.get(remplace)); // Je remplace la carte "i" par la carte "r" grace a set
			cards.set(remplace, save); // Je remplace la carte replace par la carte save (i)
			System.out.println(cards.get(i).getName()+i);
			
		}
		
		return cards;

	}
	
	public static List<Cards> fillBoard() {
		
		List<Cards> liste = new ArrayList<>();
		
		for (int i = 0; i < 5; i++) {
			
			liste.add(null);
			
		}
		
		return liste;
	}
	
}
