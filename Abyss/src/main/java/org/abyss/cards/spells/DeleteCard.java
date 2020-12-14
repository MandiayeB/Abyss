package org.abyss.cards.spells;

import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.controller.MainController;

import javafx.scene.image.Image;

public class DeleteCard extends Sorts {

	public DeleteCard(String name, Image image, String effect) {
		super(name, image, effect);
	}

	@Override
	public void applySpell(List<Cards> list, List<Cards> list2, MainController parentController) {
		int[] bestCard = {0,0};
		for (int i = 0; i < list2.size(); i++) {
			if (list2.get(i) != null) {
				if (((Combattant) list2.get(i)).getAtt() > bestCard[1]) {
					bestCard[0] = i;
					bestCard[1] = ((Combattant) list2.get(i)).getAtt();
				}
			}
		}
		
		if (list2.get(bestCard[0]) != null) {
			parentController.getEnnemyHandController().getEnnemyDeck().add(list2.get(bestCard[0]));
			list2.set(bestCard[0], null);
		}
		
	}

}
