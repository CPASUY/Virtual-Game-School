package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

class BulletTest {
	private double posX;
	private double posY;
	private int gun;
	private Bullet b;
	public void setupStage1(){
		JFXPanel jfxPanel = new JFXPanel();
		jfxPanel.getBorder();
		posX=301.3;
		posY=200;
		gun=2;
		b=new Bullet(posX,posY,gun);
	}
	@Test
	void test() {
		setupStage1();
		
		assertEquals(posX,b.getPosX(),"The x position is wrong");
		assertEquals(posY,b.getPosY(),"The y position is wrong");
	}

}
