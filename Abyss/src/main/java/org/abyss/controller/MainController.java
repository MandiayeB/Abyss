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
	private List<Cards> hand;
	@FXML
	private List<Cards> ennemyHand;
	@FXML
	private List<Cards> ennemyDeck;
	@FXML
	private List<Cards> deck;
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
	private Label allyHP;

	private List<Cards> allyBoard;
	private List<Cards> ennemyBoard;
	private Cards draggedCard;
	private boolean block = true;
	private Phase tour;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		deck = CardsUtils.getCardsGame();
		hand = new ArrayList<>();
		tour = Phase.TourEnnemi;
		allyBoard = CardsUtils.fillBoard();
		ennemyBoard = CardsUtils.fillBoard();
		deckAlly.setImage(new Image("/resources/CSS/dos.jpg"));
		deckEnnemy.setImage(new Image("/resources/CSS/dos.jpg"));

		piocher();
		afficherHand();

	}

	public void piocher() {

		while (hand.size() < 5) { // Pioche jusqu'� ce que la main soit pleine

			hand.add(deck.get(0)); // pioche la premi�re carte du deck
			deck.remove(0);
		}
	}

	public void jouer() {

		for (int i = 0; i < 4; i++) {

			System.out.println("joue ca : " + hand.get(i));

		}

	}
	
	public void retrait() {
		for (int i=0; i<allyBoard.size(); i++) {
			if(allyBoard.get(i) != null) {
				deck.add(allyBoard.get(i));
				allyBoard.remove(i);
				i--;
			}
		}
		piocher();
	}

	public void afficherHand() {

		carte0.setImage(hand.get(0).getImage());
		carte1.setImage(hand.get(1).getImage());
		carte2.setImage(hand.get(2).getImage());
		carte3.setImage(hand.get(3).getImage());
		carte4.setImage(hand.get(4).getImage());
		ennemyCard0.setImage(new Image("/resources/CSS/dos.jpg"));
		ennemyCard1.setImage(new Image("/resources/CSS/dos.jpg"));
		ennemyCard2.setImage(new Image("/resources/CSS/dos.jpg"));
		ennemyCard3.setImage(new Image("/resources/CSS/dos.jpg"));
		ennemyCard4.setImage(new Image("/resources/CSS/dos.jpg"));

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

	public void dragDetected(MouseEvent event) { // Je commence � transporter la carte

		if (tour == Phase.PhaseDeCombat) {
			Node node = (Node) event.getSource();
			String source = event.getPickResult().getIntersectedNode().getId(); // Je r�cup�re l'ID de la carte
			int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1))); // Je prend le dernier
																								// �l�ment de l'ID que
																								// je
																								// convertis en String
																								// puis
																								// en int
			draggedCard = hand.get(number);

			Dragboard db = node.startDragAndDrop(TransferMode.MOVE);

			ClipboardContent content = new ClipboardContent();
			content.putImage(draggedCard.getImage());
			db.setContent(content);

			event.consume();
		}

	}

	public void dragOver(DragEvent event) { // J'annonce que je veux transporter la carte
		if (event.getDragboard().hasImage()) {

			event.acceptTransferModes(TransferMode.COPY_OR_MOVE);

		}

		event.consume();
	}

	public void dragEntered(DragEvent event) { // Je montre � l'utilisateur sur quelle case il s'appr�te � poser la
												// carte

		if (event.getDragboard().hasImage()) {

			((ImageView) event.getSource()).setImage(new Image("/resources/CSS/dos.jpg"));

		}

		event.consume();
	}

	public void dragExited(DragEvent event) { // Je supprime l'image si l'utilisateur quitte la case

		if (!event.isDropCompleted()) {

			((ImageView) event.getSource()).setImage(null);
			event.consume();

		}
	}

	public void dragDropped(DragEvent event) { // J'assigne la carte � la case choisie

		Dragboard db = event.getDragboard();
		boolean success = false;

		String source = event.getPickResult().getIntersectedNode().getId();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));

		if (db.hasImage()) {

			((ImageView) event.getSource()).setImage(draggedCard.getImage());
			((ImageView) event.getSource()).setDisable(true);
			allyBoard.set(number, draggedCard);
			System.out.println(allyBoard);
			success = true;

		}

		event.setDropCompleted(success);
		event.consume();
	}

//	public void dragDone(DragEvent event) {
//	        /* the drag and drop gesture ended */
//	        /* if the data was successfully moved, clear it */
//	        if (event.getTransferMode() == TransferMode.MOVE) {
//	            carte0.setImage(null);
//	        }
//	        event.consume();
//	}

	public void tour() throws InterruptedException {
		int pv = 100;
		int attack = 10;
		switch (tour) {

		case TourEnnemi:

			// Platform.runLater(() ->
			phase.setText("Tour ennemi"); // Ca affiche tour ennemi
			System.out.println(tour);
			System.out.println("-----------------------");
			Thread.sleep(1000); // fait dormir le Thread (on le prend comme un timeur pour que le bot puisse
								// jouer)
			block = true;
			tour = Phase.PhaseDeStrategie;
			break;
//			phase.setText("Tour de strategie");	

		case PhaseDeStrategie:
			block = false;
			phase.setText("Tour de strategie");
			System.out.println(tour);
			System.out.println("-----------------------");
			tour = Phase.PhaseDeCombat;
//			phase.setText("Phase de Combat");
			break;
		case PhaseDeCombat:
			block = true;
			phase.setText("Phase de Combat");
//			Thread.sleep(1000);
			pv = (pv - attack);
			System.out.println(tour);
			System.out.println(pv + "Le nombre de pv restant");
			System.out.println("-----------------------");
			tour = Phase.PhaseDeRetrait;
			break;
		case PhaseDeRetrait:
			block = true;
			phase.setText("Retrait !");
			System.out.println(tour);
			System.out.println("-----------------------");
//			Thread.sleep(1000);
			tour = Phase.TourEnnemi;
			piocher();
			break;
		}

	}

}
