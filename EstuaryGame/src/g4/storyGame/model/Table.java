package g4.storyGame.model;

import java.util.ArrayList;
import java.util.List;

public class Table {

	/** list of active Cubes on the table*/
	private List<Cube> dice = new ArrayList<Cube>();
	
	/** list of finished Cubes on the table */
	private List<Cube> finished = new ArrayList<Cube>();
	
	/** Total quantity of cubes */
	public final int NUM_DICE; 
	
	/** maximum allowed of number of cubes */ 
	private static final int MAX_DICE = 7;
	/** minimum allowed of number of cubes */ 
	private static final int MIN_DICE = 4;
	
	/**
	 * Adds a random number of Cubes to the table
	 */
	public Table(){
		//put a random number of Cubes in dice
		NUM_DICE = MIN_DICE + (int)((1 + MAX_DICE - MIN_DICE)*Math.random());
		for(int i = 0; i< NUM_DICE; i++)
			dice.add(new Cube());
	}
	
	/**
	 * update the images in all the Cubes
	 */
	public void update(){
		for (Cube x : dice)
			x.changeImg();
	}
	
	/**
	 * retrieve a Cube at a specified location
	 * @param i the index of the Cube that should be retrieved
	 * @param active gets Cube from dice if true, gets Cube from finished if false
	 * @return the Cube at location i
	 */
	public Cube getCubeAt(int i, boolean active){
		if (active)
			return dice.get(i);
		else
			return finished.get(i);
	}
	
	/**
	 * fix or move a Cube at a specified location that has been interacted with
	 * @param i the index of the Cube that has been interacted with
	 */
	public void activateCube(int i){
		if (!dice.get(i).isFixed())
			//if not fixed
			dice.get(i).fix();
		else if (!dice.get(i).isMoved()){
			//if not moved
			dice.get(i).move();
			finished.add(dice.get(i));
		}
	}
	
	/**
	 * checks if all cubes have been moved yet
	 * @return true if all cubes have been moved, false otherwise
	 */
	public boolean areAllMoved(){
		return dice.size() == finished.size();
	}
	
	public int getFinishedSize(){
		return finished.size();
	}
}
