package org.abyss.cards;

import javafx.scene.image.ImageView;

public class Combattant extends Cards {
	
	private int att;
	private int hp;
	private String element;
	
	public int getAtt() {
		
		return att;
		
	}
	public Combattant(String name, ImageView image, int att, int hp, String element) {
		// Constructeur de la carte Combattant
		
		super("combattant", name, image);
		this.att = att;
		this.hp = hp;
		this.element = element;
		
	}
	
	public void element(Combattant carte2) {
		
		if (this.element == carte2.element) { // Si deux cartes sont du même élément j'applique un bonus
			
			this.att += 10;
			carte2.att += 10;
			
		}
		
	}
	
	public int combat(Combattant carte2) {
		
		if (carte2.hp - this.att < 0) { // Si l'attaque de la carte > les hp de lautre carte ->
										// Je calcule les dégâts infligés au héro adverse
			return carte2.hp - this.att;
			
		} else {
			
			return 0;
		
		}
		
	}
	
}
