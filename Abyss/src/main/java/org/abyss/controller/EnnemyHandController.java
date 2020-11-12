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

public class EnnemyHandController implements Initializable {
	
	@FXML
	private ImageView ennemyCard0; // Main ennemi
	@FXML
	private ImageView ennemyCard1; // | |
	@FXML
	private ImageView ennemyCard2; // | |
	@FXML
	private ImageView ennemyCard3; // | |
	@FXML
	private ImageView ennemyCard4; // <------->
	
	private List<Cards> ennemyHand;
	private List<Cards> ennemyDeck;
	private ArrayList<ImageView> listImage2;
	
	public List<Cards> getEnnemyHand() {
		return ennemyHand;
	}
	
	public List<Cards> getEnnemyDeck() {
		return ennemyDeck;
	}
	
	public ArrayList<ImageView> getListImage2() {
		return listImage2;
	}
	
	public EnnemyHandController() {
		ennemyDeck = CardsUtils.getCardsGame();
		ennemyHand = CardsUtils.fillBoard();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listImage2 = new ArrayList<>();
		listImage2.add(ennemyCard0);
		listImage2.add(ennemyCard1);
		listImage2.add(ennemyCard2);
		listImage2.add(ennemyCard3);
		listImage2.add(ennemyCard4);
		piocher();
		
	}
	
	public void piocher() {

		for (int i = 0; i < ennemyHand.size(); i++) { // Pareil pour la main ennemi

			if (ennemyHand.get(i) == null) {

				ennemyHand.set(i, ennemyDeck.get(0));
				ennemyDeck.remove(0);

			}

		}
		afficherHand();

	}

	public void afficherHand() {
		
		int index = 0;
		for (ImageView w : listImage2) {

			if (ennemyHand.get(index) != null) {

				w.setImage(new Image("/resources/Images/dos.jpg")); // Pareil pour l'ennemi

			} else {

				w.setImage(null);

			}
			index++;

		}

	}
	
}
