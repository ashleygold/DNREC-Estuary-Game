package g4.beachGame.model;

public class Sailboat extends Boat{
	/**height of the boat*/
	private final int BOAT_HEIGHT = 100; //so that the boat doesn't swim off the board
	
	/**speed of the sailboat*/
	private final int SAILBOAT_SPEED =5;

	/** constructor for a Sailboat*/
	public Sailboat(){
		int randomDir = 1+ (int) (Math.random() * 2);
		int randomHeight = BOAT_HEIGHT + (int) (Math.random()*(Board.SHORE_HEIGHT-200)-BOAT_HEIGHT);
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
		this.speed=SAILBOAT_SPEED;
	}

}
