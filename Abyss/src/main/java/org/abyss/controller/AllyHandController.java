package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class AllyHandController implements Initializable {

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

	private List<Cards> allyHand;
	private List<Cards> allyDeck;
	private ArrayList<ImageView> listImage1;
	private Cards draggedCard;
	private int draggedNumber;
	private MainController parentController;
	private Node cancel;

	public List<Cards> getAllyHand() {
		return allyHand;
	}

	public List<Cards> getAllyDeck() {
		return allyDeck;
	}

	public Cards getDraggedCard() {
		return draggedCard;
	}

	public int getDraggedNumber() {
		return draggedNumber;
	}

	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}

	public AllyHandController() {
		allyDeck = CardsUtils.getCardsGame();
		allyHand = CardsUtils.fillBoard(5);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		listImage1 = new ArrayList<>();
		listImage1.add(allyCard0);
		listImage1.add(allyCard1);
		listImage1.add(allyCard2);
		listImage1.add(allyCard3);
		listImage1.add(allyCard4);
		piocher();

	}

	public void afficherCarte(MouseEvent event) {

		Node node = (Node) event.getSource();
		Image image = ((ImageView) node).getImage();
		String source = node.getId().toString();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
		parentController.getInformationController().afficherCarte(image, number, node.getId());

	}

	public void cancelZoom(MouseEvent event) {

		Node node = (Node) event.getSource();
		String source = node.getId().toString();
		int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
		parentController.getInformationController().cancelZoom(number, node.getId());

	}

	public void translate1(int i, int j) {
		listImage1.get(i).setTranslateY(j);
	}

	public void piocher() {

		for (int i = 0; i < allyHand.size(); i++) { // Pioche jusqu'à ce que la main soit pleine

			if (allyHand.get(i) == null) {

				allyHand.set(i, allyDeck.get(0)); // pioche la première carte du deck
				allyDeck.remove(0); // supprime la carte du deck

			}

		}
		afficherHand();

	}

	public void afficherHand() {

		int index = 0;
		for (ImageView v : listImage1) {

			if (allyHand.get(index) != null) {

				v.setImage(allyHand.get(index).getImage()); // Affiche la main par rapport à la liste de cartes "hand"

			} else {

				v.setImage(null);

			}
			index++;

		}

	}

	public void dragDetected(MouseEvent event) { // Je commence à transporter la carte

		if (parentController.getTourController().getTour() == Phase.Transition) {

			Node node = (Node) event.getSource(); // On sauvegarde d'où vient l'évènement dans "node"
			cancel = node;
			cancel.setVisible(false);
			String source = node.getId().toString();
			// Converti l'id de la case en String

			int number = Integer.parseInt(String.valueOf(source.charAt(source.length() - 1)));
			// Récupère le dernier élément de la chaine de caractère et la converti en int *

			draggedNumber = number; // * On le stock dans un attribut
			draggedCard = allyHand.get(number); // On stock la carte dans un attribut

			Dragboard db = node.startDragAndDrop(TransferMode.MOVE);
			// On annonce au proggramme qu'on veut transporter la carte

			ClipboardContent content = new ClipboardContent();
			content.putImage(draggedCard.getImage());
			// On donne l'image qu'on veut voir pendant le drag & drop
			db.setContent(content);

			event.consume();

		}

	}

	public void dragDone(DragEvent event) {

		cancel.setVisible(true);

	}

	public void effect(DropShadow glow, String id, int number) {
		listImage1.get(number).setEffect(glow);
	}

}
