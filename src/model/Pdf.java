package model;


public abstract class Pdf extends Entity implements Motion,Attack {
	
	private double damage;
	private Player objective;
	public static double VELOCITY = 4;
	
	public Pdf(int posX, int posY, double health,double duration, double width, double height,double damage,Player objective) {
		super(posX, posY, health,duration, width, height);
		this.damage = damage;
		this.setObjective(objective);
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

	public Player getObjective() {
		return objective;
	}

	public void setObjective(Player objective) {
		this.objective = objective;
	}

}
