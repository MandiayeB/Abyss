package org.abyss.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.cards.Sorts;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	private ImageView allyCard0; // Main alliée
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
	private ImageView ally0; // Terrain allié
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
	private ImageView collision0; // Collision
	@FXML
	private ImageView collision1; // | |
	@FXML
	private ImageView collision2; // | |
	@FXML
	private ImageView collision3; // | |
	@FXML
	private ImageView collision4; // <------->
	@FXML
	private Label allyHp;
	@FXML
	private Label ennemyHp;
	@FXML
	private Label cardAtt;
	@FXML
	private Label cardHp;
	@FXML
	private Label afficherTour;
	@FXML
	private Label notif;
	@FXML
	private Label tout;
	@FXML
	private ImageView defeat;

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
	private ArrayList<ImageView> listImage1;
	private ArrayList<ImageView> listImage2;
	private ArrayList<ImageView> listImage3;
	private ArrayList<ImageView> listImage4;
	private ArrayList<ImageView> listImage5;
	private Boolean order;
	private boolean nouveau;
	private Node cancel;
	Stage popupwindow;
	Stage stage;
	Scene scene;

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		deck = CardsUtils.getCardsGame();
		ennemyDeck = CardsUtils.getCardsGame();
		hand = CardsUtils.fillBoard();
		ennemyHand = CardsUtils.fillBoard();
		allyBoard = CardsUtils.fillBoard();
		ennemyBoard = CardsUtils.fillBoard();
		allyPv = 10000;
		ennemyPv = 10000;
		tour = Phase.TourEnnemi;
		nouveau = true;

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
		listImage5 = new ArrayList<>();
		listImage5.add(collision0);
		listImage5.add(collision1);
		listImage5.add(collision2);
		listImage5.add(collision3);
		listImage5.add(collision4);
		order = false;

		allyDeck.setImage(new Image("/resources/Images/dos.jpg"));
		opponentDeck.setImage(new Image("/resources/Images/dos.jpg"));

		piocher();
		afficherHp();
	}

	public void afficherHp() {

		allyHp.setText(Integer.toString(allyPv));
		ennemyHp.setText(Integer.toString(ennemyPv));

	}

	public void mort() {

		if (allyPv <= 0 || ennemyPv <= 0) {
			defeat.toFront();
			defeat.setImage(new Image("/resources/Images/end.gif"));
			display();
		}

	}

	public void piocher() {

		for (int i = 0; i < hand.size(); i++) { // Pioche jusqu'à ce que la main soit pleine

			if (hand.get(i) == null) {

				hand.set(i, deck.get(0)); // pioche la première carte du deck
				deck.remove(0); // supprime la carte du deck

			}

		}

		for (int i = 0; i < ennemyHand.size(); i++) { // Pareil pour la main ennemi

			if (ennemyHand.get(i) == null) {

				ennemyHand.set(i, ennemyDeck.get(0));
				ennemyDeck.remove(0);

			}

		}
		afficherHand();

	}

	public void retrait() {

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < allyBoard.size(); i++) {

					if (allyBoard.get(i) != null) {
						
						((Combattant) allyBoard.get(i)).setAppliedElement(false);
						deck.add(allyBoard.get(i)); // On remet la carte dans le deck
						allyBoard.set(i, null); // On supprime la carte du terrain

					}

					if (ennemyBoard.get(i) != null) {

						((Combattant) ennemyBoard.get(i)).setAppliedElement(false);
						ennemyDeck.add(ennemyBoard.get(i)); // Pareil pour l'ennemi
						ennemyBoard.set(i, null);

					}

					listImage3.get(i).setImage(new Image("/resources/Images/tornade.gif")); // On supprime l'image de la
																							// carte du terrain
					listImage4.get(i).setImage(new Image("/resources/Images/tornade.gif"));

					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					listImage3.get(i).setImage(null);
					listImage4.get(i).setImage(null);

				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						piocher();
						afficherBoard();
						phase.setVisible(true);
						if (order) {
							tour = Phase.TourEnnemi;
							order = false;
						} else if (!order) {
							tour = Phase.PhaseDeStrategie;
							order = true;
							phase.setVisible(true);
						}
						ecrire("Nouveau tour ");
						lireLigne();
						tour();
					}
				});
			}
		}).start();

	}

	public void afficherHand() {

		int index = 0;
		for (ImageView v : listImage1) {

			if (hand.get(index) != null) {

				v.setImage(hand.get(index).getImage()); // Affiche la main par rapport à la liste de cartes "hand"

			} else {

				v.setImage(null);

			}
			index++;

		}

		index = 0;
		for (ImageView w : listImage2) {

			if (ennemyHand.get(index) != null) {

				w.setImage(new Image("/resources/Images/dos.jpg")); // Pareil pour l'ennemi

			} else {

				w.setImage(null);

			}
			index++;

		}

	}

	public void afficherBoard() {

		for (int i = 0; i < allyBoard.size(); i++) {

			if (allyBoard.get(i) != null) {

				listImage3.get(i).setImage(allyBoard.get(i).getImage()); // Affiche le board par rapport
																			// à la liste de cartes "allyBoard"
			}

		}

		for (int i = 0; i < ennemyBoard.size(); i++) {

			if (ennemyBoard.get(i) != null) {

				listImage4.get(i).setImage(ennemyBoard.get(i).getImage()); // Pareil pour l'ennemi

			}

		}

	}

	public void combat() {

		ecrire("reset"+"\n"+"Combat");
		lireLigne();
		phase.setVisible(false);

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < allyBoard.size(); i++) {

					if (allyBoard.get(i) != null) {

						Combattant carte1 = (Combattant) allyBoard.get(i);
						if (ennemyBoard.get(i) != null) {

							Combattant carte2 = (Combattant) ennemyBoard.get(i);
							ennemyPv += carte1.combat(carte2); // On enlève la différence aux pv de l'ennemi
							allyPv += carte2.combat(carte1); // Pareil pour les pv de l'allié
							listImage3.get(i).setTranslateY(100);
							listImage4.get(i).setTranslateY(-100);

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							listImage3.get(i).setTranslateY(-9);
							listImage4.get(i).setTranslateY(9);
							listImage5.get(i).setImage(new Image("/resources/Images/spark.gif"));

							try {
								Thread.sleep(350);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							listImage5.get(i).setImage(null);
							
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									afficherHp();
									ecrire("Dégâts infligés : "+ carte1.combat(carte2) + "\n" 
									+ "Dégâts reçus : "+ carte2.combat(carte1));
									lireLigne();
								}
							});

						} else {

							ennemyPv -= carte1.getAtt(); // S'il n'y a personne on attaque directement les pv
							listImage3.get(i).setTranslateY(100);

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							listImage3.get(i).setTranslateY(-100);

							try {
								Thread.sleep(350);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									afficherHp();
									ecrire("Dégâts infligés : " + carte1.getAtt());
									lireLigne();
								}
							});

						}

					} else {

						if (ennemyBoard.get(i) != null) {

							Combattant carte2 = (Combattant) ennemyBoard.get(i);
							allyPv -= carte2.getAtt(); // S'il n'y a personne l'ennemi attaque directement les pv
							listImage4.get(i).setTranslateY(-100);

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							listImage4.get(i).setTranslateY(100);

							try {
								Thread.sleep(350);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									afficherHp();
									ecrire("Dégâts reçus : " + carte2.getAtt());
									lireLigne();
								}
							});

						}

					}

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							allyHp.setTextFill(Color.RED);
							ennemyHp.setTextFill(Color.RED);
						}
					});

				}

				for (int i = 0; i < allyBoard.size(); i++) {
					listImage3.get(i).setTranslateY(0);
					listImage4.get(i).setTranslateY(0);
				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						allyHp.setTextFill(Color.web("#36d353"));
						ennemyHp.setTextFill(Color.web("#36d353"));
						afficherTour.setText("Retrait !");
						System.out.println(tour);
						System.out.println("-----------------------");
						tour = Phase.PhaseDeRetrait;
						mort();
						tour();
					}
				});
			}
		}).start();

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

	public void afficherCarte(MouseEvent event) {

		Node node = (Node) event.getSource();
		String source = node.getId().toString();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
		
		if (tour == Phase.Transition) {
			// Effet autour des cartes

			DropShadow borderGlow = new DropShadow();
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);

			if (node.getId().contains("ally")) {
				borderGlow.setColor(Color.BLUE);
			} else {
				borderGlow.setColor(Color.RED);
			}

			borderGlow.setWidth(70);
			borderGlow.setHeight(70);
			node.setEffect(borderGlow);

		}

		if (node.getId().contains("allyCard")) {
			node.setTranslateY(-10);
		}

		if (((ImageView) event.getSource()).getImage() != null) {
			notif.setVisible(false);
			
			if (node.getId().contains("ally")) {
				if (allyBoard.get(number) != null) {
					cardAtt.setText(Integer.toString(((Combattant) allyBoard.get(number)).getAtt()));
					cardHp.setText(Integer.toString(((Combattant) allyBoard.get(number)).getHp()));
				} else {
					cardAtt.setText(Integer.toString(((Combattant) hand.get(number)).getAtt()));
					cardHp.setText(Integer.toString(((Combattant) hand.get(number)).getHp()));
				}
			} else {
				if (ennemyBoard.get(number) != null) {
					cardAtt.setText(Integer.toString(((Combattant) ennemyBoard.get(number)).getAtt()));
					cardHp.setText(Integer.toString(((Combattant) ennemyBoard.get(number)).getHp()));
				} else {
					cardAtt.setText(Integer.toString(((Combattant) ennemyHand.get(number)).getAtt()));
					cardHp.setText(Integer.toString(((Combattant) ennemyHand.get(number)).getHp()));
				}
			}
		}

		imageZoom.setImage(((ImageView) event.getSource()).getImage());
		
		// Affiche la carte sélectionnée en grand
	}

	public void cancelZoom(MouseEvent event) {

		Node node = (Node) event.getSource(); // Annule le zoom quand on quitte la case

		if (node.getId().contains("allyCard")) {
			node.setTranslateY(0);
		}

		node.setEffect(null);
		imageZoom.setImage(null);
		cardAtt.setText(null);
		cardHp.setText(null);
		notif.setVisible(true);

	}

	public void dragDetected(MouseEvent event) { // Je commence à transporter la carte

		if (tour == Phase.Transition) {

			Node node = (Node) event.getSource(); // On sauvegarde d'où vient l'évènement dans "node"
			cancel = node;
			cancel.setVisible(false);
			String source = node.getId().toString();
			// Converti l'id de la case en String

			int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
			// Récupère le dernier élément de la chaine de caractère et la converti en int *

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

			event.acceptTransferModes(TransferMode.COPY_OR_MOVE); // On annonce quelle case va gérer le transfert

		}

		event.consume();

	}

	public void dragEntered(DragEvent event) { // Je montre sur quelle case il s'apprête à poser la carte

		if (event.getDragboard().hasImage() && allyBoard.get(carteVerif(event)) == null) {

			((ImageView) event.getSource()).setImage(new Image("/resources/Images/dos.jpg"));
			// Image pour indiquer où va se dérouler le transfert

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

			allyBoard.set(carteVerif(event), draggedCard); // Ajoute la carte sur le terrain
			hand.set(draggedNumber, null); // Supprime la carte de la main
			applyElement(carteVerif(event), allyBoard);
			afficherHand();
			afficherBoard();
			success = true;

		}

		event.setDropCompleted(success); // Indique que le transfert s'est bien déroulé
		event.consume();

	}

	public void dragDone(DragEvent event) {

		cancel.setVisible(true);

	}

	public int carteVerif(DragEvent event) {

		// Sert à trouver l'emplacement d'un évènement (voir dragDetected)
		String source = (((Node) event.getSource()).getId()).toString();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
		return number;

	}

	public void ennemy() {

		ecrire("Tour ennemi");
		nouveau = false;
		lireLigne();
		afficherTour.setText("Tour Ennemi");
		phase.setVisible(false);

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
							int save = searchBoard(ennemyBoard);
							ennemyBoard.set(save, c);
							ennemyHand.set(index, null);
							listImage2.get(index).setImage(null);
							applyElement(save, ennemyBoard);
							afficherHand();
							afficherBoard();

						} else if (c instanceof Sorts) {
							// Si la carte est un sort la pose sur le terrain
						}

					}
					index++;

					if (index == ennemyHand.size()) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						phase.setText("Fin de tour");
						if (!order) {
							afficherTour.setText("Tour de Stratégie");
							tour = Phase.PhaseDeStrategie;
							phase.setVisible(true);
						} else if (order) {
							afficherTour.setText("Phase de Combat");
							tour = Phase.PhaseDeCombat;
						}
						ecrire("Fin de tour");
						lireLigne();
						tour();
					}
				});
			}
		}).start();

	}

	public int searchBoard(List<Cards> board) {
		// Cherche une place sur le terrain pour l'ennemi (Ou dans la méthode pioche
		// pour arrêter de piocher)
		int location = 0;
		for (int i = 0; i < board.size(); i++) {

			if (board.get(i) == null) {

				location = i;
				break;

			}

		}
		return location;

	}

	public void ecrire(String content) {
		File fichier = new File("src/main/resources/resources/TXT/Text.txt");

		if (nouveau == true) {
			try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fichier))) {
				bufferedWriter.write("");
				bufferedWriter.close();

			} catch (IOException e) {
				System.out.println("Impossible d'ecrire");
			}
		}

		try {
			FileWriter fw = new FileWriter("src/main/resources/resources/TXT/Text.txt", true);
			fw.write(content + "\n");
			fw.close();
		} catch (IOException ioe) {
			System.out.println("Impossible d'ecrire");
		}
	}

	public void lireLigne() {

		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader("src/main/resources/resources/TXT/Text.txt"));
			String line;
			notif.setText("");
			while ((line = in.readLine()) != null) {
				// Afficher le contenu du fichier
				System.out.println(line);
				notif.setText(notif.getText() + "\n " + line);
				nouveau = false;
				if (line.equals("reset")) {
					notif.setText("");
				}
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void tour() {

		switch (tour) {

		case TourEnnemi:
			System.out.println(tour);
			System.out.println("-----------------------");
			ennemy();
			break;

		case PhaseDeStrategie:
			ecrire("Vous pouvez jouer");
			lireLigne();
			System.out.println(tour);
			System.out.println("-----------------------");
			System.out.println(hand.toString());
			afficherTour.setText("Tour de Stratégie");
			tour = Phase.Transition;
			break;

		case Transition:
			ecrire("Fin de tour");
			lireLigne();
			if (order) {
				afficherTour.setText("Tour Ennemi");
				tour = Phase.TourEnnemi;
			} else if (!order) {
				afficherTour.setText("Phase de Combat");
				tour = Phase.PhaseDeCombat;
			}
			tour();
			break;

		case PhaseDeCombat:
			System.out.println(tour);
			System.out.println("-----------------------");
			for (int i = 0; i < 5; i++) {
				listImage5.get(i).toFront();
			}
			combat();
			break;

		case PhaseDeRetrait:
			System.out.println(tour);
			System.out.println("-----------------------");
			for (int i = 0; i < 5; i++) {
				listImage5.get(i).toBack();
			}
			retrait();
			break;

		}

	}

	public void restart(ActionEvent e) {
		System.exit(0);
	}

	public void retourAccueil(ActionEvent e) {
		popupwindow.close();
		stage.setScene(scene);
		
	}

	public void display() {

		popupwindow = new Stage();

		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Fin de la partie");

		Label label1 = new Label("La partie est terminée !");

		Button button1 = new Button("Retour a l'accueil");
		Button button2 = new Button("Quitter la partie");

		button1.setOnAction(e -> retourAccueil(e));
		button2.setOnAction(e -> restart(e));

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label1, button1, button2);

		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 250);

		popupwindow.setScene(scene1);

		popupwindow.showAndWait();
	}

}
