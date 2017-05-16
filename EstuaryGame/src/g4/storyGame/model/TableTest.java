package g4.storyGame.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TableTest {
	
	Table table = new Table();
	Cube cube = new Cube();
	
	@Test
	public void testGetActiveCubeAt(){
		cube = table.getCubeAt(2, true);
		assertEquals(cube, table.getDice().get(2));
	}
	
	@Test
	public void testActivateCubePass(){
		table.activateCube(1);
		assertTrue(table.getDice().get(1).isFixed());
		table.activateCube(1);
		assertTrue(table.getDice().get(1).isMoved());
	}
	
	@Test
	public void testAreAllMovedFalse(){
		assertEquals(table.areAllMoved(), false);
	}
	
	@Test
	public void testAreAllMovedTrue(){
		for(int i=0; i<table.getDice().size(); i++){
			table.activateCube(i);
			table.activateCube(i);
		}
		assertTrue(table.areAllMoved());
	}
	
	@Test
	public void testGetInactiveCubeAt(){
		for(int i=0; i<table.getDice().size(); i++){
			table.activateCube(i);
			table.activateCube(i);
		}
		Cube cube2 = table.getCubeAt(0, false);
		assertEquals(cube2, table.getDice().get(0));
	}

	@Test
	public void testAreAllFixed(){
		Table table2 = new Table();
		assertTrue(!table2.areAllFixed());
		for (Cube x : table2.getDice())
			x.fix();
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
		for (Cube x : table.getDice()){
			int img = x.getImg();
			while (img == x.getImg())
				x.changeImg();
			int img2 = x.getImg();
			assertTrue(img != img2);
		}
	}
}
