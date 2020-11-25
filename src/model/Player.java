package model;

import java.time.LocalDate;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import ui.VirtualGameGUIController;

public class Player extends Entity implements Motion,Attack {
	
	
	private int score;
	private final static double MAX_HEALTH = 100;
	private final int QUANTITY_SPRITES = 1;
	private final String[] paths = {"E:\\MyProjects\\Virtual-Game-School\\src\\model\\file.png"};
	private LocalDate Date;
	private boolean lose;
	private boolean paused;
	private Gun gun;
	private final int VELOCITY;
	
	public Player(double duration, double width, double height){
		
		super(467,344,MAX_HEALTH,duration,width,height);
		fillFrames(paths,QUANTITY_SPRITES);
		VELOCITY = 3;
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
			setPosX(getPosX()+VELOCITY);
		}
		if(VirtualGameGUIController.left) {
			setPosX(getPosX()-VELOCITY);
		}
		if(VirtualGameGUIController.up) {
			setPosY(getPosY()-VELOCITY);
		}
		if(VirtualGameGUIController.down) {
			setPosY(getPosY()+VELOCITY);
		}
		currentFrame = getFrames()[0];
	}
	
	

}
