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
	private LocalDate Date;
	private double bulletPosX;
	private double bulletPosY;
	private boolean lose;
	private boolean paused;
	private boolean woman;
	private Gun gun;
	private final int VELOCITY = 4;
	
	public Player(double duration, double width, double height){
		super(467,344,MAX_HEALTH,duration,width,height);
		woman = false;
		gun = new Gun(getPosX(),getPosY());
		bulletPosX = gun.getBullet().getPosX();
		bulletPosY = gun.getBullet().getPosY();
	}

	@Override
	public void attack() {
				gun.setBullet(new Bullet(getPosX(),getPosY()));
				
				if(currentFrame == getFrames()[1]) {
				 gun.getBullet().setDirection("left");	
				}else {
				 gun.getBullet().setDirection("right");	
				}
				gun.getBullet().draw();
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
		//currentFrame = getFrames()[0];
	}
	
	public void setPaths() {
		
		if(woman) {
			String[] girl = {pathGirl+"static.png",pathGirl+"left0.png",pathGirl+"left1.png",pathGirl+"right0.png",pathGirl+"right1.png"};
			paths = girl;
		}
		else {
			String[] boy = {pathBoy+"static.png",pathBoy+"left0.png",pathBoy+"left1.png",pathBoy+"right0.png",pathBoy+"right1.png"};
			paths = boy;
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

}
