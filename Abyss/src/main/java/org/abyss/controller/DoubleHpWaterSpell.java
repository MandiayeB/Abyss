package org.abyss.controller;

import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.cards.Sorts;

import javafx.scene.image.Image;

public class DoubleHpWaterSpell extends Sorts {

	public DoubleHpWaterSpell(String name, Image image, String effect) {
		super(name, image, effect);
	}

	public void applySpell(List<Cards> list, List<Cards> list2) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				if (((Combattant) list.get(i)).getElement() == "water") {
					((Combattant) list.get(i)).setHp((((Combattant) list.get(i)).getHp()) * 2);
				}
			}
		}
	}
	
}
