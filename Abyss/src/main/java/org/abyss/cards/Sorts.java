package org.abyss.cards;

import java.util.List;

import javafx.scene.image.Image;

public abstract class Sorts extends Cards {
	
	private String effect;
	
	public Sorts(String name, Image image, String effect) {
		
		super("sort", name, image);
		this.effect = effect;
		
	}
	
	public abstract List<Cards> applySpell(List<Cards> list, List<Cards> list2);
}
