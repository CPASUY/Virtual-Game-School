package model;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Gun {
	
	private Image design;
	private double damage;
	private Bullet bullet;
	
	public Gun(double posX,double posY) {
		bullet = new Bullet(posX,posY);
	}
	
	public Bullet getBullet() {
		return bullet;
	}
	
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
}
