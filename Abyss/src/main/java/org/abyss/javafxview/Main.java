package org.abyss.javafxview;

import org.abyss.controller.AccueilController;
import org.abyss.controller.CollectionController;
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
			
			//Creation de la scene Accueil et de la scene GAME et la scene Collection
			FXMLLoader loaderAccueil = new FXMLLoader(getClass().getResource("/resources/FXML/Accueil.fxml"));
			FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/resources/FXML/FXML.fxml"));
			FXMLLoader loaderCollection = new FXMLLoader(getClass().getResource("/resources/FXML/Collection.fxml"));
			FXMLLoader loaderGacha = new FXMLLoader(getClass().getResource("/resources/FXML/Gacha.fxml"));
			
			Parent accueil = loaderAccueil.load();
			Scene sceneAccueil = new Scene(accueil);
			
			Parent collection = loaderCollection.load();
			Scene sceneCollection = new Scene(collection);
			
			Parent game = loaderMain.load();
			Scene sceneGame = new Scene(game);
			
			Parent gacha = loaderGacha.load();
			Scene sceneGacha = new Scene(gacha);
			
			
			//CSS
			accueil.getStylesheets().add(getClass().getResource("/resources/CSS/Accueil.css").toExternalForm());
			sceneGame.getStylesheets().add(getClass().getResource("/resources/CSS/style.css").toExternalForm());
			sceneCollection.getStylesheets().add(getClass().getResource("/resources/CSS/Collection.css").toExternalForm());
			sceneGacha.getStylesheets().add(getClass().getResource("/resources/CSS/Collection.css").toExternalForm());
			
			//On fournit au controlleur la scene jeu
			//Bouton jouer pour aller a la scene de jeu
			AccueilController accueilController = (AccueilController) loaderAccueil.getController();
			accueilController.setScene(sceneGame);
			accueilController.setStage(primaryStage);
			
			//Bouton permerttant d"aller a la collection
			AccueilController accueilCollection = (AccueilController) loaderAccueil.getController();
			accueilCollection.setScenecollection(sceneCollection);
			accueilCollection.setStage(primaryStage);
			
			//Button permerttant d'aller retour accueil
			CollectionController retourCol = (CollectionController) loaderCollection.getController();
			retourCol.setScene(sceneAccueil);
			retourCol.setStage(primaryStage);
			
			//Bouton pour retourner a la scene d'accueil a la fin de la partie
			MainController mc = (MainController) loaderMain.getController();
			mc.setScene(sceneAccueil);
			mc.setStage(primaryStage);
			
			//Gacha 
			CollectionController collec = (CollectionController) loaderCollection.getController();
			collec.setSceneGacha(sceneGacha);
			collec.setStage(primaryStage);
			
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
