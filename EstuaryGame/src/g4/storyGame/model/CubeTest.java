package g4.storyGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class CubeTest {
	
	Cube cube = new Cube();
	
	@Test
	public void moveTest(){
		cube.move();
		assertTrue(cube.getIsMoved());
	}
	
	@Test
	public void fixTest(){
		cube.fix();
		assertTrue(cube.getIsFixed());
	}
	
	@Test
	public void changeImgTest(){
		cube.changeImg();
		assertTrue(cube.getImg()>=0);
	}
}
