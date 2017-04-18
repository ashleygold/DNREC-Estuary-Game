package g4.beachGame.controller;

import g4.beachGame.view.BeachView;
import g4.mainController.MiniGameController;

public class BeachCont implements MiniGameController {
	
	final static double NANOSECOND_PER_SECOND=1000000000.0;
	final static long START_TIME= System.nanoTime(); 
<<<<<<< HEAD
	static double elapsedTime;
=======
	static long currTime;
	private BeachView bView = new BeachView();
>>>>>>> 0f291988d4330b1b5291928c6dad07957ba15653
	

	/*returns how much time has elapsed in the game in seconds*/
	public double getElapsedTime(){
		long currTime=System.nanoTime();
		elapsedTime = (currTime-START_TIME)/NANOSECOND_PER_SECOND;
		return elapsedTime;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
