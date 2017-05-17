package g4.beachGame.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import g4.beachGame.controller.BeachCont;

public class WaveTest {
	Sailboat sailboat = new Sailboat();
	CruiseLiner cruiseliner = new CruiseLiner();
	Speedboat speedboat = new Speedboat();
	
	@Test
	public void testMove() {
		Wave wave = new Wave(sailboat);
		Wave.direction = Wave.FORWARD;
		wave.move();
		assertEquals(sailboat.getYLoc()+wave.speed, wave.getY());
		
		Wave.direction = Wave.LEFT;
		int xLocation = wave.getX();
		wave.move();
		assertEquals(xLocation-Wave.direction, wave.getX());
		
		BeachCont.frameCounterWind = 8;
		wave.move();
		assertEquals(sailboat.getYLoc()+2*wave.speed, wave.getY());
		
		Wave.direction = Wave.RIGHT;
		wave.move();
		assertEquals(sailboat.getYLoc()+2*wave.speed, wave.getY());
		
		BeachCont.frameCounterWind = 6;
		wave.move();
		assertEquals(sailboat.getYLoc()+3*wave.speed, wave.getY());
	}
	
	@Test
	public void testIsOutOfRange(){
		Wave wave = new Wave(speedboat);
		Wave.direction = Wave.LEFT;
		wave.xloc = -1;
		assertTrue(wave.isOutOfRange());
		
		Wave.direction = Wave.RIGHT;
		wave.xloc = Board.WIDTH;
		assertTrue(wave.isOutOfRange());
		
		Wave.direction = Wave.FORWARD;
		wave.xloc = Board.WIDTH;
		assertTrue(wave.isOutOfRange());
		
		
		Wave.direction = Wave.LEFT;
		wave.xloc = Board.SHORE_WIDTH+1;
		wave.yloc = Board.HEIGHT/2 + 1;
		assertTrue(wave.isOutOfRange());
		
		Wave wave2 = new Wave(2, 5, -1, 600);
		Wave.direction = Wave.RIGHT;
		wave.xloc = -1;
		assertTrue(wave2.isOutOfRange());
		
		Wave wave3 = new Wave(cruiseliner);
		wave3.xloc = 400;
		assertFalse(wave3.isOutOfRange());
	}

	@Test
	public void testActivateWind(){
		ArrayList<Wave> waves = new ArrayList<Wave>();
		Wave wave = new Wave(cruiseliner);
		wave.xloc = Board.SHORE_HEIGHT+1;
		waves.add(wave);
		Wave.direction = Wave.FORWARD;
		Wave.activateWind(3, waves);
		assertEquals(Wave.FORWARD, Wave.direction);
		
		wave.xloc= Board.SHORE_HEIGHT-1;
		Wave.activateWind(3,waves);
		assertEquals(Wave.RIGHT, Wave.direction);
		
		Wave.direction = Wave.FORWARD;
		Wave.activateWind(4,waves);
		assertEquals(Wave.LEFT, Wave.direction);
		
		Wave.activateWind(3,waves);
		assertEquals(Wave.LEFT, Wave.getDirection());
	}
	
	@Test
	public void testCeaseWind(){
		Wave.ceaseWind();
		assertEquals(Wave.FORWARD, Wave.direction);
	}
	
	@Test
	public void testGetWindFace(){
		assertEquals(1, Wave.getWindFace());
	}
}
