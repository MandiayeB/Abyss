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
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	
	private static MainController GameController;

	public static void main(String[] args) {

		launch(args);

	}
	
	public static MainController getMainController() {
		return GameController;
	}
	
	public void makeSound() {

		try {

			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(
					"C:\\Users\\allan\\git\\Abyss\\Abyss\\src\\main\\resources\\resources\\Sounds\\accueil.wav"));
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
			FXMLLoader loaderGacha2 = new FXMLLoader(getClass().getResource("/resources/FXML/Gacha2.fxml"));
			FXMLLoader loaderChargement = new FXMLLoader(getClass().getResource("/resources/FXML/Chargement.fxml"));

			Parent accueil = loaderAccueil.load();
			Scene sceneAccueil = new Scene(accueil);

			Parent chargement = loaderChargement.load();
			Scene sceneChargement = new Scene(chargement);

			Parent collection = loaderCollection.load();
			Scene sceneCollection = new Scene(collection);

			Parent game = loaderMain.load();
			GameController = loaderMain.getController();
			Scene sceneGame = new Scene(game);

			Parent gacha = loaderGacha.load();
			Scene sceneGacha = new Scene(gacha);

			Parent gacha2 = loaderGacha2.load();
			Scene sceneGacha2 = new Scene(gacha2);

			HashMap<String, Scene> listScene = new HashMap<>();
			listScene.put("game", sceneGame);
			listScene.put("gacha2", sceneGacha2);
			listScene.put("collection", sceneCollection);
			listScene.put("accueil", sceneAccueil);
			listScene.put("gacha", sceneGacha);
			listScene.put("chargement", sceneChargement);

			// CSS
			accueil.getStylesheets().add(getClass().getResource("/resources/CSS/Accueil.css").toExternalForm());
			sceneGame.getStylesheets().add(getClass().getResource("/resources/CSS/style.css").toExternalForm());
			sceneCollection.getStylesheets()
					.add(getClass().getResource("/resources/CSS/Collection.css").toExternalForm());
			sceneGacha.getStylesheets().add(getClass().getResource("/resources/CSS/Collection.css").toExternalForm());
			sceneGacha2.getStylesheets().add(getClass().getResource("/resources/CSS/Collection.css").toExternalForm());

			// On fournit au controlleur la scene jeu
			// Bouton jouer pour aller a la scene de jeu
			AccueilController accueilController = (AccueilController) loaderAccueil.getController();
			accueilController.setStage(primaryStage);
			accueilController.setListScene(listScene);

			// Button permerttant d'aller retour accueil
			CollectionController collectionController = (CollectionController) loaderCollection.getController();
			collectionController.setListScene(listScene);
			collectionController.setStage(primaryStage);

			// Bouton pour retourner a la scene d'accueil a la fin de la partie
			MainController mainController = (MainController) loaderMain.getController();
			mainController.setListScene(listScene);
			mainController.setStage(primaryStage);

			//
			CollectionController back = (CollectionController) loaderGacha.getController();
			back.setListScene(listScene);
			back.setStage(primaryStage);

			CollectionController back2 = (CollectionController) loaderGacha2.getController();
			back2.setListScene(listScene);
			back2.setStage(primaryStage);

			// On change le curseur
			Image image = new Image("/resources/Images/curseur.png");
			sceneGame.setCursor(new ImageCursor(image));
			sceneAccueil.setCursor(new ImageCursor(image));
			sceneChargement.setCursor(new ImageCursor(image));
			sceneGacha.setCursor(new ImageCursor(image));
			sceneGacha2.setCursor(new ImageCursor(image));
			sceneCollection.setCursor(new ImageCursor(image));

			// On affiche la scene Accueil
			primaryStage.setTitle("Abyss");
			primaryStage.getIcons().add(new Image("/resources/Images/logo.png"));
			primaryStage.setScene(sceneChargement);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.show();
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {

						Thread.sleep(3000);

					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Platform.runLater(new Runnable() {

						@Override
						public void run() {
							primaryStage.setScene(sceneAccueil);
							primaryStage.setMaximized(true);
							makeSound();
						}
						
					});
				}
				
			}).start();


		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
