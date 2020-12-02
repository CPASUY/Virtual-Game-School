package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Entity extends AnimatedImage {
	
	private double posX;
	private double posY;
	private double health;
	
	public Entity(double posX, double posY, double health) {
		super();
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
	
	public void loseHealt(double lose) {
		health -= lose;
	}
	
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	public void draw(GraphicsContext graphics) {
		graphics.drawImage(currentFrame,getPosX(),getPosY());
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(posX, posY, currentFrame.getWidth(), currentFrame.getHeight());
	}
	
	
	
}
