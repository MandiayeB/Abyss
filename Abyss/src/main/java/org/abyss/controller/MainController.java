package org.abyss.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController implements Initializable {
	
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
	private Pane opponentDeck;
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

	private int allyPv;
	private int ennemyPv;
	private boolean restart = false;
	static Stage popupwindow;
	static Stage stage;
	static Scene scene;
	HashMap<String, Scene> listScene;
	
	public HashMap<String, Scene> getListScene() {
		return listScene;
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
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		MainController.stage = stage;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		allyPv = 3000;
		ennemyPv = 3000;
		afficherHp();
		ennemyHero.setImage(new Image("/resources/Images/Ennemy.png"));
		allyHero.setImage(new Image("/resources/Images/hero1.png"));
		boardController.setParentController(this);
		informationController.setParentController(this);
		allyHandController.setParentController(this);
		tourController.setParentController(this);
		spellController.setParentController(this);
	}

	public void afficherHp() {

		allyHp.setText(Integer.toString(allyPv));
		ennemyHp.setText(Integer.toString(ennemyPv));

	}

	public void mort() {

		if (allyPv <= 0 || ennemyPv <= 0) {
			setRestart(true);
			defeat.toFront();
			defeat.setImage(new Image("/resources/Images/end.gif"));
			defeat.setVisible(true);
			display();
		}
		else {
			tourController.stade();
		}

	}

	public boolean getRestart() {
		return restart;
	}


	public void setRestart(boolean restart) {
		this.restart = restart;
	}

	public void leave(ActionEvent e) {
		System.exit(0);
	}

	public void retourAccueil(ActionEvent e) {
		
		reset(e);
		stage.setScene(listScene.get("accueil"));

	}
	
	public void reset(ActionEvent e) {
		
		popupwindow.close();
		defeat.setVisible(false);
		defeat.toBack();
		allyPv = 3000;
		ennemyPv = 3000;
		afficherHp();
		boardController.setAllyBoard(CardsUtils.fillBoard(5));
		boardController.setEnnemyBoard(CardsUtils.fillBoard(5));
		allyHandController.setAllyDeck(CardsUtils.getCardsGame());
		allyHandController.setAllyHand(CardsUtils.fillBoard(5));
		ennemyHandController.setEnnemyDeck(CardsUtils.getEnnemyCards());
		ennemyHandController.setEnnemyHand(CardsUtils.fillBoard(5));
		spellController.setSpell1(CardsUtils.fillBoard(1));
		spellController.setSpell2(CardsUtils.fillBoard(1));
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
		tourController.changeButton();
		
	}

	public void display() {

		popupwindow = new Stage();
		popupwindow.initModality(Modality.APPLICATION_MODAL);
		popupwindow.setTitle("Fin de la partie");

		Label label1 = new Label("La partie est terminée !");
		Button button0 = new Button("Rejouer");
		Button button1 = new Button("Retour a l'accueil");
		Button button2 = new Button("Quitter la partie");

		button0.setOnAction(e -> reset(e));
		button1.setOnAction(e -> retourAccueil(e));
		button2.setOnAction(e -> leave(e));

		VBox layout = new VBox(10);

		layout.getChildren().addAll(label1, button0, button1, button2);
		layout.setAlignment(Pos.CENTER);

		Scene scene1 = new Scene(layout, 300, 250);

		popupwindow.setScene(scene1);
		popupwindow.showAndWait();
	}

}