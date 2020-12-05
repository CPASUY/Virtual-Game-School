package model;

public interface Attack {
	/**
	 * The attack of the entity
	 */
	public void attack();
	/**
	 * Returns the damage of the entity
	 * @return double damage
	 */
	public double getDamage();
}
