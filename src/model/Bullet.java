package model;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
				}
				else {
					posX=(posX-5);
					VirtualGameGUIController.graphics.drawImage(bulletImage,posX, posY);	
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

}
