package g4.beachGame.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import g4.beachGame.controller.BeachCont;

public class Board {
	final static int GAMESEC_PER_HOUR = 5; //sun time bar has 12 notches.
	final static int WIDTH = 1100; 
	final static int SHORELINE_WIDTH = WIDTH-100; 
	final static int HEIGHT = 600;
	public static int shoreline = HEIGHT/2; //where the shore starts
	private boolean shoreDestroyed = false; 
	final static int SHORELINE_RECEDING = shoreline/3; //how much the shore drops everytime
	private int protector = -1;
	public int user_width = 100;
	
	final static int GRASS = 1;
	final static int GABION = 2;
	final static int WALL = 3;
	
	final static int WATER = 1; 
	final static int SHORE = 0; 
	final static int PROTECTOR = 2;
	
	final static int SPACES_OF_SHORE = 12;
	
	public int[][] beach = new int[3][12]; //height, width
	
	public int[] posArr = {HEIGHT/2, 4*HEIGHT/6 - 15, 5*HEIGHT/6 - 30};
	
	int hoursLeft; 
	
	final static double NANOSECOND_PER_SECOND=1000000000.0;
	final static long START_TIME= System.nanoTime(); 
	public double elapsedTime=0;
	
	ArrayList<Protector> protectorLine;
	private ArrayList<Wave> currWaves;
	ArrayList<Boat> currBoats;
	
	public User user;
	int difficulty;
	
	/*creates a new board of waves and protectors*/
	public Board(){
		currBoats = new ArrayList<Boat>();
		setCurrWaves(new ArrayList<Wave>());
		user = new User();
		protectorLine = new ArrayList<Protector>();
		hoursLeft = 24;
	}

	/*returns how much time has elapsed in the game in seconds*/
	public void updateElapsedTime(){
		long currTime=System.nanoTime();
		elapsedTime = (currTime-START_TIME)/NANOSECOND_PER_SECOND;
		hoursLeft = (int) (elapsedTime /GAMESEC_PER_HOUR);
	}

	/*checks to see if player has won*/
	public boolean win(){
		if (hoursLeft==0 && shoreline>0)
			return true;
		else 
			return false;
	}
	
	/*return true if the player has lost*/
	public boolean checkLost(){
		return shoreDestroyed;
	}
	
	
	/*Checks to see if the waves has hit a protector along the shoreline.
	 *If there is a protector, it's life decreases by 1. If not, level of difficulty 
	 *drops. */
	public void checkHitProtector(){
		Iterator<Wave> wavesIt = getCurrWaves().iterator();
		while (wavesIt.hasNext()){
			Wave currWave = wavesIt.next();
			if (currWave.yloc >= shoreline){
				Iterator<Protector> protit = protectorLine.iterator();
				while (protit.hasNext()){
					Protector currProt = protit.next();
					if (currProt==null){
						shoreline-=SHORELINE_RECEDING;
					}
					//if either end of the protector is within the bounds of wave, wave has hit it
					else if ((currProt.xloc1 >= currWave.xloc && currProt.xloc1 <= currWave.xloc + currWave.length)
							||(currProt.xloc2 >= currWave.xloc && currProt.xloc2 <= currWave.xloc + currWave.length))
						currProt.loseLife();
				}
			}
		}
	}
	
	/*creates a random new boat*/
	public void createBoat(){
		int randomNum = 1 + (int)(Math.random() * 7); 
		if (randomNum>0 && randomNum<4)
			currBoats.add(new Sailboat());
		else if(randomNum<=6)
			currBoats.add(new Speedboat());
		else
			currBoats.add(new CruiseLiner());
	}
	
	/*remove boats from list of current Boats to paint*/
	public void checkBoats(){
		Iterator<Boat> boatIt = getCurrBoats().iterator();
		while (boatIt.hasNext()){
			Boat currBoat = boatIt.next();
			if (currBoat.getXLoc()>WIDTH ||currBoat.getXLoc()<0){
				currBoats.remove(currBoat);
			}
		}
	}
	
	/*creates a new wave based on the boat*/
	public void createWave(Boat boat){
		getCurrWaves().add(new Wave(boat));
	}
	
	public void wavehit(int x){
		int depth = 0;
		int spot = (int)(SPACES_OF_SHORE*x/SHORELINE_WIDTH);//the spot along the shoreline
		if (x < (int)(WIDTH-100)){
			while (depth < beach.length && beach[depth][spot] == WATER)
				depth++;
			if (depth == beach.length) // the shore has reached the bottom of the screen
				shoreDestroyed = true;
			else if (beach[depth][spot] == SHORE){
				System.out.println();
				beach[depth][spot] = WATER;
			}
			else if (beach[depth][spot] == PROTECTOR){
				//some code that reduces the life of a protector
				beach[depth][spot] = SHORE;
			}
		}
	}

	
	public int chooseProtector() {
		if ((int)(user.getxLoc()+user_width)*12/SHORELINE_WIDTH == 11){ //need to change magic number
			if (user.getyLoc() <4*HEIGHT/6 - 15)
				protector = GRASS;
			else if (user.getyLoc() >= 4*HEIGHT/6 -15 && user.getyLoc() < 5*HEIGHT/6 -30)
				protector = GABION; 
			else
				protector = WALL; 
		}
		return protector;
	}
	
	public int getProtector(){
		return protector;
	}
	
	//if the user is close to the shoreline, place protector in current "spot" along the shore
	public void placeProtector(){
		int depth = 0;
		//if (user.getyLoc() < shoreline + 50)
		int spot = (int) user.getxLoc()*SPACES_OF_SHORE/SHORELINE_WIDTH;
		while (depth < beach.length && beach[depth][(int)(SPACES_OF_SHORE*spot/SHORELINE_WIDTH)] != SHORE)
			depth++;
		System.out.println("x location of protector: " + spot + "ylocation of protector: " + depth);
		if (getProtector()== GRASS)
			beach[depth][spot] = SHORE;
		else if (getProtector()== GABION)
			beach[depth][spot] = 3;
		else if (getProtector()==WALL)
			beach[depth][spot] = 4;
		protector = -1;
	}
	
	/*getters*/
	public ArrayList<Boat> getCurrBoats() {return currBoats;}
	public int getWidth(){return WIDTH;}
	public int getHeight(){return HEIGHT;}
	public ArrayList<Wave> getCurrWaves() {return currWaves;}

	/*setters*/
	public void setCurrWaves(ArrayList<Wave> currWaves) {
		this.currWaves = currWaves;
	}
	
//	public void checkWaves(){
//	Iterator<Wave> waveIt = getCurrWaves().iterator();
//	while (waveIt.hasNext()){
//		Wave currWave = waveIt.next();
//		if (currWave.getX()>WIDTH ||currWave.getX()<0||currWave.getY()>shoreline){
//			currWaves.remove(currWave);
//		}
//	}
//}
}


