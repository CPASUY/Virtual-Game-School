package thread;
import javafx.application.Platform;
import ui.VirtualGameGUIController;

public class LoadingSaveScreenThread extends Thread {
	/**
	 * The GUI controller
	 */
	private VirtualGameGUIController vsg;
	/**
	 * Creates and instance of LoadingSaveScreenThread
	 * @param v controller
	 */
	public LoadingSaveScreenThread(VirtualGameGUIController v) {
		vsg= v;
	}
	/**
	 * Performs athe movement of the shapes
	 */
	public void run() {
		while (vsg.isMovementSave()) {
			Platform.runLater(new Thread() {
				public void run() {
					vsg.movementSave();
				}
			});

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
