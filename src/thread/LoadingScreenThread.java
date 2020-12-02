package thread;

import java.io.IOException;

import javafx.application.Platform;
import ui.VirtualGameGUIController;

public class LoadingScreenThread  extends Thread{
	
	private VirtualGameGUIController vsg;
	
	public LoadingScreenThread(VirtualGameGUIController v) {
		vsg= v;
	}
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
