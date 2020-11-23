package model;

public class Entity extends AnimatedImage {
	
	private int posX;
	private int posY;
	private double health;
	
	public Entity(int posX, int posY, double health,double duration, double width, double height) {
		super(duration,width,height);
		this.posX = posX;
		this.posY = posY;
		this.health = health;
	}
	
	public void setPosX(int posX) {
		this.posX = posX;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public int getPosY() {
		return posY;
	}
	
	public double getHealth() {
		return health;
	}
	
	public void setPosY(int posY) {
		this.posY = posY;
	}
	
}
