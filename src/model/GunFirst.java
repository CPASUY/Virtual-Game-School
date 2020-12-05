package model;

public class GunFirst extends Gun {
	/**
	 * Rute sprites of the gun
	 */
	private final String PATHBULLET = "sprites/Bullets/bulletFirstGun.png";
	/**
	 * The damage od the first gun
	 */
	private final double DAMAGE = 10;
	//Methods
	/**
	 * Creates an instance of  first Gun
	 * @param posX position x of gun
	 * @param posY position y of gun
	 * @param typeBullet the bullet type of gun
	 */
	public GunFirst(double posX, double posY,int typeBullet) {
		super(posX, posY,typeBullet);
		setDamage(DAMAGE);
		getBullet().setImage(PATHBULLET);
	}
}
