package model;

import java.time.LocalDate;

import ui.VirtualGameGUIController;

/**
 * 
 * Class Player
 *
 */
public class Player extends Entity implements Motion,Attack {
	
	
	private int score;
	private final static double MAX_HEALTH = 100;
	private final int QUANTITY_SPRITES = 5;
	private String[] paths = new String[QUANTITY_SPRITES];
	private String pathBoy = "sprites/BoyGunDefect/";
	private String pathGirl = "sprites/GirlGunDefect/";
	private int coins;
	private LocalDate Date;
	private boolean lose;
	private boolean paused;
	private boolean saveExit;
	private boolean woman;
	private int defeats;
	private int stages;
	private Gun gun;
	private String typeOfGun;
	private int cont;
	private final int VELOCITY = 3;
	private boolean on;

	public void setDefeats(int defeats) {
		this.defeats = defeats;
	}
	
	public Player(){
		super(467,344,MAX_HEALTH);
		woman = false;
		setCoins(0);
		paused = false;
		setLose(false);
		cont=0;
		on=false;
		setSaveExit(false);
		stages = 1;
		defeats = 0;
		typeOfGun = "initialGun";
	}

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
	
	public String getTypeOfGun() {
		return typeOfGun;
	}

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

	public boolean isWoman() {
		return woman;
	}

	public void setWoman(boolean woman) {
		this.woman = woman;
	}
	
	public Gun getGun() {
		return gun;
	}
	
	public void setGun(Gun gun) {
		this.gun = gun;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}
	
	public void setPaused(boolean pause) {
		paused = pause;
	}
	
	public boolean getPaused() {
		return paused;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}

	public boolean isSaveExit() {
		return saveExit;
	}

	public void setSaveExit(boolean saveExit) {
		this.saveExit = saveExit;
	}

	@Override
	public double getDamage() {
		return gun.getDamage();
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public void nextStage() {
		stages ++;
	}
	
	public int getStages() {
		return stages;
	}
	
	public void defeat() {
		defeats = getDefeats() + 1;
	}

	public void setTypeOfGun(String typeOfGun) {
		this.typeOfGun = typeOfGun;
	}

	public int getDefeats() {
		return defeats;
	}
}
