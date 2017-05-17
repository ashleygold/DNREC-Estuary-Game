package g4.beachGame.model;

import java.util.ArrayList;
import java.util.Iterator;

import g4.beachGame.controller.BeachCont;

public class Wave {
	/**length of wave */
	private int length;
	
	/**direction of wave*/
	private static int direction = 0;
	
	/**speed of wave*/
	protected int speed;
	
	/** x location of the wave*/
	protected int xloc;
	
	/**y location of the wave */
	protected int yloc;
	 
	/**length of a cruise liner */
	private final int CRUISELINER_LENGTH = 150;
	
	/**length of a sailboat*/
	private final int SAILBOAT_LENGTH = 80;
	
	/**length of a speed boat*/
	private final int SPEEDBOAT_LENGTH = 40;
	
	/**possible directions of a wave*/
	protected final static int RIGHT=1;
	protected final static int LEFT=-1; 
	protected final static int FORWARD=0;
	
	/**speed of sailboat and speedboat*/
	private final int BOAT_SPEED = 1;

	/**speed of cruiseliner */
	private int CRUISELINER_SPEED=2; 
	
	/**direction that the picture of the wind is facing */
	private static int windFace = 0;
	
	/**
	 * Constructs a wave using the instance of boat to set damage, length, and speed
	 * @param boat - the boat which creates the wave
	 */
	public Wave(Boat boat){
		if (boat instanceof CruiseLiner){
			this.length = CRUISELINER_LENGTH;
			this.speed= CRUISELINER_SPEED; 
		}
		else if (boat instanceof Sailboat){
			this.length = SAILBOAT_LENGTH;
			this.speed= BOAT_SPEED; 
		}
		else{
			this.length = SPEEDBOAT_LENGTH;
			this.speed= BOAT_SPEED; 
		}
		//direction = FORWARD; 
		this.xloc=boat.getXLoc();
		this.yloc=boat.getYLoc(); 
		
		boat.emittedWave();
	}

	/**
	 * Constructor to create a wave
	 * @param speed: speed of wave
	 * @param length: length of wave
	 * @param x: x location of wave
	 * @param y: y location of wave
	 */
	public Wave(int speed, int length, int x, int y){
		this.length=length;
		this.speed=speed;
		this.xloc=x;
		this.yloc=y;
	}
	
	/** moves a wave toward the shore*/
	public void move(){
		if (direction == FORWARD){
			this.yloc += speed;
		}
		else if (direction == LEFT){
			this.xloc-=direction;
			if (BeachCont.getFrameCounterWind() % 3 ==0)
				this.yloc+=speed;
		}
		else{
			this.xloc-=direction;
			if (BeachCont.getFrameCounterWind() % 3 ==0)
				this.yloc+=speed;
		}
	}
	
	/**
	 * @return true if wave has left the screen or going to hit the shore where the protects are on the side
	 */
	public boolean isOutOfRange(){
		if (direction == LEFT && this.getX()<1)
			return true;
		else if ((direction ==RIGHT||direction == FORWARD) && this.getX()+this.getLength()>Board.getWidth())
			return true;
		else if (this.xloc>Board.SHORE_WIDTH && this.yloc>Board.HEIGHT/2)
			return true;
		else if (this.xloc<0)
			return true;
		return false;
	}
	
	
	/**activates wind, which blows waves and offsets them left or right 
	 * @param number integer that determines whether the wind moves waves to left or right
	 * @param waves waves currently on screen 
	 */
	public static void activateWind(int number, ArrayList<Wave> waves) {
		boolean isWaveBelowStartingShoreline=false;
		Iterator<Wave> currWaves = waves.iterator();
		while (currWaves.hasNext()){
			Wave wave  = currWaves.next();
			if (wave.getX()>Board.SHORE_HEIGHT){
				isWaveBelowStartingShoreline=true;
			}
		}
		if (direction == FORWARD && !isWaveBelowStartingShoreline) {
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
	
	/**stops the wind from blowing waves*/
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
	
	public static int getDirection(){
		return direction;
	}
	
	public static void setDirection(int x){
		direction = x;
	}
	
	public static int getWindFace(){
		return windFace;
	}
}
