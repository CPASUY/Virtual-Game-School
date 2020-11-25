package model;

public class RedPDF extends Pdf{

	public RedPDF(String[] paths, int posX, int posY, double health, double duration, double width, double height,
			double damage) {
		super(paths, posX, posY, health, duration, width, height, damage);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack(Entity character) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
