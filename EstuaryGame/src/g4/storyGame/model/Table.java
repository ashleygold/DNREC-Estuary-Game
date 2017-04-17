package g4.storyGame.model;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	protected List<Cube> dice = new ArrayList<Cube>();
	protected List<FixedCube> chosen = new ArrayList<FixedCube>();
	public final int NUM_DICE; 
	
	//Allowed range of number of cubes 
	protected static final int MAX_DICE = 8;
	protected static final int MIN_DICE = 5;
	
	public Table(){
		NUM_DICE = MIN_DICE + (int)((MAX_DICE - MIN_DICE)*Math.random());
		for(int i = 0; i< NUM_DICE; i++)
			dice.add(new Cube());
	}
	
	public void update(){
		dice.get((int)(dice.size()*Math.random())).changeImg();
	}
}
