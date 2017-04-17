package g4.storyGame.controller;

import javax.swing.JFrame;

import g4.mainController.MiniGameController;
import g4.storyGame.model.Table;
import g4.storyGame.view.StoryView;

public class StoryCont implements MiniGameController{

	//The number of possible sides of cubes (total number of images)
	public static final int NUM_SIDES = 2;
	// (this is in the controller since it is required by both Cubes 
	//   and StoryView)
	
	protected Table stTable = new Table();
	protected StoryView stView = new StoryView(stTable);
	
	public StoryCont(){
		
	}
	
	@Override
	public void update(){
		stTable.update();
		stView.update();
	}

	@Override
	public void dispose() {
		stView.dispose();
	}
	
}
