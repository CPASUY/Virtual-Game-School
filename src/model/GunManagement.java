package model;

public class GunManagement {
	/**
	 * The gun intial
	 */
	private Gun initialGun;
	//Methods
	/**
	 * Creates an instance of GunManagement
	 */
	public GunManagement() {
	}
	/** addGun
     * Method used to add restaurant
     * @param newGun!=null
     */
	public void addGun(Gun newGun) {

		if(initialGun==null) {
			initialGun = newGun;
		}else {
			Gun current = initialGun;
			while(current.getNextGun()!=null) {
				current = current.getNextGun();
			}
			current.setNextGun(newGun);
			newGun.setPrevGun(current);
		}
	}
	/**
	 * Returns the Gun initial
	 * @return Gun gun
	 */
	public Gun getInitialGun() {
		return initialGun;
	}
}
