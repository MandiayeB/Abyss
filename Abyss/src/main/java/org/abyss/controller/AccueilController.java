package org.abyss.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilController implements Initializable  {

	Stage stage;
	Scene scene;
	Scene scenecollection;
	
	public Scene getScenecollection() {
		return scenecollection;
	}

	public void setScenecollection(Scene scenecollection) {
		this.scenecollection = scenecollection;
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
	
	public void commencement() {
		stage.setScene(scene);
	}
	public void collection() {
		stage.setScene(scenecollection);
	}
	
	public void quitter() {
		System.out.println("cya");
		System.exit(0);
	}
}
