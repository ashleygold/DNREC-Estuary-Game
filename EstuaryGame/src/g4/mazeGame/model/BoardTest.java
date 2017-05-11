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
	
	@Test
	public void testGetHeight(){
		Board board = new Board(3,0);
		assertEquals(board.getHeight(),17);
	}
	
	@Test
	public void testGetWidth(){
		Board board = new Board(3,0);
		assertEquals(board.getWidth(), 19);
	}
	
	@Test
	//WHY DOES THIS TEST FAIL?????????
	public void testGetUser(){
		Board board = new Board(3,0);
		User user = new User(board);
		assertSame(user, board.getUser());
	}
	
	@Test
	public void testGetIsEaten(){
		Board board = new Board(3,0);
		assertEquals(board.getIsEaten(), false);
	}
	
	@Test
	public void testGetPredator(){
		Board board = new Board(3,0);
		assertEquals(board.getPredator().size(), 1);
	}

}
