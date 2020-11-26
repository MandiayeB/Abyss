package org.abyss.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AccueilController implements Initializable  {

	Stage stage;
	
	HashMap<String, Scene> listScene;
	
	public HashMap<String, Scene> getListScene() {
		return listScene;
	}

	public void setListScene(HashMap<String, Scene> listScene) {
		this.listScene = listScene;
	}


	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void commencement() {
		stage.setScene(listScene.get("game"));
	}
	public void collection() {
		stage.setScene(listScene.get("collection"));
	}
	
	public void quitter() {
		System.out.println("cya");
		System.exit(0);
	}
}
