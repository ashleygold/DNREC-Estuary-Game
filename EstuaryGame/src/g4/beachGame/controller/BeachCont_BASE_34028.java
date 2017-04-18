package g4.beachGame.controller;

import g4.mainController.MiniGameController;

public class BeachCont implements MiniGameController {
	
	final static double NANOSECOND_PER_SECOND=1000000000.0;
	final static long START_TIME= System.nanoTime(); 
	static long currTime;
	

	/*returns how much time has elapsed in the game in seconds*/
	public double getElapsedTime(){
		currTime=System.nanoTime();
		long elapsedTime = currTime-START_TIME;
		return (double) elapsedTime / NANOSECOND_PER_SECOND;
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
