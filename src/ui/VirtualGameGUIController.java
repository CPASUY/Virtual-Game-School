package ui;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import exceptions.NoEnoughCoinsException;
import exceptions.NoFoundPositionException;
import exceptions.RepeatedNicknameException;
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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.GameManagement;
import model.GreenPDF;
import model.Gun;
import model.GunFirst;
import model.GunManagement;
import model.GunSecond;
import model.GunThird;
import model.InitialPlayerManagement;
import model.Log;
import model.Pdf;
import model.Player;
import model.RedPDF;
import model.User;
import thread.LoadingSaveScreenThread;
import thread.LoadingScreenThread;
import model.YellowPDF;
import thread.PdfMovementThread;

public class VirtualGameGUIController {
	//Atributes
	/**
	 * Stage of GUI
	 */
	private Stage sg;
	/**
	 * Player of game
	 */
	private Player player;
	/**
	 * Constant of graphic context
	 */
	public static GraphicsContext graphics;
	/**
	 * Image of scenary
	 */
	private Image scenaryGame;
	/**
	 * Constant of key up
	 */
	public static boolean up;
	/**
	 * Constant of key down
	 */
	public static boolean down;
	/**
	 * Counter for the save games
	 */
	private int contSaves;
	/**
	 * File to export the saves games
	 */
	private File export;
	/**
	 * Constant of key left
	 */
	public static boolean left;
	/**
	 * Constant of key rught
	 */
	public static boolean right;
	/**
	 * Declaration of a game management
	 */
	private GameManagement gm;
	/**
	 * Quantity of enemies in the game
	 */
	private int quantityOfEnemies;
	/**
	 * Arraylist of the enemies
	 */
	private ArrayList<Pdf> enemies;
    @FXML
    private Circle save1;

    @FXML
    private Circle save2;

    @FXML
    private Circle save3;

    @FXML
    private Circle save4;
    
	@FXML
	private Circle circle3;

	@FXML
	private Polygon triangle;

	@FXML
	private Rectangle rectangle;

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
    private TableColumn<User,Integer> idStages;

    @FXML
    private TableColumn<User,Double> idMoodleCoins;

    @FXML
	private BorderPane basePane;
    /**
	 * Movement of loading screen
	 */
    private boolean movement;
    /**
	 * Movement of loading save game
	 */
    private boolean movementSave;
    /**
	 * Declaration of Loading Screen Thread
	 */
    private LoadingScreenThread lst;
    /**
	 * Declaration of Loading Save Screen Thread
	 */
    private LoadingSaveScreenThread lsst;
    
