package org.abyss.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.abyss.javafxview.Game;
import org.abyss.javafxview.SecondStage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AccueilController implements Initializable  {

	@FXML
	private AnchorPane accueil;
	@FXML
	private ImageView logo;
	
	private Game game;
	private MainController parentController;
	private Stage stage;
	private Stage popupwindow;
	private HashMap<String, Scene> listScene;

	public Game getGame() {
		return game;
	}
	
	public HashMap<String, Scene> getListScene() {
		return listScene;
	}

	public void setParentController(MainController parentController) {
		this.parentController = parentController;
	}
	
	public void setListScene(HashMap<String, Scene> listScene) {
		this.listScene = listScene;
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DropShadow borderGlow = new DropShadow();
		borderGlow.setOffsetY(0f);
		borderGlow.setOffsetX(0f);
		borderGlow.setWidth(70);
		borderGlow.setHeight(70);
		borderGlow.setColor(Color.BLACK);
		logo.setEffect(borderGlow);
	}

	public void commencement() {
		popupChoice();
		stage.setScene(listScene.get("game"));
		stage.setMaximized(false);
		stage.setMaximized(true);
		accueil.setEffect(null);
	}
	
	public void multiplayer() {
		stage.setScene(listScene.get("game"));
		stage.setTitle("Abyss ['Joueur 1']");
		new SecondStage(new Stage(), parentController);
		game = new Game(parentController.getPlayer(), parentController.getEnnemyController().getPlayer());

		parentController.setGame(game);
		parentController.reset();
		parentController.setMulti(true);
		parentController.getEnnemyController().setGame(game);
		parentController.getTourController().setTour(Phase.PhaseDeStrategie);
		parentController.getTourController().setOrder(true);
		parentController.getEnnemyController().getTourController().setTour(Phase.TourEnnemi);
		parentController.getEnnemyController().getTourController().afficherTour("");
	}
	
	public void popupChoice() {

		popupwindow = new Stage();
		popupwindow.initStyle(StageStyle.TRANSPARENT);
		popupwindow.setTitle("Choisir une difficulté");
		
		GaussianBlur blur = new GaussianBlur(20);
		accueil.setEffect(blur);
		
		Label label1 = new Label("Sélectionnez une difficulté");
		Button button0 = new Button("Facile");
		Button button1 = new Button("Moyen");
		Button button2 = new Button("Difficile");
		
		label1.setPadding(new Insets(0,0,480,0));
		label1.setTextFill(Color.web("#FFFFFF"));
		button0.setPrefWidth(130);
		button0.setPrefHeight(60);
		button1.setPrefWidth(130);
		button1.setPrefHeight(60);
		button2.setPrefWidth(130);
		button2.setPrefHeight(60);
		
		button0.setOnAction(e -> closeWindow(e,"easy"));
		button1.setOnAction(e -> closeWindow(e,"average"));
		button2.setOnAction(e -> closeWindow(e,"difficult"));
		
		StackPane layout = new StackPane();
		VBox buttons = new VBox(30);
		
		buttons.getChildren().addAll(button0, button1, button2);
		buttons.setAlignment(Pos.CENTER_RIGHT);
		buttons.setPadding(new Insets(0,250,0,0));
		layout.getChildren().addAll(label1,buttons);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 1000, 590);
		
		scene1.getStylesheets().add(getClass().getResource("/resources/CSS/Choice.css").toExternalForm());
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}
	
	public void closeWindow(ActionEvent e, String choice) {
		
		parentController.setDifficulty(choice);
		stage.setMaximized(false);
		stage.setMaximized(true);
		popupwindow.close();
		
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
