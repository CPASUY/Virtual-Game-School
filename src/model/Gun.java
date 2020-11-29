package model;


public class Gun {
	
	private double damage;
	private Bullet bullet;
	
	public Gun(double posX,double posY) {
		bullet = new Bullet(posX,posY);
		setDamage(10);
	}
	
	public Bullet getBullet() {
		return bullet;
	}
	
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public double getDamage() {
		return damage;
	}

	public void setDamage(double damage) {
		this.damage = damage;
	}
}
