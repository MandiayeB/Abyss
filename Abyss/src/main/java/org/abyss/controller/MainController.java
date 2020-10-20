package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class MainController implements Initializable {

	@FXML
	private Button phase;
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
	@FXML
	private ImageView deckAlly;
	@FXML
	private ImageView deckEnnemy;
	@FXML
	private ImageView ally0;
	@FXML
	private ImageView ally1;
	@FXML
	private ImageView ally2;
	@FXML
	private ImageView ally3;
	@FXML
	private ImageView ally4;
	@FXML
	private Label allyHp;
	@FXML
	private Label ennemyHp;

	private List<Cards> hand;
	private List<Cards> ennemyHand;
	private List<Cards> ennemyDeck;
	private List<Cards> deck;
	private Phase tour;
	private List<Cards> allyBoard;
	private List<Cards> ennemyBoard;
	private Cards draggedCard;
	private int draggedNumber;
	private int allyPv;
	private int ennemyPv;
	ArrayList<ImageView> listImage;
	ArrayList<ImageView> listImage2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		deck = CardsUtils.getCardsGame();
		ennemyDeck = CardsUtils.getCardsGame();
		hand = new ArrayList<>();
		ennemyHand = new ArrayList<>();
		tour = Phase.TourEnnemi;
		allyBoard = CardsUtils.fillBoard();
		ennemyBoard = CardsUtils.fillBoard();
		allyPv = 100;
		ennemyPv = 100;
		listImage = new ArrayList<>();
		listImage.add(carte0);
		listImage.add(carte1);
		listImage.add(carte2);
		listImage.add(carte3);
		listImage.add(carte4);
		listImage2 = new ArrayList<>();
		listImage2.add(ennemyCard0);
		listImage2.add(ennemyCard1);
		listImage2.add(ennemyCard2);
		listImage2.add(ennemyCard3);
		listImage2.add(ennemyCard4);

		deckAlly.setImage(new Image("/resources/CSS/dos.jpg"));
		deckEnnemy.setImage(new Image("/resources/CSS/dos.jpg"));

		piocher();
		afficherHand();
		afficherHp();
	}

	public void afficherHp() {

		allyHp.setText(Integer.toString(allyPv));
		ennemyHp.setText(Integer.toString(ennemyPv));

	}

	public void piocher() {

		while (hand.size() < 5) { // Pioche jusqu'à ce que la main soit pleine

			hand.add(deck.get(0)); // pioche la première carte du deck
			deck.remove(0);

		}

		while (ennemyHand.size() < 5) { // Pioche jusqu'à ce que la main soit pleine

			ennemyHand.add(ennemyDeck.get(0)); // pioche la première carte du deck
			ennemyDeck.remove(0);

		}
		afficherHand();
	}

	public void retrait() {
		for (int i = 0; i < allyBoard.size(); i++) {
			if (allyBoard.get(i) != null) {
				deck.add(allyBoard.get(i));
				allyBoard.set(i, null);
			}
		}
		piocher();
	}

	public void afficherHand() {

		int index = 0;
		for (ImageView v : listImage) {
			v.setImage(hand.get(index).getImage());
			index++;
		}
		index = 0;
		for (ImageView w : listImage2) {
			w.setImage(new Image("/resources/CSS/dos.jpg"));
			index++;
		}

	}

	public void afficherCarte(MouseEvent event) {
		if (tour == Phase.PhaseDeCombat) {
			DropShadow borderGlow = new DropShadow();
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);
			borderGlow.setColor(Color.BLUE);
			borderGlow.setWidth(70);
			borderGlow.setHeight(70);
			Node node = (Node) event.getSource();
			node.setEffect(borderGlow);
		}
		imageZoom.setImage(((ImageView) event.getSource()).getImage());

	}

	public void cancelZoom(MouseEvent event) {

		Node node = (Node) event.getSource();
		node.setEffect(null);
		imageZoom.setImage(null);

	}

	public void dragDetected(MouseEvent event) { // Je commence à transporter la carte

		if (tour == Phase.PhaseDeCombat) {

			Node node = (Node) event.getSource();
			String source = (((Node) event.getSource()).getId()).toString(); // Je récupère l'ID de la carte
			int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1))); // Je prend le dernier
																								// élément de l'ID que
																								// je
																								// convertis en String
																								// puis
			draggedNumber = number; // en int
			draggedCard = hand.get(number);

			Dragboard db = node.startDragAndDrop(TransferMode.MOVE);

			ClipboardContent content = new ClipboardContent();
			content.putImage(draggedCard.getImage());
			db.setContent(content);

			event.consume();
		}

	}

	public void dragOver(DragEvent event) { // J'annonce que je veux transporter la carte

		if (event.getDragboard().hasImage() && allyBoard.get(carteVerif(event)) == null) {

			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

		}

		event.consume();
	}

	public void dragEntered(DragEvent event) { // Je montre à l'utilisateur sur quelle case il s'apprête à poser la
												// carte

		if (event.getDragboard().hasImage() && allyBoard.get(carteVerif(event)) == null) {

			((ImageView) event.getSource()).setImage(new Image("/resources/CSS/dos.jpg"));

		}

		event.consume();

	}

	public void dragExited(DragEvent event) { // Je supprime l'image si l'utilisateur quitte la case

		if (!event.isDropCompleted() && allyBoard.get(carteVerif(event)) == null) {

			((ImageView) event.getSource()).setImage(null);
			event.consume();

		}

	}

	public void dragDropped(DragEvent event) { // J'assigne la carte à la case choisie

		Dragboard db = event.getDragboard();
		boolean success = false;

		if (db.hasImage() && allyBoard.get(carteVerif(event)) == null) {

			((ImageView) event.getSource()).setImage(draggedCard.getImage());
			allyBoard.set(carteVerif(event), draggedCard);
			hand.set(draggedNumber, null);
			listImage.get(draggedNumber).setImage(null);
			afficherHand();
			success = true;

		}

		event.setDropCompleted(success);
		event.consume();

	}

	public int carteVerif(DragEvent event) {

		String source = (((Node) event.getSource()).getId()).toString();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
		return number;

	}

	public void tour() throws InterruptedException {
		
		switch (tour) {

		case TourEnnemi:
			// Platform.runLater(() ->
			phase.setText("Tour ennemi"); // Ca affiche tour ennemi
			System.out.println(tour);
			System.out.println("-----------------------");
			//Thread.sleep(1000); // fait dormir le Thread (on le prend comme un timeur pour que le bot puisse jouer);
			tour = Phase.PhaseDeStrategie;
			break;
//			phase.setText("Tour de strategie");	

		case PhaseDeStrategie:

			tour = Phase.PhaseDeCombat;
			phase.setText("Tour de strategie");
			System.out.println(tour);
			System.out.println("-----------------------");
			tour = Phase.PhaseDeCombat;
//			phase.setText("Phase de Combat");
			break;
		case PhaseDeCombat:

			tour = Phase.PhaseDeRetrait;
			phase.setText("Phase de Combat");
//			Thread.sleep(1000);
			System.out.println(tour);
			System.out.println("-----------------------");
			tour = Phase.PhaseDeRetrait;
			break;
		case PhaseDeRetrait:

			phase.setText("Retrait !");
			System.out.println(tour);
			System.out.println("-----------------------");
//			Thread.sleep(1000);
			tour = Phase.TourEnnemi;
			retrait(); // Permet de remettre les cartes dans le deck et qu'il pioche juste apres
			break;
		}

	}

}
