package org.abyss.cards.spells;

import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.controller.MainController;

import javafx.scene.image.Image;

public class DoubleHpEarthSpell extends Sorts {

	public DoubleHpEarthSpell(String name, Image image, String effect) {
		super(name, image, effect);
	}

	@Override
	public void applySpell(List<Cards> list, List<Cards> list2, MainController parentController) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				if (((Combattant) list.get(i)).getElement() == "earth") {
					((Combattant) list.get(i)).setHp((((Combattant) list.get(i)).getHp()) * 2);
				}
			}
		}
	}

	@Override
	public boolean isDodgeable(MainController parentController) {
		// TODO Auto-generated method stub
		return false;
	}

}
