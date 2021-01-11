package org.abyss.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AccueilController implements Initializable  {

	@FXML
	private AnchorPane background;
	
	Stage stage;
	Stage popupwindow;
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
		stage.setMaximized(false);
		stage.setMaximized(true);
		popupChoice();
		
	}
	public void popupChoice() {

		popupwindow = new Stage();
		popupwindow.initStyle(StageStyle.TRANSPARENT);
		popupwindow.setTitle("Choisir une difficulté");
		

		Label label1 = new Label("Choisir une difficulté");
		Button button0 = new Button("Facile");
		Button button1 = new Button("Moyen");
		Button button2 = new Button("Dur");

		button0.setOnAction(e -> closeWindow(e,"easy"));
		button1.setOnAction(e -> closeWindow(e,"average"));
		button2.setOnAction(e -> closeWindow(e,"difficult"));

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label1, button0, button1, button2);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 250);
		
		scene1.getStylesheets().add(getClass().getResource("/resources/CSS/Popup.css").toExternalForm());
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}
	
	
	public void closeWindow(ActionEvent e, String choice) {
		
		stage.setMaximized(false);
		popupwindow.close();
		stage.setMaximized(true);
		
	}
	
	public void collection() {
		stage.setMaximized(false);
		stage.setScene(listScene.get("collection"));
		stage.setMaximized(true);

	}
	
	public void quitter() {
		System.out.println("cya");
		System.exit(0);
	}
}
