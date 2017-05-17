package g4.beachGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TurtleTest {
	Board b1 = new Board();
	Turtle turtle = new Turtle(b1);
	
	@Test
	public void testMoveLeft(){
		turtle.setDirection(0);
		turtle.setxLoc(370); turtle.setyLoc(350);
		turtle.move(); //x:369,y:349
		assertEquals(369, turtle.getxLoc());
		turtle.setxLoc(1);
		turtle.move();//x:0,y:349
		assertEquals(1, turtle.getDirection());
		turtle.setDirection(0);
		turtle.setxLoc(370);
		b1.beach[0][4] = Board.WATER;
		turtle.move();
		assertTrue(turtle.getGotToOcean());
		turtle.setxLoc(334);
		turtle.setyLoc(400);
		b1.beach[0][3] = Board.GABION;
		turtle.move();
		assertEquals(401, turtle.getyLoc());
		turtle.move();
		assertEquals(333, turtle.getxLoc());
	}
	
	@Test
	public void testMoveRight(){
		turtle.setDirection(1);
		turtle.setxLoc(370); turtle.setyLoc(350);
		turtle.move(); //x:371,y:349
		assertEquals(371, turtle.getxLoc());
		turtle.setxLoc(Board.getWidth()-turtle.getWidth()-100);
		turtle.move();//y:349
		assertEquals(0, turtle.getDirection());
		turtle.setDirection(1);
		turtle.setxLoc(370);
		b1.beach[0][4] = Board.WATER;
		turtle.move();
		assertTrue(turtle.getGotToOcean());
		turtle.setxLoc(333);
		turtle.setyLoc(400);
		b1.beach[0][4] = Board.GABION;
		turtle.move();
		assertEquals(401, turtle.getyLoc());
		turtle.move();
		assertEquals(334, turtle.getxLoc());
	}
	
	@Test
	public void testIsShore(){
		assertFalse(turtle.isShore(370, 200));
	}
	
	@Test
	public void testIsWater(){
		assertTrue(turtle.isWater(370, 200));
		b1.beach[0][4] = Board.WATER;
		assertTrue(turtle.isWater(370, 350));
		assertFalse(turtle.isWater(300, Board.HEIGHT+1));
		assertFalse(turtle.isWater(-1, 200));
		assertFalse(turtle.isWater(Board.getWidth(), 450));
		assertFalse(turtle.isWater(300, 600));
	}
	
	@Test
	public void testGetPicNum(){
		assertEquals(0, turtle.getPicNum());
	}
	
	@Test
	public void testGetFramesLeft(){
		assertEquals(turtle.DEFAULTFRAMES, turtle.getFramesLeft());
	}
	
	@Test
	public void testGetHeight(){
		assertEquals(50, turtle.getHeight());
	}
}
