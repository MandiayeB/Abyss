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
	
	private MainController parentController;
	private Phase tour;
	private Boolean order;

	public Button getPhase() {
		return phase;
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
	
	@FXML
	private void next(ActionEvent event) {
		phase.setText("Fin de tour");
		if (parentController.getMulti()) {
			parentController.getEnnemyController().getTourController().getPhase().setText("Fin de tour");
		} else {
			parentController.getDialogueController().dialogueCommencement();
		}
		stade();
	}
	
	public void stade() {

		switch (tour) {

		case TourEnnemi:
			
			if (!parentController.getMulti()) {
				parentController.selectedDifficulty(parentController).play();
			}
			break;

		case PhaseDeStrategie:
			
			if (parentController.getMulti()) {
				parentController.getEnnemyController().getTourController().afficherTour.setText("Tour ennemi");
				parentController.getEnnemyController().getInformationController()
				.ecrire("Nouveau tour " + parentController.getGame().getCurrentPlayer().getName());
				parentController.getEnnemyController().getInformationController().lireLigne();
				
				parentController.getInformationController().ecrire("Nouveau tour " + parentController.getGame().getCurrentPlayer().getName());
				parentController.getInformationController().lireLigne();
			} else {
				parentController.getInformationController().ecrire("Vous pouvez jouer");
				parentController.getInformationController().lireLigne();
			}
			
			afficherTour.setText("Tour de Stratégie");
			tour = Phase.Transition;
			break;

		case Transition:
			
			if (parentController.getMulti()) {
				
				parentController.getInformationController().ecrire("Fin de tour " + parentController.getGame().getCurrentPlayer().getName());
				parentController.getInformationController().lireLigne();
				
				parentController.getEnnemyController().getInformationController()
				.ecrire("Fin de tour " + parentController.getGame().getCurrentPlayer().getName());
				parentController.getEnnemyController().getInformationController().lireLigne();
				
			} else {
				
				parentController.getInformationController().ecrire("Fin de tour");
				parentController.getInformationController().lireLigne();
			}
			
			if (order) {
				
				afficherTour.setText("Tour Ennemi");
				tour = Phase.TourEnnemi;
				if (parentController.getMulti()) {
					
					parentController.getGame().changeTurn();
					
					parentController.getInformationController().ecrire("Nouveau tour " + parentController.getGame().getCurrentPlayer().getName());
					parentController.getInformationController().lireLigne();
					
					parentController.getEnnemyController().getInformationController()
					.ecrire("Nouveau tour " + parentController.getGame().getCurrentPlayer().getName());
					parentController.getEnnemyController().getInformationController().lireLigne();
					
					parentController.getEnnemyController().getTourController().afficherTour.setText("Phase de Stratégie");
					parentController.getEnnemyController().getTourController().setTour(Phase.Transition);
					parentController.getEnnemyController().getTourController().visible(true);
					visible(false);
				}
			} else {
				
				afficherTour.setText("Phase de Combat");
				tour = Phase.PhaseDeCombat;
				if (parentController.getMulti()) {
					
					parentController.getInformationController().ecrire("Fin de tour " + parentController.getGame().getCurrentPlayer().getName());
					parentController.getInformationController().lireLigne();
					
					parentController.getEnnemyController().getInformationController()
					.ecrire("Fin de tour " + parentController.getGame().getCurrentPlayer().getName());
					parentController.getEnnemyController().getInformationController().lireLigne();
					
					parentController.getEnnemyController().getTourController().afficherTour.setText("Phase de Combat");
					parentController.getEnnemyController().getTourController().setTour(Phase.PhaseDeCombat);
					parentController.getEnnemyController().getTourController().visible(false);
					visible(false);
				}
				
			}
			if (!parentController.getMulti()) {
				stade();
			} else if (!order) {
				stade();
			}
			break;

		case PhaseDeCombat:
			
			parentController.getAnimationController().bringUpSpark();
			if (parentController.getMulti()) {
				parentController.getEnnemyController().getAnimationController().bringUpSpark();
			}
			Combat c = new Combat(parentController);
			c.fight();
			break;

		case PhaseDeRetrait:
			
			parentController.getAnimationController().bringDownSpark();
			if (parentController.getMulti()) {
				parentController.getEnnemyController().getAnimationController().bringDownSpark();
			}
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
