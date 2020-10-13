package org.abyss;

import org.abyss.cards.Combattant;
import org.abyss.controller.CardsUtils;
import org.abyss.controller.MainController;


public class Entrypoint {

	public static void main(String[] args) {
		
		MainController m = new MainController();
		CardsUtils c = new CardsUtils();

		m.pioche();
		
	}

}
