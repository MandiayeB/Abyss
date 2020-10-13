package org.abyss.cards;

import javafx.scene.image.ImageView;

public abstract class Cards {

	protected String type;
	protected String name;
	protected ImageView image;
	
	public String getName() {
		return name;
	}

	public Cards(String type, String name, ImageView image) {
		this.type = type;
		this.name = name;
		this.image = image;

	}

}
