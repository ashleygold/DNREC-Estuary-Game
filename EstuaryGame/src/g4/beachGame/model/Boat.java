package g4.beachGame.model;


public abstract class Boat{
	/** x location of the boat **/
	int xloc;
	
	/**y location of the boat */
	int yloc; 
	
	/**The speed of a boat*/
	int speed;
	
	/**The direction of a boat. true = right, false = left */
	boolean direction; //true=right, false=left;
	
	/**if the boat has emitted a wave or not */
	boolean hasEmittedWave;
	
	/**the horizontal location that the wave is emitted*/
	int waveLocation = (int)(Math.random() * (Board.SHORE_WIDTH)-200);

	/** Moves the boat across the screen*/
	public void move() {
		if (direction)
			xloc+=speed; 
		else 
			xloc-=speed;
	}
	
	/** is called when the boat has emitted a wave to change hasEmittedWave field */
	public void emittedWave(){
		hasEmittedWave = true;
	}
	
	//getter for X Location of boat
	public int getXLoc(){
		return this.xloc;
	}
	
	//getter for Y Location of boat
	public int getYLoc(){
		return this.yloc;
	}

	public boolean getHasEmittedWave(){
		return hasEmittedWave;
	}
	
	public int getWaveLocation(){
		return waveLocation;
	}
	
	public boolean getDirection(){
		return direction;
	}
}
