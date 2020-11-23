package org.abyss.cards;

import javafx.scene.image.Image;

public class Combattant extends Cards {
	
	private int att;
	private int hp;
	private String element;
	private Boolean appliedElement;
	
	public int getAtt() {
		return att;
	}
	
	public void setAtt(int att) {
		this.att = att;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public String getElement() {
		return element;
	}
	
	public void setElement(String element) {
		this.element = element;
	}
	
	public Boolean getAppliedElement() {
		return appliedElement;
	}
	
	public void setAppliedElement(Boolean appliedElement) {
		this.appliedElement = appliedElement;
	}
	
	public Combattant(String name, Image image, int att, int hp, String element) {
		// Constructeur de la carte Combattant
		
		super("combattant", name, image);
		this.att = att;
		this.hp = hp;
		this.element = element;
		this.appliedElement = false;
		
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
