package org.abyss.javafxview;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

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

	public void makeSound() {

		try {

			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
					new File("C:\\Users\\allan\\git\\Abyss\\Abyss\\src\\main\\resources\\resources\\Sounds\\accueil.wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(-20.0f);
			Thread.sleep(200);
			clip.start();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Je trouve pas la musique");
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {

			// Creation de la scene Accueil et de la scene GAME et la scene Collection
			FXMLLoader loaderAccueil = new FXMLLoader(getClass().getResource("/resources/FXML/Accueil.fxml"));
			FXMLLoader loaderMain = new FXMLLoader(getClass().getResource("/resources/FXML/Game.fxml"));
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

			
			HashMap<String, Scene> listScene = new HashMap<>();
			listScene.put("game", sceneGame);
			listScene.put("collection", sceneCollection);
			listScene.put("accueil", sceneAccueil);
			listScene.put("gacha", sceneGacha);
			
			
			//CSS
			accueil.getStylesheets().add(getClass().getResource("/resources/CSS/Accueil.css").toExternalForm());
			sceneGame.getStylesheets().add(getClass().getResource("/resources/CSS/style.css").toExternalForm());
			sceneCollection.getStylesheets()
					.add(getClass().getResource("/resources/CSS/Collection.css").toExternalForm());
			sceneGacha.getStylesheets().add(getClass().getResource("/resources/CSS/Collection.css").toExternalForm());

			// On fournit au controlleur la scene jeu
			// Bouton jouer pour aller a la scene de jeu
			AccueilController accueilController = (AccueilController) loaderAccueil.getController();
			accueilController.setStage(primaryStage);
			accueilController.setListScene(listScene);

			// Button permerttant d'aller retour accueil
			CollectionController collectionController = (CollectionController) loaderCollection.getController();
			collectionController.setListScene(listScene);
			collectionController.setStage(primaryStage);
			
			
			//Bouton pour retourner a la scene d'accueil a la fin de la partie
			MainController mainController = (MainController) loaderMain.getController();
			mainController.setListScene(listScene);
			mainController.setStage(primaryStage);
			
			//
			CollectionController back = (CollectionController) loaderGacha.getController();
			back.setListScene(listScene);
			back.setStage(primaryStage);

			// On change le curseur
			Image image = new Image("/resources/Images/curseur.png");
			sceneGame.setCursor(new ImageCursor(image));
			sceneAccueil.setCursor(new ImageCursor(image));

			// On affiche la scene Accueil
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
