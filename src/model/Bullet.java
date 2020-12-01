package model;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.VirtualGameGUIController;

public class Bullet {
	
	private double posX;
	private double posY;
	private final String pathBullet = "sprites/bullet.png";
	private Image bulletImage;
	private String direction;
	private AnimationTimer animationTimer;
	public Bullet(double posX,double posY) {
		this.posX = posX;
		this.posY = posY;
		File file = new File(pathBullet);
    	Image imload = new Image(file.toURI().toString());
    	bulletImage = imload;
		
	}
	
	public void setPosX(double posX) {
		this.posX = posX;
	}
	
	public double getPosX() {
		return posX;
	}
	
	public void setPosY(double posY) {
		this.posY = posY;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public Image getImage() {
		return bulletImage;
	}
	
	public void newImage() {
		File file = new File(pathBullet);
    	Image imload = new Image(file.toURI().toString());
    	bulletImage = imload;
	}
	
	public void draw(boolean pause) {
		
		if(pause ==false) {
		animationTimer = new AnimationTimer() {
			
			//60 FPS
			@Override
			public void handle(long now) {
				
				if(direction.equals("right")) {
				posX=(posX+5);
				VirtualGameGUIController.graphics.drawImage(bulletImage,posX, posY);
				VirtualGameGUIController.graphics.setStroke(Color.RED);
				VirtualGameGUIController.graphics.strokeRect(posX, posY, bulletImage.getWidth(), bulletImage.getHeight());
				}
				else {
					posX=(posX-5);
					VirtualGameGUIController.graphics.drawImage(bulletImage,posX, posY);
					VirtualGameGUIController.graphics.setStroke(Color.RED);
					VirtualGameGUIController.graphics.strokeRect(posX, posY, bulletImage.getWidth(), bulletImage.getHeight());
				}
			}
		};
		animationTimer.start();
	}
		else {
		animationTimer.stop();
		}
	}
	
	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public AnimationTimer getAnimation() {
		return animationTimer;
	}
	
	public Rectangle getRectangle() {
		return new Rectangle(posX, posY, bulletImage.getWidth(), bulletImage.getHeight());
	}
	
	public void verifyCollision(Pdf pdf) {
		if(this.getRectangle().getBoundsInLocal().intersects(pdf.getRectangle().getBoundsInLocal())) {
			pdf.loseHealt(0.3);
			System.out.println("estan colisionando");
		};
	 }

}
