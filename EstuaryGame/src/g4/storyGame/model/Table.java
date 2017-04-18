package g4.storyGame.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

	//list of Cubes
	private List<Cube> dice = new ArrayList<Cube>();
	
	//Quantity of cubes
	public final int NUM_DICE; 
	
	//Allowed range of number of cubes 
	private static final int MAX_DICE = 7;
	private static final int MIN_DICE = 4;
	
	public Table(){
		//put a random number of Cubes in dice
		NUM_DICE = MIN_DICE + (int)((1 + MAX_DICE - MIN_DICE)*Math.random());
		for(int i = 0; i< NUM_DICE; i++)
			dice.add(new Cube());
	}
	
	//change the images in all the Cubes
	public void update(){
		for (Cube x : dice)
			x.changeImg();
	}
	
	//retrieve a Cube
	public Cube getCubeAt(int i){
		return dice.get(i);
	}
}
