package g4.beachGame.model;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

public class BoardTest {

	Board b1 = new Board();
	
	@Test
	public void testUpdateElapsedTime(){
		b1.updateElapsedTime();
		assertEquals(.001,b1.elapsedTime,1);
	}
	
	@Test
	public void testCreateWave() {
		b1.elapsedTime = 20;
		b1.createWave(new Sailboat());
		assertEquals(1, b1.getCurrWaves().size());
		
		b1.elapsedTime = 17;
		b1.createWave(new Sailboat());
		assertEquals(1, b1.getCurrWaves().size());
	}
	@Test
	public void testCheckLost(){
		b1.turtles.add(new Turtle(b1));
		Iterator<Turtle> turtleIt = b1.turtles.iterator();
		while (turtleIt.hasNext()){
			Turtle currTurtle = turtleIt.next();
			while (currTurtle.getFramesLeft()>1)
				currTurtle.setFramesLeft(currTurtle.getFramesLeft()-1);
		}
		assertEquals(false, b1.checkLost());
		Iterator<Turtle> turtleIt2 = b1.turtles.iterator();
		while (turtleIt2.hasNext()){
			Turtle currTurtle = turtleIt2.next();
			while (currTurtle.getFramesLeft()>0)
				currTurtle.setFramesLeft(currTurtle.getFramesLeft()-1);
		}
		assertEquals(true, b1.checkLost());
		
		b1.setIsShoreDestroyed(true);
		assertEquals(true, b1.checkLost());
	}
	
	@Test
	public void testCreateBoat(){
		b1.elapsedTime = 20;
		for (int i = 0; i < 1000; i++)
			b1.createBoat();
		assertNotNull(b1.currBoats);
		
		b1.elapsedTime = 17;
		b1.createBoat();
		assertNotNull(b1.currBoats);
	}
	
	@Test
	public void testCheckBoats(){
		b1.currBoats.add(new Sailboat());
		Iterator<Boat> boatsIt = b1.currBoats.iterator();
		while (boatsIt.hasNext()){
			Boat boat = boatsIt.next();
			boat.xloc = -1;
		}
		b1.checkBoats();
		assertEquals(0, b1.currBoats.size());
		
		b1.currBoats.add(new Sailboat());
		Iterator<Boat> boatsIt2 = b1.currBoats.iterator();
		while (boatsIt2.hasNext()){
			Boat boat = boatsIt2.next();
			boat.xloc = 50;
		}
		b1.checkBoats();
		assertEquals(1, b1.currBoats.size());
		
		b1.currBoats.add(new Sailboat());
		Iterator<Boat> boatsIt3 = b1.currBoats.iterator();
		while (boatsIt3.hasNext()){
			Boat boat = boatsIt3.next();
			boat.xloc = b1.WIDTH+1;
		}
		b1.checkBoats();
		assertEquals(0, b1.currBoats.size());
	}
	
	@Test
	public void testCreateTurtle(){
		b1.createTurtle();
		assertEquals(1,b1.turtles.size());
	}
	
	@Test
	public void testSplitWave(){
		Wave wave = new Wave(10, 200, 350, b1.SHORE_HEIGHT+30);
		b1.beach[0][4] = b1.SHORE;
		b1.beach[0][5] = b1.WATER;
		b1.beach[0][6] = b1.SHORE;
		b1.splitWave(wave);
		assertEquals(3, b1.getSplitWaves().size());
		
		b1.beach[0][4] = b1.WATER;
		b1.splitWave(new Wave(10, 200, 350, b1.SHORE_HEIGHT+30));
		assertEquals(5, b1.getSplitWaves().size());
		
		b1.splitWave(new Wave(10, 200, b1.SHORE_WIDTH-100, b1.SHORE_HEIGHT+30));
		assertEquals(5, b1.getSplitWaves().size());
	}
	
	@Test
	public void testWaveHit(){
		Wave wave = new Wave(10, 200, b1.SHORE_WIDTH-100, b1.SHORE_HEIGHT+30);
		b1.beach[0][3] = b1.GABION_2L;
		b1.beach[0][4] = b1.GRASS_L;
		b1.beach[1][3] = b1.SHORE;
		b1.beach[1][4] = b1.WALL;
		//b1.beach[2][4] = b1.WALL;
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		b1.waveHit(1111, -1000);
		
		Wave wave2 = new Wave(10, 40, 300, b1.SHORE_HEIGHT+10);
		b1.waveHit(wave2.getX(),wave2.getX()+wave2.getLength());
		assertEquals(Board.GABION_L, b1.beach[0][3]);
		assertEquals(Board.GRASS, b1.beach[0][4]);
		
		b1.waveHit(wave2.getX(),wave2.getX()+wave2.getLength());
		assertEquals(Board.GABION, b1.beach[0][3]);
		assertEquals(Board.SHORE, b1.beach[0][4]);
		
		b1.waveHit(wave2.getX(),wave2.getX()+wave2.getLength());
		assertEquals(Board.SHORE, b1.beach[0][3]);
		assertEquals(Board.WATER, b1.beach[0][4]);
		
		b1.waveHit(wave2.getX(),wave2.getX()+wave2.getLength());
		assertEquals(Board.WATER, b1.beach[0][3]);
		assertEquals(Board.SHORE, b1.beach[1][4]);
		
		b1.beach[1][4]= Board.WATER;
		b1.waveHit(wave2.getX(),wave2.getX()+wave2.getLength());
		assertTrue(b1.getIsShoreDestroyed());
	}
	
