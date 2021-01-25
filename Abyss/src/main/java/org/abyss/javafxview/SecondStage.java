package org.abyss.javafxview;

import org.abyss.controller.CardsUtils;
import org.abyss.controller.MainController;

import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SecondStage extends Stage {

	public SecondStage(Stage secondStage, MainController ennemyController) {
		
		try {
			
			FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/resources/FXML/Game.fxml"));
			
			Parent game = loaderMain.load();
			Scene sceneGame = new Scene(game);
			
			sceneGame.getStylesheets().add(getClass().getResource("/resources/CSS/style.css").toExternalForm());
			
			Image image = new Image("/resources/Images/curseur.png");
			sceneGame.setCursor(new ImageCursor(image));
			
			MainController mainController = (MainController) loaderMain.getController();
			mainController.setStage(secondStage);
			mainController.setEnnemyController(ennemyController);
			mainController.setPlayer(new Player("Joueur 2"));
			mainController.setMulti(true);
			mainController.player2Heroes();
			mainController.getEnnemyHandController().setCardBackside("/resources/Images/Jade.png");
			mainController.getEnnemyHandController().afficherHand();
			mainController.getAllyHandController().setAllyHand(CardsUtils.fillBoard(5));
			mainController.getAllyHandController().setAllyDeck(CardsUtils.getEnnemyCards());
			mainController.getAllyHandController().piocher();
			ennemyController.setEnnemyController(mainController);
			mainController.getTourController().getInfoSquare().setVisible(false);
			mainController.updateGui();
			
			secondStage.setTitle("Abyss ['Joueur 2']");
			secondStage.getIcons().add(new Image("/resources/Images/logo.png"));
			secondStage.setScene(sceneGame);
			secondStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}    
}
