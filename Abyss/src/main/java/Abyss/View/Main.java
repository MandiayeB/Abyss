package Abyss.View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
////		exit.setOnAction(e -> {
////			System.out.println("Bye!");
////			System.exit(0);
////		});
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
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/resources/CSS/style.css").toExternalForm());
			primaryStage.setTitle("My title");
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	}

}
