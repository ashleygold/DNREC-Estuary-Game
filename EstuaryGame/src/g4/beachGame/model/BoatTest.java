package g4.beachGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoatTest {
	Boat[] boat = {new Sailboat(), new Speedboat(), new CruiseLiner()};
	
	
	@Test
	public void testMove() {
		for (int i = 0; i < boat.length - 1; i++) {
			int x = boat[i].getXLoc();
			boat[i].direction = true;
			boat[i].move();
			assertEquals(x + boat[i].speed, boat[i].getXLoc());

			boat[i].direction = false;
			boat[i].move();
			assertEquals(x, boat[i].getXLoc());
		}
	}
	
	@Test
	public void testGetYLoc(){
		for (int i = 0; i < boat.length - 1; i++) {
			int y = boat[i].yloc;
			assertEquals(y, boat[i].getYLoc());
		}
	}
		
	@Test
	public void testGetHasEmittedWave(){
		for (int i = 0; i < boat.length - 1; i++) {
			assertFalse(boat[i].getHasEmittedWave());
		}
	}
	
	@Test
	public void testGetWaveLocation(){
		for (int i = 0; i < boat.length - 1; i++) {
			assertTrue(boat[i].getWaveLocation()<Board.SHORE_WIDTH-200);
		}
	}
	
	@Test
	public void testEmittedWave(){
		for (int i = 0; i < boat.length - 1; i++) {
			boat[i].emittedWave();
			assertTrue(boat[i].hasEmittedWave);
		}
	}
	
	@Test
	public void testGetDirection(){
		for (int i = 0; i < boat.length - 1; i++) {
			boat[i].direction = true;
			assertTrue(boat[i].getDirection());
		}
	}
}
