package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController implements Initializable {
	
	@FXML
	private List<Cards> hand;
	@FXML
	private List<Cards> deck;
	@FXML
	private ImageView imageZoom;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		deck = CardsUtils.getCardsGame();
		hand = new ArrayList<>();
		imageZoom = new ImageView();
	
//		"/../../../../resources/resources/CSS/kirito.png"
		
	}

	public void piocher() {
		
		deck = CardsUtils.getCardsGame();
		int i = 0;
		
		do { // Pioche jusqu'à ce que la main soit pleine
			
			hand.add(deck.get(0)); // pioche la première carte du deck
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
