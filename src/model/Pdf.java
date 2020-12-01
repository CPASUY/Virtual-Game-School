package model;


public abstract class Pdf extends Entity implements Motion,Attack {
	
	private double damage;
	private Player objective;
	public static double VELOCITY = 4;
	
	public Pdf(int posX, int posY, double health,double duration, double width, double height,double damage,Player objective) {
		super(posX, posY, health);
		this.damage = damage;
		this.objective = objective;
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
		
		if(objective.getPosX()>getPosX()) {
		setPosX((objective.getPosX()-getPosX()>VELOCITY)?getPosX()+VELOCITY:objective.getPosX());
		setCurrentFrame(getFrames()[2]);
		}
		if(objective.getPosX()<getPosX()) {
		setPosX((getPosX()-objective.getPosX()>VELOCITY)?getPosX()-VELOCITY:objective.getPosX());
		setCurrentFrame(getFrames()[0]);
		}
		if(objective.getPosY()>getPosY()) {
		setPosY((objective.getPosY()-getPosY()>VELOCITY)?getPosY()+VELOCITY:objective.getPosY());
		setCurrentFrame(getFrames()[2]);
		}
		if(objective.getPosY()<getPosY()) {
		setPosY((getPosY()-objective.getPosY()>VELOCITY)?getPosY()-VELOCITY:objective.getPosY());
		setCurrentFrame(getFrames()[0]);
		}
		
	}
	
	public void verifyCollision(Player player) {
		if(this.getRectangle().getBoundsInLocal().intersects(player.getRectangle().getBoundsInLocal())) {
			player.loseHealt(0.1);
			System.out.println(player.getHealth());
		};
	 }

}
