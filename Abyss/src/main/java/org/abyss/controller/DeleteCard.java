package org.abyss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.cards.Sorts;

import javafx.scene.image.Image;

public class DeleteCard extends Sorts {

	public DeleteCard(String name, Image image, String effect) {
		super(name, image, effect);
	}

	@Override
	public void applySpell(List<Cards> list, List<Cards> list2) {
		HashMap<Integer, Integer> bestCard = new HashMap<>();
		bestCard.put(0, 0);
		for (int i = 0; i < list2.size(); i++) {
			if (list2.get(i) != null) {
				if (((Combattant) list2.get(i)).getAtt() > bestCard.get(0)) {
					bestCard.put(i, ((Combattant) list2.get(i)).getAtt());
				}
			}
		}
		for (Entry<Integer, Integer> entry : bestCard.entrySet()) {
			if (list2.get(entry.getKey()) != null) {
				list2.set(entry.getKey(), null);
			}
		}
	}

}
