package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javafx.embed.swing.JFXPanel;

class YellowPDFTest {
	private double posX;
	private double posY;
	private Player objective;
	private Player p;
	private YellowPDF yp;
	public void setupStage1(){
		JFXPanel jfxPanel = new JFXPanel();
		jfxPanel.getBorder();
		p=new Player();
		posX=301.3;
		posY=200;
		objective=p;
		yp=new YellowPDF(posX,posY,objective);
	}
	@Test
	void test() {
		setupStage1();
		
		assertEquals(posX,yp.getPosX(),"The x position is wrong");
		assertEquals(posY,yp.getPosY(),"The y position is wrong");
		assertEquals(objective,yp.getObjective(),"The objetive is wrong");
	}

}
