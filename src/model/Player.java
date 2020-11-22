package model;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Player extends Entity implements Motion,Attack {
	
	
	private int score;
	private final double MAX_HEALTH = 100;
	private LocalDate Date;
	private boolean lose;
	private boolean paused;
	private Gun gun;
	
	public Player(){
		
	}

	@Override
	public void attack(Character character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void move(int posX, int posY) {
		// TODO Auto-generated method stub
		
	}
	

}
