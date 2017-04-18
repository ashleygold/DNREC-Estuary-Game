package g4.storyGame.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	private List<Cube> dice = new ArrayList<Cube>();
	public final int NUM_DICE; 
	
	//Allowed range of number of cubes 
	private static final int MAX_DICE = 8;
	private static final int MIN_DICE = 5;
	
	public Table(){
		NUM_DICE = MIN_DICE + (int)((MAX_DICE - MIN_DICE)*Math.random());
		for(int i = 0; i< NUM_DICE; i++)
			dice.add(new Cube());
	}
	
	public void update(){
		for (Cube x : dice)
			x.changeImg();
	}
	
	public Cube getCubeAt(int i){
		return dice.get(i);
	}
}
