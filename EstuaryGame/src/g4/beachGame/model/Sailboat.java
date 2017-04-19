package g4.beachGame.model;

public class Sailboat extends Boat{
	
	final int SAILBOAT_SPEED =5;

	/*constructor for a Sailboat*/
	public Sailboat(){
		int randomDir = 1+ (int) (Math.random() * 1);
		int randomHeight = (int) (Math.random()*Board.shoreline-100);
		if (randomDir==1)
			this.xloc=0;
		else
			this.xloc=randomDir; 
		this.yloc=randomHeight;
		this.hasEmittedWave=false;
		this.speed=SAILBOAT_SPEED;
	}


}
