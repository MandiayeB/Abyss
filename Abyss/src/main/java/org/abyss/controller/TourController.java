package org.abyss.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TourController implements Initializable {
	
	@FXML
	private Button phase;
	@FXML
	private Label afficherTour;
	
	private Phase tour;
	private Boolean order;


	private MainController parentController;
	
	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}
	
	public TourController() {
		tour = Phase.TourEnnemi;
		order = false;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
			
	}
	
	public void changeButton() {
		
		phase.setVisible(true);
		phase.setText("Start");
		afficherTour.setText("Appuyez sur Start");
	}
	
	@FXML
	private void next(ActionEvent event) {
		stade();
		parentController.getDialogueController().dialogueCommencement();
	}
	
	public Phase getTour() {
		return tour;
	}

	public void setTour(Phase tour) {
		this.tour = tour;
	}

	public Boolean getOrder() {
		return order;
	}

	public void setOrder(Boolean order) {
		this.order = order;
	}
	
	public void stade() {

		switch (tour) {

		case TourEnnemi:
			System.out.println(tour);
			System.out.println("-----------------------");
			Ennemy e = new Ennemy(parentController);
			e.play();
			break;

		case PhaseDeStrategie:
			parentController.getInformationController().ecrire("Vous pouvez jouer");
			parentController.getInformationController().lireLigne();
			System.out.println(tour);
			System.out.println("-----------------------");
			afficherTour.setText("Tour de Stratégie");
			tour = Phase.Transition;
			break;

		case Transition:
			parentController.getInformationController().ecrire("Fin de tour");
			parentController.getInformationController().lireLigne();
			if (order) {
				afficherTour.setText("Tour Ennemi");
				tour = Phase.TourEnnemi;
			} else if (!order) {
				afficherTour.setText("Phase de Combat");
				tour = Phase.PhaseDeCombat;
			}
			stade();
			break;

		case PhaseDeCombat:
			System.out.println(tour);
			System.out.println("-----------------------");
			parentController.getAnimationController().bringUpSpark();
			Combat c = new Combat(parentController);
			c.fight();
			break;

		case PhaseDeRetrait:
			System.out.println(tour);
			System.out.println("-----------------------");
			parentController.getAnimationController().bringDownSpark();
			parentController.getBoardController().retrait();
			break;

		}

	}
	
	public void visible(Boolean bool) {
		phase.setVisible(bool);
	}
	
	public void button(String texte) {
		phase.setText(texte);
	}
	
	public void afficherTour(String texte) {
		afficherTour.setText(texte);
	}
	
}
