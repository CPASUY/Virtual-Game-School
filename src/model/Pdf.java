package model;


public abstract class Pdf extends Entity implements Motion,Attack {
	
	private  double damage;
	private Player objective;
	public static double VELOCITY = 4;
	
	public Pdf(double posX, double posY, double health,double damage,Player objective) {
		super(posX, posY, health);
		this.damage = damage;
		this.objective = objective;
	}
	
	@Override
	public void attack() {
	   objective.loseHealt(damage);
	}
	
	@Override
	public double getDamage() {
		
		return damage;
	}

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
	
	
	public void verifyCollision() {
		if(this.getRectangle().getBoundsInLocal().intersects(objective.getRectangle().getBoundsInLocal())) {
		objective.loseHealt(damage);
		}
	 }

	public Player getObjective() {
		return objective;
	}

	public void setObjective(Player objective) {
		this.objective = objective;
	}

}
