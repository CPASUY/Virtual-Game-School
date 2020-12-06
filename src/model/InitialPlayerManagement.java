package model;

/**
 * 
 * InitialPlayer Class
 *
 */
public class InitialPlayerManagement {
	
	private Player boy;
	
	/**
	 * Constructor of InitialPlayerManagement
	 */
	public InitialPlayerManagement() {
		
	}
	
	/**
	 * Add a new player to the list
	 * @param player
	 */
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
	
	/**
	 * get the firstPlayer
	 * @return Player boy
	 */
	public Player getInitialPlayer() {
		return boy;
	}
	
	/**
	 *  Set the firstPlayer
	 * @param player
	 */
	public void setInitialPlayer(Player player) {
		boy = player;
	}
}
