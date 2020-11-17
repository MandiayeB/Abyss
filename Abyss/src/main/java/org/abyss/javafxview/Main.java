package org.abyss.javafxview;

import java.io.InputStream;

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
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Main extends Application {

	public static void main(String[] args) {

			launch(args);
					
	}
	public void makeSound() {
		
		try {
			//je cherche la musique
			InputStream inputStream = getClass().getResourceAsStream("/resources/Sounds/accueil.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			//Le loop
//			AudioData audioData = audioStream.getData();
//			ContinuousAudioDataStream loop = new ContinuousAudioDataStream(audioData);
			// Start
			AudioPlayer.player.start(audioStream);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Je trouve pas la musique");
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		try {
			
			//Creation de la scene Accueil et de la scene GAME et la scene Collection
			FXMLLoader loaderAccueil = new FXMLLoader(getClass().getResource("/resources/FXML/Accueil.fxml"));
			FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/resources/FXML/Game.fxml"));
			FXMLLoader loaderCollection = new FXMLLoader(getClass().getResource("/resources/FXML/Collection.fxml"));
			FXMLLoader loaderGacha = new FXMLLoader(getClass().getResource("/resources/FXML/Gacha.fxml"));
			//FXMLLoader loaderInformation = new FXMLLoader(getClass().getResource("/resources/FXML/Information.fxml"));
			
			Parent accueil = loaderAccueil.load();
			Scene sceneAccueil = new Scene(accueil);
			
			Parent collection = loaderCollection.load();
			Scene sceneCollection = new Scene(collection);
			
			Parent game = loaderMain.load();
			Scene sceneGame = new Scene(game);
			
			Parent gacha = loaderGacha.load();
			Scene sceneGacha = new Scene(gacha);
			
			//Parent information = loaderInformation.load();
			
			
			
			//CSS
			accueil.getStylesheets().add(getClass().getResource("/resources/CSS/Accueil.css").toExternalForm());
			sceneGame.getStylesheets().add(getClass().getResource("/resources/CSS/style.css").toExternalForm());
			sceneCollection.getStylesheets().add(getClass().getResource("/resources/CSS/Collection.css").toExternalForm());
			sceneGacha.getStylesheets().add(getClass().getResource("/resources/CSS/Collection.css").toExternalForm());
			
			//On fournit au controlleur la scene jeu
			//Bouton jouer pour aller a la scene de jeu
			AccueilController accueilController = (AccueilController) loaderAccueil.getController();
			accueilController.setScene(sceneGame);
			accueilController.setScenecollection(sceneCollection);
			accueilController.setStage(primaryStage);
			
			//Button permerttant d'aller retour accueil
			CollectionController collectionController = (CollectionController) loaderCollection.getController();
			collectionController.setScene(sceneAccueil);
			collectionController.setSceneGacha(sceneGacha);
			collectionController.setStage(primaryStage);
			
			//Bouton pour retourner a la scene d'accueil a la fin de la partie
			MainController mc = (MainController) loaderMain.getController();
			mc.setScene(sceneAccueil);
			mc.setStage(primaryStage);
			
			//
			CollectionController back = (CollectionController) loaderGacha.getController();
			back.setSceneBack(sceneCollection);
			back.setStage(primaryStage);
			
			//On change le curseur 
			Image image = new Image("/resources/Images/curseur.png");
			sceneGame.setCursor(new ImageCursor(image));
			sceneAccueil.setCursor(new ImageCursor(image));
			
			//On affiche la scene Accueil
			primaryStage.setTitle("Abyss");
			primaryStage.getIcons().add(new Image("/resources/Images/logo.png"));
			primaryStage.setScene(sceneAccueil);
			makeSound();
			primaryStage.show();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
