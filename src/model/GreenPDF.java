package model;

public class GreenPDF extends Pdf{

	private final int QUANTITY_SPRITES = 4;
	private String[] paths = {"sprites/Enemies/greenLeft0.png","sprites/Enemies/greenLeft1.png","sprites/Enemies/greenRight0.png",
								"sprites/Enemies/greenRight1.png"};
	public GreenPDF(int posX, int posY, double health, double duration, double width, double height,
			double damage, Player objective) {
		super(posX, posY, health, duration, width, height, damage,objective);
		fillFrames(paths,QUANTITY_SPRITES);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getDamage() {
		// TODO Auto-generated method stub
		return 0;
	}

}
