package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;

public class Entity extends AnimatedImage {
	/**
	 * The x position
	 */
	private double posX;
	/**
	 * The y position
	 */
	private double posY;
	/**
	 * The health
	 */
	private double health;
	//Methods
	/**
	 * Creates an instance of Entity
	 * @param posX position x of the entity
	 * @param posY position y of the entity
	 * @param health health of the entity
	 */
	public Entity(double posX, double posY, double health) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.health = health;
	}
	/**
	 * Sets the x position
	 * @param double x position
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}
	/**
	 * Returns x position 
	 * @return double x position
	 */
	public double getPosX() {
		return posX;
	}
	/**
	 * Returns y position 
	 * @return double y position
	 */
	public double getPosY() {
		return posY;
	}
	/**
	 * Returns the health 
	 * @return double health
	 */
	public double getHealth() {
		return health;
	}
	/**
	 * Sets the lose health
	 * @param double lose
	 */
	public void loseHealt(double lose) {
		health -= lose;
	}
	/**
	 * Sets the y position
	 * @param double y position
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	/**
	 * Sets the frames 
	 * @param GraphicsContext graphics
	 */
	public void draw(GraphicsContext graphics) {
		graphics.drawImage(currentFrame,getPosX(),getPosY());
	}
	/**
	 * Returns the rectangle
	 * @return Rectangle rectangle
	 */
	public Rectangle getRectangle() {
		return new Rectangle(posX, posY, currentFrame.getWidth(), currentFrame.getHeight());
	}
	
	
	
}
