package org.abyss.controller;

import java.util.ArrayList;
import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

public class CardsUtils {

	public static List<Cards> getCardsGame() {

		ArrayList<Cards> cards = new ArrayList<>();

		Combattant c = new Combattant("test", null, 10, 5, "test");
		Combattant c1 = new Combattant("Guts", null,100,20,"demon");
		Combattant m = new Combattant("Mandiaye", null,50,20,"TFT");
		Combattant t = new Combattant("Takoo", null,100,20,"CSGO");
		Combattant sessa = new Combattant("Sessa", null,100,20,"JAVA");
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

}
