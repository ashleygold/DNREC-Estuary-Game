package g4.storyGame.controller;

import g4.mainController.MiniGameController;
import g4.storyGame.model.Table;
import g4.storyGame.view.StoryView;

public class StoryCont implements MiniGameController{

	/** The model for the story game, holds cubes */
	private Table stTable = new Table();
	
	/** the view for the story game */
	private StoryView stView = new StoryView(stTable);

	/**
	 * Updates the model and the view, called once per tick
	 */
	@Override
	public void update(){
		//update the model and the view
		stTable.update();
		stView.update();
	}

	/**
	 * Safely disposes of the view when the game is over
	 */
	@Override
	public void dispose() {
		//safely close the view
		stView.dispose();
	}
	
}
