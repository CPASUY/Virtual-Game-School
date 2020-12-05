package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

class GreenPDFTest {
	private double posX;
	private double posY;
	private Player objective;
	private Player p;
	private GreenPDF gp;
	public void setupStage1(){
		JFXPanel jfxPanel = new JFXPanel();
		jfxPanel.getBorder();
		p=new Player();
		posX=301.3;
		posY=200;
		objective=p;
		gp=new GreenPDF(posX,posY,objective);
	}
	@Test
	void testGreenPDF() {
		setupStage1();
		
		assertEquals(posX,gp.getPosX(),"The x position is wrong");
		assertEquals(posY,gp.getPosY(),"The y position is wrong");
		assertEquals(objective,gp.getObjective(),"The objetive is wrong");
	}

}
