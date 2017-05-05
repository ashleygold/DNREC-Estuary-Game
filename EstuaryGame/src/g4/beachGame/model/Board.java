package g4.beachGame.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import g4.beachGame.controller.BeachCont;

public class Board {
	/** the amount of seconds that is equals 1 hour on the time of the game**/
	final static int GAMESEC_PER_HOUR = 5; //sun time bar has 12 notches.
	
	/**width of the board**/
	final static int WIDTH = 1100; 
	
	/**width of the shore (not including the last column reserved for protectors)**/
	final static int SHORELINE_WIDTH = WIDTH-100; 

	/**the number of spots along the shore where the user can place protectors**/
	final static int SPACES_OF_SHORE = 12;
	
	/**height of the board**/
	final static int HEIGHT = 600;
	
	/**the x location where the shoreline is **/
	public static int shoreline = HEIGHT/2; //where the shore starts
	
	/**whether or not the a spot along the shore has receded to the bottom of the screen**/
	private boolean isShoreDestroyed = false; 
	
	//DELETE THIS EVENTUALLY
	/**how much the shore recedes every time**/
	final static int SHORELINE_RECEDING = shoreline/3; //how much the shore drops everytime
	
	/**which protect is currently being used placed**/
	private int protector = -1;
	
	/**the width of the user, which may change depending on the state**/
	public int user_width = 100;
	
	/**final fields that represents types of objects on the board**/
	final static int SHORE = 0; 
	final static int WATER = 1; 
	final static int GRASS = 2; 
	final static int GRASS_L =3; 
	final static int WALL = 4;
	final static int GABION = 5;
	final static int GABION_L = 6;
	final static int GABION_2L = 7;
	
	/**the array representing what was originally the shore**/
	public int[][] beach = new int[3][SPACES_OF_SHORE]; //height, width
	
	//I KNOW NO IDEA WHAT THIS ACTUALLY IS
	/**the array representing the protectors**/
	public int[] posArr = {HEIGHT/2, 4*HEIGHT/6 - 15, 5*HEIGHT/6 - 30};
	
	/**how much time is left in the game**/
	int hoursLeft; 
	
	/**nano seconds per second conversion**/
	final static double NANOSECOND_PER_SECOND=1000000000.0;
	/**time when user starts playing**/
	final static long START_TIME= System.nanoTime();
	
	/**how much time has passed in seconds since user started playing**/
	public double elapsedTime=0;
	
	//THIS SHOULD BE ABLE TO BE DELETED, RIGHT JORDAN? 
	/**ArrayList of protectors along the shore**/
	ArrayList<Protector> protectorLine;
	
	/**current Waves on the screen moving towards the shore**/
	private ArrayList<Wave> currWaves;
	
	/**current Boats on the screen**/
	ArrayList<Boat> currBoats;
	
	/**the user**/
	public User user;
	
	/**the game should get easier as people keep losing**/
	int difficulty;
	
	/**
	 * creates a new board of waves and protectors
	 * */
	public Board(){
		currBoats = new ArrayList<Boat>();
		setCurrWaves(new ArrayList<Wave>());
		user = new User();
		protectorLine = new ArrayList<Protector>();
		hoursLeft = 24;
	}

	/**
	 * updates how much time has elapsed in the game in seconds
	 * */
	public void updateElapsedTime(){
		long currTime=System.nanoTime();
		elapsedTime = (currTime-START_TIME)/NANOSECOND_PER_SECOND;
		hoursLeft = (int) (elapsedTime /GAMESEC_PER_HOUR);
	}

	/**
	 * @return true if player has won, false if player has lost
	 * */
	public boolean win(){
		if (hoursLeft==0 && shoreline>0)
			return true;
		else 
			return false;
	}
	
	/**
	 * 
	 * @return true if player has lost, false if player has not lost
	 */
	public boolean checkLost(){
		return isShoreDestroyed;
	}
	
	
	/**Checks to see if the waves has hit a protector along the shoreline.
	 *If there is a protector, it's life decreases by 1. If not, level of difficulty 
	 *drops. 
	 **/
//	public void checkHitProtector(){
//		Iterator<Wave> wavesIt = getCurrWaves().iterator();
//		while (wavesIt.hasNext()){
//			Wave currWave = wavesIt.next();
//			if (currWave.yloc >= shoreline){
//				Iterator<Protector> protit = protectorLine.iterator();
//				while (protit.hasNext()){
//					Protector currProt = protit.next();
//					if (currProt==null){
//						shoreline-=SHORELINE_RECEDING;
//					}
//					//if either end of the protector is within the bounds of wave, wave has hit it
//					else if ((currProt.xloc1 >= currWave.xloc && currProt.xloc1 <= currWave.xloc + currWave.length)
//							||(currProt.xloc2 >= currWave.xloc && currProt.xloc2 <= currWave.xloc + currWave.length))
//						currProt.loseLife();
//				}
//			}
//		}
//	}
	