	@Test
	public void testIsInOcean(){ //method called in waveHit
		//depth = 0
		b1.user.setyLoc(200);
		b1.beach[0][4]=b1.SHORE;
		Wave wave = new Wave(10, 20, 350, b1.SHORE_HEIGHT+30);
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		assertFalse(b1.user.isInOcean);
		
		b1.user.setyLoc(320);
		b1.beach[0][4]=b1.SHORE;
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		assertTrue(b1.user.isInOcean);
		
		b1.user.isInOcean = false;
		b1.user.setyLoc(400);
		b1.beach[0][4]=b1.SHORE;
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		assertFalse(b1.user.isInOcean);
		
		b1.user.setyLoc(251);
		b1.beach[0][4]=b1.SHORE;
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		assertTrue(b1.user.isInOcean);
		
		//depth = 1
		b1.user.isInOcean = false;
		b1.user.setyLoc(400);
		b1.beach[0][4]=b1.WATER;
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		assertTrue(b1.user.isInOcean);
		
		b1.user.isInOcean = false;
		b1.user.setyLoc(325);
		b1.beach[0][4]=b1.WATER;
		b1.beach[1][4]=b1.SHORE;
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		assertFalse(b1.user.isInOcean);
		
		b1.user.setyLoc(500);
		b1.beach[0][4]=Board.WATER;
		b1.beach[1][4]=Board.SHORE;
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		assertFalse(b1.user.isInOcean);
		
		b1.user.setyLoc(345);
		b1.beach[0][4]=Board.WATER;
		b1.beach[1][4]=Board.SHORE;
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		assertTrue(b1.user.isInOcean);
		
		b1.user.isInOcean = true;
		b1.beach[0][4]=Board.WATER;
		b1.beach[1][4]=Board.SHORE;
		b1.waveHit(wave.getX(), wave.getX()+wave.getLength());
		assertTrue(b1.user.isInOcean);
	}
	
	@Test
	public void testChooseProtector(){
		b1.user.setyLoc(370);
		b1.user.setxLoc(400);
		assertEquals(-1, b1.chooseProtector());
		
		b1.user.setxLoc(950);
		assertEquals(Board.GRASS_L, b1.chooseProtector());
		
		b1.user.setyLoc(400);
		assertEquals(Board.GABION_2L, b1.chooseProtector());
		
		b1.user.setyLoc(500);
		assertEquals(Board.WALL, b1.chooseProtector());
	}
	
	@Test
	public void testPlaceProtector(){
		b1.user.setxLoc(950);
		b1.user.setyLoc(400); //gabion
		b1.chooseProtector();
		b1.user.setxLoc(370);
		b1.placeProtector();
		assertEquals(Board.GABION_2L, b1.beach[0][4]);
		
		b1.user.setxLoc(950);
		b1.user.setyLoc(400); //gabion
		b1.chooseProtector();
		b1.user.setxLoc(370);
		b1.placeProtector();
		assertEquals(Board.GABION_2L, b1.beach[1][4]);
		
		
		b1.user.setxLoc(370);
		b1.placeProtector();
		assertEquals(-1, b1.getProtector());
	}
	
	//@Test
	//public void testIsShore(){
	//	assertFalse(b1.isShore(-1, 350));
	//	assertTrue(b1.isShore(370, 350));
	//	b1.beach[0][4] = Board.WATER;
	//	assertFalse(b1.isShore(370, 350));
	//	assertFalse(b1.isShore(Board.SHORE_WIDTH+1, 350));
	//	assertFalse(b1.isShore(370, 200));
	//	assertFalse(b1.isShore(370, Board.HEIGHT+100));
	//}
	
	@Test
	public void testGetWidth(){
		assertEquals(Board.WIDTH, b1.getWidth());
	}
	
	@Test
	public void testGetHeight(){
		assertEquals(Board.HEIGHT, b1.getHeight());
	}
	
	@Test
	public void testGetCurrTurtles(){
		assertEquals(0, b1.getCurrTurtles().size());
	}
	
	@Test
	public void testSetTurtleDie(){
		b1.setTurtleDie(true);
		assertTrue(b1.checkLost());
	}
}
