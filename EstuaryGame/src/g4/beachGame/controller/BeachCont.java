package g4.beachGame.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;

import javax.swing.JFrame;

import g4.beachGame.model.Board;
import g4.beachGame.model.Boat;
import g4.beachGame.model.Protector;
import g4.beachGame.model.User;
import g4.beachGame.model.Wave;
import g4.beachGame.view.BeachView;
import g4.mainController.MiniGameController;


public class BeachCont implements MiniGameController{
	
	private Board board1 = new Board();

	private BeachView bView = new BeachView(board1);
	private boolean hasWon=false;
	private boolean hasLost=false;
	private int frameCounter;
	private int framesBetweenBoats;
	final int timeBetweenBoats= 6;
	
	public BeachCont() {
		bView.addKeyListener(new Listener(board1));
		frameCounter=0;
		framesBetweenBoats=180;
	} 

	public void couldCreateWave(Boat boat){
		if (!boat.getHasEmittedWave()){
			if (boat.getDirection() && boat.getXLoc()>=boat.getWaveLocation()){
				board1.createWave(boat);
			}
			else if (!boat.getDirection() && boat.getXLoc()<=boat.getWaveLocation()){
				board1.createWave(boat);
			}
		}
	}
	
	/*everything that changes every frame*/
	@Override
	public void update() {
		frameCounter++;
		/*user*/
		board1.user.move();
		
		//spawn boats
		if (frameCounter==framesBetweenBoats){
			board1.createBoat();
			frameCounter=0;
			framesBetweenBoats-=5;
		}
		
		//waves move down screen
		Iterator<Wave> wavesIt = board1.getCurrWaves().iterator();
		while (wavesIt.hasNext()){
			Wave currWave = wavesIt.next();
			currWave.move();
			if (currWave.getY() >= Board.shoreline){
				board1.wavehit(currWave.getX());
				wavesIt.remove();
			}
		}
		
		//moves boats across screen
		Iterator<Boat> boatIt = board1.getCurrBoats().iterator();
		while (boatIt.hasNext()){
			Boat currBoat = boatIt.next();
			currBoat.move();
			couldCreateWave(currBoat);	
		}
		
		bView.frame.repaint();
		if (board1.checkLost()){
			hasLost=true;
			this.dispose();
		}
	}

	@Override
	public void dispose() {
		bView.frame.dispose();
	}

}
