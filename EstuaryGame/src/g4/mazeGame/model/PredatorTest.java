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
		assertTrue(pred2.move());
	}
	
	@Test
	public void testSetDirection(){
		pred.setDirection(Predator.RIGHT);
		assertEquals(pred.getDirection(), Predator.RIGHT);
		pred.setDirection(Predator.LEFT);
		assertEquals(pred.getDirection(), Predator.LEFT);
	}
	
	@Test
	public void testMoveCardinalDirection(){
		double initX = 17;
		double initY = 15;
		
		Predator pred2 = new Predator(board, user, (int) initX, (int) initY);
		pred2.setDirection(Predator.RIGHT);
		pred2.move();
		
		assertTrue(initX < pred2.getXLoc());
		assertTrue(initY == pred2.getYLoc());
		
		for (int i = 0; i < 10; i++){
			pred2.setDirection(Predator.RIGHT);
			pred2.move();
		}
		initX = pred2.getXLoc();
		initY = pred2.getYLoc();
		
		pred2.setDirection(Predator.RIGHT);
		pred2.move();
		assertTrue(initX == pred2.getXLoc());
		assertTrue(initY == pred2.getYLoc());
	}
	
	@Test
	public void testMoveDiagonalDirection(){
		double initX = 17;
		double initY = 15;
		
		Predator pred2 = new Predator(board, user, (int) initX, (int) initY);
		pred2.setDirection(Predator.DOWN_RIGHT);
		pred2.move();
		
		assertTrue(initX < pred2.getXLoc());
		assertTrue(initY < pred2.getYLoc());
		
		for (int i = 0; i < 10; i++){
			pred2.setDirection(Predator.DOWN_RIGHT);
			pred2.move();
		}
		initX = pred2.getXLoc();
		initY = pred2.getYLoc();
		
		pred2.setDirection(Predator.DOWN_RIGHT);
		pred2.move();
		assertTrue(initX == pred2.getXLoc());
		assertTrue(initY == pred2.getYLoc());
	}
}
