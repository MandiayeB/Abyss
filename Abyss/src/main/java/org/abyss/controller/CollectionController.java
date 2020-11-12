package org.abyss.controller;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

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
	@FXML
	private Button pack1;
	@FXML
	private ImageView effet;
	@FXML
	private ImageView pak1;
	@FXML 
	private Label affichedrop;

	private ArrayList<ImageView> carte;
	
	Stage stage;
	Scene scene;
	Scene sceneGacha;
	Scene sceneBack;
	public void makeSound() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/resources/Sounds/tada.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Scene getSceneBack() {
		return sceneBack;
	}

	public void setSceneBack(Scene sceneBack) {
		this.sceneBack = sceneBack;
	}

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
		carte = new ArrayList<>();
		carte.add(carte1);
		carte.add(carte2);
		carte.add(carte3);
		carte.add(carte4);
		carte.add(carte5);
		carte.add(carte6);
	}
	
	public void retour() {
		stage.setScene(scene);
	}
	public void gacha1() {
		stage.setScene(sceneGacha);
	}
	public void back() {
		stage.setScene(sceneBack);
	}

	public void animation (String chemindrop1, String chemincarte1,int quelleCarte) {
		new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(2750);
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                        	
                        	makeSound();
							drop1.setImage(new Image(chemindrop1));
							if(quelleCarte != 50) {
								carte.get(quelleCarte).setImage(new Image(chemincarte1));
								effet.setVisible(true);
							}
                			pack1.setVisible(true);
                			pak1.setVisible(true);
                			affichedrop.setText("Vous n'avez malheureusement rien eu !");
                			
                            
                        }
                    });
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
	}
	public void invoc1() {
		effet.setImage(new Image("/resources/Images/confeti.gif"));
		effet.setVisible(false);
		affichedrop.setText("");
		int random = (int) ((Math.random()* 50)); // Random va de 1 a 50
		System.out.println(random);
		if (random < 10) {
			animation("/resources/Images/rien.gif", null, 50);
			drop1.setImage(new Image ("/resources/Images/invocation.gif"));
			pack1.setVisible(false);
			pak1.setVisible(false);

		}
		if (random > 10 && random < 20) {
			animation("/resources/Images/yugi.jpg", "/resources/Images/yugi.jpg", 0);
			drop1.setImage(new Image ("/resources/Images/invocation.gif"));
			pack1.setVisible(false);
			pak1.setVisible(false);
		}
		if (random > 20 && random < 30) {
			animation("/resources/Images/yugi1.jpg", "/resources/Images/yugi1.jpg",1);
			drop1.setImage(new Image ("/resources/Images/invocation.gif"));
			pack1.setVisible(false);
			pak1.setVisible(false);

		}
		if (random > 30 && random < 40) {
			animation("/resources/Images/yugi2.jpg", "/resources/Images/yugi2.jpg",2);
			drop1.setImage(new Image ("/resources/Images/invocation.gif"));
			pack1.setVisible(false);
			pak1.setVisible(false);

		}
		if (random > 40 && random < 45) {
			animation("/resources/Images/yugi3.jpg", "/resources/Images/yugi3.jpg",3);
			drop1.setImage(new Image ("/resources/Images/invocation.gif"));
			pack1.setVisible(false);
			pak1.setVisible(false);
		}
		if (random > 45 && random < 50) {
			animation("/resources/Images/yugi4.jpg", "/resources/Images/yugi4.jpg",4);
			drop1.setImage(new Image ("/resources/Images/invocation.gif"));
			pack1.setVisible(false);
			pak1.setVisible(false);

		}
	}
}
