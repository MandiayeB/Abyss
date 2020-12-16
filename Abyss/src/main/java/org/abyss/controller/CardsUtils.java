package org.abyss.controller;

import java.util.ArrayList;
import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.cards.spells.DeleteCard;
import org.abyss.cards.spells.DoubleAttIceSpell;
import org.abyss.cards.spells.DoubleAttSpell;
import org.abyss.cards.spells.DoubleHpEarthSpell;
import org.abyss.cards.spells.DoubleHpWaterSpell;
import org.abyss.cards.spells.PenaltyIceSpell;

import javafx.scene.image.Image;

public class CardsUtils {

	public static List<Cards> getCardsGame() {

		ArrayList<Cards> cards = new ArrayList<>();
		DoubleAttSpell lumos = new DoubleAttSpell("Bénédiction de Lumière",
				new Image("/resources/Images/lightspell1.png"), "Double l'Attaque des cartes alliées");
		DeleteCard delete = new DeleteCard("Rivalité", new Image("/resources/Images/lightSpell2.png"),
				"Détruit la carte avec le plus d'Attaque");
		DoubleHpEarthSpell flor = new DoubleHpEarthSpell("Floraison", new Image("/resources/Images/plantsort1.png"),
				"Double les PV des cartes Plante");
		Combattant lancier = new Combattant("Lancier de Lumière", new Image("/resources/Images/light1.png"), 150, 70,
				"light");
		Combattant chevalier = new Combattant("Chevalier de Lumière", new Image("/resources/Images/light2.png"), 120,
				40, "light");
		Combattant yamamoto = new Combattant("Yamamoto", new Image("/resources/Images/light3.png"), 100, 60, "light");
		Combattant sakura = new Combattant("Sakura", new Image("/resources/Images/light4.png"), 100, 60, "light");
		Combattant poka = new Combattant("Pocahontas", new Image("/resources/Images/light5.png"), 90, 80, "light");
		Combattant arto = new Combattant("Artorius", new Image("/resources/Images/light6.png"), 200, 100, "light");
		Combattant ouaf = new Combattant("Ouaf", new Image("/resources/Images/plante1.png"), 100, 60, "earth");
		Combattant shen = new Combattant("Chêne", new Image("/resources/Images/plante2.png"), 50, 200, "earth");
		Combattant pom = new Combattant("Pomme", new Image("/resources/Images/plante3.png"), 40, 40, "earth");
		Combattant tort = new Combattant("Torterra", new Image("/resources/Images/plante4.png"), 100, 200, "earth");
		Combattant grog = new Combattant("Grog", new Image("/resources/Images/plante5.png"), 100, 60, "earth");
		Combattant hydre = new Combattant("Hydre", new Image("/resources/Images/plante6.png"), 120, 70, "earth");

		cards.add(lumos);
		cards.add(delete);
		cards.add(flor);
		cards.add(lancier);
		cards.add(chevalier);
		cards.add(yamamoto);
		cards.add(sakura);
		cards.add(poka);
		cards.add(arto);
		cards.add(ouaf);
		cards.add(shen);
		cards.add(pom);
		cards.add(tort);
		cards.add(grog);
		cards.add(hydre);

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
		PenaltyIceSpell penalty = new PenaltyIceSpell("Prison de glace", new Image("/resources/Images/iceSpell1.png"),
				"Retire 50 points d'attaque aux cartes adverses");
		DoubleAttIceSpell ral = new DoubleAttIceSpell("Ralliement", new Image("/resources/Images/iceSpell2.png"),
				"Double l'Attaque des cartes glace");
		Combattant sir = new Combattant("Sirène", new Image("/resources/Images/eau1.png"), 100, 60, "water");
		Combattant pos = new Combattant("Poseidon", new Image("/resources/Images/eau2.png"), 120, 70, "water");
		Combattant brebis = new Combattant("Brebis Aquatique", new Image("/resources/Images/eau3.png"), 100, 60,
				"water");
		Combattant rasengan = new Combattant("Rasengan", new Image("/resources/Images/eau4.png"), 120, 40, "water");
		Combattant shark = new Combattant("Shark", new Image("/resources/Images/eau5.png"), 90, 80, "water");
		Combattant myst = new Combattant("Armin", new Image("/resources/Images/eau6.png"), 40, 40, "water");
		Combattant elfe = new Combattant("Elfe Lunaire", new Image("/resources/Images/ice1.png"), 100, 60, "ice");
		Combattant reine = new Combattant("Reine de Givre", new Image("/resources/Images/ice2.png"), 150, 70, "ice");
		Combattant sorcerer = new Combattant("Sorcière de Cristal", new Image("/resources/Images/ice3.png"), 100, 60,
				"ice");
		Combattant art = new Combattant("Artemis", new Image("/resources/Images/ice4.png"), 50, 200, "ice");
		Combattant bumble = new Combattant("Bumble", new Image("/resources/Images/ice5.png"), 100, 200, "ice");
		Combattant slifer = new Combattant("Slifer", new Image("/resources/Images/ice6.png"), 200, 100, "ice");

		cards.add(poulpe);
		cards.add(penalty);
		cards.add(ral);
		cards.add(sir);
		cards.add(pos);
		cards.add(brebis);
		cards.add(rasengan);
		cards.add(shark);
		cards.add(myst);
		cards.add(elfe);
		cards.add(reine);
		cards.add(sorcerer);
		cards.add(art);
		cards.add(bumble);
		cards.add(slifer);

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
