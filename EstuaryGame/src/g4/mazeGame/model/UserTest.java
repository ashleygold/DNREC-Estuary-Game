package g4.mazeGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	Board board = new Board(3,0);
	User user = new User(board);
	
	@Test
	public void testTryMoveCardinalDirection() {
		double initX = 17;
		double initY = 15;
		
		User user2 = new User(board);
		user2.setXLoc((int) initX);
		user2.setYLoc((int) initY);
		
		user2.setDirection(User.RIGHT);
		user2.update();
		
		assertTrue(initX != user2.getXLoc());
		assertTrue(initY == user2.getYLoc());
		
		for (int i = 0; i < 10; i++){
			user2.setDirection(User.RIGHT);
			user2.update();
		}
		initX = user2.getXLoc();
		initY = user2.getYLoc();
		
		user2.setDirection(User.RIGHT);
		user2.update();
		assertTrue(initX == user2.getXLoc());
		assertTrue(initY == user2.getYLoc());
	}
	
	@Test
	public void testTryMoveOrdinalDirectionTest() {
		double initX = 17;
		double initY = 15;
		
		User user2 = new User(board);
		user2.setXLoc((int) initX);
		user2.setYLoc((int) initY);
		
		user2.setDirection(User.DOWN_RIGHT);
		user2.update();
		
		assertTrue(initX < user2.getXLoc());
		assertTrue(initY < user2.getYLoc());
		
		for (int i = 0; i < 10; i++){
			user2.setDirection(User.DOWN_RIGHT);
			user2.update();
		}
		initX = user2.getXLoc();
		initY = user2.getYLoc();
		
		user2.setDirection(User.DOWN_RIGHT);
		user2.update();
		assertTrue(initX == user2.getXLoc());
		assertTrue(initY != user2.getYLoc());
		
		initX = user2.getXLoc();
		initY = user2.getYLoc();
		
		user2.setDirection(User.UP_LEFT);
		user2.update();
		assertTrue(initX > user2.getXLoc());
		assertTrue(initY > user2.getYLoc());
	}
	
	@Test
	public void testEatFood(){		
		User user2 = new User(board);
		user2.setXLoc(17);
		user2.setYLoc(15);
		board.setFoodAtCorner();
		int iFood = user2.getFoodCount();
		user2.update();
		assertEquals(iFood + 1, user2.getFoodCount());
	}
	
	@Test
	public void getFoodCountTest(){
		int foodCount = user.getFoodCount();
		assertEquals(foodCount,0);
	}
	
	@Test
	public void getDisposeTest(){
		boolean dispose = user.getDispose();
		assertEquals(dispose,false);
	}
	
	@Test
	public void testGetDirection(){
		assertEquals(user.getDirection(),User.STILL);
	}
	
	@Test
	public void testCheckWin(){
		Board board2 = new Board (1,0);
		User user2 = new User(board2);
		assertFalse(user2.checkWin());
		user2.setFoodCount(10);
		user2.setYLoc(0);
		user2.setXLoc(9);
		assertTrue(user2.checkWin());
	}
	
	@Test
	public void testGetPicNum(){
		assertEquals(0,user.getPicNum());
	}

}
