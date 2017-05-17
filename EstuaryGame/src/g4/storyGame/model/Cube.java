package g4.storyGame.model;

//Imported solely for use of constant NUM_SIDES
import g4.storyGame.view.StoryView;

public class Cube {

	/** Used to allow each cube to change image randomly independent from any other cube */
	private final int RAND_ID;
	
	/** Current side of the cube */
	private int imageNum;
	
	/** control finalization of cube image */
	private boolean isFixed = false;
	
	/** control location of cubes (movement to story row)*/
	private boolean isMoved = false;
	
	/**
	 * initializes with a random seed and random image
	 */
	public Cube() {
		RAND_ID = StoryView.NUM_SIDES + (int)(200*Math.random());
		changeImg();
	}
	
	/**
	 * change to a new random image, if the image has not been finalized yet
	 */
	protected void changeImg(){
		if (!isFixed)
			imageNum = (int)(RAND_ID*Math.random()) % StoryView.NUM_SIDES;
	}
	
	/**
	 * prevents the image from changing again
	 */
	protected void fix(){
		isFixed = true;
	}
	
	/**
	 * Sets the cube as having been moved to the story row
	 */
	protected void move(){
		isMoved = true;
	}
	
	public int getImg(){
		return imageNum;
	}
	
	protected boolean getIsFixed(){
		return isFixed;
	}
	
	public boolean getIsMoved(){
		return isMoved;
	}
}
