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
	@FXML
	private ImageView carte0;
	@FXML
	private ImageView carte1;
	@FXML
	private ImageView carte2;
	@FXML
	private ImageView carte3;
	@FXML
	private ImageView carte4;
	@FXML
	private ImageView ennemyCard0;
	@FXML
	private ImageView ennemyCard1;
	@FXML
	private ImageView ennemyCard2;
	@FXML
	private ImageView ennemyCard3;
	@FXML
	private ImageView ennemyCard4;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		deck = CardsUtils.getCardsGame();
		hand = new ArrayList<>();
		imageZoom.setImage(new Image("/resources/CSS/yugi.jpg"));
		carte0.setImage(new Image("/resources/CSS/yugi.jpg"));
		carte1.setImage(new Image("/resources/CSS/yugi1.jpg"));
		carte2.setImage(new Image("/resources/CSS/yugi2.jpg"));
		carte3.setImage(new Image("/resources/CSS/yugi3.jpg"));
		carte4.setImage(new Image("/resources/CSS/yugi4.jpg"));
		ennemyCard0.setImage(new Image("/resources/CSS/dos.jpg"));
		ennemyCard1.setImage(new Image("/resources/CSS/dos.jpg"));
		ennemyCard2.setImage(new Image("/resources/CSS/dos.jpg"));
		ennemyCard3.setImage(new Image("/resources/CSS/dos.jpg"));
		ennemyCard4.setImage(new Image("/resources/CSS/dos.jpg"));

	
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

}
