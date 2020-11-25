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
	public static boolean up;
	public static boolean down;
	public static boolean left;
	public static boolean right;
	public static boolean attack;
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
		eventManager();
		draw();
    	loopGame();
	}
	
	public void eventManager() {
		basePane.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent event) {
			switch(event.getCode().toString()) {
			case "RIGHT":
				right = true;
				break;
			case "LEFT":
				left = true;
				break;
			case "UP":
				up = true;
				break;
			case "DOWN":
				down = true;
				break;
			case "Z":
				attack =true;
				break;
				
			default:
				break;
			}
			}
		});
		
		basePane.setOnKeyReleased(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent event) {
			switch(event.getCode().toString()) {
			case "RIGHT":
				right = false;
				break;
			case "LEFT":
				left = false;
				break;
			case "UP":
				up = false;
				break;
			case "DOWN":
				down = false;
				break;
			case "Z":
				attack =false;
				break;
				
			default:
				break;
			}
			}
		});
	}
	
	public void updateState() {
		player.move();
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
		File file = new File("E:/MyProjects/Virtual-Game-School/images/imagesUI/Backgrounds/Scenary.jpg");
    	Image imload = new Image(file.toURI().toString());
    	scenaryGame = imload;

	}
	
	public void draw() {
		graphics.drawImage(scenaryGame, 0, 0);
		player.draw(graphics);
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
