package g4.mazeGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {
	Board board = new Board(3,0);
	User user = board.getUser();

	@Test
	public void testIsEmpty(){
		assertTrue(board.isEmpty(user.getXLoc(), user.getYLoc()));
	}
	
	@Test
	public void testOpenGate(){
		user.setFoodCount(5);
		assertTrue(board.openGate());
	}
	
	
	@Test
	public void testGetGoalFood() {
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
		assertEquals(board.getSalinity(),3);
		Board board2 = new Board(2,0);
		assertEquals(board2.getSalinity(),2);
		Board board3 = new Board(1,0);
		assertEquals(board3.getSalinity(),1);
	}
	
	@Test
	public void testGetUser(){
		assertEquals(user, board.getUser());
	}
	
	@Test
	public void testGetIsEaten(){
		Board board2 = new Board(2,0);
		User user2 = board2.getUser();
		assertEquals(board2.getIsEaten(), false);
		board2.getPredator().add(new Predator(board2, user2, (int) user2.getXLoc(), (int) user2.getYLoc()));
		board2.update();
		assertEquals(board2.getIsEaten(), true);
	}
	
	@Test
	public void testGetPredator(){
		assertEquals(board.getPredator().size(), 1);
	}
	
	@Test
	public void testWinGame(){
		assertEquals(board.winGame(user.getXLoc(), user.getYLoc()), false);
		user.setFoodCount(5);
		board.openGate();
		user.setXLoc(10);
		user.setYLoc(0);
		assertTrue(user.checkWin());
	}
	
	@Test
	public void testEatFood(){
		assertEquals(board.eatFood(user.getXLoc(), user.getYLoc()), false);
	}
	
	@Test
	public void testUpdate(){
		board.update();
		for(Predator x : board.getPredator()){
			int beforeX = (int) x.getXLoc();
			int beforeY = (int) x.getYLoc();
			assertTrue((x.getXLoc()!= beforeX) || x.getYLoc()!= beforeY);
		}
	}


}
