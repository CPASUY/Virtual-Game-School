package model;

public class GunThird extends Gun {
	
	private final double DAMAGE = 20;
	public GunThird(double posX, double posY) {
		super(posX, posY);
		setDamage(DAMAGE);
	}

}
