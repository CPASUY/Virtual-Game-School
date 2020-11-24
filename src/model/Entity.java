package model;

public class Entity extends AnimatedImage {
	
	private double posX;
	private double posY;
	private double health;
	
	public Entity(double posX, double posY, double health,double duration, double width, double height) {
		super(duration,width,height);
		this.posX = posX;
		this.posY = posY;
		this.health = health;
	}
	
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	public double getPosX() {
		return posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public double getHealth() {
		return health;
	}
	
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
}
