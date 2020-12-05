package model;


public class Gun {
	/**
	 * The gun damage
	 */
	private double damage;
	/**
	 * The gun bullet
	 */
	private Bullet bullet;
	/**
	 * The next gun
	 */
	private Gun nextGun;
	/**
	 * The previous gun
	 */
	private Gun prevGun;
	//Methods
	/**
	 * Creates an instance of Gun
	 * @param posX position x of gun
	 * @param posY position y of gun
	 * @param typeBullet the bullet type of gun
	 */
	public Gun(double posX,double posY,int typeBullet) {
		bullet = new Bullet(posX,posY,typeBullet);
		damage = 5;
	}
	/**
	 * Returns the bullet of the gun
	 * @return Bullet bullet
	 */
	public Bullet getBullet() {
		return bullet;
	}
	/**
	 * Sets the bullet of the gun
	 * @param Bullet bullet
	 */
	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}
	/**
	 * Returns the gun damage
	 * @return double damage
	 */
	public double getDamage() {
		return damage;
	}
	/**
	 * Sets the gun damage
	 * @param double damage
	 */
	public void setDamage(double damage) {
		this.damage = damage;
	}
	/**
	 * Sets the next gun
	 * @param Gun gun
	 */
	public void setNextGun(Gun nextGun) {
		this.nextGun = nextGun;	
	}
	/**
	 * Sets the previous gun
	 * @param Gun gun
	 */
	public void setPrevGun(Gun prevGun) {
		this.prevGun = prevGun;
	}
	/**
	 * Returns the next Gun
	 * @return Gun gun
	 */
	public Gun getNextGun() {
		return nextGun;
	}
	/**
	 * Returns the previous Gun
	 * @return Gun gun
	 */
	public Gun getPrevGun() {
		return prevGun;
	}
}
