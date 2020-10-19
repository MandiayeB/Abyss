package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainController implements Initializable {

	@FXML
	private List<Cards> hand;
	@FXML
	private List<Cards> ennemyHand;
	@FXML
	private List<Cards> ennemyDeck;
	@FXML
	private List<Cards> deck;
	@FXML
	private Phase moment;
	@FXML
	private Button phase;
	@FXML
	private ImageView imageZoom;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		deck = CardsUtils.getCardsGame();
		hand = new ArrayList<>();
		imageZoom = new ImageView();
		moment = Phase.TourEnnemie;

//		"/../../../../resources/resources/CSS/kirito.png"

	}

	public void piocher() {

		deck = CardsUtils.getCardsGame();
		int i = 0;

		do { // Pioche jusqu'à ce que la main soit pleine

			hand.add(deck.get(0)); // pioche la première carte du deck
			System.out
					.println("Je pioche ma " + (i + 1) + "eme carte, et j'ai eu cette carte " + hand.get(i).getName());
			deck.remove(0);
			i++;

		} while (hand.size() < 5);

	}

	public void jouer() {

		for (int i = 0; i < 4; i++) {

			System.out.println("joue ca : " + hand.get(i));

		}

	}

	public void tour() {

		int pv = 100;
		int attack = 10;

//		Phase next = Phase.values()[i.ordinal() + 1];
		switch (moment) {

		case TourEnnemie:
			moment = Phase.PhaseDeStrategie;
			phase.setText("Tour ennemie");
			System.out.println(moment);
			System.out.println("-----------------------");
			break;
		case PhaseDeStrategie:
			moment = Phase.PhaseDeCombat;
			phase.setText("Tour de strategie");
			System.out.println(moment);
			System.out.println("-----------------------");
			break;
		case PhaseDeCombat:
			moment = Phase.PhaseDeRetrait;
			phase.setText("Phase de Combat");
			pv = (pv - attack);
			System.out.println(moment);
			System.out.println(pv + "Le nombre de pv restant");
			System.out.println("-----------------------");
			break;
		case PhaseDeRetrait:
			moment = Phase.TourEnnemie;
			phase.setText("Retrait !");
			System.out.println(moment);
			System.out.println("-----------------------");
			break;

		}
	}
}
