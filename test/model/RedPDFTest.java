package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

class RedPDFTest {
	private double posX;
	private double posY;
	private Player objective;
	private Player p;
	private RedPDF rp;
	public void setupStage1(){
		JFXPanel jfxPanel = new JFXPanel();
		jfxPanel.getBorder();
		p=new Player();
		posX=301.3;
		posY=200;
		objective=p;
		rp=new RedPDF(posX,posY,objective);
	}
	@Test
	void testRedPDF() {
		setupStage1();
		
		assertEquals(posX,rp.getPosX(),"The x position is wrong");
		assertEquals(posY,rp.getPosY(),"The y position is wrong");
		assertEquals(objective,rp.getObjective(),"The objetive is wrong");
	}

}
