package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

class GunManagementTest {
	private GunManagement gm;
	private double posX;
	private double posY;
	private int gun;
	private GunThird gt;
	public void setupStage1(){
		JFXPanel jfxPanel = new JFXPanel();
		jfxPanel.getBorder();
		posX=301.3;
		posY=200;
		gun=4;
		gt=new GunThird(posX,posY,gun);
		gm=new GunManagement();
	}
	@Test
	void test() {
		setupStage1();
		gm.addGun(gt);
		assertEquals(gt.getDamage(),gm.getInitialGun().getDamage(),"The first gun is wrong");
	}

}
