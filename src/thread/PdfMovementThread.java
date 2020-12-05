package thread;
import javafx.application.Platform;
import model.Pdf;
import model.Player;
import ui.VirtualGameGUIController;
public class PdfMovementThread extends Thread {
	/**
	 * Player
	 */
	Player player;
	/**
	 * Enemie
	 */
	Pdf enemy;
	/**
	 * The GUI controller
	 */
	private VirtualGameGUIController gui;
	/**
	 * Creates and instance of LoadingScreenThread
	 * @param gui the controller
	 * @param enemy the enemie
	 * @param player the player
	 */
	public PdfMovementThread(VirtualGameGUIController gui,Pdf enemy, Player player) {
		setDaemon(true);
		this.enemy = enemy;
		this.player = player;
		this.gui = gui;
	}
	/**
	 *Performs the attack of the pdf
	 */
	public void run() {
		synchronized (enemy) {
			while (!player.isLose() && !player.isSaveExit() && enemy.getHealth()>0) {
				if(!player.getPaused()) {
					enemy.move();	
				}
				Platform.runLater(new Thread() {
					public void run() {
						gui.draw();
					}
				});
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
