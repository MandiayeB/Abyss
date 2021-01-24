package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class DialogueController implements Initializable {

	@FXML
	ImageView BulleEnnemy;
	@FXML
	ImageView BulleAlly;
	@FXML
	Label TxtAlly;
	@FXML
	Label TxtEnnemy;
	
	private boolean parlerE = true;
	private boolean parlerA = true;


	private int hideA = 0;
	private int hideE = 0;
	


	private ArrayList<String> dialogue;

	private MainController parentController;

	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}

	public DialogueController() {

	}
	
	public int getHideA() {
		return hideA;
	}

	public void setHideA(int hideA) {
		this.hideA = hideA;
	}

	public int getHideE() {
		return hideE;
	}

	public void setHideE(int hideE) {
		this.hideE = hideE;
	}
	public boolean isParlerE() {
		return parlerE;
	}

	public void setParlerE(boolean parlerE) {
		this.parlerE = parlerE;
	}

	public boolean isParlerA() {
		return parlerA;
	}

	public void setParlerA(boolean parlerA) {
		this.parlerA = parlerA;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		dialogue = new ArrayList<>();
		dialogue.add("Prépare toi à \n souffrir !");// 1
		dialogue.add("Arrête tes \n sottises !");

		dialogue.add("Si tu penses que \n cela va suffir, \n tu te mets le \n doigt dans l'oeil...");// 2
		dialogue.add("Ça ne va pas \n se passer \n comme ça !");

		dialogue.add("Ce n'est qu'une \n égratinure.");// 3
		dialogue.add("Je me batterai \n jusqu'au bout !");

		dialogue.add("Passons aux \n choses sérieuses !");// 4
		dialogue.add("Je triompherai \n de l'Abyss quoi \n qu'il en coûte !");

		dialogue.add("Arggggg...");// 5
		dialogue.add("Je \n n'abandonnerai \n pas !");
	}

	public void afficherBulleEnnemy(int id) {
		if(parlerE == true) {
			BulleEnnemy.setVisible(true);
			TxtEnnemy.setVisible(true);
			TxtEnnemy.setText(dialogue.get(id));
			parlerE = false;
		}
	}

	public void cacherBullyEnnemy(int id) {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					Thread.sleep(id);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(new Runnable() {

					@Override
					public void run() {

						BulleEnnemy.setVisible(false);
						TxtEnnemy.setVisible(false);

					}
				});

			}
		}).start();
	}

	public void afficherBulleAlly(int id) {
		if(parlerA == true) {
			BulleAlly.setVisible(true);
			TxtAlly.setVisible(true);
			TxtAlly.setText(dialogue.get(id));
			parlerA=false;
		}
	}

	public void cacherBulleAlly(int id) {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					Thread.sleep(id);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Platform.runLater(new Runnable() {

					@Override
					public void run() {

						BulleAlly.setVisible(false);
						TxtAlly.setVisible(false);

					}
				});

			}
		}).start();

	}
	
	public void dialogueCommencement() {

		if (parentController.getAllyPv() == 1500 && parentController.getEnnemyPv() == 1500) {
			afficherBulleEnnemy(0);

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {

						Thread.sleep(1500);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Platform.runLater(new Runnable() {

						@Override
						public void run() {

							cacherBullyEnnemy(50);
							afficherBulleAlly(1);

						}
					});
					try {

						Thread.sleep(1000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					cacherBulleAlly(1000);

				}
			}).start();

		}
		if(parentController.getAllyPv() < 1301 && hideA == 0) {
			parlerA = true;
			afficherBulleAlly(3);
			cacherBulleAlly(2500);
			hideA = 1;
		}
		if(parentController.getEnnemyPv() < 1301 && hideE == 0) {
			parlerE = true;
			afficherBulleEnnemy(2);
			cacherBullyEnnemy(2500);
			hideE = 1;
		}
		if(parentController.getEnnemyPv() < 1001 && hideE == 1) {
			parlerE = true;
			afficherBulleEnnemy(4);
			cacherBullyEnnemy(2500);
			hideE = 2;
		}
		if(parentController.getAllyPv() < 1001 && hideA == 1) {
			parlerA = true;
			afficherBulleAlly(5);
			cacherBulleAlly(2500);
			hideA = 2;
		}
		if(parentController.getEnnemyPv() < 501 && hideE == 2) {
			parlerE = true;
			afficherBulleEnnemy(6);
			cacherBullyEnnemy(2500);
			hideE = 3;
		}
		if(parentController.getAllyPv() < 501 && hideA == 2) {
			parlerA = true;
			afficherBulleAlly(7);
			cacherBulleAlly(2500);
			hideA = 3;
		}
		if(parentController.getEnnemyPv() == 101 && hideE == 3) {
			parlerE = true;
			afficherBulleEnnemy(8);
			cacherBullyEnnemy(2500);
			hideE = 4;
		}
		if(parentController.getAllyPv() == 101 && hideA == 3) {
			parlerA = true;
			afficherBulleAlly(9);
			cacherBulleAlly(2500);
			hideA = 4;
		}
	}
}
