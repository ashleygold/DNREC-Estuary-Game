package g4.beachGame.model;

public class Wave {
	int damage;
	int length;
	int direction;
	int speed;
	int xloc;
	int yloc;
	 
	final int CRUISELINER_LENGTH =10;
	final int SAILBOAT_LENGTH=4;
	final int SPEEDBOAT_LENGTH=2;
	
	final int CRUISELINER_DAMAGE = 3;
	final int SAILBOAT_DAMAGE=2; 
	final int SPEEDBOAT_DAMAGE=1; 
	
	final int RIGHT=0;
	final int LEFT=2; 
	final int FORWARD=1;
	
	final int BOAT_SPEED = 1;
	final int CRUISELINER_SPEED=2; 
	
	
	
	public Wave(Boat boat){
		if (boat instanceof CruiseLiner){
			this.damage = CRUISELINER_DAMAGE;
			this.length = CRUISELINER_LENGTH;
			this.speed= CRUISELINER_SPEED; 
		}
		else if (boat instanceof Sailboat){
			this.damage = SAILBOAT_DAMAGE;
			this.length = SAILBOAT_LENGTH;
			this.speed= BOAT_SPEED; 
		}
		else{
			this.damage = SPEEDBOAT_DAMAGE;
			this.length = SPEEDBOAT_LENGTH;
			this.speed= BOAT_SPEED; 
		}
		this.direction = FORWARD; 
		this.xloc=boat.getXLoc();
		this.yloc=boat.getYLoc(); 
		
		boat.emittedWave();
		
	}
	
	public void move(){
		this.yloc += speed;
	}
	
	public int getX(){
		return xloc;
	}
	
	public int getY(){
		return yloc;
	}
	
	public int getLength(){
		return length;
	}
}
