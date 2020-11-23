package org.abyss.controller;

import java.util.ArrayList;
import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

import javafx.scene.image.Image;

public class CardsUtils {

	public static List<Cards> getCardsGame() {

		ArrayList<Cards> cards = new ArrayList<>();
		DoubleAttSpell lumos = new DoubleAttSpell("Bénédiction de Lumière",
				new Image("/resources/Images/lightspell1.png"), "Double l'Attaque des cartes alliées");
		Combattant lancier = new Combattant("Lancier de Lumière", new Image("/resources/Images/light1.png"), 150, 70, "light");
		Combattant chevalier = new Combattant("Chevalier de Lumière", new Image("/resources/Images/light2.png"), 120, 40, "light");
		Combattant yamamoto = new Combattant("Yamamoto", new Image("/resources/Images/light3.png"), 100, 60, "light");
		Combattant sakura = new Combattant("Sakura", new Image("/resources/Images/light4.png"), 100, 60, "light");
		Combattant ouaf = new Combattant("Ouaf", new Image("/resources/Images/plante1.png"), 100, 60, "earth");
		

		cards.add(lumos);
		cards.add(lancier);
		cards.add(chevalier);
		cards.add(yamamoto);
		cards.add(sakura);
		cards.add(ouaf);

		for (int i = 0; i < cards.size(); i++) {

			int remplace = (int) (Math.random() * (cards.size() - 0));
			Cards save = cards.get(i); // save sert a stocker le i
			cards.set(i, cards.get(remplace)); // Je remplace la carte "i" par la carte "r" grace a set
			cards.set(remplace, save); // Je remplace la carte replace par la carte save (i)

		}

		return cards;

	}
	
	public static List<Cards> getEnnemyCards() {

		ArrayList<Cards> cards = new ArrayList<>();
		DoubleHpWaterSpell poulpe = new DoubleHpWaterSpell("Bénédiction du Poulpe",
				new Image("/resources/Images/waterSpell.png"), "Double les PV des cartes Eau");
		Combattant brebis = new Combattant("Brebis Aquatique", new Image("/resources/Images/eau3.png"), 100, 60, "water");
		Combattant rasengan = new Combattant("Rasengan", new Image("/resources/Images/eau4.png"), 120, 40, "water");
		Combattant elfe = new Combattant("Elfe Lunaire", new Image("/resources/Images/ice1.png"), 100, 60, "ice");
		Combattant reine = new Combattant("Reine de Givre", new Image("/resources/Images/ice2.png"), 150, 70, "ice");
		Combattant sorcerer = new Combattant("Sorcière de Cristal", new Image("/resources/Images/ice3.png"), 100, 60, "ice");
		

		cards.add(poulpe);
		cards.add(brebis);
		cards.add(rasengan);
		cards.add(elfe);
		cards.add(reine);
		cards.add(sorcerer);

		for (int i = 0; i < cards.size(); i++) {

			int remplace = (int) (Math.random() * (cards.size() - 0));
			Cards save = cards.get(i); // save sert a stocker le i
			cards.set(i, cards.get(remplace)); // Je remplace la carte "i" par la carte "r" grace a set
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
