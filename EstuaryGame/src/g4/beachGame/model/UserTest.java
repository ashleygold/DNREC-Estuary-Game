package g4.beachGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	Board b1 = new Board();
	User user = new User();
	
	@Test
	public void testGetPicNum() {
		assertEquals(0, user.getPicNum());
	}
	
	@Test
	public void getDirection(){
		assertEquals(8, user.getDirection());
	}
	
	@Test
	public void testMove(){
		//testing still
		user.move();
		assertEquals(0, user.getPicNum());
		
		//testing left
		user.setDirection(User.LEFT);
		user.move();
		assertEquals(88, user.getxLoc());
		
		user.setxLoc(-1);
		user.move();
		assertEquals(-1, user.getxLoc());
	
		//testing right
		user.setDirection(User.RIGHT);
		user.setxLoc(15); //isShore = true
		user.move();
		assertEquals(27, user.getxLoc());
		
		user.setxLoc(Board.SHORE_WIDTH); //isShore = false
		user.move();
		assertEquals(Board.SHORE_WIDTH, user.getxLoc());
	
		user.setxLoc(920);
		user.move();
		assertEquals(920, user.getxLoc());
	
		//testing up
		user.setDirection(User.UP);
		user.move();
		assertEquals(442, user.getyLoc());
		
		user.setyLoc(600);
		user.move();
		assertEquals(600, user.getyLoc());
		
		user.setyLoc(450);
		user.setxLoc(975);
		user.move();
		assertEquals(450, user.getyLoc()); //first term in if is false
		
		//testing down
		user.setDirection(User.DOWN);
		user.move();
		assertEquals(458, user.getyLoc());
		
		user.setxLoc(Board.getWidth());
		user.move();
		assertEquals(458, user.getyLoc());
	
		//testing up_right
		user.setxLoc(100);
		user.setDirection(User.UP_RIGHT);
		user.move();
		assertEquals(112, user.getxLoc());
		
		user.setxLoc(Board.getWidth());
		user.move();
		assertEquals(Board.getWidth(), user.getxLoc());
		
		//testing up_left
		user.setxLoc(100);
		user.setDirection(User.UP_LEFT);
		user.move();
		assertEquals(88, user.getxLoc());
		
		user.setxLoc(Board.getWidth());
		user.move();
		assertEquals(Board.getWidth(), user.getxLoc());
		
		//testing down_right
		user.setxLoc(100);
		user.setDirection(User.DOWN_RIGHT);
		user.move();
		assertEquals(112, user.getxLoc());
		
		user.setxLoc(Board.getWidth());
		user.move();
		assertEquals(Board.getWidth(), user.getxLoc());	
	
		//testing down_left
		user.setxLoc(100);
		user.setDirection(User.DOWN_LEFT);
		user.move();
		assertEquals(88, user.getxLoc());
		
		user.setxLoc(Board.getWidth());
		user.move();
		assertEquals(Board.getWidth(), user.getxLoc());
	}
	
	@Test
	public void testIsShore(){
		assertFalse(user.isShore(-1, 350));
		assertTrue(user.isShore(370, 350));
		b1.beach[0][4] = Board.WATER;
	}

}
