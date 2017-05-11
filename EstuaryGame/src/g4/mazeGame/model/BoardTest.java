package g4.mazeGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testIsEmpty(){
		Board board = new Board(3,0);
		User user = new User(board);
		assertEquals(board.getCell((int)user.getXLoc(), (int)user.getYLoc()),'.');
	}
	
	@Test
	public void testOpenGate(){
		Board board = new Board(3,0);
		User user=new User(board);
		user.setFoodCount(5);
		assertTrue(board.openGate());
	}
	
	public void testIsEmptyPred(){
		Board board = new Board(3,0);
		for (Predator x : board.getPredator()){
			assertEquals(board.getCell((int)x.getXLoc(), (int)x.getYLoc()),'.');
		}
	}
	
	@Test
	public void testEatFood() {
		Board board = new Board(3,0);
		assertEquals(board.getGoalFood(),5);
		Board board2 = new Board(2,0);
		assertEquals(board2.getGoalFood(),7);
		Board board3 = new Board(1,0);
		assertEquals(board3.getGoalFood(),10);
		Board board0 = new Board(4,0);
		assertEquals(board0.getGoalFood(),5);
	}
	
	@Test
	public void testGetSalinity(){
		Board board = new Board(3,0);
		assertEquals(board.getSalinity(),3);
		Board board2 = new Board(2,0);
		assertEquals(board2.getSalinity(),2);
		Board board3 = new Board(1,0);
		assertEquals(board3.getSalinity(),1);
	}

}
