package org.abyss.controller;

import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

public class Element {
	
	public Element() {

	}
	
	public void applyElement(int carte, List<Cards> board) {

		for (int i = 0; i < board.size(); i++) {

			if (i != carte) {

				if (board.get(i) != null) {
					
					if (((Combattant) board.get(carte)).getElement() == ((Combattant) board.get(i))
							.getElement()) {
						// Si deux cartes sont du même élément j'applique un bonus
						
						if (!((Combattant) board.get(carte)).getAppliedElement()) {
							((Combattant) board.get(carte)).setAtt(((Combattant) board.get(carte)).getAtt() + 50);
							((Combattant) board.get(carte)).setAppliedElement(true);
						}

						if (!((Combattant) board.get(i)).getAppliedElement()) {
							((Combattant) board.get(i)).setAtt(((Combattant) board.get(i)).getAtt() + 50);
							((Combattant) board.get(i)).setAppliedElement(true);
						}

					}
					
				}

			}

		}

	}
}
