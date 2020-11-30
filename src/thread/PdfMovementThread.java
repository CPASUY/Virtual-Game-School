package thread;
import javafx.application.Platform;
import model.Pdf;
import model.Player;
import ui.VirtualGameGUIController;
public class PdfMovementThread extends Thread {
		
		Player player;
		Pdf enemy;
		private VirtualGameGUIController gui;
		public PdfMovementThread(VirtualGameGUIController gui,Pdf enemy, Player player) {
			setDaemon(true);
			this.enemy = enemy;
			this.player = player;
			this.gui = gui;
		}
		
		public void run() {
			synchronized (enemy) {
				while (!player.isLose()) {
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
