package Abyss.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.Random;
public class MainController {
		@FXML
		private Label myMessage;

		public void generateRandom(ActionEvent event) {
		

		
		Random random = new Random();
		int myrandom = random.nextInt(50) + 1;
		myMessage.setText(Integer.toString(myrandom));
		
		System.out.println(Integer.toString(myrandom));
	
	
	}

}
