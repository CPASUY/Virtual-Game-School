package model;

public class RedPDF extends Pdf{
	
	private final int QUANTITY_SPRITES = 4;
	private String[] paths = {"sprites/Enemies/redLeft0.png","sprites/Enemies/redLeft1.png","sprites/Enemies/redRight0.png",
								"sprites/Enemies/redRight1.png"};
	public RedPDF(int posX, int posY, double health, double duration, double width, double height,
			double damage,Player objective) {
		super(posX, posY, health, duration, width, height, damage,objective);
		fillFrames(paths,QUANTITY_SPRITES);
		// TODO Auto-generated constructor stub
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
