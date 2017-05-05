package g4.beachGame.model;

public class Wave {
	int damage;
	int length;
	int direction;
	int speed;
	int xloc;
	int yloc;
	public int left;
	public int right;
	 
	final int CRUISELINER_LENGTH = 200;
	final int SAILBOAT_LENGTH = 80;
	final int SPEEDBOAT_LENGTH = 40;
	
	final int CRUISELINER_DAMAGE = 3;
	final int SAILBOAT_DAMAGE=2; 
	final int SPEEDBOAT_DAMAGE=1; 
	
	final int RIGHT=0;
	final int LEFT=2; 
	final int FORWARD=1;
	
	final int BOAT_SPEED = 1;
	final int CRUISELINER_SPEED=2; 
	
	final int WAVE_MOVEMENT_W_WIND =5; 
	
	
	
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
	
	public Wave(int speed, int length, int x, int y){
		this.damage=3;
		this.length=length;
		this.speed=speed;
		this.xloc=x;
		this.yloc=y;
	}
	
	public void move(){
		this.yloc += speed;
		if (direction == LEFT && (this.xloc>=WAVE_MOVEMENT_W_WIND)){
			this.xloc-=WAVE_MOVEMENT_W_WIND;
		}
		else if (direction == RIGHT && this.xloc<=Board.SHORELINE_WIDTH -WAVE_MOVEMENT_W_WIND){
			this.xloc+=WAVE_MOVEMENT_W_WIND; 
		}
	}
	
	public void activateWind(){
		int randomDir = 1+ (int) (Math.random() * 2);
		if (randomDir==1)
			this.direction=RIGHT;
		else
			this.direction=LEFT;
	}
	
	public void ceaseWind(){
		this.direction=FORWARD;
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
	
	public int getLeft(){return left;}
	public int getRight(){return right;}
}
