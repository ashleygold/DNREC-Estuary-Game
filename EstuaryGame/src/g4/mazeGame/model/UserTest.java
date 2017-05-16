package g4.mazeGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	Board board = new Board(3,0);
	User user = new User(board);
	
	@Test
	public void tryMoveLeftTest() {
		double userX = user.getXLoc();
		double userY = user.getYLoc();
		user.setDirection(User.LEFT);
		user.update();
		user.setDirection(User.STILL);
		user.update();
		assertTrue(user.getXLoc()<userX);
		assertTrue(user.getYLoc()==userY);
	}
	
	@Test
	public void tryMoveRightTest() {
		double userX = user.getXLoc();
		double userY = user.getYLoc();
		user.setDirection(User.RIGHT);
		user.update();
		user.setDirection(User.STILL);
		user.update();
		assertTrue(user.getXLoc()>userX);
		assertTrue(user.getYLoc()==userY);
	}
	
	@Test
	public void tryMoveUpTest() {
		double userX = user.getXLoc();
		double userY = user.getYLoc();
		user.setDirection(User.UP);
		user.update();
		user.setDirection(User.STILL);
		user.update();
		assertTrue(user.getXLoc()==userX);
		assertTrue(user.getYLoc()<userY);
	}
	
	@Test
	public void tryMoveDownTest() {
		double userX = user.getXLoc();
		double userY = user.getYLoc();
		user.setDirection(User.DOWN);
		user.update();
		user.setDirection(User.STILL);
		user.update();
		assertTrue(user.getXLoc()==userX);
		assertTrue(user.getYLoc()>userY);
	}
	
	@Test
	public void tryMoveDownLeftTest() {
		double userX = user.getXLoc();
		double userY = user.getYLoc();
		user.setDirection(User.DOWN_LEFT);
		user.update();
		user.setDirection(User.STILL);
		user.update();
		assertTrue(user.getXLoc()<userX);
		assertTrue(user.getYLoc()>userY);
	}
	
	@Test
	public void tryMoveDownRightTest() {
		double userX = user.getXLoc();
		double userY = user.getYLoc();
		user.setDirection(User.DOWN_LEFT);
		user.update();
		user.setDirection(User.STILL);
		user.update();
		assertTrue(user.getXLoc()>userX);
		assertTrue(user.getYLoc()>userY);
	}
	
	@Test
	public void tryMoveUpRightTest() {
		double userX = user.getXLoc();
		double userY = user.getYLoc();
		user.setDirection(User.DOWN_LEFT);
		user.update();
		user.setDirection(User.STILL);
		user.update();
		assertTrue(user.getXLoc()>userX);
		assertTrue(user.getYLoc()<userY);
	}
	
	@Test
	public void tryMoveUpLeftTest() {
		double userX = user.getXLoc();
		double userY = user.getYLoc();
		user.setDirection(User.DOWN_LEFT);
		user.update();
		user.setDirection(User.STILL);
		user.update();
		assertTrue(user.getXLoc()<userX);
		assertTrue(user.getYLoc()<userY);
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
		user2.setFoodCount(10);
		assertFalse(user2.checkWin());
		user2.setYLoc(0);
		user2.setXLoc(9);
		assertTrue(user2.checkWin());
	}
	
	@Test
	public void testGetPicNum(){
		assertEquals(0,user.getPicNum());
	}

}
