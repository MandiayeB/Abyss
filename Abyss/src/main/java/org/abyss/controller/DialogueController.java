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
		dialogue.add("Pr�pare toi � \n souffrir !");// 1
		dialogue.add("Arr�te tes sottises !");

		dialogue.add("Si tu penses que \n cela va suffir, \n tu te mets le \n doigt dans l'oeil...");// 2
		dialogue.add("�a ne va pas \n se passer \n comme �a !");

		dialogue.add("Ce n'est une \n �gratinure.");// 3
		dialogue.add("Je me batterai \n jusqu'au bout !");

		dialogue.add("Passons aux \n choses s�rieuses !");// 4
		dialogue.add("Je triompherai \n de l'Abyss quoi \n qu'il en co�te !");

		dialogue.add("Arggggg...");// 5
		dialogue.add("Je n'abandonnerai pas !");
	}

	public void afficherBulleEnnemy(int id) {
		if(parlerE == true) {
			BulleEnnemy.setVisible(true);
			TxtEnnemy.setVisible(true);
			TxtEnnemy.setText(dialogue.get(id));
			parlerE = false;
		}
	}

	public void cacherBullyEnnemy() {

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					Thread.sleep(2500);

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

	public void cacherBulleAlly() {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {

					Thread.sleep(2500);

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

		if (parentController.getAllyPv() == 3000 && parentController.getEnnemyPv() == 3000) {
			afficherBulleEnnemy(0);

			new Thread(new Runnable() {

				@Override
				public void run() {
					try {

						Thread.sleep(1000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Platform.runLater(new Runnable() {

						@Override
						public void run() {

							cacherBullyEnnemy();
							afficherBulleAlly(1);

						}
					});
					try {

						Thread.sleep(1000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					cacherBulleAlly();

				}
			}).start();

		}
		if(parentController.getAllyPv() < 2251 && hideA == 0) {
			parlerA = true;
			afficherBulleAlly(3);
			cacherBulleAlly();
			hideA = 1;
		}
		if(parentController.getEnnemyPv() < 2251 && hideE == 0) {
			parlerE = true;
			afficherBulleEnnemy(2);
			cacherBullyEnnemy();
			hideE = 1;
		}
		if(parentController.getEnnemyPv() < 1501 && hideE == 1) {
			parlerE = true;
			afficherBulleEnnemy(4);
			cacherBullyEnnemy();
			hideE = 2;
		}
		if(parentController.getAllyPv() < 1501 && hideA == 1) {
			parlerA = true;
			afficherBulleAlly(5);
			cacherBulleAlly();
			hideA = 2;
		}
		if(parentController.getEnnemyPv() < 751 && hideE == 2) {
			parlerE = true;
			afficherBulleEnnemy(6);
			cacherBullyEnnemy();
			hideE = 3;
		}
		if(parentController.getAllyPv() < 751 && hideA == 2) {
			parlerA = true;
			afficherBulleAlly(7);
			cacherBulleAlly();
			hideA = 3;
		}
		if(parentController.getEnnemyPv() == 0 && hideE == 3) {
			parlerE = true;
			afficherBulleEnnemy(8);
			cacherBullyEnnemy();
			hideE = 4;
		}
		if(parentController.getAllyPv() == 0 && hideA == 3) {
			parlerA = true;
			afficherBulleAlly(9);
			cacherBulleAlly();
			hideA = 4;
		}
	}
}