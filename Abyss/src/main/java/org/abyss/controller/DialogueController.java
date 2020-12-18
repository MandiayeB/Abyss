package org.abyss.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
	
	public DialogueController() {

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
		TxtEnnemy.setVisible(false);
	}
	
	public void afficherBulleAlly() {
		BulleAlly.setVisible(true);
		TxtAlly.setVisible(true);
	
	}
	
	public void cacherBulleAlly() {
		BulleAlly.setVisible(false);
		TxtAlly.setVisible(false);
	
	}
	
	public void commencementEnnemy() {
		afficherBulleEnnemy();
		TxtEnnemy.setText("Prepare toi a souffrir");
	}
	
	public void quartEnnemy() {
		afficherBulleEnnemy();
		TxtEnnemy.setText("Si tu pense que cela va suffir, tu te met le doigts dans l'oeil");
	}
	public void moitierEnnemy() {
		afficherBulleEnnemy();
		TxtEnnemy.setText("Ce n'est une égratinure");
	}
	public void troiquartEnnemy() {
		afficherBulleEnnemy();
		TxtEnnemy.setText("Passons aux choses serieuse ! ");
	}
	public void meursEnnemy() {
		afficherBulleEnnemy();
		TxtEnnemy.setText("Arggggg...");
	}
	//ALLY
	public void commencementAlly() {
		afficherBulleAlly();
		TxtAlly.setText("A nous deux !");
	}
	
	public void quartAlly() {
		afficherBulleAlly();
		TxtAlly.setText("Ca ne va pas ce passer comme ca ! ");
	}
	public void moitierAlly() {
		afficherBulleAlly();
		TxtAlly.setText("Je me battrerai jusqu'au bout");
	}
	public void troiquartAlly() {
		afficherBulleAlly();
		TxtAlly.setText("Je triompherai de l'abysse quoi qu'il en coute");
	}
	public void meursAlly() {
		afficherBulleAlly();
		TxtAlly.setText("Je n'abonerai pas ");
	}
	
	public void dialogueEffet() {
		
		if(parentController.getAllyPv() == 3000 && parentController.getEnnemyPv()== 3000) {
			commencementAlly();
	    	commencementEnnemy();
		}
			new Thread(new Runnable() {

	            @Override
	            public void run() {
	                try {
	                	
	                    Thread.sleep(2950);
	                   
	                    Platform.runLater(new Runnable() {
	                    
	                        @Override
	                        public void run() {
	                        	
	                        	cacherBulleAlly();
	         	        		cacherBullyEnnemy();
	                           
	                        }
	                    });
	                    
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	        		
	            }
	        }).start();
		}


}
