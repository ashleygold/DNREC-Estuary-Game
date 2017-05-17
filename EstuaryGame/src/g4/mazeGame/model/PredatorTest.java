package g4.mazeGame.model;

import static org.junit.Assert.*;

import org.junit.Test;


public class PredatorTest {
	Board board = new Board(3,0);
	User user = new User(board);
	Predator pred = new Predator(board, user, 10, 15);
	
	@Test
	public void checkEatTestTrue() {
		Predator pred2 = new Predator(board, user, 15, 15);
		assertTrue(pred2.checkEat());
	}
	
	@Test
	public void testGetDirection(){
		pred.setDirection(Predator.STILL);
		assertEquals(pred.getDirection(), Predator.STILL);
	}
	
	@Test
	public void testMove(){
		int initX = (int) pred.getXLoc();
		int initY = (int) pred.getYLoc();
		for(int i=0; i<1000; i++){
			pred.move();
		}
		assertTrue(initX!=pred.getXLoc());
		assertTrue(initY!=pred.getYLoc());
	}
}
