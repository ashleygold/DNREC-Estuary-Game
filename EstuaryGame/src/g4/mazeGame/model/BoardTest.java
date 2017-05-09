package g4.mazeGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTest {

	@Test
	public void testEatFood() {
		Board board = new Board(3,0);
		assertEquals(board.getGoalFood(),5);
		Board board2 = new Board(2,0);
		assertEquals(board2.getGoalFood(),7);
		Board board3 = new Board(1,0);
		assertEquals(board3.getGoalFood(),10);
	}

}
