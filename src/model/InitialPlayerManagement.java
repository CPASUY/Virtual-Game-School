package model;

public class InitialPlayerManagement {
	
	private Player boy;
	
	public InitialPlayerManagement() {
		
	}
	
	public void addPlayer(Player player) {

		if(boy==null) {
			boy = player;
		}else {
			Player current = boy;
			while(current.getNextPlayer()!=null) {
				current = current.getNextPlayer();
			}
			current.setNextPlayer(player);
		}
	}
	
	public Player getInitialPlayer() {
		return boy;
	}
}
