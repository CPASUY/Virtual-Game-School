package model;

public class GunFirst extends Gun {
	private final String PATHBULLET = "sprites/Bullets/bulletFirstGun.png";
	private final double DAMAGE = 10;
	public GunFirst(double posX, double posY,int typeBullet) {
		super(posX, posY,typeBullet);
		setDamage(DAMAGE);
		getBullet().setImage(PATHBULLET);
	}

}
