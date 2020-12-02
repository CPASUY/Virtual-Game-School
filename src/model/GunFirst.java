package model;

public class GunFirst extends Gun {
	
	private final double DAMAGE = 10;
	public GunFirst(double posX, double posY) {
		super(posX, posY);
		setDamage(DAMAGE);
	}

}
