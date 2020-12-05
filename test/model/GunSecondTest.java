/**
 * 
 */
package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

class GunSecondTest {
	private double posX;
	private double posY;
	private int gun;
	private GunSecond gs;
	public void setupStage1(){
		JFXPanel jfxPanel = new JFXPanel();
		jfxPanel.getBorder();
		posX=301.3;
		posY=200;
		gun=3;
		gs=new GunSecond(posX,posY,gun);
	}
	@Test
	void test() {
		setupStage1();
		
		assertNotNull(gs);
	}

}
