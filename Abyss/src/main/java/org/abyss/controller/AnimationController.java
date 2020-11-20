package org.abyss.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class AnimationController implements Initializable {

	@FXML
	private HBox animation;
	@FXML
	private ImageView collision0; // Collision
	@FXML
	private ImageView collision1; // | |
	@FXML
	private ImageView collision2; // | |
	@FXML
	private ImageView collision3; // | |
	@FXML
	private ImageView collision4; // <------->
	
	private ArrayList<ImageView> listImage5;
	
	public AnimationController() {
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listImage5 = new ArrayList<>();
		listImage5.add(collision0);
		listImage5.add(collision1);
		listImage5.add(collision2);
		listImage5.add(collision3);
		listImage5.add(collision4);
		bringDownSpark();
	}
	
	public void showSpark(int i) {
		listImage5.get(i).setImage(new Image("/resources/Images/spark.gif"));
	}
	
	public void hideSpark(int i) {
		listImage5.get(i).setImage(null);
	}
	
	public void bringUpSpark() {
		for (int i = 0; i < 5; i++) {
			listImage5.get(i).setManaged(true);
			listImage5.get(i).setVisible(true);
		}
	}
	
	public void bringDownSpark() {
		for (int i = 0; i < 5; i++) {
			listImage5.get(i).setManaged(false);
			listImage5.get(i).setVisible(false);
		}
	}

}
