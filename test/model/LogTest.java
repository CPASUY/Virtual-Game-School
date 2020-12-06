package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class LogTest {
	private String nickname;
	private double score;
	private int position;
	private int defeats;
	private int stage;
	private double coins;
	private User u;
	private LocalDate ld;
	private Log l;
	public void setupStage1(){
		nickname="Unicode";
		score=300;
		position=3;
		defeats=2;
		stage=1;
		coins=600;
		ld = LocalDate.now();
		u=new User(nickname,score,defeats,stage,coins);
		l=new Log(u,ld);
	}
	@Test
	void testLog() {
		setupStage1();
		
		assertEquals(nickname,u.getNickname(),"The user nickname is wrong");
		assertEquals(score,u.getScore(),"The user score is wrong");
		assertEquals(position,u.getPosition(),"The user position is wrong");
		assertEquals(defeats,u.getDefeats(),"The user defeats is wrong");
		assertEquals(stage,u.getStages(),"The user last stage wrong");
		assertEquals(coins,u.getMoodleCoins(),"The user coins is wrong");
		
		assertEquals(ld,l.getDate(),"The log date wrong");
	}

}
