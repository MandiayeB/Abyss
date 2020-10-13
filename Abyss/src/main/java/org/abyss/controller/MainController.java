package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainController implements Initializable {
	@FXML

	private Label myMessage;

	private List<Cards> hand = new ArrayList<>();

	private List<Cards> deck;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		deck = CardsUtils.getCardsGame();

		myMessage.setText(String.valueOf(deck.size()));
	}

	public void pioche() {
		deck = CardsUtils.getCardsGame();
		int i = 0;
		// Pioche jusqu'a que la main est pleine
		do {
			hand.add(deck.get(0)); // pioche la premiere carte du deck
			System.out.println("Je pioche ma " + (i+1) + "eme carte, et j'ai eu cette carte " + hand.get(i).getName());
			deck.remove(0);
			i++;
		} while (hand.size() < 5);
		
	}

	public void jouer() {
		for (int i = 0; i < 4; i++) {
			System.out.println("joue ca : " + hand.get(i));
		}
	}

//	public void fillHand() {
//		hand.add(allCardsGame.get(0));
//		myMessage.setText(allCardsGame.get(0).getName() + "piochée");
//	}

}
