package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.cards.Sorts;

import javafx.application.Platform;
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
	private ImageView allyCard0; // Main alli�e
	@FXML
	private ImageView allyCard1; // | |
	@FXML
	private ImageView allyCard2; // | |
	@FXML
	private ImageView allyCard3; // | |
	@FXML
	private ImageView allyCard4; // <------->
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
	@FXML
	private ImageView ally0; // Terrain alli�
	@FXML
	private ImageView ally1; // | |
	@FXML
	private ImageView ally2; // | |
	@FXML
	private ImageView ally3; // | |
	@FXML
	private ImageView ally4; // <------->
	@FXML
	private ImageView ennemy0; // Terrain ennemi
	@FXML
	private ImageView ennemy1; // | |
	@FXML
	private ImageView ennemy2; // | |
	@FXML
	private ImageView ennemy3; // | |
	@FXML
	private ImageView ennemy4; // <------->
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

		for (int i = 0; i < hand.size(); i++) { // Pioche jusqu'� ce que la main soit pleine

			if (searchBoard(hand) == 10) {

				break; // Si la main est pleine on arr�te la boucle

			}

			if (hand.get(i) == null) {

				hand.set(i, deck.get(0)); // pioche la premi�re carte du deck
				deck.remove(0); // supprime la carte du deck

			}

		}

		for (int i = 0; i < ennemyHand.size(); i++) { // Pareil pour la main ennemi

			if (searchBoard(ennemyHand) == 10) {
				break;
			}

			if (ennemyHand.get(i) == null) {

				ennemyHand.set(i, ennemyDeck.get(0));
				ennemyDeck.remove(0);

			}

		}
		afficherHand();

	}

	public void retrait() {

		for (int i = 0; i < allyBoard.size(); i++) {

			if (allyBoard.get(i) != null) {

				deck.add(allyBoard.get(i)); // On remet la carte dans le deck
				allyBoard.set(i, null); // On supprime la carte du terrain
				listImage3.get(i).setImage(null); // On supprime l'image de la carte du terrain

			}

			if (ennemyBoard.get(i) != null) {

				ennemyDeck.add(ennemyBoard.get(i)); // Pareil pour l'ennemi
				ennemyBoard.set(i, null);
				listImage4.get(i).setImage(null);

			}

		}

		piocher();
		afficherBoard();

	}

	public void afficherHand() {

		int index = 0;
		for (ImageView v : listImage1) {

			if (hand.get(index) != null) {

				v.setImage(hand.get(index).getImage()); // Affiche la main par rapport � la liste de cartes "hand"

			} else {

				v.setImage(null);

			}
			index++;

		}

		index = 0;
		for (ImageView w : listImage2) {

			if (ennemyHand.get(index) != null) {

				w.setImage(new Image("/resources/CSS/dos.jpg")); // Pareil pour l'ennemi

			} else {

				w.setImage(null);

			}
			index++;

		}

	}

	public void button() {

		if (disable == true) {

			phase.setDisable(true);

		}

		else {

			phase.setDisable(false);

		}

	}

	public void afficherBoard() {

		for (int i = 0; i < allyBoard.size(); i++) {

			if (allyBoard.get(i) != null) {

				listImage3.get(i).setImage(allyBoard.get(i).getImage()); // Affiche le board par rapport
																			// � la liste de cartes "allyBoard"
			}

		}

		for (int i = 0; i < ennemyBoard.size(); i++) {

			if (ennemyBoard.get(i) != null) {

				listImage4.get(i).setImage(ennemyBoard.get(i).getImage()); // Pareil pour l'ennemi

			}

		}

	}

	public void combat() {

		for (int i = 0; i < allyBoard.size(); i++) {

			if (allyBoard.get(i) != null) {

				Combattant carte1 = (Combattant) allyBoard.get(i);
				if (ennemyBoard.get(i) != null) {

					Combattant carte2 = (Combattant) ennemyBoard.get(i);
					ennemyPv += carte1.combat(carte2); // On enl�ve la diff�rence aux pv de l'ennemi
					allyPv += carte2.combat(carte1); // Pareil pour les pv de l'alli�

				} else {

					ennemyPv -= carte1.getAtt(); // S'il n'y a personne on attaque directement les pv

				}

			} else {

				if (ennemyBoard.get(i) != null) {

					Combattant carte2 = (Combattant) ennemyBoard.get(i);
					allyPv -= carte2.getAtt(); // S'il n'y a personne l'ennemi attaque directement les pv

				}

			}

		}
		afficherBoard();
		afficherHp();

	}

	public void afficherCarte(MouseEvent event) {

		if (tour == Phase.PhaseDeCombat) {
			// Effet autour des cartes
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
		// Affiche la carte s�lectionn�e en grand
	}

	public void cancelZoom(MouseEvent event) {

		Node node = (Node) event.getSource(); // Annule le zoom quand on quitte la case
		node.setEffect(null);
		imageZoom.setImage(null);

	}

	public void dragDetected(MouseEvent event) { // Je commence � transporter la carte

		if (tour == Phase.PhaseDeCombat) {

			Node node = (Node) event.getSource(); // On sauvegarde d'o� vient l'�v�nement dans "node"
			String source = (((Node) event.getSource()).getId()).toString();
			// Converti l'id de la case en String

			int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
			// R�cup�re le dernier �l�ment de la chaine de caract�re et la converti en int *

			draggedNumber = number; // * On le stock dans un attribut
			draggedCard = hand.get(number); // On stock la carte dans un attribut

			Dragboard db = node.startDragAndDrop(TransferMode.MOVE);
			// On annonce au proggramme qu'on veut transporter la carte

			ClipboardContent content = new ClipboardContent();
			content.putImage(draggedCard.getImage());
			// On donne l'image qu'on veut voir pendant le drag & drop
			db.setContent(content);

			event.consume();
		}

	}

	public void dragOver(DragEvent event) { // J'annonce que je veux transporter la carte

		if (event.getDragboard().hasImage() && allyBoard.get(carteVerif(event)) == null) {

			event.acceptTransferModes(TransferMode.COPY_OR_MOVE); // On annonce quelle case va g�rer le transfert

		}

		event.consume();
	}

	public void dragEntered(DragEvent event) { // Je montre sur quelle case il s'appr�te � poser la carte

		if (event.getDragboard().hasImage() && allyBoard.get(carteVerif(event)) == null) {

			((ImageView) event.getSource()).setImage(new Image("/resources/CSS/dos.jpg"));
			// Image pour indiquer o� va se d�rouler le transfert

		}

		event.consume();

	}

	public void dragExited(DragEvent event) { // Je supprime l'image si l'utilisateur quitte la case

		if (!event.isDropCompleted() && allyBoard.get(carteVerif(event)) == null) {

			((ImageView) event.getSource()).setImage(null);
			event.consume();

		}

	}

	public void dragDropped(DragEvent event) { // J'assigne la carte � la case choisie

		Dragboard db = event.getDragboard();
		boolean success = false;

		if (db.hasImage() && allyBoard.get(carteVerif(event)) == null) {

			allyBoard.set(carteVerif(event), draggedCard); // Ajoute la carte sur le terrain
			hand.set(draggedNumber, null); // Supprime la carte de la main
			afficherHand();
			afficherBoard();
			success = true;

		}

		event.setDropCompleted(success); // Indique que le transfert s'est bien d�roul�
		event.consume();

	}

	public int carteVerif(DragEvent event) {
		// Sert � trouver l'emplacement d'un �v�nement (voir dragDetected)
		String source = (((Node) event.getSource()).getId()).toString();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
		return number;

	}

	public void ennemy() {

		new Thread(new Runnable() {
			public void run() {
				
				int index = 0;
				for (Cards c : ennemyHand) {
					
					if (c != null) {
						
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
						if (c instanceof Combattant) {
							// Si la carte est un combattant le pose sur le terrain
							
							System.out.println(index);
							ennemyBoard.set(searchBoard(ennemyBoard), c);
							ennemyHand.set(index, null);
							listImage2.get(index).setImage(null);
							afficherHand();
							afficherBoard();
							
						} else if (c instanceof Sorts) {
							// Si la carte est un sort la pose sur le terrain
						}
						
					}
					index++;
				}
			}
		}).start();

	}

	public int searchBoard(List<Cards> board) {
		// Cherche une place sur le terrain pour l'ennemi (Ou dans la m�thode pioche
		// pour arr�ter de piocher)
		int location = 10;
		for (int i = 0; i < board.size(); i++) {

			if (board.get(i) == null) {

				location = i;
				break;

			}

		}
		return location;

	}

	public void tour() {

		switch (tour) {

		case TourEnnemi:

			retrait();
			ennemy();
			phase.setText("Tour Ennemi"); // On l'affiche
			disable = true;
			button();
			// Vu qu'il passe dans un nouveau Thread Affiche le setText
			new Thread(new Runnable() { // On utilise un nouveau Thread
				@Override
				public void run() {
					try {

						Thread.sleep(2000);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								phase.setText("Tour de Strat�gie");
//								System.out.println(tour);
								disable = false;
								button();
							}
						});
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start(); // Lance le Thread
			// Platform.runLater(() ->

			tour = Phase.PhaseDeStrategie;

		case PhaseDeStrategie:

			System.out.println(tour);
			System.out.println("-----------------------");
			tour = Phase.PhaseDeCombat;
			break;

		case PhaseDeCombat:

			disable = true;
			phase.setText("Phase de Combat"); // On affiche grave au Thread en bas
			button();
			new Thread(new Runnable() { // On utilise un nouveau Thread pour faire l'affichage du prochain SetText
				@Override
				public void run() {
					try {
						Thread.sleep(2000); // fait dormir le Thread (on le prend comme un timeur pour que l'adverse
											// puisse jouer);
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								phase.setText("Retrait !"); // Ca affiche Retrait
								disable = false;
								button();
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
