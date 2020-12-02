package model;

public class GunManagement {
	
	private Gun initialGun;
	public GunManagement() {
		
	}
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
	
	public Gun getInitialGun() {
		return initialGun;
	}
}
