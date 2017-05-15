package g4.beachGame.model;

public class Speedboat extends Boat{
	
	/** height of the boat*/
	final int HEIGHT=100;
	
	/**speed of the speedboat*/
	final int SPEEDBOAT_SPEED=20;
	
	/**minimum distance from starting shoreline*/
	final int MIN_DISTANCE=100; 
	
	/**
	 * constructor for a speedboat
	 */
	public Speedboat(){
		int randomDir = 1+ (int) (Math.random() * 2);
		int randomHeight = 100 + (int) (Math.random()*(Board.SHORE_HEIGHT-MIN_DISTANCE-HEIGHT));
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
