package org.abyss.controller;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

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
	private ArrayList<String> url;
	
	Stage stage;
	Scene scene;
	Scene sceneGacha;
	Scene sceneBack;

	
	HashMap<String, Scene> listScene;

	
	public void makeSound() {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/resources/Sounds/tada.wav");
			AudioStream audioStream = new AudioStream(inputStream);
			AudioPlayer.player.start(audioStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
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
		carte = new ArrayList<>();
		carte.add(carte1);
		carte.add(carte2);
		carte.add(carte3);
		carte.add(carte4);
		carte.add(carte5);
		carte.add(carte6);
		carte.add(carte7);
		carte.add(carte8);
		
		url = new ArrayList<>();
		url.add("/resources/Images/rien.gif");
		url.add("/resources/Images/eau1.png");
		url.add("/resources/Images/eau2.png");
		url.add("/resources/Images/eau3.png");
		url.add("/resources/Images/eau4.png");
		url.add("/resources/Images/ice1.png");
		url.add("/resources/Images/ice2.png");
		url.add("/resources/Images/ice3.png");
		
	}
	
	public void retour() {
		stage.setScene(listScene.get("accueil"));
	}
	public void gacha1() {
		stage.setScene(listScene.get("gacha"));
	}
	public void back() {
		stage.setScene(listScene.get("collection"));
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
							if(quelleCarte != 0) {
								carte.get(quelleCarte-1).setImage(new Image(chemincarte1));
								effet.setVisible(true);
							}
                			pack1.setVisible(true);
                			pak1.setVisible(true);
                			affichedrop.setText("Vous avez eu ceci !");
                			
                            
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
		int random = (int) ((Math.random()* 8)); // Random va de 0 a 8-1
		System.out.println(random);
		animation(url.get(random),url.get(random),random);
		drop1.setImage(new Image ("/resources/Images/invocation.gif"));
		pack1.setVisible(false);
		pak1.setVisible(false);

	}
}
