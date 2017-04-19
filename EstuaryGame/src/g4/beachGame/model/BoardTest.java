package g4.beachGame.model;
import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

Board b1 = new Board();

	@Test
	public void testCheckWaves() {
		b1.createWave(new Sailboat());
		assertEquals(1, b1.getCurrWaves().size());
	}

}
