package thread;

import java.io.IOException;

import javafx.application.Platform;
import ui.VirtualGameGUIController;

public class LoadingScreenThread  extends Thread{
	/**
	 * The GUI controller
	 */
	private VirtualGameGUIController vsg;
	/**
	 * Creates and instance of LoadingScreenThread
	 * @param v controller
	 */
	public LoadingScreenThread(VirtualGameGUIController v) {
		vsg= v;
	}
	/**
	 * Performs athe movement of the shapes
	 */
	public void run() {
		while (vsg.isMovement()) {
			Platform.runLater(new Thread() {
				public void run() {
					try {
						vsg.movement();
					} catch (IOException e) {
						e.printStackTrace();
					}
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
