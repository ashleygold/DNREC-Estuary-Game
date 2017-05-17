package g4.storyGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableTest {
	
	Table table = new Table();
	Cube cube = new Cube();
	
	@Test
	public void testActivateCubePass(){
		assertTrue(!table.getCubeAt(1, true).getIsFixed());
		table.activateCube(1);
		assertTrue(table.getCubeAt(1, true).getIsFixed());
		assertTrue(!table.getCubeAt(1, true).getIsMoved());
		table.activateCube(1);
		assertTrue(table.getCubeAt(1, true).getIsMoved());
	}
	
	@Test
	public void testAreAllMovedFalse(){
		assertEquals(table.areAllMoved(), false);
	}
	
	@Test
	public void testAreAllMovedTrue(){
		for(int i=0; i<table.getActiveSize(); i++){
			table.activateCube(i);
			table.activateCube(i);
		}
		assertTrue(table.areAllMoved());
	}
	
	@Test
	public void testGetInactiveCubeAt(){
		for(int i=0; i < table.getActiveSize(); i++){
			table.activateCube(i);
			table.activateCube(i);
		}
		Cube cube2 = table.getCubeAt(0, false);
		assertEquals(cube2, table.getCubeAt(0, false));
	}

	@Test
	public void testAreAllFixed(){
		Table table2 = new Table();
		assertTrue(!table2.areAllFixed());
		for (int i = 0; i < table2.getActiveSize(); i++)
			table2.getCubeAt(i, true).fix();
		assertTrue(table2.areAllFixed());
	}
	
	@Test
	public void testgetFinishedSize(){
		Table table2 = new Table();
		assertEquals(table2.getFinishedSize(), 0);
	}
	
	@Test
	public void testUpdate(){
		table.update();
		table.update();
		for (int i = 0; i < table.getActiveSize(); i++){
			Cube x = table.getCubeAt(i, true);
			int img = x.getImg();
			while (img == x.getImg())
				x.changeImg();
			int img2 = x.getImg();
			assertTrue(img != img2);
		}
	}
}
