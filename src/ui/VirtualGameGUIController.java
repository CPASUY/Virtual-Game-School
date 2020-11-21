package ui;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
public class VirtualGameGUIController {
	//Atributes
	private Stage stage;
	@FXML
	private BorderPane basePane;
	public VirtualGameGUIController(Stage s) {
		stage=s;
	}
	//Menu
	public void startMenu() throws IOException {
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("GameMenu.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		basePane.getChildren().clear();
		basePane.setCenter(root);
	}
	@FXML
	void exit(ActionEvent event) {

	}

	@FXML
	void loadGame(ActionEvent event) {

	}

	@FXML
	void newGame(ActionEvent event) {

	}

	@FXML
	void showScore(ActionEvent event) throws IOException {
		startScores();
	}
	//SCENENARY
	public void startScenary() throws IOException {
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("GameScenary.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		basePane.getChildren().clear();
		basePane.setCenter(root);
	}
	@FXML
    private Label coinsGame;

    @FXML
    void buyItems(ActionEvent event) {

    }

    @FXML
    void pauseGame(ActionEvent event) {

    }
    public void startScores() throws IOException {
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Scores.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		basePane.getChildren().clear();
		basePane.setCenter(root);
	}

    @FXML
    private TextField searchNickname;

    @FXML
    private TextField searchPosition;

    @FXML
    void backScoreToMenu(ActionEvent event) {

    }

    @FXML
    void searchByNickname(ActionEvent event) {

    }

    @FXML
    void searchByPosition(ActionEvent event) {

    }

    @FXML
    void sortByDefeats(ActionEvent event) {

    }

    @FXML
    void sortByLastStage(ActionEvent event) {

    }

    @FXML
    void sortByMoodleCoins(ActionEvent event) {

    }
    public void startShop() throws IOException {
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Shop.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		basePane.getChildren().clear();
		basePane.setCenter(root);
	}
    @FXML
    private Button gunOne;

    @FXML
    private Button gunTwo;

    @FXML
    private Button gunThree;

    @FXML
    private Label coinsShop;

    @FXML
    void backShopToGame(ActionEvent event) {

    }
    
}
