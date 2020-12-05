package model;


import ui.VirtualGameGUIController;

/**
 * 
 * Class Player
 *
 */
public class Player extends Entity implements Motion,Attack {
	
	/**
	 * The player score
	 */
	private int score;
	/**
	 * The player health
	 */
	private final static double MAX_HEALTH = 100;
	/**
	 * The quantity of sprites of the player
	 */
	private final int QUANTITY_SPRITES = 5;
	/**
	 * The array of paths
	 */
	private String[] paths = new String[QUANTITY_SPRITES];
	/**
	 * The path of a boy character
	 */
	private String pathBoy = "sprites/BoyGunDefect/";
	/**
	 * The path of a girl character
	 */
	private String pathGirl = "sprites/GirlGunDefect/";
	/**
	 * The player health
	 */
	private int coins;
	/**
	 * The player lose
	 */
	private boolean lose;
	/**
	 * The player paused
	 */
	private boolean paused;
	/**
	 * The player save exit
	 */
	private boolean saveExit;
	/**
	 * If the character is woman
	 */
	private boolean woman;
	/**
	 * The player defeats
	 */
	private int defeats;
	/**
	 * The player stages
	 */
	private int stages;
	/**
	 * The player gun
	 */
	private Gun gun;
	/**
	 * The player type of gun
	 */
	private String typeOfGun;
	/**
	 * Counter
	 */
	private int cont;
	/**
	 * The player velocity
	 */
	private final int VELOCITY = 3;
	/**
	 * The next player
	 */
	private Player nextPlayer;

