package model;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

class GunThirdTest {
	private double posX;
	private double posY;
	private int gun;
	private GunThird gt;
	public void setupStage1(){
		JFXPanel jfxPanel = new JFXPanel();
		posX=301.3;
		posY=200;
		gun=4;
		gt=new GunThird(posX,posY,gun);
		jfxPanel.getBorder();
	}
	@Test
	void test() {
		setupStage1();
		
		assertNotNull(gt);
	}

}
