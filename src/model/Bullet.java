package model;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import ui.VirtualGameGUIController;

public class Bullet {
	
	private double posX;
	private double posY;
	private final String pathBullet1 = "sprites/Bullets/bulletInitialGun.png";
	private final String pathBullet2 = "sprites/Bullets/bulletFirstGun.png";
	private final String pathBullet3= "sprites/Bullets/bulletSecondGun.png";
	private final String pathBullet4 = "sprites/Bullets/bulletThirdGun.png";
	private Image bulletImage;
	private String direction;
	private boolean impact;
	private AnimationTimer animationTimer;
	public Bullet(double posX,double posY,int gun) {
		this.posX = posX;
		this.posY = posY;
		
		if(gun == 1) {
			setImage(pathBullet1);
		}
		else if(gun == 2) {
			setImage(pathBullet2);
		}
		else if(gun == 3) {
			setImage(pathBullet3);
		}
		else {
			setImage(pathBullet4);
		}
		impact = false;
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
	
	public void setImage(String path) {
		File file = new File(path);
    	Image imload = new Image(file.toURI().toString());
    	bulletImage = imload;
	}
	
	public void draw(boolean pause) {
		
		if(pause ==false) {
		animationTimer = new AnimationTimer() {
			
			//60 FPS
			@Override
			public void handle(long now) {
				
				if(impact) {
					return;
				}
				else {
				if(direction.equals("right")) {
				posX=(posX+5);
				if(bulletImage !=null) {
				VirtualGameGUIController.graphics.drawImage(bulletImage,posX, posY);
				}
				}
				else {
					posX=(posX-5);
					if(bulletImage != null) {
					VirtualGameGUIController.graphics.drawImage(bulletImage,posX, posY);
					}
				}
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
	
	public void verifyCollision(Pdf pdf,Player player) {
		if(this.getRectangle().getBoundsInLocal().intersects(pdf.getRectangle().getBoundsInLocal()) && !impact) {
			pdf.loseHealt(player.getGun().getDamage());
			impact = true;
		};
	 }

}
