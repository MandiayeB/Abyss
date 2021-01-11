package org.abyss.ennemy;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.abyss.cards.Cards;
import org.abyss.cards.Combattant;
import org.abyss.cards.spells.Sorts;
import org.abyss.controller.Element;
import org.abyss.controller.MainController;
import org.abyss.controller.Phase;

import javafx.application.Platform;

public class EnnemyEasy implements IEnnemy {
	
	private MainController parentController;

	public EnnemyEasy(MainController parent) {
		parentController = parent;
	}

	public void play() {

		parentController.getInformationController().ecrire("Tour ennemi");
		parentController.getInformationController().setNouveau(false);
		parentController.getInformationController().lireLigne();
		parentController.getTourController().afficherTour("Tour Ennemi");
		parentController.getTourController().visible(false);
		HashMap<Integer, Sorts> sorts = new HashMap<>();

		
				int index = 0;
				for (Cards c : parentController.getEnnemyHandController().getEnnemyHand()) {

					if (c != null) {

						if (c instanceof Combattant) {
							// Si la carte est un combattant le pose sur le terrain

							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}

							int save = searchBoard(parentController.getBoardController().getEnnemyBoard());
							parentController.getBoardController().getEnnemyBoard().set(save, c);
							parentController.getEnnemyHandController().getEnnemyHand().set(index, null);
							Element e = new Element();
							e.applyElement(save, parentController.getBoardController().getEnnemyBoard());
							parentController.getBoardController().afficherBoard();

						} else if (c instanceof Sorts) {
							// Si la carte est un sort la pose sur le terrain
							sorts.put(index, (Sorts) c);
						}
						parentController.getEnnemyHandController().afficherHand();
					}
					index++;

				}

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				for (Entry<Integer, Sorts> s : sorts.entrySet()) {
					if (parentController.getSpellController().getSpell2().get(0) == null) {
						if (s.getValue() != null) {
							parentController.getSpellController().getSpell2().set(0, s.getValue());
							parentController.getEnnemyHandController().getEnnemyHand().set(s.getKey(), null);
							parentController.getSpellController().afficherSpells();
							s.getValue().applySpell(parentController.getBoardController().getEnnemyBoard(),
									parentController.getBoardController().getAllyBoard(), parentController);
						}
						parentController.getEnnemyHandController().afficherHand();

						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
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

	public int searchBoard(List<Cards> board) {

		int location = 0;
		for (int i = 0; i < board.size(); i++) {
			if (board.get(i) == null) {
				location = i;
				break;
			}
		}
		return location;

	}

}
