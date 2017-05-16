package g4.beachGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {
	Board b1 = new Board();
	User user = new User(b1);
	
	@Test
	public void testGetPicNum() {
		assertEquals(0, user.getPicNum());
	}
	
	@Test
	public void testMoveLeft(){
		user.setDirection(User.LEFT);
		user.setyLoc(600); //sets isOnEdge true
		user.move();
		assertEquals(15, user.getxLoc());
		
		user.setxLoc(15);
		user.isInOcean = true;
		user.move();
		assertEquals(3, user.getxLoc());
		
		user.isInOcean = false;
		user.move();
		assertEquals(-9, user.getxLoc());
		
		user.isInOcean = true;
		user.setxLoc(15);
		user.setyLoc(450);
		user.move();
		assertEquals(3, user.getxLoc());
	}
	
	@Test
	public void testMoveRight(){
		user.setDirection(User.RIGHT);
		user.setxLoc(15); //isShore = true
		user.move();
		assertEquals(27, user.getxLoc());
		
		user.setxLoc(Board.WIDTH); //isShore = false
		user.move();
		assertEquals(Board.WIDTH, user.getxLoc());
		
		user.isInOcean = true;
		user.setxLoc(Board.WIDTH);
		user.setyLoc(600);
		user.move();
		assertEquals(Board.WIDTH+12, user.getxLoc());
		
		user.isInOcean = false;
		user.setxLoc(15);
		user.move();
		assertEquals(27, user.getxLoc());
		
		user.setxLoc(Board.WIDTH);
		user.move();
		assertEquals(Board.WIDTH, user.getxLoc());
		
		user.setxLoc(15);
		user.setyLoc(450);
		user.isInOcean = true;
		user.move();
		assertEquals(27, user.getxLoc());
	}
	
	@Test
	public void testMoveUp(){
		user.setDirection(User.UP);
		user.move();
		assertEquals(442, user.getyLoc());
		
		user.setyLoc(600);
		user.move();
		assertEquals(600, user.getyLoc());
		
		user.setyLoc(450);
		user.move(); //isOnEdge = False
		
		user.setxLoc(975);
		user.move();
		assertEquals(442, user.getyLoc()); //first term in if is false
		
		user.isInOcean = true;
		user.move();
		assertEquals(434, user.getyLoc());
		
		user.setyLoc(600);
		user.move(); //isOnEdge = True
		
		user.move();
		assertEquals(592, user.getyLoc());
	}
	
	@Test
	public void testMoveDown(){
		user.setDirection(User.DOWN);
		user.move();
		assertEquals(458, user.getyLoc());
		
		user.setxLoc(Board.WIDTH);
		user.move();
		assertEquals(458, user.getyLoc());
		
		user.isInOcean = true;
		user.move();
		assertEquals(466, user.getyLoc());
		
		user.isInOcean = false;
		user.setyLoc(600);
		user.move();
		assertEquals(600, user.getyLoc());
		
		user.move();
		assertEquals(608, user.getyLoc());
	}
	
	@Test
	public void testMoveUpRight(){
		user.setDirection(User.UP_RIGHT);
		user.move();
		assertEquals(27, user.getxLoc());
		
		user.setxLoc(Board.WIDTH);
		user.move();
		assertEquals(Board.WIDTH, user.getxLoc());
		
		user.isInOcean = true;
		user.move();
		assertEquals(Board.WIDTH+12, user.getxLoc());
	}
	
	@Test
	public void testMoveUpLeft(){
		user.setDirection(User.UP_LEFT);
		user.move();
		assertEquals(3, user.getxLoc());
		
		user.setxLoc(Board.WIDTH);
		user.move();
		assertEquals(Board.WIDTH, user.getxLoc());
		
		user.isInOcean = true;
		user.move();
		assertEquals(Board.WIDTH-12, user.getxLoc());
	}
	
	@Test
	public void testMoveDownRight(){
		user.setDirection(User.DOWN_RIGHT);
		user.move();
		assertEquals(27, user.getxLoc());
		
		user.setxLoc(Board.WIDTH);
		user.move();
		assertEquals(Board.WIDTH, user.getxLoc());
		
		user.isInOcean = true;
		user.move();
		assertEquals(Board.WIDTH+12, user.getxLoc());
		
		user.setyLoc(600); //isOnEdge = true
		user.move(); 
		
		user.isInOcean = false;
		user.move();
		assertEquals(616, user.getyLoc());
	}
	
	@Test
	public void testMoveDownLeft(){
		user.setDirection(User.DOWN_LEFT);
		user.move();
		assertEquals(3, user.getxLoc());
		
		user.setxLoc(Board.WIDTH);
		user.move();
		assertEquals(Board.WIDTH, user.getxLoc());
		
		user.isInOcean = true;
		user.move();
		assertEquals(Board.WIDTH-12, user.getxLoc());
		
		user.isInOcean = false;
		user.setyLoc(600);
		user.move();
		
		user.move();
		assertEquals(608, user.getyLoc());
	}
	
	@Test
	public void testIsShore(){
		assertFalse(user.isShore(-1, 350));
		assertTrue(user.isShore(370, 350));
		b1.beach[0][4] = Board.WATER;
		assertFalse(user.isShore(370, 350));
		assertFalse(user.isShore(Board.SHORE_WIDTH+1, 350));
		assertFalse(user.isShore(370, 200));
		assertFalse(user.isShore(370, Board.HEIGHT+100));
	}
}
