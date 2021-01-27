package org.abyss.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.abyss.ennemy.EnnemyAverage;
import org.abyss.ennemy.EnnemyDifficult;
import org.abyss.ennemy.EnnemyEasy;
import org.abyss.ennemy.IEnnemy;
import org.abyss.javafxview.Game;
import org.abyss.javafxview.Player;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainController implements Initializable {
	
	@FXML
	private DialogueController dialogueController;
	@FXML
	private InformationController informationController;
	@FXML
	private BoardController boardController;
	@FXML
	private TourController tourController;
	@FXML
	private AllyHandController allyHandController;
	@FXML
	private EnnemyHandController ennemyHandController;
	@FXML
	private AnimationController animationController;
	@FXML
	private SpellController spellController;
	@FXML
	private AnchorPane background;
	@FXML
	private ImageView mainDeck;
	@FXML
	private ImageView opponentDeck;
	@FXML
	private Label allyHp;
	@FXML
	private Label ennemyHp;
	@FXML
	private ImageView defeat;
	@FXML
	private ImageView ennemyHero;
	@FXML
	private ImageView allyHero;
	@FXML
	private Button allyPet;

	private MainController ennemyController;
	private Player player;
	private Game game;
	private boolean multi;
	private int allyPv;
	private int ennemyPv;
	private String difficulty;
	private Stage popupwindow;
	private Stage stage;
	HashMap<String, Scene> listScene;
	private boolean petReversed;
	
	public MainController getEnnemyController() {
		return ennemyController;
	}
	
	public void setEnnemyController(MainController ennemyController) {
		this.ennemyController = ennemyController;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}

	public boolean getMulti() {
		return multi;
	}
	
	public void setMulti(boolean multi) {
		this.multi = multi;
	}
	
	public HashMap<String, Scene> getListScene() {
		return listScene;
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	public void setListScene(HashMap<String, Scene> listScene) {
		this.listScene = listScene;
	}

	public int getAllyPv() {
		return allyPv;
	}
	
	public void setAllyPv(int allyPv) {
		this.allyPv = allyPv;
	}
	
	public int getEnnemyPv() {
		return ennemyPv;
	}
	
	public void setEnnemyPv(int ennemyPv) {
		this.ennemyPv = ennemyPv;
	}
	
	public InformationController getInformationController() {
		return informationController;
	}

	public BoardController getBoardController() {
		return boardController;
	}

	public TourController getTourController() {
		return tourController;
	}

	public AllyHandController getAllyHandController() {
		return allyHandController;
	}

	public EnnemyHandController getEnnemyHandController() {
		return ennemyHandController;
	}

	public AnimationController getAnimationController() {
		return animationController;
	}

	public SpellController getSpellController() {
		return spellController;
	}
	
	public DialogueController getDialogueController() {
		return dialogueController;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		allyPv = 2000;
		ennemyPv = 2000;
		afficherHp();
		dialogueController.BulleAlly.setVisible(false);
		dialogueController.BulleEnnemy.setVisible(false);
		dialogueController.TxtAlly.setVisible(false);
		dialogueController.TxtEnnemy.setVisible(false);
		ennemyHero.setImage(new Image("/resources/Images/Ennemy.png"));
		allyHero.setImage(new Image("/resources/Images/hero1.png"));
		boardController.setParentController(this);
		informationController.setParentController(this);
		allyHandController.setParentController(this);
		tourController.setParentController(this);
		spellController.setParentController(this);
		dialogueController.setParentController(this);
		allyPet.setGraphic(new ImageView(new Image("/resources/Images/allyPet.gif")));
		mainDeck.setImage(new Image("/resources/Images/Jade.png"));
		opponentDeck.setImage(new Image("/resources/Images/Ice.png"));
		
	}

	
	public void afficherHp() {
		
		if (getMulti()) {
			getEnnemyController().setAllyPv(ennemyPv);
			getEnnemyController().setEnnemyPv(allyPv);
			getEnnemyController().allyHp.setText(Integer.toString(ennemyPv));
			getEnnemyController().ennemyHp.setText(Integer.toString(allyPv));
		}
		
		allyHp.setText(Integer.toString(allyPv));
		ennemyHp.setText(Integer.toString(ennemyPv));

	}
	
	public void endGame(String url) {
		defeat.toFront();
		defeat.setVisible(true);
		defeat.setImage(new Image(url));
	}
	
	public void mort() {

		if (allyPv <= 0 || ennemyPv <= 0) {
			defeat.toFront();
			defeat.setVisible(true);
			if (allyPv > ennemyPv) {
				defeat.setImage(new Image("/resources/Images/victory.png"));
				if (getMulti()) {
					getEnnemyController().endGame("/resources/Images/defeat.png");
				}
			} else {
				defeat.setImage(new Image("/resources/Images/defeat.png"));
				if (getMulti()) {
					getEnnemyController().endGame("/resources/Images/victory.png");
				}
			}
			new Thread(new Runnable() {
				public void run() {
					try {
						Thread.sleep(600);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							GaussianBlur blur = new GaussianBlur(20);
							background.setEffect(blur);
							display();
						}
					});
				}
			}).start();
			
			
		} else {
			tourController.stade();
		}
		
	}
	
	public void updateGui() {
		tourController.visible(false);
	}
	
	public void player2Heroes() {
		
		allyHero.setImage(new Image("/resources/Images/Ennemy.png"));
		ennemyHero.setImage(new Image("/resources/Images/hero1.png"));
		allyHp.setTextFill(Color.web("#36c4d1"));
		ennemyHp.setTextFill(Color.web("#ffff00"));
		allyHp.setTranslateY(265);
		ennemyHp.setTranslateY(-265);
		allyPet.setGraphic(new ImageView(new Image("/resources/Images/ennemyPetReversed.gif")));
		allyPet.setTranslateY(-30);
		mainDeck.setImage(new Image("/resources/Images/Ice.png"));
		opponentDeck.setImage(new Image("/resources/Images/Jade.png"));
	}
	
	public void flip(ActionEvent event) {

		new Thread(new Runnable() {
			public void run() {
			for (int i = 0; i < 10; i++) {
				
				if (!petReversed) {
					allyPet.setTranslateX(allyPet.getTranslateX() - 110);
					
				} else {
					allyPet.setTranslateX(allyPet.getTranslateX() + 110);
				}
				
				try {
					Thread.sleep(350);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						
						if (petReversed) {
							if (!getMulti() || getPlayer().getName() == "Joueur 1") {
								allyPet.setGraphic(new ImageView(new Image("/resources/Images/allyPet.gif")));
							} else if (getPlayer().getName() == "Joueur 2") {
								allyPet.setGraphic(new ImageView(new Image("/resources/Images/ennemyPetReversed.gif")));
							}
							petReversed = false;
						} else {
							if (!getMulti() || getPlayer().getName() == "Joueur 1") {
								allyPet.setGraphic(new ImageView(new Image("/resources/Images/allyPetReversed.gif")));
							} else if (getPlayer().getName() == "Joueur 2") {
								allyPet.setGraphic(new ImageView(new Image("/resources/Images/ennemyPet.gif")));
							}
							petReversed = true;
						}
					}
				});
			}
		}).start();
	}

	public void leave() {
		System.exit(0);
	}

	public void close() {
		stage.close();
	}
	public void retourAccueil() {
		
		if (getMulti()) {
			if (getPlayer().getName() == "Joueur 2") {
				popupwindow.close();
				stage.close();
				getEnnemyController().retourAccueil();
			} else {
				popupwindow.close();
				getEnnemyController().close();
				reset();
				stage.setTitle("Abyss");
				stage.setScene(listScene.get("accueil"));
			} 
		} else {
			reset();
			stage.setTitle("Abyss");
			stage.setScene(listScene.get("accueil"));
		}

	}
	
	public IEnnemy selectedDifficulty(MainController parentController) {
		
		IEnnemy ennemy = null;
		
		switch (difficulty) {
			
			case "easy":
				ennemy = new EnnemyEasy(parentController);
				break;
			
			case "average":
				ennemy = new EnnemyAverage(parentController);
				break;
				
			case "difficult":
				ennemy = new EnnemyDifficult(parentController);
				break;
		
		}
		
		return ennemy;
		
	}
	
	public void reset() {
		
		if (popupwindow != null) {
			popupwindow.close();
		}
		setMulti(false);
		defeat.setVisible(false);
		defeat.toBack();
		allyPv = 2000;
		ennemyPv = 2000;
		dialogueController.setHideA(0);
		dialogueController.setHideE(0);
		dialogueController.setParlerA(true);
		dialogueController.setParlerE(true);
		afficherHp();
		boardController.setAllyBoard(CardsUtils.fillBoard(5));
		boardController.setEnnemyBoard(CardsUtils.fillBoard(5));
		allyHandController.setAllyDeck(CardsUtils.getCardsGame());
		allyHandController.setAllyHand(CardsUtils.fillBoard(5));
		ennemyHandController.setEnnemyDeck(CardsUtils.getEnnemyCards());
		ennemyHandController.setEnnemyHand(CardsUtils.fillBoard(5));
		spellController.setSpell1(null);
		spellController.setSpell2(null);
		allyHandController.afficherHand();
		ennemyHandController.afficherHand();
		boardController.afficherBoard();
		spellController.afficherSpells();
		allyHandController.piocher();
		ennemyHandController.piocher();
		informationController.setNouveau(true);
		informationController.ecrire("");
		informationController.lireLigne();
		tourController.setTour(Phase.TourEnnemi);
		tourController.setOrder(false);
		tourController.visible(true);
		tourController.getPhase().setText("Start");
		tourController.afficherTour("Appuyez sur start");
	}

	public void display() {

		popupwindow = new Stage();
		popupwindow.initStyle(StageStyle.TRANSPARENT);
		popupwindow.setTitle("Fin de la partie");
		

		Label label1 = new Label("La partie est terminée !");
		Button button0 = new Button("Rejouer");
		Button button1 = new Button("Retour à l'accueil");
		Button button2 = new Button("Quitter la partie");

		label1.setTextFill(Color.web("#FFFFFF"));
		label1.setPadding(new Insets(0,0,50,0));
		button0.setOnAction(e -> reset());
		button1.setOnAction(e -> retourAccueil());
		button2.setOnAction(e -> leave());
		button0.setPrefWidth(200);
		button0.setPrefHeight(60);
		button1.setPrefWidth(200);
		button1.setPrefHeight(60);
		button2.setPrefWidth(200);
		button2.setPrefHeight(60);

		VBox layout = new VBox(30);

		layout.getChildren().addAll(label1, button0, button1, button2);
		layout.setAlignment(Pos.CENTER);
		

		Scene scene1 = new Scene(layout, 600, 500);
		
		scene1.getStylesheets().add(getClass().getResource("/resources/CSS/Popup.css").toExternalForm());
		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
		background.setEffect(null);
		
	}

}