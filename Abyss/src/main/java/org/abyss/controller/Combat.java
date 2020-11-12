package org.abyss.controller;

import org.abyss.cards.Combattant;

import javafx.application.Platform;

public class Combat {
	
	private MainController parentController;
	
	
	public Combat(MainController parent) {
		parentController = parent;
	}
	
	public void fight() {

		parentController.getInformationController().ecrire("reset"+"\n"+"Combat");
		parentController.getInformationController().lireLigne();
		parentController.getTourController().visible(false);

		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < parentController.getBoardController().getAllyBoard().size(); i++) {

					if (parentController.getBoardController().getAllyBoard().get(i) != null) {

						Combattant carte1 = (Combattant) parentController.getBoardController().getAllyBoard().get(i);
						if (parentController.getBoardController().getEnnemyBoard().get(i) != null) {

							Combattant carte2 = (Combattant) parentController.getBoardController().getEnnemyBoard().get(i);
							parentController.setEnnemyPv(parentController.getEnnemyPv() + carte1.combat(carte2)); // On enlève la différence aux pv de l'ennemi
							parentController.setAllyPv(parentController.getEnnemyPv() + carte2.combat(carte1)); // Pareil pour les pv de l'allié
							parentController.getBoardController().translate3(i, 100);
							parentController.getBoardController().translate4(i, -100);

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							parentController.getBoardController().translate3(i, -9);
							parentController.getBoardController().translate4(i, 9);
							parentController.getAnimationController().showSpark(i);

							try {
								Thread.sleep(350);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							parentController.getAnimationController().hideSpark(i);
							
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									parentController.afficherHp();
									parentController.getInformationController().ecrire("Dégâts infligés : "+ carte1.combat(carte2) + "\n" 
									+ "Dégâts reçus : "+ carte2.combat(carte1));
									parentController.getInformationController().lireLigne();
								}
							});

						} else {

							parentController.setEnnemyPv(parentController.getEnnemyPv() - carte1.getAtt()); // S'il n'y a personne on attaque directement les pv
							parentController.getBoardController().translate3(i, 100);

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							parentController.getBoardController().translate3(i, -100);

							try {
								Thread.sleep(350);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									parentController.afficherHp();
									parentController.getInformationController().ecrire("Dégâts infligés : " + carte1.getAtt());
									parentController.getInformationController().lireLigne();
								}
							});

						}

					} else {

						if (parentController.getBoardController().getEnnemyBoard().get(i) != null) {

							Combattant carte2 = (Combattant) parentController.getBoardController().getEnnemyBoard().get(i);
							parentController.setAllyPv(parentController.getAllyPv() - carte2.getAtt()); // S'il n'y a personne l'ennemi attaque directement les pv
							parentController.getBoardController().translate4(i, -100);

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							parentController.getBoardController().translate4(i, 100);

							try {
								Thread.sleep(350);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									parentController.afficherHp();
									parentController.getInformationController().ecrire("Dégâts reçus : " + carte2.getAtt());
									parentController.getInformationController().lireLigne();
								}
							});

						}

					}

//					Platform.runLater(new Runnable() {
//						@Override
//						public void run() {
//							allyHp.setTextFill(Color.RED);
//							ennemyHp.setTextFill(Color.RED);
//						}
//					});

				}

				for (int i = 0; i < parentController.getBoardController().getAllyBoard().size(); i++) {
					parentController.getBoardController().translate3(i, 0);
					parentController.getBoardController().translate4(i, 0);
				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
//						allyHp.setTextFill(Color.web("#36d353"));
//						ennemyHp.setTextFill(Color.web("#36d353"));
						parentController.getTourController().afficherTour("Retrait !");
						parentController.getTourController().setTour(Phase.PhaseDeRetrait);
						parentController.mort();
						parentController.getTourController().stade();
					}
				});
			}
		}).start();

	}
}