	/**
	 * Sets the player defeats
	 * @param int defeats
	 */
	public void setDefeats(int defeats) {
		this.defeats = defeats;
	}
	//Methods
		/**
		 * Creates an instance of Player
		 */
	public Player(){
		super(467,344,MAX_HEALTH);
		woman = false;
		setCoins(0);
		paused = false;
		setLose(false);
		cont=0;
		setSaveExit(false);
		stages = 1;
		defeats = 0;
		typeOfGun = "initialGun";
	}
	/**
	 * Creates an instance of Player
	 * @param woman if is woman
	 * @param score score of player
	 * @param health of player
	 * @param coins of player
	 * @param posY position y of the player
	 * @param posX position x of the player
	 * @param defeats of the player
	 * @param stages of player
	 * @param typeOfGun of the player
	 */
	public Player(boolean woman,int score,double health,int coins,double posY, double posX,int defeats,int stages,String typeOfGun) {
		super(posX,posY,health);
		this.woman = woman;
		this.score = score;
		this.coins  = coins;
		this.defeats = defeats;
		this.stages = stages;
		this.typeOfGun = typeOfGun;
	}
	/**
	 * The attack of the player
	 */
	@Override
	public void attack() {
				
			if(paused == false) {
				
				if(typeOfGun.equals("initialGun")) {
				  gun.setBullet(new Bullet(getPosX(),getPosY()+15,1));	
				}
				else if(typeOfGun.equals("firstGun")) {
				  gun.setBullet(new Bullet(getPosX(),getPosY()+15,2));
				}
				else if(typeOfGun.equals("secondGun")) {
				  gun.setBullet(new Bullet(getPosX(),getPosY()+15,3));
				}
				else {
				  gun.setBullet(new Bullet(getPosX(),getPosY()+15,4));
				}
				
				if(currentFrame == getFrames()[1] || currentFrame == getFrames()[2]) {
				 gun.getBullet().setDirection("left");	
				}else {
				 gun.getBullet().setDirection("right");	
				}
				gun.getBullet().draw(paused);
			}
	}
	/**
	 * The movement of the player
	 */
	@Override
	public void move() {
		cont++;
		if(getPosX()<0) {
			setPosX(0);
		}
		if(getPosY()<250) {
			setPosY(250);
		}
		if(getPosX()+currentFrame.getWidth()>=935) {
			setPosX(935-currentFrame.getWidth());
		}
		
		if(getPosY()+currentFrame.getHeight()>=688) {
			setPosY(688-currentFrame.getHeight());
		}
		
		if(VirtualGameGUIController.right) {
			currentFrame = getFrames()[3];
			if(cont>10) {
				currentFrame = getFrames()[4];
				if(cont>20) {
					cont=0;
				}
			}
			setPosX(getPosX()+VELOCITY);
		}
		if(VirtualGameGUIController.left) {
			currentFrame = getFrames()[1];
			if(cont>10) {
				currentFrame = getFrames()[2];
				if(cont>20) {
					cont=0;
				}
			}
			setPosX(getPosX()-VELOCITY);
		}
		if(VirtualGameGUIController.up) {
			currentFrame = getFrames()[3];
			if(cont>10) {
				currentFrame = getFrames()[4];
				if(cont>20) {
					cont=0;
				}
			}
			setPosY(getPosY()-VELOCITY);
		}
		if(VirtualGameGUIController.down) {
			currentFrame = getFrames()[1];
			if(cont>10) {
				currentFrame = getFrames()[2];
				if(cont>20) {
					cont=0;
				}
			}
			setPosY(getPosY()+VELOCITY);
		}
	}
	/**
	 * Returns the type of gun
	 * @return String typeOfGun
	 */
	public String getTypeOfGun() {
		return typeOfGun;
	}
	/**
	 * Sets the paths of the character
	 */
	public void setPaths() {
		
		if(woman) {
			
			if(gun instanceof GunFirst) {
				 String[] firstGun = {"sprites/GirlFirstGun/static.png","sprites/GirlFirstGun/left0.png","sprites/GirlFirstGun/left1.png",
			  							"sprites/GirlFirstGun/right0.png","sprites/GirlFirstGun/right1.png"};
				 paths = firstGun;
			}
			else if(gun instanceof GunSecond) {
				String[] secondGun = {"sprites/GirlSecondGun/static.png","sprites/GirlSecondGun/left0.png","sprites/GirlSecondGun/left1.png",
										"sprites/GirlSecondGun/right0.png","sprites/GirlSecondGun/right1.png"};
				paths = secondGun;
			}
			else if(gun instanceof GunThird) {
				String[] thirdGun = {"sprites/GirlThirdGun/static.png","sprites/GirlThirdGun/left0.png","sprites/GirlThirdGun/left1.png",
										"sprites/GirlThirdGun/right0.png","sprites/GirlThirdGun/right1.png"};
				paths = thirdGun;
			}
			else {
				String[] girl = {pathGirl+"static.png",pathGirl+"left0.png",pathGirl+"left1.png",pathGirl+"right0.png",pathGirl+"right1.png"};
				paths = girl;	
			}
			
			
		}
		else {
			
			if(gun instanceof GunFirst) {
			  String[] firstGun = {"sprites/BoyFirstGun/static.png","sprites/BoyFirstGun/left0.png","sprites/BoyFirstGun/left1.png",
					  				"sprites/BoyFirstGun/right0.png","sprites/BoyFirstGun/right1.png"};
			  paths = firstGun;
			}
			else if(gun instanceof GunSecond) {
				String[] secondGun = {"sprites/BoySecondGun/static.png","sprites/BoySecondGun/left0.png","sprites/BoySecondGun/left1.png",
		  								"sprites/BoySecondGun/right0.png","sprites/BoySecondGun/right1.png"};
				paths = secondGun;
			}
			else if(gun instanceof GunThird) {
				String[] thirdGun = {"sprites/BoyThirdGun/static.png","sprites/BoyThirdGun/left0.png","sprites/BoyThirdGun/left1.png",
										"sprites/BoyThirdGun/right0.png","sprites/BoyThirdGun/right1.png"};
				paths = thirdGun;
			}
			else {
				String[] boy = {pathBoy+"static.png",pathBoy+"left0.png",pathBoy+"left1.png",pathBoy+"right0.png",pathBoy+"right1.png"};
				paths = boy;	
			}
		}
		fillFrames(paths,QUANTITY_SPRITES);
	}
	/**
	 * Returns the genre of the character
	 * @return boolean woman
	 */
	public boolean isWoman() {
		return woman;
	}
	/**
	 * Sets the genre of the character
	 * @param int defeats
	 */
	public void setWoman(boolean woman) {
		this.woman = woman;
	}
	/**
	 * Returns the gun
	 * @return Gun gun
	 */
	public Gun getGun() {
		return gun;
	}
	/**
	 * Sets the player gun
	 * @param Gun gun
	 */
	public void setGun(Gun gun) {
		this.gun = gun;
	}
	/**
	 * Returns the player coins
	 * @return int coins
	 */
	public int getCoins() {
		return coins;
	}
	/**
	 * Sets the player coins
	 * @param int coins
	 */
	public void setCoins(int coins) {
		this.coins = coins;
	}
	/**
	 * Sets the player paused
	 * @param boolean pause
	 */
	public void setPaused(boolean pause) {
		paused = pause;
	}
	/**
	 * Returns the paused game
	 * @return boolean paused
	 */
	public boolean getPaused() {
		return paused;
	}
	/**
	 * Returns the lose
	 * @return boolean lose
	 */
	public boolean isLose() {
		return lose;
	}
	/**
	 * Sets the player lose
	 * @param boolean lose
	 */
	public void setLose(boolean lose) {
		this.lose = lose;
	}
	/**
	 * Returns the is save
	 * @return boolean saveExit
	 */
	public boolean isSaveExit() {
		return saveExit;
	}
	/**
	 * Sets the player save and exit
	 * @param boolean saveExit
	 */
	public void setSaveExit(boolean saveExit) {
		this.saveExit = saveExit;
	}
	/**
	 * Returns the player damage
	 * @return double damage
	 */
	@Override
	public double getDamage() {
		return gun.getDamage();
	}
	/**
	 * Returns the player score
	 * @return int score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * Sets the player score
	 * @param int score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * Sum the next stage
	 * @return int stage
	 */
	public void nextStage() {
		stages ++;
	}
	/**
	 * Returns the player stages
	 * @return int stages
	 */
	public int getStages() {
		return stages+1;
	}
	/**
	 * Sum the player defeats
	 * @return int defeats
	 */
	public void defeat() {
		defeats = getDefeats() + 1;
	}
	/**
	 * Sets the player type of gun
	 * @param String typeOfGun
	 */
	public void setTypeOfGun(String typeOfGun) {
		this.typeOfGun = typeOfGun;
	}
	/**
	 * Returns the player defeats
	 * @return int defeats
	 */
	public int getDefeats() {
		return defeats;
	}
	/**
	 * Returns the next player
	 * @return PLayer nextPlayer
	 */
	public Player getNextPlayer() {
		return nextPlayer;
	}
	/**
	 * Sets the next player
	 * @param Player nextPLayer
	 */
	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}
}
