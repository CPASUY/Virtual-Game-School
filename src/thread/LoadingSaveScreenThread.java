package thread;

import java.io.IOException;

import javafx.application.Platform;
import ui.VirtualGameGUIController;

public class LoadingSaveScreenThread extends Thread {
	private VirtualGameGUIController vsg;
	public LoadingSaveScreenThread(VirtualGameGUIController v) {
		vsg= v;
	}public void run() {
		while (vsg.isMovementSave()) {
			Platform.runLater(new Thread() {
				public void run() {
					try {
						vsg.movementSave();
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
