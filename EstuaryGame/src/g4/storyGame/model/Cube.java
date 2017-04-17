package g4.storyGame.model;

import g4.storyGame.view.StoryView;

public class Cube {

	//Used to allow each cube to change image randomly independent from any other cube
	protected final int RAND_ID;
	
	//Current side of the cube
	private int imageNum;
	
	//control location/finalization of cubes
	private boolean fixed = false;
	private boolean moved = false;
	
	public Cube(){
		RAND_ID = StoryView.NUM_SIDES + (int)(200*Math.random());
		imageNum = RAND_ID % StoryView.NUM_SIDES;
	}
	
	protected void changeImg(){
		if (!fixed)
			imageNum = (int)(RAND_ID*Math.random()) % StoryView.NUM_SIDES;
	}
	
	public int getImg(){
		return imageNum;
	}
	
	public void fix(){
		fixed = true;
	}
	
	public void move(){
		moved = true;
	}
	
	public boolean isFixed(){
		return fixed;
	}
	
	public boolean isMoved(){
		return moved;
	}
}
