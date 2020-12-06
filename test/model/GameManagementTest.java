package model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class GameManagementTest {
	public GameManagement gm;
	public void setupStage1() throws IOException{
		gm=new GameManagement();
	}
	@Test
	void testGameManagement() {
		assertNull(gm);
	}

}
