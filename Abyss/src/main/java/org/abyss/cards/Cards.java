package org.abyss.cards;

import java.util.List;

import org.abyss.controller.MainController;

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
	
	public abstract boolean isDodgeable(MainController parentController);
	
	public int searchElement(List<Cards> board, String element) {
		int nbElement = 0;
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i) != null) {
				if (((Combattant) board.get(i)).getElement() == element) {
					nbElement++;
				}
			}
		}
		return nbElement;
	}

}
