package g4.beachGame.model;

public class Speedboat extends Boat{
	
	/** height of the boat*/
	final int HEIGHT=100;
	
	final int SPEEDBOAT_SPEED=20;
	final int MIN_DISTANCE=100; //minimum distance from starting shoreline
	/**
	 * constructor a speedboat
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
