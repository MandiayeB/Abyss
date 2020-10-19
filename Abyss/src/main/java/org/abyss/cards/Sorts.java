package org.abyss.cards;

import javafx.scene.image.Image;

public class Sorts extends Cards {
	
	private String effect;
	
	public Sorts(String name, Image image, String effect) {
		
		super("sort", name, image);
		this.effect = effect;
		
	}
	
}
