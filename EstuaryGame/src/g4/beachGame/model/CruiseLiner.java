package g4.beachGame.model;

public class CruiseLiner extends Boat{
	
	final int CRUISELINER_SPEED=10;
	
	public CruiseLiner(){
		int randomDir = 1+ (int) (Math.random() * 1);
		int randomHeight = (int) (Math.random()*Board.shoreline-100);
		if (randomDir==1)
			this.xloc=0;
		else
			this.xloc=randomDir; 
		this.yloc=randomHeight;
		this.hasEmittedWave=false;
		this.speed=CRUISELINER_SPEED;
	}
	
	
}
