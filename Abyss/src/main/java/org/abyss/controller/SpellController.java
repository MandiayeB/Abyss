package org.abyss.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import org.abyss.cards.spells.Sorts;

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

public class SpellController implements Initializable {

	@FXML
	private ImageView allySpell;
	@FXML
	private ImageView ennemySpell;

	private MainController parentController;
	private Cards spell1;
	private Cards spell2;

	public void setSpell1(Cards spell1) {
		this.spell1 = spell1;
	}

	public void setSpell2(Cards spell2) {
		this.spell2 = spell2;
	}

	public Cards getSpell1() {
		return spell1;
	}
	
	public Cards getSpell2() {
		return spell2;
	}
	
	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}

	public SpellController() {
		spell1 = null;
		spell2 = null;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void afficherSpells() {
		
		if (spell1 != null) {
			allySpell.setImage(spell1.getImage());
			if (parentController.getMulti()) {parentController.getEnnemyController().getSpellController().ennemySpell.setImage(spell1.getImage());}
		} else {
			allySpell.setImage(null);
			if (parentController.getMulti()) {parentController.getEnnemyController().getSpellController().ennemySpell.setImage(null);}
		}
		
		if (!parentController.getMulti()) {
			if (spell2 != null) {
				ennemySpell.setImage(spell2.getImage());
			} else {
				ennemySpell.setImage(null);  
			}
		}
		
	}
	
	public void cacherSpells() {
		
		allySpell.setImage(new Image("/resources/Images/tornade.gif"));
		if (spell1 != null) {
			parentController.getAllyHandController().getAllyDeck().add(spell1);
			spell1 = null;
		}
		ennemySpell.setImage(new Image("/resources/Images/tornade.gif"));
		parentController.getEnnemyHandController().getEnnemyDeck().add(spell2);
		spell2 = null;
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
			if (spell1 != null)
				name = spell1.getName();
		} else {
			if (spell2 != null)
				name = spell2.getName();
		}
		parentController.getInformationController().afficherCarte(image, 0, node.getId(), name);

	}

	public void cancelZoom(MouseEvent event) {

		Node node = (Node) event.getSource();
		parentController.getInformationController().cancelZoom(0, node.getId());

	}

	public void dragOver(DragEvent event) { // J'annonce que je veux transporter la carte

		if (event.getDragboard().hasImage() && spell1 == null
				&& parentController.getAllyHandController().getDraggedCard() instanceof Sorts) {

			event.acceptTransferModes(TransferMode.COPY_OR_MOVE); // On annonce quelle case va g�rer le transfert

		}

		event.consume();

	}

	public void dragEntered(DragEvent event) { // Je montre sur quelle case il s'appr�te � poser la carte

		if (event.getDragboard().hasImage() && spell1 == null
				&& parentController.getAllyHandController().getDraggedCard() instanceof Sorts) {

			if (parentController.getMulti()) {
				if (parentController.getPlayer().getName() == "Joueur 2") {
					((ImageView) event.getSource()).setImage(new Image("/resources/Images/Ice.png"));
				} else {
					((ImageView) event.getSource()).setImage(new Image("/resources/Images/Jade.png"));
				}
			} else {
				((ImageView) event.getSource()).setImage(new Image("/resources/Images/Jade.png"));
			}
		}

		event.consume();

	}

	public void dragExited(DragEvent event) { // Je supprime l'image si l'utilisateur quitte la case

		if (!event.isDropCompleted() && spell1 == null) {

			((ImageView) event.getSource()).setImage(null);
			event.consume();

		}

	}

	public void dragDropped(DragEvent event) { // J'assigne la carte � la case choisie

		Dragboard db = event.getDragboard();
		boolean success = false;
		
		if (db.hasImage() && spell1 == null
				&& parentController.getAllyHandController().getDraggedCard() instanceof Sorts) {

			spell1 = parentController.getAllyHandController().getDraggedCard();
			parentController.getAllyHandController().getAllyHand()
					.set(parentController.getAllyHandController().getDraggedNumber(), null);
			Sorts myCard = (Sorts) parentController.getAllyHandController().getDraggedCard();
			myCard.applySpell(parentController.getBoardController().getAllyBoard(),
					parentController.getBoardController().getEnnemyBoard(), parentController);
			success = true;
			parentController.getAllyHandController().afficherHand();
			parentController.getBoardController().afficherBoard();
			afficherSpells();
			
			if (parentController.getMulti()) {
				parentController.getEnnemyController().getBoardController().afficherBoard();
				parentController.getEnnemyController().getSpellController().afficherSpells();
			}

		}

		event.setDropCompleted(success); // Indique que le transfert s'est bien d�roul�
		event.consume();

	}
	
	public void effect(DropShadow glow, Boolean bool) {
		
		if (bool) {
			allySpell.setEffect(glow);
		} else {
			ennemySpell.setEffect(glow);
		}
	
	}

}
