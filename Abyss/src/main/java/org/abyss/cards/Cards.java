package org.abyss.cards;

import javafx.scene.image.Image;

public abstract class Cards {

	protected String type;
	protected String name;
	protected Image image;
	
	public String getName() {
		
		return name;
		
	}
	
	public Image getImage() {
		
		return image;
		
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	public Cards(String type, String name, Image image) {
		
		this.type = type;
		this.name = name;
		this.image = image;

	}

}
