package g4.mazeGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void tryMoveLeftTest() {
		Board board = new Board (3,0);
		User user = new User(board);
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
		Board board = new Board (3,0);
		User user = new User(board);
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
		Board board = new Board (3,0);
		User user = new User(board);
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
		Board board = new Board (3,0);
		User user = new User(board);
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
		Board board = new Board (3,0);
		User user = new User(board);
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
		Board board = new Board (3,0);
		User user = new User(board);
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
		Board board = new Board (3,0);
		User user = new User(board);
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
		Board board = new Board (3,0);
		User user = new User(board);
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
		Board board = new Board (3,0);
		User user = new User(board);
		int foodCount = user.getFoodCount();
		assertEquals(foodCount,0);
	}
	
	@Test
	public void getDisposeTest(){
		Board board = new Board (3,0);
		User user = new User(board);
		boolean dispose = user.getDispose();
		assertEquals(dispose,false);
	}

}
