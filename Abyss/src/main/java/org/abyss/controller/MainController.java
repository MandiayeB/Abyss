package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

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
	private ImageView allyDeck;
	@FXML
	private ImageView opponentDeck;
	@FXML
	private ImageView imageZoom;
	@FXML
	private ImageView allyCard0;
	@FXML
	private ImageView allyCard1;
	@FXML
	private ImageView allyCard2;
	@FXML
	private ImageView allyCard3;
	@FXML
	private ImageView allyCard4;
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
	private ImageView ennemy0;
	@FXML
	private ImageView ennemy1;
	@FXML
	private ImageView ennemy2;
	@FXML
	private ImageView ennemy3;
	@FXML
	private ImageView ennemy4;
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
	private boolean disable;
	ArrayList<ImageView> listImage1;
	ArrayList<ImageView> listImage2;
	ArrayList<ImageView> listImage3;
	ArrayList<ImageView> listImage4;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		deck = CardsUtils.getCardsGame();
		ennemyDeck = CardsUtils.getCardsGame();
		hand = CardsUtils.fillBoard();
		ennemyHand = CardsUtils.fillBoard();
		tour = Phase.TourEnnemi;
		disable = false;
		allyBoard = CardsUtils.fillBoard();
		ennemyBoard = CardsUtils.fillBoard();
		allyPv = 1000;
		ennemyPv = 1000;
		listImage1 = new ArrayList<>();
		listImage1.add(allyCard0);
		listImage1.add(allyCard1);
		listImage1.add(allyCard2);
		listImage1.add(allyCard3);
		listImage1.add(allyCard4);
		listImage2 = new ArrayList<>();
		listImage2.add(ennemyCard0);
		listImage2.add(ennemyCard1);
		listImage2.add(ennemyCard2);
		listImage2.add(ennemyCard3);
		listImage2.add(ennemyCard4);
		listImage3 = new ArrayList<>();
		listImage3.add(ally0);
		listImage3.add(ally1);
		listImage3.add(ally2);
		listImage3.add(ally3);
		listImage3.add(ally4);
		listImage4 = new ArrayList<>();
		listImage4.add(ennemy0);
		listImage4.add(ennemy1);
		listImage4.add(ennemy2);
		listImage4.add(ennemy3);
		listImage4.add(ennemy4);

		allyDeck.setImage(new Image("/resources/CSS/dos.jpg"));
		opponentDeck.setImage(new Image("/resources/CSS/dos.jpg"));

		piocher();
		afficherHp();
	}

	public void afficherHp() {

		allyHp.setText(Integer.toString(allyPv));
		ennemyHp.setText(Integer.toString(ennemyPv));

	}

	public void piocher() {

		for (int i = 0; i < hand.size(); i++) { // Pioche jusqu'à ce que la main soit pleine

			if (hand.get(i) == null) {

				hand.set(i, deck.get(0)); // pioche la première carte du deck
				deck.remove(0);
				System.out.println("AHAHAHAHAHAH");

			}
			
			if (ennemyHand.get(i) == null) {
				
				ennemyHand.set(i, ennemyDeck.get(0)); // pioche la première carte du deck ennemi
				ennemyDeck.remove(0);
				
			}

		}
		System.out.println(hand);
		afficherHand();

	}

	public void retrait() {

		for (int i = 0; i < allyBoard.size(); i++) {

			if (allyBoard.get(i) != null) {

				deck.add(allyBoard.get(i));
				allyBoard.set(i, null);
				listImage3.get(i).setImage(null);

			}

		}

		piocher();
		afficherBoard();
		
	}

	public void afficherHand() {

		int index = 0;
		for (ImageView v : listImage1) {

			if (hand.get(index) != null) {

				v.setImage(hand.get(index).getImage());

			}
			index++;

		}

		index = 0;
		for (ImageView w : listImage2) {

			if (hand.get(index) != null) {

				w.setImage(new Image("/resources/CSS/dos.jpg"));

			}
			index++;

		}

	}
	
	public void Button () {
		
		if(disable==true) {
			phase.setDisable(true);
		}
		else {
			phase.setDisable(false);
		}
	}

	public void afficherBoard() {

		for (int i = 0; i < allyBoard.size(); i++) {

			if (allyBoard.get(i) != null) {

				listImage3.get(i).setImage(allyBoard.get(i).getImage());

			}

		}

		for (int i = 0; i < ennemyBoard.size(); i++) {

			if (ennemyBoard.get(i) != null) {

				listImage4.get(i).setImage(ennemyBoard.get(i).getImage());

			}

		}

	}

	public void combat() {

		for (int i = 0; i < allyBoard.size(); i++) {

			if (allyBoard.get(i) != null) {

				Combattant carte1 = (Combattant) allyBoard.get(i);
				if (ennemyBoard.get(i) != null) {

					Combattant carte2 = (Combattant) ennemyBoard.get(i);
					ennemyPv -= carte1.combat(carte2);
					allyPv -= carte2.combat(carte1);

				} else {

					ennemyPv -= carte1.getAtt();

				}

			} else {

				if (ennemyBoard.get(i) != null) {

					Combattant carte2 = (Combattant) ennemyBoard.get(i);
					allyPv -= carte2.getAtt();

				}

			}

		}
		afficherBoard();
		afficherHp();

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
			String source = (((Node) event.getSource()).getId()).toString();
			int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
			draggedNumber = number;
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

			allyBoard.set(carteVerif(event), draggedCard);
			hand.set(draggedNumber, null);
			listImage1.get(draggedNumber).setImage(null);
			afficherHand();
			afficherBoard();
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

	public void tour(){

		switch (tour) {

		case TourEnnemi:
			
			
			phase.setText("Tour Ennemi"); // On l'affiche 
			disable = true;
			Button();
			//Vu qu'il passe dans un nouveau Thread Affiche le setText
			new Thread(new Runnable() { // On utilise un nouveau Thread 
                @Override
                public void run() {
                    try {
                    	
                    	Thread.sleep(2000); // fait dormir le Thread (on le prend comme un timeur pour que l'adverse puisse jouer);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                            	phase.setText("Tour de Strategie"); // Affiche le tour de Strategie apres avoir attendu le reveille du Thread
//								System.out.println(tour);
                    			disable = false;
                    			Button();
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start(); // Lance le Threads
			// Platform.runLater(() ->

			tour = Phase.PhaseDeStrategie;
			retrait();

		case PhaseDeStrategie:
			

			System.out.println(tour);
			System.out.println("-----------------------");
			tour = Phase.PhaseDeCombat;
			break;

		case PhaseDeCombat:
			
			disable = true;
			phase.setText("Phase de Combat"); // On affiche grave au Thread en bas
			Button();
			new Thread(new Runnable() { // On utilise un nouveau Thread pour faire l'affichage du prochain SetText
                @Override
                public void run() {
                    try {
                    	
                    	Thread.sleep(2000); // fait dormir le Thread (on le prend comme un timeur pour que l'adverse puisse jouer);
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                            	phase.setText("Retrait !"); // Ca affiche Retrait
                    			disable = false;
                    			Button();
//								System.out.println(tour);
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
			
			try {
				Thread.sleep(1000); // Freeze au moment du combat
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println(tour);
			System.out.println("-----------------------");
			tour = Phase.PhaseDeRetrait;
			

		case PhaseDeRetrait:
			

			combat();		
			System.out.println(tour);
			System.out.println("-----------------------");
			tour = Phase.TourEnnemi;
			
			break;
		}
		
		/////////////////////////////// TEST //////////////////////////
//		if (rerun == true) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			rerun = false;
//			tour();
//		}

	}

}
