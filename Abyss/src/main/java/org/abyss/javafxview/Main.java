package org.abyss.javafxview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {

			launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
//		Button btn = new Button("Click me");
//		Button exit = new Button("Exit");
//		exit.setOnAction(e -> System.exit(0));
//		exit.setOnAction(e -> {
//			System.out.println("Bye!");
//			System.exit(0);
//		});
//
//		btn.setOnAction(new EventHandler<ActionEvent>(	) {
//			
//			public void handle(ActionEvent event) {
//				
//				System.out.println("Hello World");
//				
//			}
//		});
//		VBox root = new VBox();
//		root.getChildren().addAll(btn, exit);
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/resources/FXML/FXML.fxml"));
			root.setId("test");
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/resources/CSS/style.css").toExternalForm());
			primaryStage.setTitle("Abyss");
			primaryStage.getIcons().add(new Image("/resources/CSS/logo.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
