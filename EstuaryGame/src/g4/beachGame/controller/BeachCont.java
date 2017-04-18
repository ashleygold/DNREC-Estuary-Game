package g4.beachGame.controller;

import g4.mainController.MiniGameController;

public class BeachCont implements MiniGameController{
	final static double NANOSECOND_PER_SECOND=1000000000.0;
	final static long START_TIME= System.nanoTime(); 
	double elapsedTime;
	
	public BeachCont(){
		elapsedTime=0.0;
	}
	
	/*returns how much time has elapsed in the game in seconds*/
	public double getElapsedTime(){
		long currTime=System.nanoTime();
		this.elapsedTime = (currTime-START_TIME)/NANOSECOND_PER_SECOND;
		return this.elapsedTime;
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
