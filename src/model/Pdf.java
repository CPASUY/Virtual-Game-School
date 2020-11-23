package model;

import javafx.scene.image.Image;

public class Pdf extends Entity implements Motion,Attack {
	
	private double damage;
	private final int QUANTITY_SPRITES = 5;
	
	
	public Pdf(String[] paths,int posX, int posY, double health,double duration, double width, double height,double damage) {
		super(posX, posY, health,duration, width, height);
		this.damage = damage;
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
		// TODO Auto-generated method stub
		
	}

}
