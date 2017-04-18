package g4.beachGame.controller;

import g4.beachGame.model.Board;
import g4.beachGame.view.BeachView;
import g4.mainController.MiniGameController;

public class BeachCont implements MiniGameController{
	final static double NANOSECOND_PER_SECOND=1000000000.0;
	final static long START_TIME= System.nanoTime(); 
	double elapsedTime;
	private BeachView bView = new BeachView();
	Board b1;
	
	
	public BeachCont(){
		elapsedTime=0.0;
		b1 = new Board(); 
	}	
	
	/*returns how much time has elapsed in the game in seconds*/
	public double updateElapsedTime(){
		long currTime=System.nanoTime();
		this.elapsedTime = (currTime-START_TIME)/NANOSECOND_PER_SECOND;
		return this.elapsedTime;
	}
	
	//public void 


	@Override
	public void update() {
		updateElapsedTime();
		

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
