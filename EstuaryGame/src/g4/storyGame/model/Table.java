package g4.storyGame.model;

//Imported solely for use of constant NUM_SIDES
import g4.storyGame.view.StoryView;
import java.util.ArrayList;
import java.util.List;

public class Table {

	/** list of active Cubes on the table*/
	private List<Cube> dice = new ArrayList<Cube>();
	
	/** list of finished Cubes on the table */
	private List<Cube> finished = new ArrayList<Cube>();
	
	/** array which determines which images are being displayed by cubes */
	private byte[] imgsInUse = new byte[StoryView.NUM_SIDES];
	
	/**	code used for imgsInUse array to signal that an image is unused */
	private final byte FREE = 0;
	
	/**	code used for imgsInUse array to signal that an image is currently in use */
	private final byte IN_USE = 1;
	
	/**	code used for imgsInUse array to signal that an image has been used finally */
	private final byte FIXED = 2;
	
	/** Total quantity of cubes */
	public final int NUM_DICE; 
	
	/** maximum allowed of number of cubes */ 
	private static final int MAX_DICE = 9;
	
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
	public void update() {
		//reset the used images array
		for (int i = 0; i < imgsInUse.length; i++){
			if (imgsInUse[i] == IN_USE)
				imgsInUse[i] = FREE;
		}
		
		//change to an unused image for each cube that isn't fixed
		for (Cube x : dice) {
			if (!x.getIsFixed()) {
				do {
					x.changeImg();
				} while (imgsInUse[x.getImg()] != FREE);
				imgsInUse[x.getImg()] = IN_USE;
			}
		}
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
		if (!dice.get(i).getIsFixed()) {
			//if not fixed
			dice.get(i).fix();
			imgsInUse[dice.get(i).getImg()] = FIXED;
		} else if (!dice.get(i).getIsMoved()){
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
	
	/**
	 * checks if all cubes have been fixed yet
	 * @return true if all cubes have been fixed, false otherwise
	 */
	public boolean areAllFixed(){
		for (Cube x : dice){
			if (!x.getIsFixed())
				return false;
		}
		return true;
	}
	
	/**
	 * gets the number of active cubes
	 * @return the size of the active cubes arrayList (dice)
	 */
	protected int getActiveSize() {
		return dice.size();
	}
	
	/**
	 * gets the number of finished cubes
	 * @return the size of the finished cubes arrayList
	 */
	public int getFinishedSize() {
		return finished.size();
	}


}
