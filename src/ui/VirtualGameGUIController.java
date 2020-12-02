package ui;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.GameManagement;
import model.GreenPDF;
import model.Gun;
import model.GunFirst;
import model.GunManagement;
import model.GunSecond;
import model.GunThird;
import model.Pdf;
import model.Player;
import model.RedPDF;
import model.User;
import thread.LoadingScreenThread;
import model.YellowPDF;
import thread.PdfMovementThread;

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
	private int quantityOfEnemies;
	private boolean nextStage;
	private ArrayList<Pdf> enemies;
	@FXML
    private Circle circle1;

    @FXML
    private Circle circle2;

    @FXML
    private Circle circle3;
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
    
    private boolean movement;
    
    private LoadingScreenThread lst;
    
    private AnimationTimer animationTimer;
    
    private GunManagement gunManagement;

	public VirtualGameGUIController(Stage s) {
		stage=s;
		gm=new GameManagement();
		enemies = new ArrayList<Pdf>();
		nextStage = false;
		quantityOfEnemies = 3;
		gunManagement = new GunManagement();
		
	}
	public void starLoadingScreen() throws IOException {
		basePane.setOnKeyPressed(null);
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("LoadingScreen.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		basePane.getChildren().clear();
		basePane.setCenter(root);
	}
	@FXML
	void startAnimation(ActionEvent event) {
		if (movement == false){
	    	movement = true;
	    	} 
	    	lst = new LoadingScreenThread(this);
			lst.setDaemon(true);
			lst.start();
	}
	 public boolean isMovement() {
			return movement;
	}
	 public void movement() throws IOException {
		 circle1.setLayoutX(circle1.getLayoutX()+20);
			circle2.setLayoutX(circle2.getLayoutX()+20);
			circle3.setLayoutX(circle3.getLayoutX()+20);
			
			if(circle1.getLayoutX()>935+100) {
				circle1.setLayoutX(-50);
			}
			if(circle2.getLayoutX()>935+100) {
				circle2.setLayoutX(-50);
			}
			if(circle3.getLayoutX()>935+100) {
				circle3.setLayoutX(-50);
			}
			if(circle3.getLayoutX()==310) {
				movement = false;
				startMenu();
			}
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
		player = new Player();
		Gun initial = new Gun(player.getPosX(),player.getPosY(),1);
		GunFirst gf = new GunFirst(player.getPosX(),player.getPosY(),2);
		GunSecond gs = new GunSecond(player.getPosX(),player.getPosY(),3);
		GunThird gt = new GunThird(player.getPosX(),player.getPosY(),4);
		gunManagement.addGun(initial);
		gunManagement.addGun(gf);
		gunManagement.addGun(gs);
		gunManagement.addGun(gt);
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
		healthLabel.setText(String.format("%.2f", player.getHealth()));
		coinsGame.setText(String.valueOf(player.getCoins()));
		verifyCollisions();
		generateEnemies();
	}
	
	public void initScenary() throws IOException {
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("GameScenary.fxml"));
		fxmload.setController(this);
		Parent root=fxmload.load();
		Canvas canva  = new Canvas(935,688);
		player.setPaths();
		player.setGun(gunManagement.getInitialGun());
		basePane.getChildren().clear();
		basePane.getChildren().add(canva);
		basePane.setCenter(root);
		graphics = canva.getGraphicsContext2D();
		File file = new File("images/imagesUI/Backgrounds/Scenary.jpg");
    	Image imload = new Image(file.toURI().toString());
    	scenaryGame = imload;
    	player.setSaveExit(false);
    	
	}
	
	public void draw() {
		graphics.drawImage(scenaryGame, 0, 0);
		player.draw(graphics);
		for(int i=0;i<enemies.size();i++) {
				enemies.get(i).draw(graphics);
			}
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
	private Label healthLabel;

    @FXML
    void buyItems(ActionEvent event) throws IOException {
    	player.setPaused(true);
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
    	player.setSaveExit(true);
    	enemies.clear();
    }
    
    @FXML
    void backMenuWithoutSaving(ActionEvent event) throws IOException{
    	player.setSaveExit(true);
    	enemies.clear();
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
    void refreshTable(ActionEvent event) {
    	ObservableList<User> user= FXCollections.observableArrayList(gm.showList());
		tableScore.setItems(user);
		
		idPosition.setCellValueFactory(new PropertyValueFactory<User, Integer>("Position"));
		idNickname.setCellValueFactory(new PropertyValueFactory<User, String>("Nickname"));
		idScore.setCellValueFactory(new PropertyValueFactory<User, Double>("Score"));
		idDefeats.setCellValueFactory(new PropertyValueFactory<User, Integer>("Defeats"));
		idLastStage.setCellValueFactory(new PropertyValueFactory<User, Integer>("Last Stage"));
		idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
    }
    @FXML
    void searchByNickname(ActionEvent event) {
    	ArrayList<User> listUsers = new ArrayList<User>();
    	String nickname = searchNickname.getText();
		if(nickname.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Empty space");
			alert.setHeaderText("You must fill in the blank");
			alert.setContentText("Check that you have entered the nickname correctly");
		}else {
			listUsers = gm.searchUser(nickname);
			if(listUsers.size()==1) {
				searchNickname.clear();
				tableScore.getItems().clear();
				ObservableList<User> user= FXCollections.observableArrayList(gm.searchUser(nickname));
				tableScore.setItems(user);
				
				idPosition.setCellValueFactory(new PropertyValueFactory<User, Integer>("Position"));
				idNickname.setCellValueFactory(new PropertyValueFactory<User, String>("Nickname"));
				idScore.setCellValueFactory(new PropertyValueFactory<User, Double>("Score"));
				idDefeats.setCellValueFactory(new PropertyValueFactory<User, Integer>("Defeats"));
				idLastStage.setCellValueFactory(new PropertyValueFactory<User, Integer>("Last Stage"));
				idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("User not found");
				alert.setContentText("Check that you have entered the nickname correctly");
			}
		}
    }

    @FXML
    void searchByPosition(ActionEvent event) {
    	ArrayList<User> listUsers = new ArrayList<User>();
    	String position = searchPosition.getText();
		if(position.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Empty space");
			alert.setHeaderText("You must fill in the blank");
			alert.setContentText("Check that you have entered the position correctly");
		}else {
			int pos=Integer.parseInt(position);
			listUsers = gm.searchPosition(pos);
			if(listUsers.size()==1) {
				searchPosition.clear();
				tableScore.getItems().clear();
				ObservableList<User> user= FXCollections.observableArrayList(gm.searchPosition(pos));
				tableScore.setItems(user);
				
				idPosition.setCellValueFactory(new PropertyValueFactory<User, Integer>("Position"));
				idNickname.setCellValueFactory(new PropertyValueFactory<User, String>("Nickname"));
				idScore.setCellValueFactory(new PropertyValueFactory<User, Double>("Score"));
				idDefeats.setCellValueFactory(new PropertyValueFactory<User, Integer>("Defeats"));
				idLastStage.setCellValueFactory(new PropertyValueFactory<User, Integer>("Last Stage"));
				idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("User not found");
				alert.setContentText("Check that you have entered the name correctly");
			}
		}
    
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
		coinsShop.setText(String.valueOf(player.getCoins()));
	}
   
    @FXML
    private Label coinsShop;
    
    @FXML
    void buyGunOne() {
    	
    	if(player.getCoins()>=1500) {
        	player.setGun(gunManagement.getInitialGun().getNextGun());
        	player.setTypeOfGun("firstGun");
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
    		
        	player.setGun(gunManagement.getInitialGun().getNextGun().getNextGun());
        	player.setTypeOfGun("secondGun");
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
        	player.setGun(gunManagement.getInitialGun().getNextGun().getNextGun().getNextGun());
        	player.setTypeOfGun("thirdGun");
        	player.setPaths();
        	player.setCoins(player.getCoins()-5000);
        	coinsShop.setText(String.valueOf(player.getCoins()));
    	}
    	else {
    		
    	}
    	
    }


    @FXML
    void backShopToGame(ActionEvent event) throws IOException {
    	player.setPaused(false);
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
    	generateInitialEnemies();
    	startScenary();
    }

    @FXML
    void chooseGirl(ActionEvent event) throws IOException {
    	player.setWoman(true);
    	generateInitialEnemies();
    	startScenary();
    }
    
    private void generateInitialEnemies() {
    	RedPDF enemy1 = new RedPDF(200, 100,player);
		RedPDF enemy2 = new RedPDF(700, 321,player);
		RedPDF enemy3 = new RedPDF(200, 400,player);
		enemies.add(enemy1);
		enemies.add(enemy2);
		enemies.add(enemy3);
		for(int i = 0;i<enemies.size();i++) {
			new PdfMovementThread(this,enemies.get(i), player).start();	
		}
    }
    
    void eventsManagement() {
    	
    }
    
    public void generateEnemies() {
    	if(enemies.size() == 0) {
    		player.nextStage();
			quantityOfEnemies +=1;
			if(quantityOfEnemies>3 && quantityOfEnemies<7) {
				for(int i=0;i<quantityOfEnemies;i++) {
				double posX = Math.random()*(688);
				double posY = Math.random()*(688-250) + 250;
				RedPDF gn = new RedPDF(posX,posY,player);
				enemies.add(gn);
				}
			}
			else if(quantityOfEnemies>=7 && quantityOfEnemies<12) {
				for(int i=0;i<quantityOfEnemies;i++) {
					double posX = Math.random()*(688);
					double posY = Math.random()*(688-250) + 250;
					GreenPDF gn = new GreenPDF(posX,posY,player);
					enemies.add(gn);
					}
			}
			else {
				for(int i=0;i<quantityOfEnemies;i++) {
					double posX = Math.random()*(688);
					double posY = Math.random()*(688-250) + 250;
					YellowPDF gn = new YellowPDF(posX,posY,player);
					enemies.add(gn);
					}
			}
		}
		for(int i = 0;i<enemies.size();i++) {
			new PdfMovementThread(this,enemies.get(i), player).start();	
		}
    }
    
    public void verifyCollisions() {
    	for(int i = 0;i<enemies.size();i++) {
			player.getGun().getBullet().verifyCollision(enemies.get(i),player);
			enemies.get(i).verifyCollision(player);
			if(enemies.get(i).getHealth() <=0) {
				if(enemies.get(i) instanceof RedPDF){
					player.setCoins(player.getCoins()+200);
					player.setScore(player.getScore()+100);
					player.defeat();
				}
				else if(enemies.get(i) instanceof GreenPDF) {
					player.setCoins(player.getCoins()+300);
					player.setScore(player.getScore()+200);
					player.defeat();
				}
				else if(enemies.get(i) instanceof YellowPDF) {
					player.setCoins(player.getCoins()+500);
					player.setScore(player.getScore()+400);
					player.defeat();
				}
				enemies.remove(i);
			}
		}
    }
}
