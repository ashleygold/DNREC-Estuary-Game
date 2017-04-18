package g4.beachGame.model;

import java.util.ArrayList;
import java.util.Iterator;

import g4.beachGame.controller.BeachCont;

public class Board {
	final static int GAMESEC_PER_HOUR = 5; //sun time bar has 12 notches.
	final static int WIDTH = 600; 
	final static int HEIGHT = 600;
	static int shoreline = 400;
	int hour; 
	
	
	ArrayList<Protector> protectorLine;
	ArrayList<Wave> currWaves;
	ArrayList<Boat> currBoats;
	
	User user;
	int difficulty;
	
	
	Board(){
		currWaves = new ArrayList<Wave>();
		user = new User();
		protectorLine = new ArrayList<Protector>();
		hour = 24;
	}
	
	
	/*returns the hour of the day and calls controller to move sun*/
	public int getCurrHour(double currTime){ //beachCont.getElapsedTime
		hour = (int) (currTime /GAMESEC_PER_HOUR);
		return hour;
	}
	/*checks to see if player has won*/
	public boolean win(){
		getCurrHour();
		if (hour==0 && shoreline>0)
			return true;
		else 
			return false;
	}
	
	/*checks to if the shoreline has completely disappeared*/
	public void checkShoreline(){
		if (shoreline==0){
			restart();
		}	
	}
	
	public void restart(){
		
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
}
