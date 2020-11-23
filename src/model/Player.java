package model;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Player extends Entity implements Motion,Attack {
	
	
	private int score;
	private final static double MAX_HEALTH = 100;
	private final int QUANTITY_SPRITES = 1;
	private final String[] paths = {"E:\\MyProjects\\Virtual-Game-School\\src\\model\\file.png"};
	private LocalDate Date;
	private boolean lose;
	private boolean paused;
	private Gun gun;
	
	public Player(double duration, double width, double height){
		
		super(467,344,MAX_HEALTH,duration,width,height);
		fillFrames(paths,QUANTITY_SPRITES);
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
	public void move(int posX, int posY) {
		setPosX(getPosX()-posX);
		setPosY(getPosY()-posY);
		
	}
	

}
