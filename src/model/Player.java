package model;

import java.time.LocalDate;

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
	private boolean lose;
	private boolean paused;
	private boolean woman;
	private Gun gun;
	private final int VELOCITY = 4;
	
	public Player(double duration, double width, double height){
		super(467,344,MAX_HEALTH,duration,width,height);
		woman = false;
	}

	@Override
	public void attack(Entity character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move() {
		
		
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
	
	

}
