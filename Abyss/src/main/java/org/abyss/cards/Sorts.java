package org.abyss.cards;

import javafx.scene.image.ImageView;

public class Sorts extends Cards {
	
	private String effect;
	
	public Sorts(String name, ImageView image, String effect) {
		
		super("sort", name, image);
		this.effect = effect;
		
	}
	
}
