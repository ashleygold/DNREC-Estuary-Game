package g4.storyGame.controller;

import g4.mainController.MiniGameController;
import g4.storyGame.model.Table;
import g4.storyGame.view.StoryView;

public class StoryCont implements MiniGameController{

	
	private Table stTable = new Table();
	private StoryView stView = new StoryView(stTable);
	
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
