package g4.storyGame.model;

import g4.storyGame.controller.StoryCont;

public class Cube {

	//Used to allow each cube to change image randomly independent from any other cube
	protected final int RAND_ID;
	
	private int imageNum;
	
	public Cube(){
		RAND_ID = StoryCont.NUM_SIDES + (int)(200*Math.random());
		imageNum = RAND_ID % StoryCont.NUM_SIDES;
	}
	
	protected void changeImg(){
		imageNum = (int)(RAND_ID*Math.random()) % StoryCont.NUM_SIDES;
	}
	
	public int getImg(){
		return imageNum;
	}
}
