package ui;
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
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("PaneBase.fxml"));
		VirtualGameGUIController vgc=new VirtualGameGUIController(primaryStage);
		fxmload.setController(vgc);
		Parent root=fxmload.load();
		Scene scene = new Scene(root,935,688);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Virtual School Game");
		primaryStage.setResizable(false);
		primaryStage.show();
		vgc.starLoadingScreen() ;
	}
}