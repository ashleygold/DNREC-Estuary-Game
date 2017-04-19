package g4.beachGame.model;

public class CruiseLiner extends Boat{
	
	final int CRUISELINER_SPEED=10;
	
	public CruiseLiner(){
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
		this.yloc=randomHeight;
		this.hasEmittedWave=false;
		this.speed=CRUISELINER_SPEED;
	}
	
	
}
