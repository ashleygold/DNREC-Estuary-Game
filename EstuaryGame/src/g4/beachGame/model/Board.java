package g4.beachGame.model;

import java.util.ArrayList;
import java.util.Iterator;

import g4.beachGame.controller.BeachCont;

public class Board {
	final static int GAMESEC_PER_HOUR = 5; //sun time bar has 12 notches.
	final static int WIDTH = 600; 
	final static int HEIGHT = 600;
	static int shoreline = 400;
	int hoursLeft; 
	
	final static double NANOSECOND_PER_SECOND=1000000000.0;
	final static long START_TIME= System.nanoTime(); 
	double elapsedTime=0;
	
	ArrayList<Protector> protectorLine;
	ArrayList<Wave> currWaves;
	ArrayList<Boat> currBoats;
	
	public User user;
	int difficulty;
	
	/*creates a new board of waves and protectors*/
	public Board(){
		currBoats = new ArrayList<Boat>();
		currWaves = new ArrayList<Wave>();
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
		return (shoreline==0);
	}
	
	
	/*Checks to see if the waves has hit a protector along the shoreline.
	 *If there is a protector, it's life decreases by 1. If not, level of difficulty 
	 *drops. */
	public void checkHitProtector(){
		Iterator<Wave> wavesIt = currWaves.iterator();
		while (wavesIt.hasNext()){
			Wave currWave = wavesIt.next();
			if (currWave.yloc >= shoreline){
				Iterator<Protector> protit = protectorLine.iterator();
				while (protit.hasNext()){
					Protector currProt = protit.next();
					if (currProt==null){
						difficulty++; 
						//loss(); 
					}
					//if either end of the protector is within the bounds of wave, wave has hit it
					else if ((currProt.xloc1 >= currWave.xloc && currProt.xloc1 <= currWave.xloc + currWave.length)||(currProt.xloc2 >= currWave.xloc && currProt.xloc2 <= currWave.xloc + currWave.length))
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
	
	/*creates a new wave based on the boat*/
	public void createWave(Boat boat){
		currWaves.add(new Wave(boat));
	}
	public int getWidth(){
		return WIDTH;
	}
	
	public int getHeight(){
		return HEIGHT;	
	}
}
