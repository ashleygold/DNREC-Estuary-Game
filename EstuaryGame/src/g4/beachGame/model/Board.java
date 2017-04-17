package g4.beachGame.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Board {
	final static int WIDTH = 600; 
	final static int HEIGHT = 600;
	final static int SHORELINE = 400;
	ArrayList<Protector> protectorLine;
	ArrayList<Wave> currWaves;
	User user;
	int difficulty;
	
	Board(){
		currWaves = new ArrayList<Wave>();
		user = new User();
		protectorLine = new ArrayList<Protector>();
	}
	
	public void win(){
		
	}
	
	public void loss(){
		
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
			if (currWave.yloc >= SHORELINE){
				Iterator<Protector> protit = protectorLine.iterator();
				while (protit.hasNext()){
					Protector currProt = protit.next();
					if (currProt==null){
						difficulty++; 
						loss(); 
					}
					//if either end of the protector is within the bounds of wave, wave has hit it
					else if ((currProt.xloc1 >= currWave.xloc && currProt.xloc1 <= currWave.xloc + currWave.length)||(currProt.xloc2 >= currWave.xloc && currProt.xloc2 <= currWave.xloc + currWave.length))
						currProt.loseLife();
				}
			}
		}
	}
	public void createWave(){
		currWaves.add(new Wave());
	}
}
