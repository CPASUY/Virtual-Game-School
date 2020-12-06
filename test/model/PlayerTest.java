package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import exceptions.NoEnoughCoinsException;
import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;

class PlayerTest {
	private Player p;
	private String paths;
	private Image image;
	public void setupStage1(){
		JFXPanel jfxPanel = new JFXPanel();
		jfxPanel.getBorder();
		p=new Player();
	}
	
	public void setupStage2() {
		JFXPanel jfxPanel = new JFXPanel();
		jfxPanel.getBorder();
		p= new Player(false,323,4343,3232,200, -10 ,5,6,"firstGun");
		paths = "sprites/BoyGunDefect/static.png";
		File file = new File(paths);
    	Image imload = new Image(file.toURI().toString());
		image = imload;
	}
	@Test
	void test() {
		setupStage1();
		assertNotNull(p);
	}
	
	@Test
	void test2() {
		setupStage2();
		assertNotNull(p);
	}
	
	@Test
	void test3() {
		setupStage2();
		p.setCurrentFrame(image);
		p.move();
		assertEquals(p.getPosX(),0);
	}
	
	@Test
	void test4() throws NoEnoughCoinsException {
		setupStage2();
		p.setCoins(3500);
		p.discountCoins("secondGun");
		assertEquals(0, p.getCoins());
	}

}
