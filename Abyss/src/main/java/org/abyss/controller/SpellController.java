package org.abyss.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import org.abyss.cards.Sorts;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class SpellController implements Initializable {

	@FXML
	private ImageView allySpell;
	@FXML
	private ImageView ennemySpell;

	private MainController parentController;
	private List<Cards> spell1;
	private List<Cards> spell2;

	public void setSpell1(List<Cards> spell1) {
		this.spell1 = spell1;
	}

	public void setSpell2(List<Cards> spell2) {
		this.spell2 = spell2;
	}

	public List<Cards> getSpell1() {
		return spell1;
	}
	
	public List<Cards> getSpell2() {
		return spell2;
	}
	
	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}

	public SpellController() {
		spell1 = CardsUtils.fillBoard(1);
		spell2 = CardsUtils.fillBoard(1);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void afficherSpells() {
		if (spell1.get(0) != null) {
			allySpell.setImage(spell1.get(0).getImage());
		} else {
			allySpell.setImage(null);  
		}
		if (spell2.get(0) != null) {
			ennemySpell.setImage(spell2.get(0).getImage());
		}else {
			ennemySpell.setImage(null);  
		}
	}
	
	public void cacherSpells() {
		allySpell.setImage(new Image("/resources/Images/tornade.gif"));
		if (spell1.get(0) != null) {
			parentController.getAllyHandController().getAllyDeck().add(spell1.get(0));
			spell1.set(0, null);
		}
		ennemySpell.setImage(new Image("/resources/Images/tornade.gif"));
		parentController.getEnnemyHandController().getEnnemyDeck().add(spell2.get(0));
		spell2.set(0, null);
	}
	
	public void clean() {
		allySpell.setImage(null);
		ennemySpell.setImage(null);
	}
	
	public void afficherCarte(MouseEvent event) {

		Node node = (Node) event.getSource();
		Image image = ((ImageView) node).getImage();
		String name = "";
		if (node.getId().contains("ally")) {
			if (spell1.get(0) != null)
				name = spell1.get(0).getName();
		} else {
			if (spell2.get(0) != null)
				name = spell2.get(0).getName();
		}
		parentController.getInformationController().afficherCarte(image, 0, node.getId(), name);

	}

	public void cancelZoom(MouseEvent event) {

		Node node = (Node) event.getSource();
		parentController.getInformationController().cancelZoom(0, node.getId());

	}

	public void dragOver(DragEvent event) { // J'annonce que je veux transporter la carte

		if (event.getDragboard().hasImage() && spell1.get(0) == null
				&& parentController.getAllyHandController().getDraggedCard() instanceof Sorts) {

			event.acceptTransferModes(TransferMode.COPY_OR_MOVE); // On annonce quelle case va gérer le transfert

		}

		event.consume();

	}

	public void dragEntered(DragEvent event) { // Je montre sur quelle case il s'apprête à poser la carte

		if (event.getDragboard().hasImage() && spell1.get(0) == null
				&& parentController.getAllyHandController().getDraggedCard() instanceof Sorts) {

			((ImageView) event.getSource()).setImage(new Image("/resources/Images/dos.jpg"));
			// Image pour indiquer où va se dérouler le transfert

		}

		event.consume();

	}

	public void dragExited(DragEvent event) { // Je supprime l'image si l'utilisateur quitte la case

		if (!event.isDropCompleted() && spell1.get(0) == null) {

			((ImageView) event.getSource()).setImage(null);
			event.consume();

		}

	}

	public void dragDropped(DragEvent event) { // J'assigne la carte à la case choisie

		Dragboard db = event.getDragboard();
		boolean success = false;
		
		if (db.hasImage() && spell1.get(0) == null
				&& parentController.getAllyHandController().getDraggedCard() instanceof Sorts) {

			spell1.set(0, parentController.getAllyHandController().getDraggedCard());
			parentController.getAllyHandController().getAllyHand()
					.set(parentController.getAllyHandController().getDraggedNumber(), null);
			Sorts myCard = (Sorts) parentController.getAllyHandController().getDraggedCard();
			myCard.applySpell(parentController.getBoardController().getAllyBoard(),
					parentController.getBoardController().getEnnemyBoard(), parentController);
			success = true;
			parentController.getAllyHandController().afficherHand();
			parentController.getBoardController().afficherBoard();
			afficherSpells();

		}

		event.setDropCompleted(success); // Indique que le transfert s'est bien déroulé
		event.consume();

	}

}
