package org.abyss.cards.spells;

import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.controller.MainController;

import javafx.scene.image.Image;

public abstract class Sorts extends Cards {
	
	private String effect;
	
	public String getEffect() {
		return effect;
	}
	
	public Sorts(String name, Image image, String effect) {
		
		super("sort", name, image);
		this.effect = effect;
		
	}
	
	public abstract void applySpell(List<Cards> list, List<Cards> list2, MainController parentController);
	
}
