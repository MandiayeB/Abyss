package org.abyss.controller;

import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.cards.Sorts;

import javafx.scene.image.Image;

public class DoubleSpell extends Sorts{
	
	public DoubleSpell(String name, Image image, String effect) {
		super(name, image, effect);
	}
	
	public List<Cards> applySpell(List<Cards> list, List<Cards> list2) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) != null) {
				((Combattant) list.get(i)).setAtt((((Combattant) list.get(i)).getAtt()) * 2);
			}
		}
		return list;
	}
}
