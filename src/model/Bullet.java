package model;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import ui.VirtualGameGUIController;

public class Bullet {
	/**
	 * The x position
	 */
	private double posX;
	/**
	 * The y position
	 */
	private double posY;
	/**
	 * The sprite of the intial gun bullet
	 */
	private final String pathBullet1 = "sprites/Bullets/bulletInitialGun.png";
	/**
	 * The sprite of the first gun bullet
	 */
	private final String pathBullet2 = "sprites/Bullets/bulletFirstGun.png";
	/**
	 * The sprite of the second gun bullet
	 */
	private final String pathBullet3= "sprites/Bullets/bulletSecondGun.png";
	/**
	 * The sprite of the intial third bullet
	 */
	private final String pathBullet4 = "sprites/Bullets/bulletThirdGun.png";
	/**
	 * The mage of the bullet
	 */
	private Image bulletImage;
	/**
	 * The direction of the bullet
	 */
	private String direction;
	/**
	 * The impact of the bullet
	 */
	private boolean impact;
	/**
	 * The animation timer of the object
	 */
	private AnimationTimer animationTimer;
	//Methods
	/**
	 * Creates an instance of Bullet
	 * @param posX position x of the bullet
	 * @param posY position y of the bullet
	 * @param gun gun of the bullet
	 */
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
	/**
	 * Sets the x position
	 * @param double x position
	 */
	public void setPosX(double posX) {
		this.posX = posX;
	}
	/**
	 * Returns the x position
	 * @return double x position
	 */
	public double getPosX() {
		return posX;
	}
	/**
	 * Sets the y position
	 * @param double y position
	 */
	public void setPosY(double posY) {
		this.posY = posY;
	}
	/**
	 * Returns the y position
	 * @return double y position
	 */
	public double getPosY() {
		return posY;
	}
	/**
	 * Returns the image of the bullet
	 * @return Image image
	 */
	public Image getImage() {
		return bulletImage;
	}
	/**
	 * Sets the path image of the bullet
	 * @param String path
	 */
	public void setImage(String path) {
		File file = new File(path);
    	Image imload = new Image(file.toURI().toString());
    	bulletImage = imload;
	}
	/**
	 * Draw the images animation
	 * @param boolean pause
	 */
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
	/**
	 * Sets the direction
	 * @param String direction
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * Returns the animation timer
	 * @return AnimationTimer animation timer
	 */
	public AnimationTimer getAnimation() {
		return animationTimer;
	}
	/**
	 * Returns the rectange
	 * @return Rectangle rectangle
	 */
	public Rectangle getRectangle() {
		return new Rectangle(posX, posY, bulletImage.getWidth(), bulletImage.getHeight());
	}
	/**
	 * Verifies the colision between the bullet and the objetive
	 * @param Pdf pdf
	 * @param Player player
	 */
	public void verifyCollision(Pdf pdf,Player player) {
		if(this.getRectangle().getBoundsInLocal().intersects(pdf.getRectangle().getBoundsInLocal()) && !impact) {
			pdf.loseHealt(player.getGun().getDamage());
			impact = true;
		};
	 }

}
