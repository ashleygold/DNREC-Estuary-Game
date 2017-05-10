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
	public void testMove(){
		user.setDirection(User.LEFT);
		user.move();
		assertEquals(3, user.getxLoc());
	}
}
