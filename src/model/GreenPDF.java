package model;

public class GreenPDF extends Pdf{

	private static double DAMAGE = 0.3;
	private static double HEALTH = 100;
	private final int QUANTITY_SPRITES = 4;
	private int cont;
	private String[] paths = {"sprites/Enemies/greenLeft0.png","sprites/Enemies/greenLeft1.png","sprites/Enemies/greenRight0.png",
								"sprites/Enemies/greenRight1.png"};
	public GreenPDF(double posX, double posY,Player objective) {
		super(posX, posY, HEALTH,DAMAGE,objective);
		fillFrames(paths,QUANTITY_SPRITES);
		cont=0;
	}

	@Override
	public void move() {
		cont++;
		if(getObjective().getPosX()>getPosX()) {
			setPosX((getObjective().getPosX()-getPosX()>VELOCITY)?getPosX()+VELOCITY:getObjective().getPosX());
			currentFrame = getFrames()[0];
			if(cont>2) {
				currentFrame = getFrames()[1];
				if(cont>4) {
					cont=0;
				}
			}
		}
		if(getObjective().getPosX()<getPosX()) {
			setPosX((getPosX()-getObjective().getPosX()>VELOCITY)?getPosX()-VELOCITY:getObjective().getPosX());
			currentFrame = getFrames()[2];
			if(cont>2) {
				currentFrame = getFrames()[3];
				if(cont>4) {
					cont=0;
				}
			}
		}
		if(getObjective().getPosY()>getPosY()) {
			setPosY((getObjective().getPosY()-getPosY()>VELOCITY)?getPosY()+VELOCITY:getObjective().getPosY());
			currentFrame = getFrames()[0];
			if(cont>2) {
				currentFrame = getFrames()[1];
				if(cont>4) {
					cont=0;
				}
			}
		}
		if(getObjective().getPosY()<getPosY()) {
			setPosY((getPosY()-getObjective().getPosY()>VELOCITY)?getPosY()-VELOCITY:getObjective().getPosY());
			currentFrame = getFrames()[2];
			if(cont>2) {
				currentFrame = getFrames()[3];
				if(cont>4) {
					cont=0;
				}
			}
		}
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
