package ui;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Player;
import javafx.scene.control.Button;
public class VirtualGameGUIController {
	//Atributes
	private Stage stage;
	private Player player;
	private GraphicsContext graphics;
	private Image scenaryGame;
	@FXML
	private BorderPane basePane;
	public VirtualGameGUIController(Stage s) {
		stage=s;
	}
	//Menu
	public void startMenu() throws IOException {
		basePane.setOnKeyPressed(null);
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
	
	@FXML
	
	public void startScenary() throws IOException {
		initScenary();
		draw();
    	loopGame();
		
		basePane.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent event) {
			switch(event.getCode().toString()) {
			case "RIGHT":
				player.move(-10,0);
				break;
				
			case "LEFT":
				
				player.move(10, 0);
				
				break;
				
			case "UP":
				player.move(0, 10);
				break;
				
			case "DOWN":
				player.move(0,-10);
				break;
				
			case "Z":
				System.out.println("Z");
				break;
				
			default:
				break;
			}
			}
		});
	}
	
	public void updateState() {
		
	}
	
	public void initScenary() throws IOException {
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("GameScenary.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		Canvas canva  = new Canvas(935,688);
		player = new Player(50,20,100);
		basePane.getChildren().clear();
		basePane.getChildren().add(canva);
		basePane.setCenter(root);
		graphics = canva.getGraphicsContext2D();
		File file = new File("E:/MyProjects/Virtual-Game-School/images/imagesUI/Backgrounds/ScenaryGame.jpg");
    	Image imload = new Image(file.toURI().toString());
    	scenaryGame = imload;
    	draw();
	}
	
	public void draw() {
		graphics.drawImage(scenaryGame, 0, 0);
		graphics.drawImage(player.getFrames()[0], player.getPosX(), player.getPosY());
	}
	
	public void loopGame() {
		
		AnimationTimer animationTimer = new AnimationTimer() {
			
			//60 FPS
			@Override
			public void handle(long now) {
				updateState();
				draw();
			}
		};
		animationTimer.start();
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
    	basePane.setOnKeyPressed(null);
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
		basePane.setOnKeyPressed(null);
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
    
    void eventsManagement() {
    	
    }
}
