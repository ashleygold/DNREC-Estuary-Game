package g4.beachGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoatTest {
	Boat boat = new Sailboat();
	
	@Test
	public void testMove() {
		int x = boat.getXLoc();
		boat.direction = true;
		boat.move();
		assertEquals(x+boat.speed, boat.getXLoc());
		
		boat.direction = false;
		boat.move();
		assertEquals(x, boat.getXLoc());
		
	}
	
	@Test
	public void testGetYLoc(){
		int y = boat.yloc;
		assertEquals(y, boat.getYLoc());
	}
	
	@Test
	public void testGetHasEmittedWave(){
		assertFalse(boat.getHasEmittedWave());
	}
	
	@Test
	public void testGetWaveLocation(){
		assertTrue(boat.getWaveLocation()<Board.SHORE_WIDTH-200);
	}
	
	@Test
	public void testEmittedWave(){
		boat.emittedWave();
		assertTrue(boat.hasEmittedWave);
	}
	
	@Test
	public void testGetDirection(){
		boat.direction = true;
		assertTrue(boat.getDirection());
	}
}
