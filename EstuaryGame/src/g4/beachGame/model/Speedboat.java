package g4.beachGame.model;

import java.util.Random;

public class Speedboat extends Boat{
	
	final int SPEEDBOAT_SPEED=20;

	public Speedboat(){
		int randomDir = 1+ (int) (Math.random() * 2);
		int randomHeight = (int) (Math.random()*Board.shoreline-100);
		if (randomDir==1){
			this.xloc=0;
			this.direction=true;
		}
		else{
			this.xloc=Board.WIDTH; 
			this.direction=false;
		}
		this.hasEmittedWave=false;
		this.speed=SPEEDBOAT_SPEED;
	}
}
