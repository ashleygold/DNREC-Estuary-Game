package g4.storyGame.controller;

import g4.mainController.MiniGameController;
import g4.storyGame.model.Table;
import g4.storyGame.view.StoryView;

public class StoryCont implements MiniGameController{

	//model and view
	private Table stTable = new Table();
	private StoryView stView = new StoryView(stTable);

	@Override
	public void update(){
		//update the model and the view
		stTable.update();
		stView.update();
	}

	@Override
	public void dispose() {
		//safely close the view
		stView.dispose();
	}
	
}
