package g4.beachGame.model;

import g4.beachGame.controller.BeachCont;

public class Wave {
	int damage;
	int length;
	static int direction = 0;
	int speed;
	int xloc;
	int yloc;
	 
	final int CRUISELINER_LENGTH = 150;
	final int SAILBOAT_LENGTH = 80;
	final int SPEEDBOAT_LENGTH = 40;
	
	final int CRUISELINER_DAMAGE = 3;
	final int SAILBOAT_DAMAGE=2; 
	final int SPEEDBOAT_DAMAGE=1; 
	
	final static int RIGHT=1;
	final static int LEFT=-1; 
	final static int FORWARD=0;
	
	final int BOAT_SPEED = 1;
	final int CRUISELINER_SPEED=2; 
	
	final int WAVE_MOVEMENT_W_WIND =5; 
	
	private static int windFace = 0;
	
	/**
	 * Constructs a wave using the instance of boat to set damage, length, and speed
	 * @param boat - the boat which creates the wave
	 */
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
		//direction = FORWARD; 
		this.xloc=boat.getXLoc();
		this.yloc=boat.getYLoc(); 
		
		boat.emittedWave();
	}

	public Wave(int speed, int length, int x, int y){
		this.damage=3;
		this.length=length;
		this.speed=speed;
		this.xloc=x;
		this.yloc=y;
	}
	
	public void move(){
		if (direction == FORWARD){
			this.yloc += speed;
		}
		else if (direction == LEFT){
			this.xloc-=direction;
			if (BeachCont.frameCounterWind % 3 ==0)
				this.yloc+=speed;
		}
		else{
			this.xloc-=direction;
			if (BeachCont.frameCounterWind % 3 ==0)
				this.yloc+=speed;
		}
	}
	
	public boolean isOutOfRange(){
		if (direction == LEFT && this.getX()<1)
			return true;
		else if ((direction ==RIGHT||direction == FORWARD) && this.getX()+this.getLength()>Board.WIDTH)
			return true;
		else if (this.xloc>Board.SHORE_WIDTH && this.yloc>Board.HEIGHT/2)
			return true;
		else if (this.xloc<0)
			return true;
		return false;
	}
	
	public static void activateWind(int number) {
		if (direction == FORWARD) {
			int randomDir = number %2 ;
			if (randomDir == 1){
				direction = RIGHT;
				windFace = LEFT;
			}
			else{
				direction = LEFT;
				windFace = RIGHT;
			}
		}
	}
	
	public static void ceaseWind(){
		direction=FORWARD;
		windFace = FORWARD;
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
	
	public static int getDirection(){return direction;}
	
	public static int getWindFace(){return windFace;}
}
