package model;

public class GunSecond extends Gun {
	/**
	 * Rute sprites of the gun
	 */
	private final String PATHBULLET = "sprites/Bullets/bulletSecondGun.png";
	/**
	 * The damage od the second gun
	 */
	private final double DAMAGE = 15;
	//Methods
	/**
	 * Creates an instance of second Gun
	 * @param posX position x of gun
	 * @param posY position y of gun
	 * @param typeBullet the bullet type of gun
	 */
	public GunSecond(double posX, double posY,int typeBullet) {
		super(posX, posY,typeBullet);
		setDamage(DAMAGE);
		getBullet().setImage(PATHBULLET);
	}

}
