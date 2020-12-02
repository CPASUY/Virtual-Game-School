package model;

public class GunSecond extends Gun {
	private final String PATHBULLET = "sprites/Bullets/bulletSecondGun.png";
	private final double DAMAGE = 15;
	public GunSecond(double posX, double posY) {
		super(posX, posY);
		setDamage(DAMAGE);
		getBullet().setImage(PATHBULLET);
	}

}
