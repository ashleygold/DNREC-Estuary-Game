package g4.beachGame.model;

public class Sailboat extends Boat{
	/**height of the boat*/
	final int BOAT_HEIGHT = 100; //so that the boat doesn't swim off the board
	/**speed of the sailbaot*/
	final int SAILBOAT_SPEED =5;

	/** constructor for a Sailboat*/
	public Sailboat(){
		int randomDir = 1+ (int) (Math.random() * 2);
		int randomHeight = BOAT_HEIGHT + (int) (Math.random()*(Board.SHORE_HEIGHT-Board.RAISE)-BOAT_HEIGHT);
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
		this.speed=SAILBOAT_SPEED;
		System.out.println("Boat Height:" +yloc);
	}

}
