package org.abyss.cards.spells;

import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.controller.MainController;

import javafx.scene.image.Image;

public class PenaltyIceSpell extends Sorts {

	public PenaltyIceSpell(String name, Image image, String effect) {
		super(name, image, effect);
	}

	public void applySpell(List<Cards> list, List<Cards> list2, MainController parentController) {
		for (int i = 0; i < list2.size(); i++) {
			if (list2.get(i) != null) {
				((Combattant) list2.get(i)).setAtt((((Combattant) list2.get(i)).getAtt()) - 50);
			}
		}
	}
	
	public boolean isDodgeable(MainController parentController) {
		
		return !parentController.getTourController().getOrder();
		// On joue la carte si l'adversaire joue en 2ème
		
	}

}
