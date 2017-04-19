package g4.beachGame.model;

import java.util.TimerTask;

public abstract class Boat{
	
	int xloc; 
	int yloc; 
	boolean hasEmittedWave;
	int waveLocation = 0 + (int)(Math.random() * (Board.WIDTH + 1));


	
	//getter for X Location of boat
	public int getXLoc(){
		return this.xloc;
	}
	
	//getter for Y Location of boat
	public int getYLoc(){
		return this.yloc;
	}
	
}
