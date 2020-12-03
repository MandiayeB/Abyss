package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class BoardController implements Initializable {

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

	private MainController parentController;
	private List<Cards> allyBoard;
	private List<Cards> ennemyBoard;
	private ArrayList<ImageView> listImage3;
	private ArrayList<ImageView> listImage4;

	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}

	public List<Cards> getAllyBoard() {
		return allyBoard;
	}

	public void setAllyBoard(List<Cards> allyBoard) {
		this.allyBoard = allyBoard;
	}
	
	public List<Cards> getEnnemyBoard() {
		return ennemyBoard;
	}
	
	public void setEnnemyBoard(List<Cards> ennemyBoard) {
		this.ennemyBoard = ennemyBoard;
	}
	
	public ArrayList<ImageView> getListImage3() {
		return listImage3;
	}

	public ArrayList<ImageView> getListImage4() {
		return listImage4;
	}

	public BoardController() {

		allyBoard = CardsUtils.fillBoard(5);
		ennemyBoard = CardsUtils.fillBoard(5);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
	}

	public void afficherCarte(MouseEvent event) {

		Node node = (Node) event.getSource();
		Image image = ((ImageView) node).getImage();
		String source = node.getId().toString();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
		String name = "";
		
		if (source.contains("ally")) {
			if (allyBoard.get(number) != null)
				name = allyBoard.get(number).getName();
		} else {
			if (ennemyBoard.get(number) != null)
				name = ennemyBoard.get(number).getName();
		}
		parentController.getInformationController().afficherCarte(image, number, node.getId(), name);

	}

	public void cancelZoom(MouseEvent event) {

		Node node = (Node) event.getSource();
		String source = node.getId().toString();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
		parentController.getInformationController().cancelZoom(number, node.getId());

	}

	public void afficherBoard() {

		for (int i = 0; i < allyBoard.size(); i++) {

			if (allyBoard.get(i) != null) {

				listImage3.get(i).setImage(allyBoard.get(i).getImage()); // Affiche le board par rapport
																			// à la liste de cartes "allyBoard"
			} else {
				listImage3.get(i).setImage(null);  
			}
			

		}

		for (int i = 0; i < ennemyBoard.size(); i++) {

			if (ennemyBoard.get(i) != null) {

				listImage4.get(i).setImage(ennemyBoard.get(i).getImage()); // Pareil pour l'ennemi

			} else {
				listImage4.get(i).setImage(null);  
			}

		}

	}

	public void retrait() {

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < allyBoard.size() + 1; i++) {
					
					if (i < allyBoard.size()) {
						
						if (allyBoard.get(i) != null) {

							((Combattant) allyBoard.get(i)).setAppliedElement(false);
							parentController.getAllyHandController().getAllyDeck().add(allyBoard.get(i)); // On remet la
																											// carte
																											// dans le
																											// deck
							allyBoard.set(i, null); // On supprime la carte du terrain

						}

						if (ennemyBoard.get(i) != null) {

							((Combattant) ennemyBoard.get(i)).setAppliedElement(false);
							parentController.getEnnemyHandController().getEnnemyDeck().add(ennemyBoard.get(i)); // Pareil
																												// pour
																												// l'ennemi
							ennemyBoard.set(i, null);

						}

						listImage3.get(i).setImage(new Image("/resources/Images/tornade.gif"));
						listImage4.get(i).setImage(new Image("/resources/Images/tornade.gif"));
					} else {
						parentController.getSpellController().cacherSpells();
					}

					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					if (i < allyBoard.size()) {
						listImage3.get(i).setImage(null);
						listImage4.get(i).setImage(null);
					} else {
						parentController.getSpellController().clean();
					}

				}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						parentController.getAllyHandController().piocher();
						parentController.getEnnemyHandController().piocher();
						afficherBoard();
						parentController.getTourController().visible(true);
						if (parentController.getTourController().getOrder()) {
							parentController.getTourController().setTour(Phase.TourEnnemi);
							parentController.getTourController().setOrder(false);
						} else {
							parentController.getTourController().setTour(Phase.PhaseDeStrategie);
							parentController.getTourController().setOrder(true);
							parentController.getTourController().visible(true);
						}
						parentController.getInformationController().ecrire("Nouveau tour ");
						parentController.getInformationController().lireLigne();
						parentController.getTourController().stade();
					}
				});
			}
		}).start();

	}

	public void dragOver(DragEvent event) { // J'annonce que je veux transporter la carte

		if (event.getDragboard().hasImage() && allyBoard.get(carteVerif(event)) == null
				&& parentController.getAllyHandController().getDraggedCard() instanceof Combattant) {

			event.acceptTransferModes(TransferMode.COPY_OR_MOVE); // On annonce quelle case va gérer le transfert

		}

		event.consume();

	}

	public void dragEntered(DragEvent event) { // Je montre sur quelle case il s'apprête à poser la carte

		if (event.getDragboard().hasImage() && allyBoard.get(carteVerif(event)) == null
				&& parentController.getAllyHandController().getDraggedCard() instanceof Combattant) {

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

		if (db.hasImage() && allyBoard.get(carteVerif(event)) == null
				&& parentController.getAllyHandController().getDraggedCard() instanceof Combattant) {

			allyBoard.set(carteVerif(event), parentController.getAllyHandController().getDraggedCard());
			parentController.getAllyHandController().getAllyHand()
					.set(parentController.getAllyHandController().getDraggedNumber(), null); // Supprime la carte de la
			Element e = new Element(); // main
			e.applyElement(carteVerif(event), allyBoard);
			parentController.getAllyHandController().afficherHand();
			afficherBoard();
			success = true;

		}

		event.setDropCompleted(success); // Indique que le transfert s'est bien déroulé
		event.consume();

	}

	public int carteVerif(DragEvent event) {

		// Sert à trouver l'emplacement d'un évènement (voir dragDetected)
		String source = (((Node) event.getSource()).getId()).toString();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
		return number;

	}

	public void translate3(int i, int j) {
		listImage3.get(i).setTranslateY(j);
	}

	public void translate4(int i, int j) {
		listImage4.get(i).setTranslateY(j);
	}

	public void effect(DropShadow glow, String id, int number) {

		if (id.contains("ally")) {
			listImage3.get(number).setEffect(glow);
		} else {
			listImage4.get(number).setEffect(glow);
		}

	}

}