package g4.beachGame.model;



public abstract class Boat{
	
	int xloc; 
	int yloc; 
	int speed;
	boolean direction; //true=right, false=left;
	boolean hasEmittedWave;
	int waveLocation = (int)(Math.random() * (Board.WIDTH));

	
	public void move() {
		if (direction)
			xloc-=speed; 
		else 
			xloc+=speed;
	}
	
	//getter for X Location of boat
	public int getXLoc(){
		return this.xloc;
	}
	
	//getter for Y Location of boat
	public int getYLoc(){
		return this.yloc;
	}
	
	
}
