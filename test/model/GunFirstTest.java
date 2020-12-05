package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

class GunFirstTest {
	private double posX;
	private double posY;
	private int gun;
	private GunFirst gf;
	public void setupStage1(){
		JFXPanel jfxPanel = new JFXPanel();
		jfxPanel.getBorder();
		posX=301.3;
		posY=200;
		gun=2;
		gf=new GunFirst(posX,posY,gun);
	}
	@Test
	void test() {
		setupStage1();
		
		assertNotNull(gf);
	}

}
