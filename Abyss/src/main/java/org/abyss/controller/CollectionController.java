package org.abyss.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CollectionController implements Initializable  {
	
	@FXML
	private Label rien1;
	@FXML
	private ImageView drop1;

	Stage stage;
	Scene scene;
	Scene sceneGacha;
	
	public Scene getSceneGacha() {
		return sceneGacha;
	}

	public void setSceneGacha(Scene sceneGacha) {
		this.sceneGacha = sceneGacha;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
	
	public void retour() {
		stage.setScene(scene);
	}
	public void gacha() {
		stage.setScene(sceneGacha);
	}
	public void invoc1() {
		int random = (int) (1 + (Math.random()* 100)); // Random va de 1 a 50
		System.out.println(random);
		if (random > 10) {
			rien1.setText("Tu n'as rien eu ");
		}
		else {
			drop1.setImage(new Image("/resources/Images/yugi.jpg"));
			rien1.setText("");
		}
	}
}
