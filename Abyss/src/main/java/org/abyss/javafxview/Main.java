package org.abyss.javafxview;

import org.abyss.controller.AccueilController;
import org.abyss.controller.MainController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {

			launch(args);
					
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			
//			Parent retourAccueil = FXMLLoader.load(getClass().getResource("/resources/FXML/Accueil.fxml"));
//			Scene sceneRetourAccueil = new Scene(retourAccueil);
//			sceneGame.getStylesheets().add(getClass().getResource("/resources/CSS/Accueil.css").toExternalForm());
			
			//Creation de la scene Accueil et de la scene GAME
			FXMLLoader loaderAccueil = new FXMLLoader(getClass().getResource("/resources/FXML/Accueil.fxml"));
			FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/resources/FXML/FXML.fxml"));
			
			Parent accueil = loaderAccueil.load();
			Scene sceneAccueil = new Scene(accueil);
			
			Parent game = loaderMain.load();
			Scene sceneGame = new Scene(game);
			
			
			//CSS
			accueil.getStylesheets().add(getClass().getResource("/resources/CSS/Accueil.css").toExternalForm());
			sceneGame.getStylesheets().add(getClass().getResource("/resources/CSS/style.css").toExternalForm());
			
			//On fournit au controlleur la scene jeu
			AccueilController accueilController = (AccueilController) loaderAccueil.getController();
			accueilController.setScene(sceneGame);
			accueilController.setStage(primaryStage);
			
			MainController mc = (MainController) loaderMain.getController();
			mc.setScene(sceneAccueil);
			mc.setStage(primaryStage);
			
			//On change le curseur 
			Image image = new Image("/resources/Images/curseur.png");
			sceneGame.setCursor(new ImageCursor(image));
			sceneAccueil.setCursor(new ImageCursor(image));
			
			//On affiche la scene Accueil
			primaryStage.setTitle("Abyss");
			primaryStage.getIcons().add(new Image("/resources/Images/logo.png"));
			primaryStage.setScene(sceneAccueil);
			primaryStage.show();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
