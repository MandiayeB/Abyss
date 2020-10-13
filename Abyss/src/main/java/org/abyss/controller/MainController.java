package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainController  implements Initializable {
	@FXML
	private Label myMessage;
	
	private List<Cards> allCardsGame;
	
	private List<Cards> hand = new ArrayList<>();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		allCardsGame = CardsUtils.getCardsGame();
		
		myMessage.setText(String.valueOf(allCardsGame.size()));
	}
	
//	public void fillHand() {
//		hand.add(allCardsGame.get(0));
//		myMessage.setText(allCardsGame.get(0).getName() + "piochée");
//	}
	



}

