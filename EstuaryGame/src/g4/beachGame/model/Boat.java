package g4.beachGame.model;


public abstract class Boat{
	/** x and y location of the boat **/
	int xloc;
	int yloc; 
	
	/** the speed the boat is traveling*/
	int speed;
	/** the direction that the boat is traveling*/
	boolean direction; //true=right, false=left;
	
	/**whether or not the boat as emitted a wave*/
	boolean hasEmittedWave;
	
	/**where along the x axis the boat will emit the wave*/
	int waveLocation = (int)(Math.random() * (Board.SHORE_WIDTH)-200);

	/**
	 * moves the boat across the screen
	 */
	public void move() {
		if (direction)
			xloc+=speed; 
		else 
			xloc-=speed;
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
	
	public void emittedWave(){
		hasEmittedWave = true;
	}
	
	public boolean getDirection(){
		return direction;
	}
}
