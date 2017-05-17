package g4.beachGame.model;

public class CruiseLiner extends Boat{
	
	/**the speed of the cruiseliner*/
	final int CRUISELINER_SPEED=10;
	
	/**the height of the boat image or how much of the ocean the boat takes up*/
	final int BOAT_HEIGHT = 100;
	
	/**the distance away from shore the closest boat can spawn*/
	final int MIN_DISTANCE_FROM_SHORE = 400;
	
	/** constructor for a new CruiseLiner*/
	public CruiseLiner(){
		int randomDir = 1+ (int) (Math.random() * 2);
		int randomHeight = 100 + (int) (Math.random()*(Board.SHORE_HEIGHT-MIN_DISTANCE_FROM_SHORE));
		if (randomDir==1){
			this.xloc=0;
			this.direction=true;
		}
		else{
			this.xloc=Board.getWidth(); 
			this.direction=false;
		}
		this.yloc=randomHeight;
		this.hasEmittedWave=false;
		this.speed=CRUISELINER_SPEED;
	}
	
	
}
