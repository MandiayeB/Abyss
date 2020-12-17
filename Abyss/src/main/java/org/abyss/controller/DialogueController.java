package org.abyss.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DialogueController implements Initializable{
	
	@FXML 
	ImageView BulleEnnemy;
	@FXML
	ImageView BulleAlly;
	@FXML
	Label TxtAlly;
	@FXML
	Label TxtEnnemy;
	
	private MainController parentController;
	
	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public void afficherBulleEnnemy() {
		BulleEnnemy.setVisible(true);
		TxtEnnemy.setVisible(true);
	
	}
	
	public void cacherBullyEnnemy() {
		BulleEnnemy.setVisible(false);
		BulleEnnemy.setVisible(false);
	}
	
	public void afficherBulleAlly() {
		BulleAlly.setVisible(true);
		TxtAlly.setVisible(true);
	
	}
	
	public void cacherBulleAlly() {
		BulleAlly.setVisible(false);
		BulleAlly.setVisible(false);
	}
	

}
