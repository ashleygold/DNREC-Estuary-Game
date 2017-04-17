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
	
	Board(){
		currWaves = new ArrayList<Wave>();
		user = new User();
		protectorLine = new ArrayList<Protector>();
	}
	
	public void showWin(){
		
	}
	public void showLoss(){
		
	}
	public void restart(){
		
	}
	public void checkHitProtector(){
		Iterator<Wave> wavesit = currWaves.iterator();
		while (wavesit.hasNext()){
			Wave currWave = wavesit.next();
			if (currWave.yloc >= SHORELINE){
				Iterator<Protector> protit = protectorLine.iterator();
				while (protit.hasNext()){
					Protector currProt = protit.next();
					//if either end of the protector is within the bounds of wave, wave has hit it
					if ((currProt.xloc1 >= currWave.xloc && currProt.xloc1 <= currWave.xloc + currWave.length)||(currProt.xloc2 >= currWave.xloc && currProt.xloc2 <= currWave.xloc + currWave.length))
						currProt.loseLife();
				}
			}
		}
	}
	public void createWave(){
		currWaves.add(new Wave());
	}
}
