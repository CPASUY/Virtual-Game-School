package model;


public abstract class Pdf extends Entity implements Motion,Attack {
	/**
	 * The pdf damage
	 */
	private  double damage;
	/**
	 * The objetive of the pdf
	 */
	private Player objective;
	/**
	 * The pdf velocity
	 */
	public static double VELOCITY = 4;
	//Methods
	/**
	 * Creates an instance of Pdf
	 * @param posX position x of the pdf
	 * @param posY position y of the pdf
	 * @param health health of the pdf
	 * @param damage damage of the pdf
	 * @param objetive the objetive of the pdf
	 */
	public Pdf(double posX, double posY, double health,double damage,Player objective) {
		super(posX, posY, health);
		this.damage = damage;
		this.objective = objective;
	}
	/**
	 * The attack of the pdf
	 */
	@Override
<<<<<<< HEAD
	public void attack() {	
		objective.loseHealt(damage);
=======
	public void attack() {
>>>>>>> 0c6f835fed7c08d560724cacce36c9d7fe77beec
	}
	/**
	 * Returns the damage of the pdf
	 * @return double damage
	 */
	@Override
	public double getDamage() {
		return damage;
	}
	/**
	 * The movement of the pdf
	 */
	@Override
	public void move() {
		
		if(getObjective().getPosX()>getPosX()) {
		setPosX((getObjective().getPosX()-getPosX()>VELOCITY)?getPosX()+VELOCITY:getObjective().getPosX());
		setCurrentFrame(getFrames()[2]);
		}
		if(getObjective().getPosX()<getPosX()) {
		setPosX((getPosX()-getObjective().getPosX()>VELOCITY)?getPosX()-VELOCITY:getObjective().getPosX());
		setCurrentFrame(getFrames()[0]);
		}
		if(getObjective().getPosY()>getPosY()) {
		setPosY((getObjective().getPosY()-getPosY()>VELOCITY)?getPosY()+VELOCITY:getObjective().getPosY());
		setCurrentFrame(getFrames()[2]);
		}
		if(getObjective().getPosY()<getPosY()) {
		setPosY((getPosY()-getObjective().getPosY()>VELOCITY)?getPosY()-VELOCITY:getObjective().getPosY());
		setCurrentFrame(getFrames()[0]);
		}
		
	}
	/**
	 * Method that verify the colision between the pdf and the objetive
	 */
	public void verifyCollision(Player player) {
		if(this.getRectangle().getBoundsInLocal().intersects(player.getRectangle().getBoundsInLocal())) {
			player.loseHealt(damage);
		};
	 }
	/**
	 * Returns the objetive
	 * @return Player objetive
	 */
	public Player getObjective() {
		return objective;
	}
	/**
	 * Sets the objetive
	 * @param Player objetive
	 */
	public void setObjective(Player objective) {
		this.objective = objective;
	}

}
