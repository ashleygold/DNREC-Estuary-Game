package g4.beachGame.model;


public abstract class Boat{
	/** x location of the boat **/
	protected int xloc;

	/**y location of the boat */
	protected int yloc; 
	
	/**The speed of a boat*/
	protected int speed;
	
	/**The direction of a boat. true = right, false = left */
	protected boolean direction; //true=right, false=left;
	
	/**if the boat has emitted a wave or not */
	protected boolean hasEmittedWave;
	
	/**the horizontal location that the wave is emitted*/
	private int waveLocation = (int)(Math.random() * (Board.SHORE_WIDTH)-200);

	/** Moves the boat across the screen*/
	public void move() {
		if (direction)
			xloc+=speed; 
		else 
			xloc-=speed;
	}
	
	/** is called when the boat has emitted a wave to change hasEmittedWave field */
	protected void emittedWave(){
		hasEmittedWave = true;
	}
	
	//getter for X Location of boat
	public int getXLoc(){
		return xloc;
	}
	
	//getter for Y Location of boat
	public int getYLoc(){
		return yloc;
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
