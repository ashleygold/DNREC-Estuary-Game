package g4.storyGame.model;

//Imported solely for use of constant NUM_SIDES
import g4.storyGame.view.StoryView;

public class Cube {

	/** Used to allow each cube to change image randomly independent from any other cube */
	private final int RAND_ID;
	
	/** Current side of the cube */
	private int imageNum;
	
	/** control finalization of cube image */
	private boolean fixed = false;
	/** control location of cubes (movement to story row)*/
	private boolean moved = false;
	
	/**
	 * initializes with a random seed and random image
	 */
	public Cube() {
		RAND_ID = StoryView.NUM_SIDES + (int)(200*Math.random());
		imageNum = RAND_ID % StoryView.NUM_SIDES;
	}
	
	/**
	 * change to a new random image, if the image has not been set
	 */
	protected void changeImg(){
		if (!fixed)
			imageNum = (int)(RAND_ID*Math.random()) % StoryView.NUM_SIDES;
	}
	
	/**
	 * prevents the image from changing again
	 */
	public void fix(){
		fixed = true;
	}
	
	/**
	 * Sets the cube as having been moved to the story row
	 */
	public void move(){
		moved = true;
	}
	
	public int getImg(){
		return imageNum;
	}
	
	public boolean isFixed(){
		return fixed;
	}
	
	public boolean isMoved(){
		return moved;
	}
}
