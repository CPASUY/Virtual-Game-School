package model;

public class YellowPDF extends Pdf {

	private final int QUANTITY_SPRITES = 4;
	private String[] paths = {"sprites/Enemies/yellowLeft0.png","sprites/Enemies/yellowLeft1.png","sprites/Enemies/yellowRight0.png",
								"sprites/Enemies/yellowRight1.png"};
	public YellowPDF(int posX, int posY, double health, double duration, double width, double height,
			double damage,Player objective) {
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
