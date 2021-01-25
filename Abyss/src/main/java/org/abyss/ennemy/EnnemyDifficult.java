package org.abyss.ennemy;

import java.util.List;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.cards.spells.Sorts;
import org.abyss.controller.Element;
import org.abyss.controller.MainController;
import org.abyss.controller.Phase;

import javafx.application.Platform;

public class EnnemyDifficult implements IEnnemy {

	private MainController parentController;

	public EnnemyDifficult(MainController parent) {
		parentController = parent;
	}

	public void play() {

		parentController.getInformationController().ecrire("Tour ennemi");
		parentController.getInformationController().setNouveau(false);
		parentController.getInformationController().lireLigne();
		parentController.getTourController().afficherTour("   Tour Ennemi");
		parentController.getTourController().visible(false);

		new Thread(new Runnable() {
			public void run() {

				int index = 0;

				while (true) {

					if (index > 4) {
						parentController.getEnnemyHandController().afficherHand();
						break;
					}
					Cards c = parentController.getEnnemyHandController().getEnnemyHand().get(index);

					if (c != null) {

						if (c instanceof Sorts) {

							if (!emptyFighters(parentController.getEnnemyHandController().getEnnemyHand())) {

								parentController.getEnnemyHandController().getEnnemyHand().add(c);
								parentController.getEnnemyHandController().getEnnemyHand().remove(index);
								index--;

							} else {

								if (parentController.getSpellController().getSpell2() == null) {

									if (c.isDodgeable(parentController)) {

										index++;
										continue;

									}

									try {
										Thread.sleep(500);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}

									parentController.getSpellController().setSpell2(c);
									parentController.getEnnemyHandController().getEnnemyHand().set(index, null);
									parentController.getSpellController().afficherSpells();
									((Sorts) c).applySpell(parentController.getBoardController().getEnnemyBoard(),
											parentController.getBoardController().getAllyBoard(), parentController);

								} else {
									break;
								}

							}

						} else if (c instanceof Combattant) {

							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							int save = searchBestPosition(parentController.getBoardController().getAllyBoard(),
									parentController.getBoardController().getEnnemyBoard());
							
							parentController.getBoardController().getEnnemyBoard().set(save, c);
							parentController.getEnnemyHandController().getEnnemyHand().set(index, null);
							Element e = new Element();
							e.applyElement(save, parentController.getBoardController().getEnnemyBoard());
							parentController.getBoardController().afficherBoard();

						}

					}
					parentController.getEnnemyHandController().afficherHand();
					index++;

				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						parentController.getTourController().button("Fin de tour");
						if (!parentController.getTourController().getOrder()) {
							parentController.getTourController().afficherTour("Tour de Stratégie");
							parentController.getTourController().setTour(Phase.PhaseDeStrategie);
							parentController.getTourController().visible(true);
						} else if (parentController.getTourController().getOrder()) {
							parentController.getTourController().afficherTour("Phase de Combat");
							parentController.getTourController().setTour(Phase.PhaseDeCombat);
						}
						parentController.getInformationController().ecrire("Fin de tour");
						parentController.getInformationController().lireLigne();
						parentController.getTourController().stade();
					}
				});
			}
		}).start();

	}

	public int searchBestPosition(List<Cards> board, List<Cards> board2) {

		int location = 0;
		int forbidden = 10;
		int allyCard = 10000;

		for (int i = 0; i < board.size(); i++) {

			if (i != forbidden) {

				if (board.get(i) == null) {

					if (board2.get(i) != null) {
						forbidden = i;
					} else {
						location = i;
						break;
					}

				} else if (((Combattant) board.get(i)).getHp() < allyCard) {
					
					if (board2.get(i) != null) {
						forbidden = i;
					} else {
						allyCard = ((Combattant) board.get(i)).getHp();
						location = i;
					}
					
				}

			}

		}
		return location;
	}

	public boolean emptyFighters(List<Cards> board) {

		boolean result = true;
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i) instanceof Combattant) {
				result = false;
				break;
			}
		}
		return result;

	}
}
