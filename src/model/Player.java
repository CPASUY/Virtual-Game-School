package model;

import java.time.LocalDate;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ui.VirtualGameGUIController;

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
	private boolean woman;
	private Gun gun;
	private final int VELOCITY = 3;
	
	public Player(double duration, double width, double height){
		super(467,344,MAX_HEALTH,duration,width,height);
		woman = false;
		gun = new Gun(getPosX(),getPosY());
		setCoins(0);
		paused = false;
		setLose(false);
	}

	@Override
	public void attack() {
				
			if(paused == false) {
				 gun.setBullet(new Bullet(getPosX(),getPosY()));
				
				if(currentFrame == getFrames()[1]) {
				 gun.getBullet().setDirection("left");	
				}else {
				 gun.getBullet().setDirection("right");	
				}
				gun.getBullet().draw(paused);
			}
	}

	@Override
	public double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int cont(int n) {
		int cont=+n;
		return cont;
	}
	@Override
	public void move() {

		int cont=cont(1);
		
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
			setPosX(getPosX()+VELOCITY);
		}
		if(VirtualGameGUIController.left) {
			currentFrame = getFrames()[1];
			setPosX(getPosX()-VELOCITY);
		}
		if(VirtualGameGUIController.up) {
			currentFrame = getFrames()[3];
			setPosY(getPosY()-VELOCITY);
		}
		if(VirtualGameGUIController.down) {
			currentFrame = getFrames()[1];
			setPosY(getPosY()+VELOCITY);
		}
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

}
