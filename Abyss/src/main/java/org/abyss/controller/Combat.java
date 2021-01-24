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
		if (parentController.getMulti()) {
			parentController.getEnnemyController().getInformationController().ecrire("reset"+"\n"+"Combat");
			parentController.getEnnemyController().getInformationController().lireLigne();
		}
		parentController.getTourController().visible(false);
		
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < parentController.getBoardController().getAllyBoard().size(); i++) {
					
					if (parentController.getBoardController().getAllyBoard().get(i) != null) {

						Combattant carte1 = (Combattant) parentController.getBoardController().getAllyBoard().get(i);
						if (parentController.getBoardController().getEnnemyBoard().get(i) != null) {

							Combattant carte2 = (Combattant) parentController.getBoardController().getEnnemyBoard().get(i);
							parentController.setEnnemyPv(parentController.getEnnemyPv() + carte1.combat(carte2)); // On enlève la différence aux pv de l'ennemi
							parentController.setAllyPv(parentController.getAllyPv() + carte2.combat(carte1)); // Pareil pour les pv de l'allié
							parentController.getBoardController().translate3(i, 100);
							parentController.getBoardController().translate4(i, -100);
							
							if (parentController.getMulti()) {
								parentController.getEnnemyController().getBoardController().translate3(i, 100);
								parentController.getEnnemyController().getBoardController().translate4(i, -100);
							}

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							parentController.getBoardController().translate3(i, -9);
							parentController.getBoardController().translate4(i, 9);
							parentController.getAnimationController().showSpark(i);
							
							if (parentController.getMulti()) {
								parentController.getEnnemyController().getBoardController().translate3(i, -9);
								parentController.getEnnemyController().getBoardController().translate4(i, 9);
								parentController.getEnnemyController().getAnimationController().showSpark(i);
							}

							try {
								Thread.sleep(350);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							parentController.getAnimationController().hideSpark(i);
							
							if (parentController.getMulti()) {
								parentController.getEnnemyController().getAnimationController().hideSpark(i);
							}
							
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									
									parentController.afficherHp();
									parentController.getInformationController().ecrire("Dégâts infligés : "+ carte1.combat(carte2) + "\n" 
									+ "Dégâts reçus : " + carte2.combat(carte1));
									parentController.getInformationController().lireLigne();
									
									if (parentController.getMulti()) {
										parentController.getEnnemyController().afficherHp();
										parentController.getEnnemyController().getInformationController().
										ecrire("Dégâts reçus : "+ carte1.combat(carte2) + "\n" + "Dégâts infligés : " + carte2.combat(carte1));
										parentController.getEnnemyController().getInformationController().lireLigne();
									}
								}
							});

						} else {

							parentController.setEnnemyPv(parentController.getEnnemyPv() - carte1.getAtt()); // S'il n'y a personne on attaque directement les pv
							parentController.getBoardController().translate3(i, 100);
							
							if (parentController.getMulti()) {
								parentController.getEnnemyController().getBoardController().translate3(i, 100);
							}

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							parentController.getBoardController().translate3(i, -100);
							
							if (parentController.getMulti()) {
								parentController.getEnnemyController().getBoardController().translate3(i, -100);
							}

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
									
									if (parentController.getMulti()) {
										parentController.getEnnemyController().getInformationController().ecrire("Dégâts reçus : " + carte1.getAtt());
										parentController.getEnnemyController().getInformationController().lireLigne();
									}
								}
							});

						}

					} else {

						if (parentController.getBoardController().getEnnemyBoard().get(i) != null) {

							Combattant carte2 = (Combattant) parentController.getBoardController().getEnnemyBoard().get(i);
							parentController.setAllyPv(parentController.getAllyPv() - carte2.getAtt()); // S'il n'y a personne l'ennemi attaque directement les pv
							parentController.getBoardController().translate4(i, -100);
							
							if (parentController.getMulti()) {
								parentController.getEnnemyController().getBoardController().translate4(i, -100);
							}

							try {
								Thread.sleep(200);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							parentController.getBoardController().translate4(i, 100);
							
							if (parentController.getMulti()) {
								parentController.getEnnemyController().getBoardController().translate4(i, 100);
							}

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
									
									if (parentController.getMulti()) {
										parentController.getEnnemyController().getInformationController().ecrire("Dégâts infligés : " + carte2.getAtt());
										parentController.getEnnemyController().getInformationController().lireLigne();
									}
								}
							});

						}

					}

				}

				for (int i = 0; i < parentController.getBoardController().getAllyBoard().size(); i++) {
					
					parentController.getBoardController().translate3(i, 0);
					parentController.getBoardController().translate4(i, 0);
					
					if (parentController.getMulti()) {
						parentController.getEnnemyController().getBoardController().translate3(i, 0);
						parentController.getEnnemyController().getBoardController().translate4(i, 0);
					}
				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						parentController.getInformationController().ecrire("reset"+"\n");
						parentController.getInformationController().ecrire("Phase de retrait");
						parentController.getInformationController().lireLigne();
						parentController.getTourController().afficherTour("Phase de retrait");
						parentController.getTourController().setTour(Phase.PhaseDeRetrait);
						if (parentController.getMulti()) {
							parentController.getEnnemyController().getInformationController().ecrire("reset"+"\n");
							parentController.getEnnemyController().getInformationController().ecrire("Phase de retrait");
							parentController.getEnnemyController().getInformationController().lireLigne();
							parentController.getEnnemyController().getTourController().afficherTour("Phase de retrait");
							parentController.getEnnemyController().getTourController().setTour(Phase.PhaseDeRetrait);
						}
						parentController.mort();
					}
				});
			}
		}).start();

	}
}
