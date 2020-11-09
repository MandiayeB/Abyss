package org.abyss.controller;

import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimationController {

	private ArrayList<ImageView> listImage5;

	public AnimationController(ArrayList<ImageView> listImage) {
		listImage5 = listImage;
	}
	
	public void showSpark(int i) {
		listImage5.get(i).setImage(new Image("/resources/Images/spark.gif"));
	}
	
	public void hideSpark(int i) {
		listImage5.get(i).setImage(null);
	}
	
	public void bringUpSpark() {
		for (int i = 0; i < 5; i++) {
			listImage5.get(i).toFront();
		}
	}
	
	public void bringDownSpark() {
		for (int i = 0; i < 5; i++) {
			listImage5.get(i).toBack();
		}
	}

}
