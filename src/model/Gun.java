package model;


public class Gun {
	
	private double damage;
	private Bullet bullet;
	private Gun nextGun;
	private Gun prevGun;
	
	
	public Gun(double posX,double posY,int typeBullet) {
		bullet = new Bullet(posX,posY,typeBullet);
		damage = 5;
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
	
	public void setNextGun(Gun nextGun) {
		this.nextGun = nextGun;	
	}
	
	public void setPrevGun(Gun prevGun) {
		this.prevGun = prevGun;
	}
	
	public Gun getNextGun() {
		return nextGun;
	}
	
	public Gun getPrevGun() {
		return prevGun;
	}
}
