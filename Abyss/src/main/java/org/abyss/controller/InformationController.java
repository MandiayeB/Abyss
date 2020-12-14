package org.abyss.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.abyss.cards.Combattant;
import org.abyss.cards.spells.Sorts;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class InformationController implements Initializable {

	@FXML
	private StackPane information;
	@FXML
	private ImageView imageZoom;
	@FXML
	private Label cardAtt;
	@FXML
	private Label cardHp;
	@FXML
	private Label notif;
	@FXML
	private Label spellDetails;
	@FXML
	private Label cardName;

	private MainController parentController;
	private boolean nouveau;

	public void setNouveau(boolean nouveau) {
		this.nouveau = nouveau;
	}

	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}

	public InformationController() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nouveau = true;
	}

	public void afficherCarte(Image image, int number, String id, String name) {

		if (parentController.getTourController().getTour() == Phase.Transition) {
			// Effet autour des cartes

			DropShadow borderGlow = new DropShadow();
			borderGlow.setOffsetY(0f);
			borderGlow.setOffsetX(0f);
			borderGlow.setWidth(70);
			borderGlow.setHeight(70);
			borderGlow.setColor(Color.YELLOW);

			if (id.contains("allyCard")) {
				parentController.getAllyHandController().effect(borderGlow, id, number);
			} else if (id.contains("allySpell")) {
				parentController.getSpellController().effect(borderGlow, true);
			} else if (id.contains("ally")) {
				parentController.getBoardController().effect(borderGlow, id, number);
			} else if (id.contains("ennemySpell")) {
				borderGlow.setColor(Color.BLUE);
				parentController.getSpellController().effect(borderGlow, false);
			} else {
				borderGlow.setColor(Color.BLUE);
				parentController.getBoardController().effect(borderGlow, id, number);
			}

		}

		if (id.contains("allyCard")) {
			parentController.getAllyHandController().translate1(number, -10);
		}

		if (image != null) {
			notif.setVisible(false);

			if (id.contains("allySpell")) {
				spellDetails.setText((((Sorts) parentController.getSpellController().getSpell1().get(0)).getEffect()));
			} else if (id.contains("ally")) {

				if (id.contains("Card")
						&& parentController.getAllyHandController().getAllyHand().get(number) instanceof Sorts) {
					spellDetails.setText(
							((Sorts) parentController.getAllyHandController().getAllyHand().get(number)).getEffect());
				} else if (id.contains("Card")
						&& parentController.getAllyHandController().getAllyHand().get(number) != null) {
					cardAtt.setText(Integer
							.toString(((Combattant) parentController.getAllyHandController().getAllyHand().get(number))
									.getAtt()));
					cardHp.setText(Integer.toString(
							((Combattant) parentController.getAllyHandController().getAllyHand().get(number)).getHp()));
				} else if (parentController.getBoardController().getAllyBoard().get(number) != null) {
					cardAtt.setText(Integer.toString(
							((Combattant) parentController.getBoardController().getAllyBoard().get(number)).getAtt()));
					cardHp.setText(Integer.toString(
							((Combattant) parentController.getBoardController().getAllyBoard().get(number)).getHp()));
				}

			} else {

				if (id.contains("ennemySpell")) {
					spellDetails
							.setText((((Sorts) parentController.getSpellController().getSpell2().get(0)).getEffect()));
				} else if (parentController.getBoardController().getEnnemyBoard().get(number) != null) {
					cardAtt.setText(Integer
							.toString(((Combattant) parentController.getBoardController().getEnnemyBoard().get(number))
									.getAtt()));
					cardHp.setText(Integer.toString(
							((Combattant) parentController.getBoardController().getEnnemyBoard().get(number)).getHp()));
				}

			}
			imageZoom.setImage(image);
			cardName.setText(name);
			// Affiche la carte sélectionnée en grand
		}

	}

	public void cancelZoom(int number, String id) {

		if (id.contains("allyCard")) {
			parentController.getAllyHandController().translate1(number, 0);
			parentController.getAllyHandController().effect(null, id, number);
		} else if (!id.contains("Spell")) {
			parentController.getBoardController().effect(null, id, number);
		} else {
			parentController.getSpellController().effect(null, true);
			parentController.getSpellController().effect(null, false);
		}

		imageZoom.setImage(null);
		cardAtt.setText(null);
		cardHp.setText(null);
		spellDetails.setText(null);
		notif.setVisible(true);
		cardName.setText(null);

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
			System.out.println("Impossible d'écrire");
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

}
