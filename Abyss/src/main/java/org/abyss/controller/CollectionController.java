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
	private ImageView carte16;
	@FXML
	private ImageView carte10;
	@FXML
	private ImageView carte11;
	@FXML
	private ImageView carte12;
	@FXML
	private ImageView carte13;
	@FXML
	private ImageView carte14;
	@FXML
	private ImageView carte15;
	@FXML
	private ImageView carte17;
	@FXML
	private ImageView carte18;
	@FXML
	private ImageView carte19;
	@FXML
	private ImageView carte20;
	@FXML
	private ImageView carte21;
	@FXML
	private ImageView carte22;
	@FXML
	private ImageView carte23;
	@FXML
	private ImageView carte24;
	@FXML
	private ImageView carte25;
	@FXML
	private ImageView carte26;
	@FXML
	private ImageView carte27;
	@FXML
	private ImageView carte28;
	@FXML
	private ImageView carte29;
	@FXML
	private ImageView carte30;
	@FXML
	private ImageView carte31;
	@FXML
	private ImageView carte32;
	@FXML
	private Button pack1;
	@FXML
	private Button pack2;
	@FXML
	private ImageView effet;
	@FXML
	private ImageView pak1;
	@FXML
	private ImageView pak2;
	@FXML 
	private Label affichedrop;
	

	private ArrayList<ImageView> carte;
	private ArrayList<String> url;
	
	Stage stage;
	Scene scene;
	Scene sceneGacha;
	Scene sceneBack;
	Scene sceneBack2;

	
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
		carte.add(carte9);
		carte.add(carte10);
		carte.add(carte11);
		carte.add(carte12);
		carte.add(carte13);
		carte.add(carte14);
		carte.add(carte15);
		carte.add(carte16);
		carte.add(carte17);
		carte.add(carte18);
		carte.add(carte19);
		carte.add(carte20);
		carte.add(carte21);
		carte.add(carte22);
		carte.add(carte23);
		carte.add(carte24);
		carte.add(carte25);
		carte.add(carte26);
		carte.add(carte27);
		carte.add(carte28);
		carte.add(carte29);
		carte.add(carte30);
		carte.add(carte31);
		carte.add(carte32);
		

		
		url = new ArrayList<>();
		url.add("/resources/Images/rien.gif");
		for(int i=1; i<7; i++) {
			url.add("/resources/Images/eau"+i+".png");

		}
		for(int i=1; i<7; i++) {
			url.add("/resources/Images/ice"+i+".png");

		}
		url.add("/resources/Images/iceSpell1.png");
		url.add("/resources/Images/iceSpell2.png");
		url.add("/resources/Images/waterSpell.png");
		url.add("/resources/Images/Ennemy.png");
		url.add("/resources/Images/rien.gif");
		
		for(int i=1; i<7; i++) {
			url.add("/resources/Images/plante"+i+".png");

		}
		
		for(int i=1; i<7; i++) {
			url.add("/resources/Images/light"+i+".png");

		}
		url.add("/resources/Images/lightSpell1.png");
		url.add("/resources/Images/lightSpell2.png");
		url.add("/resources/Images/plantsort1.png");
		url.add("/resources/Images/hero1.png");
		
	}
	
	public void retour() {
		stage.setScene(listScene.get("accueil"));
	}
	public void gacha1() {
		stage.setScene(listScene.get("gacha"));
	}
	public void gacha2() {
		stage.setScene(listScene.get("gacha2"));
	}
	public void back() {
		stage.setScene(listScene.get("collection"));
	}
	public void back2() {
		stage.setScene(listScene.get("collection"));
	}
	
	public void mettreVisible1() {
		pack1.setVisible(true);
		pak1.setVisible(true);
	}
	public void mettreVisible2() {
		pack2.setVisible(true);
		pak2.setVisible(true);
	}
	public void animation (String chemindrop1, String chemincarte1,int quelleCarte) {
		new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(1250);
                    Platform.runLater(new Runnable() {

                        @Override
                        public void run() {
                        	
                        	makeSound();
							drop1.setImage(new Image(chemindrop1));
						
							if(quelleCarte != 0 && quelleCarte < 17) {
								carte.get(quelleCarte-1).setImage(new Image(chemincarte1));
								effet.setVisible(true);
								mettreVisible1();
							}
							if(quelleCarte==0) {
								mettreVisible1();
							}
							if(quelleCarte > 17) {
								carte.get(quelleCarte-2).setImage(new Image(chemincarte1));
								effet.setVisible(true);
								mettreVisible2();
								
							}
							if(quelleCarte==17) {
								mettreVisible2();
							}
                			
                			affichedrop.setText("Vous avez eu ceci !");
                			
                            
                        }
                    });
                    
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
	}
	public void invoc(int nb, int min) {
		effet.setImage(new Image("/resources/Images/confeti.gif"));
		effet.setVisible(false);
		affichedrop.setText("");
		int random = min + (int) ((Math.random()* ((nb- min)))); // Random va de 0 a 16-1
		System.out.println(random);
		animation(url.get(random),url.get(random),random);
		drop1.setImage(new Image ("/resources/Images/invocation.gif"));
	}
	public void invoc1() {
		invoc(16,0);
		pack1.setVisible(false);
		pak1.setVisible(false);
	}
	
	public void invoc2() {
		invoc(34,17);
		pack2.setVisible(false);
		pak2.setVisible(false);
	}
}
