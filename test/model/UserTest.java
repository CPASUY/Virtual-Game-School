package model;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class UserTest {
	private String nickname;
	private double score;
	private int position;
	private int defeats;
	private int stage;
	private double coins;
	private User u;
	public void setupStage1(){
		nickname="Unicode";
		score=440;
		position=1;
		defeats=4;
		stage=2;
		coins=900;
		u=new User(nickname,score,defeats,stage,coins);
	}
	@Test
	void testUser() {
		setupStage1();
		
		assertEquals(nickname,u.getNickname(),"The user nickname is wrong");
		assertEquals(score,u.getScore(),"The user score is wrong");
		assertEquals(position,u.getPosition(),"The user position is wrong");
		assertEquals(defeats,u.getDefeats(),"The user defeats is wrong");
		assertEquals(stage,u.getStages(),"The user last stage wrong");
		assertEquals(coins,u.getMoodleCoins(),"The user coins is wrong");
	}

}