    /**
	 * Animation timer
	 */
    private AnimationTimer animationTimer;
    /**
	 * Declaration of a object of Gun management
	 */
    private GunManagement gunManagement;
    /**
	 * Declaration of initial playermanagement
	 */
    private InitialPlayerManagement initialPlayers;
    /**
	 * Creates an instance of VirtualGameGUIController
	 * @param Stage s
	 */
	public VirtualGameGUIController(Stage s) {              
		File carpeta = new File("data/saves"); 
		File[] lista = carpeta.listFiles();
		contSaves=lista.length;
		sg=s;
		try {
			gm=new GameManagement();
		} catch (IOException e) {
			e.printStackTrace();
		}
		enemies = new ArrayList<Pdf>();
		gunManagement = new GunManagement();
		initialPlayers = new InitialPlayerManagement();
		Player boy = new Player();
		Player girl = new Player();
		girl.setWoman(true);
		initialPlayers.addPlayer(boy);
		initialPlayers.addPlayer(girl);
	}
	/**
	 * initialize
	 * Method for serialize the users and log when a player close the windoe
	 */
	public void initialize() {
		sg.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				System.out.println("Closing the window!");
				try {
					gm.saveRootLogs();
					gm.saveRootUsers();
				} catch (FileNotFoundException e) {
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});

	}
	/**
	 * starLoadingScreen
	 * @throws IOException 
	 */
	public void starLoadingScreen(){
		basePane.setOnKeyPressed(null);
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("LoadingScreen.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * startAnimation
	 * Method that start all the animations of the game
	 * @param event event
	 * @throws IOException
	 */
	@FXML
	void startAnimation(ActionEvent event) {
		if (movement == false){
	    	movement = true;
	    	} 
	    	lst = new LoadingScreenThread(this);
			lst.setDaemon(true);
			lst.start();
	}
	/**
	 * isMovement
	 * @return is is moving or not
	 */
	 public boolean isMovement() {
			return movement;
	}
	 /**
	  * movement
	  * Method that move the shapes
	  * @throws IOException
	  */
	 public void movement(){
		 circle3.setLayoutX(circle3.getLayoutX()+20);
		 triangle.setLayoutX(triangle.getLayoutX()+20);
		 rectangle.setLayoutX(rectangle.getLayoutX()+20);

		 if(circle3.getLayoutX()>935+100) {
			 circle3.setLayoutX(-50);
		 }
		 if(triangle.getLayoutX()>935+100) {
			triangle.setLayoutX(-50);
		 }
		 if(rectangle.getLayoutX()>935+100) {
			 rectangle.setLayoutX(-50);
		 }
		 if(circle3.getLayoutX()==310) {
			 movement = false;
			 startMenu();
		 }
	 }
	 /**
	  * movementSave
	  * Method that move the shapes of the loading save game
	  * @throws IOException
	  */
	 public void movementSave(){
		 save1.setLayoutX(save1.getLayoutX()+20);
		 save2.setLayoutX(save2.getLayoutX()+20);
		 save3.setLayoutX(save3.getLayoutX()+20);
		 save4.setLayoutX(save4.getLayoutX()+20);

		 if(save1.getLayoutX()>935+100) {
			 save1.setLayoutX(-50);
		 }
		 if(save2.getLayoutX()>935+100) {
			 save2.setLayoutX(-50);
		 } if(save3.getLayoutX()>935+100) {
			 save3.setLayoutX(-50);
		 } if(save4.getLayoutX()>935+100) {
			 save4.setLayoutX(-50);
		 }
		 if(save1.getLayoutX()==130) {
			 movementSave = false;
			 startMenu();
		 }
	 }
	 /**
	  * isMovementSave
	  * @return movementSave is is moving or not
	  */
	 public boolean isMovementSave() {
		 return movementSave;
	}
	 /**
	  * startMenu
	  * Method that start the menu
	  * @throws IOException
	  */ 
	public void startMenu(){
		basePane.setOnKeyPressed(null);
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("GameMenu.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	/**
	  * backMenu
	  * Method that back to menu
	  * @throws IOException
	  */ 
	@FXML
	void backMenu(){
		enemies.clear();
		startMenu();
	}
	/**
	 * exit
	 * Method that close the program
	 * @param event event
	 * @throws IOException
	 */
	@FXML
	void exit(ActionEvent event){
		try {
			gm.saveRootLogs();
			gm.saveRootUsers();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
}
		
	/**
	 * loadGame
	 * Method that load save games
	 * @param event event
	 * @throws IOException
	 */
	@FXML
	void loadGame(ActionEvent event){
		startLoadGame();
	}
	/**
	 * newGame
	 * Method that start a new game
	 * @param event event
	 * @throws IOException
	 */
	@FXML
	void newGame(ActionEvent event){
		player = new Player();
		quantityOfEnemies = 0;
		Player boy = new Player();
		Player girl = new Player();
		girl.setWoman(true);
		initialPlayers.setInitialPlayer(boy);
		initialPlayers.getInitialPlayer().setNextPlayer(girl);
		enemies.clear();
		createGunList();
		starChoosePlayers();
	}
	/**
	 * startAnimation
	 * Method that show the scores, the sorts, and the binary search
	 * @param event event
	 * @throws IOException
	 */
	@FXML
	void showScore(ActionEvent event){
		startScores();
	}
	/**
	 * startScenary
	 * @throws IOException
	 */
	@FXML
	public void startScenary(){
		initScenary();
		eventManager();
		draw();
    	loopGame();
	}
	/**
	 * eventManager
	 * @throws IOException
	 */
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
	/**
	 * updateState
	 * @throws IOException
	 */
	public void updateState() {
		player.move();
		healthLabel.setText(String.format("%.2f", player.getHealth()));
		coinsGame.setText(String.valueOf(player.getCoins()));
		verifyCollisions();
		generateEnemies();
	}
	 /**
	  * initScenary
	  * Method for init all the game
	  * @throws IOException
	  */
	public void initScenary(){
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("GameScenary.fxml"));
		fxmload.setController(this);
		Parent root = null;
		try {
			root = fxmload.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Canvas canva  = new Canvas(935,688);
		player.setPaths();
		if(player.getGun() == null) {
			player.setGun(gunManagement.getInitialGun());	
		}
		basePane.getChildren().clear();
		basePane.getChildren().add(canva);
		basePane.setCenter(root);
		graphics = canva.getGraphicsContext2D();
		File file = new File("images/imagesUI/Backgrounds/Scenary.jpg");
    	Image imload = new Image(file.toURI().toString());
    	scenaryGame = imload;
    	if(quantityOfEnemies == 0) {
    	quantityOfEnemies = 3;
    	}else {
    		generateEnemies();
    	}
    	player.setSaveExit(false);
	}
	 /**
	  * draw
	  * draw all the images
	  */
	public void draw() {
		graphics.drawImage(scenaryGame, 0, 0);
		player.draw(graphics);
		for(int i=0;i<enemies.size();i++) {
				enemies.get(i).draw(graphics);
			}
		}
	 /**
	  * loopGame
	  * Repeat 60fps
	  */
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
	/**
	 * buyItems
	 * Method that display the shop
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void buyItems(ActionEvent event){
    	player.setPaused(true);
    	startShop();
    }
    /**
	  * startPauseGame
	  * @throws IOException
	  */
    public void startPauseGame(){
    	basePane.setOnKeyPressed(null);
    	FXMLLoader fxmload = new FXMLLoader(getClass().getResource("PauseGame.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
    /**
	 * pauseGame
	 * Method that pause the game
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void pauseGame(ActionEvent event){
    		startPauseGame();	
    		animationTimer.stop();	
    		player.setPaused(true);
    }
    /**
	 * resumeGame
	 * Method for resume game
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void resumeGame(ActionEvent event){
		player.setPaused(false);
    	startScenary();
    	animationTimer.start();
    }
    /**
	  * startLoadingSaveScreen
	  * @throws IOException
	  */
    public void startLoadingSaveScreen(){
		basePane.setOnKeyPressed(null);
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("LoadingSaveScreen.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    /**
	 * saveAndExit
	 * Method that save and exit a game
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void saveAndExit(ActionEvent event){
    	startLoadingSaveScreen();
    	contSaves++;
    	String name="data/saves/LoadGames"+contSaves+".csv";
    	export=new File(name);
    	PrintWriter pw;
		try {
			if (movementSave == false){
	    		movementSave = true;
	    	} 
	    	lsst = new LoadingSaveScreenThread(this);
	    	lsst.setDaemon(true);
	    	lsst.start();
			pw = new PrintWriter(export);
			pw.write(player.isWoman()+" "+player.getScore()+" "+player.getHealth()+" "+player.getCoins()+" "+player.getPosY()+" "+player.getPosX()+" "+player.getDefeats()+" "+player.getStagesDefault()+" "+player.getTypeOfGun()+" "+(quantityOfEnemies-1)+"\n");
	    	pw.close();
	    	player.setSaveExit(true);
	    	enemies.clear();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
    /**
	  * startScores
	  * @throws IOException
	  */
    public void startScores() {
    	basePane.setOnKeyPressed(null);
    	FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Scores.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tableScore.getItems().clear();
		ObservableList<User> user= FXCollections.observableArrayList(gm.showList());
		tableScore.setItems(user);
		
		idPosition.setCellValueFactory(new PropertyValueFactory<User, Integer>("Position"));
		idNickname.setCellValueFactory(new PropertyValueFactory<User, String>("Nickname"));
		idScore.setCellValueFactory(new PropertyValueFactory<User, Double>("Score"));
		idDefeats.setCellValueFactory(new PropertyValueFactory<User, Integer>("Defeats"));
		idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
		idStages.setCellValueFactory(new PropertyValueFactory<User, Integer>("Stages"));
	}

    @FXML
    private TextField searchNickname;

    @FXML
    private TextField searchPosition;
    /**
	 * refreshTable
	 * Method for refresh the table
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void refreshTable(ActionEvent event) {
    	tableScore.getItems().clear();
    	ObservableList<User> user= FXCollections.observableArrayList(gm.showList());
		tableScore.setItems(user);
		
		idPosition.setCellValueFactory(new PropertyValueFactory<User, Integer>("Position"));
		idNickname.setCellValueFactory(new PropertyValueFactory<User, String>("Nickname"));
		idScore.setCellValueFactory(new PropertyValueFactory<User, Double>("Score"));
		idDefeats.setCellValueFactory(new PropertyValueFactory<User, Integer>("Defeats"));
		idStages.setCellValueFactory(new PropertyValueFactory<User, Integer>("Stages"));
		idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
    }
    /**
	 * searchByNickname
	 * Method for search a nickname in the table
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void searchByNickname(ActionEvent event) {
    	ArrayList<User> listUsers = new ArrayList<User>();
    	String nickname = searchNickname.getText();
		if(nickname.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Empty space");
			alert.setHeaderText("You must fill in the blank");
			alert.setContentText("Check that you have entered the nickname correctly");
			alert.showAndWait();
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
				idStages.setCellValueFactory(new PropertyValueFactory<User, Integer>("Stages"));
				idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("User not found");
				alert.setContentText("Check that you have entered the nickname correctly");
				alert.showAndWait();
			}
		}
    }
    /**
	 * searchByPosition
	 * Method for search a user by the position
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void searchByPosition(ActionEvent event) {
    	try {
    	
    	String position = searchPosition.getText();
		if(position.isEmpty()) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Empty space");
			alert.setHeaderText("You must fill in the blank");
			alert.setContentText("Check that you have entered the position correctly");
			alert.showAndWait();
		}else {
			int pos=Integer.parseInt(position);
			ArrayList<User> listUsers = new ArrayList<User>();
			listUsers = gm.searchPosition(pos);
				searchPosition.clear();
				tableScore.getItems().clear();
				ObservableList<User> user= FXCollections.observableArrayList(listUsers);
				tableScore.setItems(user);
				idPosition.setCellValueFactory(new PropertyValueFactory<User, Integer>("Position"));
				idNickname.setCellValueFactory(new PropertyValueFactory<User, String>("Nickname"));
				idScore.setCellValueFactory(new PropertyValueFactory<User, Double>("Score"));
				idDefeats.setCellValueFactory(new PropertyValueFactory<User, Integer>("Defeats"));
				idStages.setCellValueFactory(new PropertyValueFactory<User, Integer>("Stages"));
				idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
		}
    }
    catch(NoFoundPositionException e) {
    	Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("User not found");
		alert.setContentText("This position no exists");
		alert.showAndWait();	
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
		idStages.setCellValueFactory(new PropertyValueFactory<User, Integer>("Stages"));
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
		idStages.setCellValueFactory(new PropertyValueFactory<User, Integer>("Stages"));
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
		idStages.setCellValueFactory(new PropertyValueFactory<User, Integer>("Stages"));
		idMoodleCoins.setCellValueFactory(new PropertyValueFactory<User, Double>("MoodleCoins"));
    }
    /**
	  * startShop
	  * @throws IOException
	  */
    public void startShop(){
		basePane.setOnKeyPressed(null);
    	FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Shop.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		coinsShop.setText(String.valueOf(player.getCoins()));
	}
   
    @FXML
    private Label coinsShop;
    /**
	  * buyGunOne
	  * @throws NoEnoughCoinsException
	  */
    @FXML
    void buyGunOne(){
        	try {
				player.discountCoins("firstGun");
				player.setGun(gunManagement.getInitialGun().getNextGun());
				coinsShop.setText(String.valueOf(player.getCoins()));
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Buy succesfully");
				alert.setHeaderText("Your purchase has been completed successfully");
				alert.setContentText("Enjoy defeating enemies");
				alert.show();
			} catch (NoEnoughCoinsException e) {
				Alert warning = new Alert(AlertType.WARNING);
				warning.setTitle("Not Enough MoodleCoins");
				warning.setHeaderText("You need have the enough MoodleCoins to buy this gun");
				warning.setContentText("Try to defeat more enemies");
				warning.show();
			}
    	}
    /**
	  * buyGunTwo
	  * @throws NoEnoughCoinsException
	  */
    @FXML
    void buyGunTwo(){
    	try {
    		player.discountCoins("secondGun");
			player.setGun(gunManagement.getInitialGun().getNextGun().getNextGun());
			coinsShop.setText(String.valueOf(player.getCoins()));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Buy succesfully");
			alert.setHeaderText("Your purchase has been completed successfully");
			alert.setContentText("Enjoy defeating enemies");
			alert.show();
		} catch (NoEnoughCoinsException e) {
			Alert warning = new Alert(AlertType.WARNING);
			warning.setTitle("Not Enough MoodleCoins");
			warning.setHeaderText("You need have the enough MoodleCoins to buy this gun");
			warning.setContentText("Try to defeat more enemies");
			warning.show();
		}
	}
    /**
	  * buyGunThree
	  * @throws NoEnoughCoinsException
	  */
    @FXML
    void buyGunThree(){
    	try {
    		player.discountCoins("thirdGun");
			player.setGun(gunManagement.getInitialGun().getNextGun().getNextGun().getNextGun());
			coinsShop.setText(String.valueOf(player.getCoins()));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Buy succesfully");
			alert.setHeaderText("Your purchase has been completed successfully");
			alert.setContentText("Enjoy defeating enemies");
			alert.show();
		} catch (NoEnoughCoinsException e) {
			Alert warning = new Alert(AlertType.WARNING);
			warning.setTitle("Not Enough MoodleCoins");
			warning.setHeaderText("You need have the enough MoodleCoins to buy this gun");
			warning.setContentText("Try to defeat more enemies");
			warning.show();
		}
    }
    /**
	 * backShopToGame
	 * Method to back to game
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void backShopToGame(ActionEvent event){
    	player.setPaused(false);
    	initScenary();
		eventManager();
		draw();
    }
    /**
	  * startLoadGame
	  * @throws IOException
	  */
    public void startLoadGame(){
    	FXMLLoader fxmload = new FXMLLoader(getClass().getResource("LoadGame.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    /**
	 * loadSave
	 * Method for load the save games
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void loadSave(ActionEvent event){
    	FileChooser fc = new FileChooser();
    	fc.setInitialDirectory(new File("data/saves"));
    	fc.getExtensionFilters().addAll(new ExtensionFilter("CSV Files", "*.csv"));
    	File selectedFile = fc.showOpenDialog(null);
    	if(selectedFile != null) {
    		loadSaveGame(selectedFile);
    	}
    	else {
    		System.out.println("File is not valid");
    	}
    }
    /**
	  * loadSaveGame
	  * @param File !=null
	  * @throws IOException
	  */
    public void loadSaveGame(File f){
    	BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(f));
			String line=br.readLine();
			String [] parts=line.split(" ");
			boolean woman=Boolean.parseBoolean(parts[0]);
			int score=Integer.parseInt(parts[1]);
			double health=Double.parseDouble(parts[2]);
			int coins=Integer.parseInt(parts[3]);
			double posY=Double.parseDouble(parts[4]);
			double posX=Double.parseDouble(parts[5]);
			int defeats=Integer.parseInt(parts[6]);
			int stage=Integer.parseInt(parts[7]);
			int sta=stage-2;
			Player p=new Player(woman,score,health,coins,posY,posX,defeats,sta,parts[8]);
			player=p;
			createGunList();
			if(parts[8].equals("initialGun")) {
				player.setGun(gunManagement.getInitialGun());
			}
			else if(parts[8].equals("firstGun")) {
				player.setGun(gunManagement.getInitialGun().getNextGun());
			}
			else if(parts[8].equals("secondGun")) {
				player.setGun(gunManagement.getInitialGun().getNextGun().getNextGun());
			}
			else {
				player.setGun(gunManagement.getInitialGun().getNextGun().getNextGun().getNextGun());
			}
			quantityOfEnemies=Integer.parseInt(parts[9]);
			br.close();
	    	startScenary();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    /**
	 * loadSaveGames
	 * Method for load the save games
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void loadSaveGame(ActionEvent event){
    	startScenary();
    }
    /**
	  * startChoosePlayers
	  * @throws IOException
	  */
    public void starChoosePlayers(){
    	FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Players.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    /**
	 * chooseBoy
	 * Method for choose the character
	 * @param event event
	 * @throws IOException
	 */
    @FXML
    void chooseBoy(ActionEvent event){
    	
    	player = initialPlayers.getInitialPlayer();
    	startScenary();
    	generateInitialEnemies();
    }
    /**
   	 * chooseGirl
   	 * Method for choose the character
   	 * @param event event
   	 * @throws IOException
   	 */
    @FXML
    void chooseGirl(ActionEvent event){
    	player = initialPlayers.getInitialPlayer().getNextPlayer();
    	startScenary();
    	generateInitialEnemies();
    }
    /**
	  * generateInitialEnemies
	  * @throws IOException
	  */
    private void generateInitialEnemies() {
    	RedPDF enemy1 = new RedPDF(200, 500,player);
		RedPDF enemy2 = new RedPDF(700, 321,player);
		RedPDF enemy3 = new RedPDF(200, 400,player);
		enemies.add(enemy1);
		enemies.add(enemy2);
		enemies.add(enemy3);
		for(int i = 0;i<enemies.size();i++) {
			new PdfMovementThread(this,enemies.get(i), player).start();	
		}
    }
    /**
	  * generateEnemies
	  * @throws IOException
	  */
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
			else if(quantityOfEnemies>=12){
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
    /**
	  * verifyCollisions
	  * @throws IOException
	  */
    public void verifyCollisions(){
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
    	if(player.getHealth()<=0) {
    		animationTimer.stop();
    		enemies.clear();
    		quantityOfEnemies = 0;
    		startGameOver();
    	}
    }
    /**
	  * startGameOver
	  * @throws IOException
	  */
    public void startGameOver(){
		basePane.setOnKeyPressed(null);
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("GameOver.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    @FXML
    private TextField txtNicknameGameOver;
    /**
   	 * saveUserOver
   	 * Method for save the user when lose
   	 * @param event event
   	 * @throws IOException
   	 */
    @FXML
    void saveUserOver(ActionEvent event){
    	String n=txtNicknameGameOver.getText();
    	if(n.isEmpty()) {
    		Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Empty space");
			alert.setHeaderText("You must fill in the blank");
			alert.setContentText("Check that you have entered a nickname");
    	}else {
    	int c=player.getCoins();
    	double s=player.getScore();
    	int st=player.getStagesDefault();
    	int d=player.getDefeats();
    	try {
			gm.addUser(n, s, d, st,c);
			Alert alert = new Alert(AlertType.INFORMATION);
	    	alert.setTitle("Information");
	    	alert.setHeaderText(null);
	    	alert.setContentText("It has been saved successfully!");
	    	alert.showAndWait();
	    	enemies.clear();
		} catch (RepeatedNicknameException e) {
			Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("RepeatedNickname");
	    	alert.setHeaderText(null);
	    	alert.setContentText("This nickname already exist, think in another!");
	    	alert.showAndWait();
		}
    	}
    }
    
    @FXML
    private TableView<Log> tableLogs;
    
    @FXML
    private TableColumn<Log,String> idUser;
    @FXML
    private TableColumn<Log,LocalDate> idDate;
    @FXML
    void showLogs(){
    	startLogs();
    }
    /**
	  * startLogs
	  * @throws IOException
	  */
    public void startLogs(){
    	basePane.setOnKeyPressed(null);
		FXMLLoader fxmload = new FXMLLoader(getClass().getResource("Logs.fxml"));
		fxmload.setController(this);
		Parent root;
		try {
			root = fxmload.load();
			basePane.getChildren().clear();
			basePane.setCenter(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
		tableLogs.getItems().clear();
		ObservableList<Log> logs= FXCollections.observableArrayList(gm.showListLogs());
		tableLogs.setItems(logs);
		
		idUser.setCellValueFactory(new PropertyValueFactory<Log,String>("Nickname"));
		idDate.setCellValueFactory(new PropertyValueFactory<Log,LocalDate>("Date"));

    }
    /**
	  * createGunList
	  * @throws IOException
	  */
    public void createGunList() {
    	Gun initial = new Gun(player.getPosX(),player.getPosY(),1);
		GunFirst gf = new GunFirst(player.getPosX(),player.getPosY(),2);
		GunSecond gs = new GunSecond(player.getPosX(),player.getPosY(),3);
		GunThird gt = new GunThird(player.getPosX(),player.getPosY(),4);
		gunManagement.addGun(initial);
		gunManagement.addGun(gf);
		gunManagement.addGun(gs);
		gunManagement.addGun(gt);
    }
}
