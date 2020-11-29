package ui;
import java.io.File;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.GameManagement;
import model.GunFirst;
import model.GunSecond;
import model.GunThird;
import model.Player;
import model.User;
import javafx.scene.control.Button;
public class VirtualGameGUIController {
	//Atributes
	private Stage stage;
	private Player player;
	public static GraphicsContext graphics;
	private Image scenaryGame;
	public static boolean up;
	public static boolean down;
	public static boolean left;
	public static boolean right;
	private GameManagement gm;
	@FXML
    private TableView<User> tableScore;

    @FXML
    private TableColumn<User,Integer> idPosition;

    @FXML
    private TableColumn<User,String> idNickname;

    @FXML
    private TableColumn<User, Double> idScore;

    @FXML
    private TableColumn<User,Integer> idDefeats;

    @FXML
    private TableColumn<User,Integer> idLastStage;

    @FXML
    private TableColumn<User,Double> idMoodleCoins;

    @FXML
	private BorderPane basePane;
    
    private AnimationTimer animationTimer;

	public VirtualGameGUIController(Stage s) {
		stage=s;
		gm=new GameManagement();
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
		player = new Player(50,20,100);
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
				player.attack();
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
		player.setPaths();
		basePane.getChildren().clear();
		basePane.getChildren().add(canva);
		basePane.setCenter(root);
		graphics = canva.getGraphicsContext2D();
		File file = new File("images/imagesUI/Backgrounds/Scenary.jpg");
    	Image imload = new Image(file.toURI().toString());
    	scenaryGame = imload;
	}
	
	public void draw() {
		graphics.drawImage(scenaryGame, 0, 0);
		player.draw(graphics);
		}
	
	public void loopGame() {
		
		animationTimer = new AnimationTimer() {
			
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
    
    public void startPauseGame() throws IOException {
    	basePane.setOnKeyPressed(null);
    	FXMLLoader fxmload = new FXMLLoader(getClass().getResource("PauseGame.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		basePane.getChildren().clear();
		basePane.setCenter(root);
	}
    
    @FXML
    void pauseGame(ActionEvent event) throws IOException {
    		startPauseGame();	
    		animationTimer.stop();	
    		player.setPaused(true);
    }
    
    @FXML
    void resumeGame(ActionEvent event) throws IOException{
		player.setPaused(false);
    	startScenary();
    	animationTimer.start();
    }
    
    @FXML
    void saveAndExit(ActionEvent event) throws IOException{
    	
    }
    
    @FXML
    void backMenuWithoutSaving(ActionEvent event) throws IOException{
    	startMenu();
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
    /**
  	 * Sort by defeats
  	 * @param ActionEvent event
  	 */
    void sortByDefeats(ActionEvent event) {
    	ObservableList<User> user= FXCollections.observableArrayList(gm.insertionSortByDefeats());
		tableScore.setItems(user);
		
		idPosition.setCellValueFactory(new PropertyValueFactory<User, Integer>("Position"));
		idNickname.setCellValueFactory(new PropertyValueFactory<User, String>("Nickname"));
		idScore.setCellValueFactory(new PropertyValueFactory<User, Double>("Score"));
		idDefeats.setCellValueFactory(new PropertyValueFactory<User, Integer>("Defeats"));
		idLastStage.setCellValueFactory(new PropertyValueFactory<User, Integer>("Last Stage"));
		idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
    }

    @FXML
    /**
  	 * Sort by last stage
  	 * @param ActionEvent event
  	 */
    void sortByLastStage(ActionEvent event) {
    	ObservableList<User> user= FXCollections.observableArrayList(gm.bubbleSortByLastStage());
		tableScore.setItems(user);
		
		idPosition.setCellValueFactory(new PropertyValueFactory<User, Integer>("Position"));
		idNickname.setCellValueFactory(new PropertyValueFactory<User, String>("Nickname"));
		idScore.setCellValueFactory(new PropertyValueFactory<User, Double>("Score"));
		idDefeats.setCellValueFactory(new PropertyValueFactory<User, Integer>("Defeats"));
		idLastStage.setCellValueFactory(new PropertyValueFactory<User, Integer>("Last Stage"));
		idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
    }

    @FXML
    /**
	 * Sort by moodleCoins
	 * @param ActionEvent event
	 */
    void sortByMoodleCoins(ActionEvent event) {
    	ObservableList<User> user= FXCollections.observableArrayList(gm.selectionSortByMoodleCoins());
		tableScore.setItems(user);
		
		idPosition.setCellValueFactory(new PropertyValueFactory<User, Integer>("Position"));
		idNickname.setCellValueFactory(new PropertyValueFactory<User, String>("Nickname"));
		idScore.setCellValueFactory(new PropertyValueFactory<User, Double>("Score"));
		idDefeats.setCellValueFactory(new PropertyValueFactory<User, Integer>("Defeats"));
		idLastStage.setCellValueFactory(new PropertyValueFactory<User, Integer>("Last Stage"));
		idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
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
    private Label coinsShop;
    
    @FXML
    void buyGunOne() {
    	
    	if(player.getCoins()>=1500) {
    		GunFirst gf = new GunFirst(player.getPosX(),player.getPosY());
        	player.setGun(gf);
        	player.setPaths();
        	player.setCoins(player.getCoins()-1500);
        	coinsShop.setText(String.valueOf(player.getCoins()));
    	}
    	else {
    		
    	}
    	
    }

    @FXML
    void buyGunTwo() {
    	if(player.getCoins()>=3500) {
    		GunSecond gs = new GunSecond(player.getPosX(),player.getPosY());
        	player.setGun(gs);
        	player.setPaths();
        	player.setCoins(player.getCoins()-3500);
        	coinsShop.setText(String.valueOf(player.getCoins()));
    	}
    	else {
    		
    	}
    }

    @FXML
    void buyGunThree() {
    	
    	if(player.getCoins()>=5000) {
    		GunThird gt = new GunThird(player.getPosX(),player.getPosY());
        	player.setGun(gt);
        	player.setPaths();
        	player.setCoins(player.getCoins()-5000);
        	coinsShop.setText(String.valueOf(player.getCoins()));
    	}
    	else {
    		
    	}
    	
    }


    @FXML
    void backShopToGame(ActionEvent event) throws IOException {
    	initScenary();
		eventManager();
		draw();
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
    	player.setWoman(false);
    	startScenary();
    }

    @FXML
    void chooseGirl(ActionEvent event) throws IOException {
    	player.setWoman(true);
    	startScenary();
    }
    
    void eventsManagement() {
    	
    }
    
    
}
