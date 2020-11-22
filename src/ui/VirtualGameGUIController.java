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
		System.exit(0);
	}

	@FXML
	void loadGame(ActionEvent event) throws IOException {
		startLoadGame();
	}

	@FXML
	void newGame(ActionEvent event) throws IOException {
		starChoosePlayers();
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
    void buyItems(ActionEvent event) throws IOException {
    	startShop();
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
    void backScoreToMenu(ActionEvent event) throws IOException {
    	startMenu();
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
    void backShopToGame(ActionEvent event) throws IOException {
    	startScenary();
    }
    public void startLoadGame() throws IOException {
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("LoadGame.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		basePane.getChildren().clear();
		basePane.setCenter(root);
	}
    @FXML
    void backLoadToMenu(ActionEvent event) throws IOException {
    	startMenu();
    }

    @FXML
    void loadSaveGame(ActionEvent event) throws IOException {
    	startScenary();
    }
    public void starChoosePlayers() throws IOException {
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Players.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		basePane.getChildren().clear();
		basePane.setCenter(root);
	}

    @FXML
    void backPlayerToMenu(ActionEvent event) throws IOException {
    	startMenu();
    }

    @FXML
    void chooseBoy(ActionEvent event) throws IOException {
    	startScenary();
    }

    @FXML
    void chooseGirl(ActionEvent event) throws IOException {
    	startScenary();
    }
}
