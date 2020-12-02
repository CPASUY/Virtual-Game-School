package model;

public class GunThird extends Gun {
	private final String PATHBULLET = "sprites/Bullets/bulletThirdGun.png";
	private final double DAMAGE = 20;
	public GunThird(double posX, double posY,int typeBullet) {
		super(posX, posY,typeBullet);
		setDamage(DAMAGE);
		getBullet().setImage(PATHBULLET);
	}

}
