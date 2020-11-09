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
	private ImageView drop1;
	@FXML
	private ImageView carte1;
	@FXML
	private ImageView carte2;
	@FXML
	private ImageView carte3;
	@FXML
	private ImageView carte4;
	@FXML
	private ImageView carte5;
	@FXML
	private ImageView carte6;
	@FXML
	private ImageView carte7;
	@FXML
	private ImageView carte8;
	@FXML
	private ImageView carte9;

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
	public void gacha1() {
		stage.setScene(sceneGacha);
	}
	public void invoc1() {
		int random = (int) ((Math.random()* 50)); // Random va de 1 a 50
		System.out.println(random);
		if (random < 10) {
			drop1.setImage(new Image("/resources/Images/dos.jpg"));
		}
		if (random > 10 && random < 20) {
			drop1.setImage(new Image("/resources/Images/yugi.jpg"));
			carte1.setImage(new Image("/resources/Images/yugi.jpg"));
		}
		if (random > 20 && random < 30) {
			drop1.setImage(new Image("/resources/Images/yugi1.jpg"));
			carte2.setImage(new Image("/resources/Images/yugi1.jpg"));
		}
		if (random > 30 && random < 40) {
			drop1.setImage(new Image("/resources/Images/yugi2.jpg"));
			carte3.setImage(new Image("/resources/Images/yugi2.jpg"));
		}
		if (random > 40 && random < 45) {
			drop1.setImage(new Image("/resources/Images/yugi3.jpg"));
			carte4.setImage(new Image("/resources/Images/yugi3.jpg"));
		}
		if (random > 45 && random < 50) {
			drop1.setImage(new Image("/resources/Images/yugi4.jpg"));
			carte5.setImage(new Image("/resources/Images/yugi4.jpg"));
		}
	}
}
