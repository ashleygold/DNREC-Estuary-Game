package g4.beachGame.model;

import java.util.Random;

public class Speedboat extends Boat{
	
	final int SPEEDBOAT_SPEED=30;

	public Speedboat(){
		int randomDir = 1+ (int) (Math.random() * 1);
		int randomHeight = (int) (Math.random()*Board.shoreline);
		if (randomDir==1)
			this.xloc=0;
		else
			this.xloc=randomDir; 
		this.yloc=randomHeight;
		this.hasEmittedWave=false;
		this.speed=SPEEDBOAT_SPEED;
	}
<<<<<<< HEAD
	
=======
>>>>>>> b87327dfb7c3d4af88baa6a7d39bf12cb8b37a38
}
