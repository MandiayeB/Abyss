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
			
			//Creation de la scene jeu
			Parent game = FXMLLoader.load(getClass().getResource("/resources/FXML/FXML.fxml"));
			Scene sceneGame = new Scene(game);
			sceneGame.getStylesheets().add(getClass().getResource("/resources/CSS/style.css").toExternalForm());
			
			//Creation de la scene Accueil
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/FXML/accueil.fxml"));
			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/resources/FXML/FXML.fxml"));
			
			Parent accueil = loader.load();
			Parent retourAccueil = loader2.load();
			
			Scene sceneAccueil = new Scene(accueil);
			Scene sceneRetourAccueil = new Scene(retourAccueil);
			
			
			//CSS
			accueil.getStylesheets().add(getClass().getResource("/resources/CSS/accueil.css").toExternalForm());
			
			//On fournit au controlleur la scene jeu
			AccueilController accueilController = (AccueilController) loader.getController();
			accueilController.setScene(sceneGame);
			accueilController.setStage(primaryStage);
			
			MainController mc = (MainController) loader2.getController();
			mc.setScene(sceneRetourAccueil);
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