	/**
	 * creates a random new boat of a random variety
	 * Boats from most frequent to least frequent: Sailboat, Speedboats,and CruiseLiner
	 */
	public void createBoat(){
		int randomNum = 1 + (int)(Math.random() * 7); 
		if (randomNum>0 && randomNum<4)
			currBoats.add(new Sailboat());
		else if(randomNum<=6)
			currBoats.add(new Speedboat());
		else
			currBoats.add(new CruiseLiner());
	}
	
	/**
	 * remove boats from list of current Boats to paint
	 */
	public void checkBoats(){
		Iterator<Boat> boatIt = getCurrBoats().iterator();
		while (boatIt.hasNext()){
			Boat currBoat = boatIt.next();
			if (currBoat.getXLoc()>WIDTH ||currBoat.getXLoc()<0){
				currBoats.remove(currBoat);
			}
		}
	}
	
	/**
	 * @param boat : the boat that creates the wave
	 * creates a new wave generated by the input parameter boat and adds it to the
	 * of arrayList of waves currently on the board
	 */
	public void createWave(Boat boat){
		getCurrWaves().add(new Wave(boat));
	}
	
	/**
	 * This method sets the beach grid when the wave hits the shore.  If the shore is already at
	 * the bottom of the screen, the shore is destroyed. If the wave hits the shore, that cell becomes 
	 * water. If the wave hits a protector, the cell becomes the shore.
	 * @param x the x location of the wave
	 */
	public void wavehit(int x){
		int depth = 0;
		int spot = (int)(SPACES_OF_SHORE*x/SHORELINE_WIDTH);//the spot along the shoreline
		if (x < (int)(WIDTH-100)){
			while (depth < beach.length && beach[depth][spot] == WATER)
				depth++;
			if (depth == beach.length) // the shore has reached the bottom of the screen
				isShoreDestroyed = true;
			else if (beach[depth][spot] == SHORE){
				System.out.println();
				beach[depth][spot] = WATER;
			}
			else if (beach[depth][spot] != WATER || beach[depth][spot]!=SHORE){
				//some code that reduces the life of a protector
				beach[depth][spot] = SHORE;
			}
		}
	}

	/**
	 * Returns the protector closest to the user by the user's position on the grid.
	 * @return the integer representing the protector chosen
	 */
	public int chooseProtector() {
		if ((int)(user.getxLoc()+user_width)*12/SHORELINE_WIDTH == 11){ //need to change magic number
			if (user.getyLoc() <4*HEIGHT/6 - 15)
				protector = GRASS_L;
			else if (user.getyLoc() >= 4*HEIGHT/6 -15 && user.getyLoc() < 5*HEIGHT/6 -30)
				protector = GABION_2L; 
			else
				protector = WALL; 
		}
		return protector;
	}
	
	/**
	 * Returns the current protector.
	 * @return the integer representing the current protector
	 */
	public int getProtector(){
		return protector;
	}
	
	/**
	 * Sets specific cell of beachgrid on the shoreline at user's xlocation to a protector.
	 */
	public void placeProtector(){
		int depth = 0;
		int spot = (int) user.getxLoc()*SPACES_OF_SHORE/SHORELINE_WIDTH;
		while (depth < beach.length && beach[depth][spot] == WATER)
			depth++;
		beach[depth][spot] = getProtector();
		protector = -1;
	}
	
	/**
	 * Returns the array of current boats on the screen.
	 * @return the array of current boats on the screen
	 */
	public ArrayList<Boat> getCurrBoats() {return currBoats;}
	
	/**
	 * Returns the width of the board.
	 * @return the width of the board
	 */
	public int getWidth(){return WIDTH;}
	
	/**
	 * Returns the height of the board.
	 * @return the height of the board
	 */
	public int getHeight(){return HEIGHT;}
	
	/**
	 * Returns the arraylist of current waves on the screen.
	 * @return the arraylist of current waves on the screen
	 */
	public ArrayList<Wave> getCurrWaves() {return currWaves;}

	/**
	 * Sets the attribute currWaves to the parameter currWaves.
	 * @param currWaves the arraylist to replace the attribute currWaves
	 */
	public void setCurrWaves(ArrayList<Wave> currWaves) {
		this.currWaves = currWaves;
	}
	
}


