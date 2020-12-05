package model;

public class YellowPDF extends Pdf {
	/**
	 * The yellow pdf damage
	 */
	private static double DAMAGE = 0.5;
	/**
	 * The yellow pdf healt
	 */
	private static double HEALTH = 150;
	/**
	 * The quantity sprites of yellow pdf
	 */
	private final int QUANTITY_SPRITES = 4;
	/**
	 * counter
	 */
	private int cont;
	/**
	 * The array rutes of the sprites
	 */
	private String[] paths = {"sprites/Enemies/yellowLeft0.png","sprites/Enemies/yellowLeft1.png","sprites/Enemies/yellowRight0.png",
								"sprites/Enemies/yellowRight1.png"};
	/**
	 * Creates an instance of YellowPDF
	 * @param posX position x of the pdf
	 * @param posY position y of the pdf
	 * @param obejtive objetive of the pdf
	 */
	public YellowPDF(double posX, double posY,Player objective) {
		super(posX, posY, HEALTH,DAMAGE,objective);
		fillFrames(paths,QUANTITY_SPRITES);
		cont=0;
	}
	/**
	 * The movement of the pdf
	 */
	@Override
	public void move() {
		cont++;
		if(getObjective().getPosX()>getPosX()) {
			setPosX((getObjective().getPosX()-getPosX()>VELOCITY)?getPosX()+VELOCITY:getObjective().getPosX());
			currentFrame = getFrames()[2];
			if(cont>2) {
				currentFrame = getFrames()[3];
				if(cont>4) {
					cont=0;
				}
			}
		}
		if(getObjective().getPosX()<getPosX()) {
			setPosX((getPosX()-getObjective().getPosX()>VELOCITY)?getPosX()-VELOCITY:getObjective().getPosX());
			currentFrame = getFrames()[0];
			if(cont>2) {
				currentFrame = getFrames()[1];
				if(cont>4) {
					cont=0;
				}
			}
		}
		if(getObjective().getPosY()>getPosY()) {
			setPosY((getObjective().getPosY()-getPosY()>VELOCITY)?getPosY()+VELOCITY:getObjective().getPosY());
			currentFrame = getFrames()[2];
			if(cont>2) {
				currentFrame = getFrames()[3];
				if(cont>4) {
					cont=0;
				}
			}
		}
		if(getObjective().getPosY()<getPosY()) {
			setPosY((getPosY()-getObjective().getPosY()>VELOCITY)?getPosY()-VELOCITY:getObjective().getPosY());
			currentFrame = getFrames()[0];
			if(cont>2) {
				currentFrame = getFrames()[1];
				if(cont>4) {
					cont=0;
				}
			}
		}
		
	}
	/**
	 * The attack of the pdf
	 */
	@Override
	public void attack() {
	}
	/**
	 * Returns the damage of the pdf
	 * @return double damage
	 */
	@Override
	public double getDamage() {
		return 0;
	}

}
