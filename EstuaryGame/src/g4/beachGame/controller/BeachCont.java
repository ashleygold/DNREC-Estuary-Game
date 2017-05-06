package g4.beachGame.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import g4.beachGame.model.Board;
import g4.beachGame.model.Boat;
import g4.beachGame.model.Turtle;
import g4.beachGame.model.User;
import g4.beachGame.model.Wave;
import g4.beachGame.view.BeachView;
import g4.mainController.MiniGameController;


public class BeachCont implements MiniGameController{


	private Board board1 = new Board();

	private BeachView bView = new BeachView(board1);
	private boolean hasWon=false;
	private boolean hasLost=false;
	public int frameCounter;
	public static int frameCounterWind;
	public int frameCounterTurtles;
	public int frameCounterTurtleLife;
	private int framesBetweenBoats;
	private int framesBetweenTurtles;
	private int framesBetweenWind;
	final int timeBetweenBoats= 6;
	private int[] waveCounter= new int[12];
	
	public BeachCont() {
		bView.addKeyListener(new Listener(board1));
		frameCounter=0;
		frameCounterWind=0;
		frameCounterTurtles=0;
		frameCounterTurtleLife = 0;
		framesBetweenBoats=230;
		framesBetweenTurtles=605;
		framesBetweenWind=600;
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
		frameCounterWind++;
		frameCounterTurtles++;
		frameCounterTurtleLife++;
		/*user*/
		board1.user.move();
		
		//spawn boats
		if (frameCounter==framesBetweenBoats){
			board1.createBoat();
			frameCounter=0;
			framesBetweenBoats-=3;
		}
		
		//spawn turtles
		if (frameCounterTurtles == framesBetweenTurtles){
			board1.createTurtle();
			frameCounterTurtles=0;
		}
		
		if (frameCounterWind > framesBetweenWind){
			Wave.activateWind(frameCounterTurtles);
		}
		
		if (frameCounterWind == framesBetweenWind + 700){
			Wave.ceaseWind();
			frameCounterWind=0;
		}
		
		//waves move down screen
		Iterator<Wave> wavesIt = board1.getCurrWaves().iterator();
		while (wavesIt.hasNext()){
			Wave currWave = wavesIt.next();
			currWave.move();
			if (currWave.isOutOfRange()){
				wavesIt.remove();
			}
			if (currWave.getY() >= Board.shoreline - Board.RAISE){
				board1.waveHit(currWave.getX(),currWave.getX()+currWave.getLength());
				wavesIt.remove();
			}
		}
	
		
		
//		Iterator<Wave> splitWavesIt = board1.getSplitWaves().iterator();
//		while (splitWavesIt.hasNext()){
//			Wave swave = splitWavesIt.next();
//			board1.addCurrWave(swave);
//		}
		
		//moves boats across screen
		Iterator<Boat> boatIt = board1.getCurrBoats().iterator();
		while (boatIt.hasNext()){
			Boat currBoat = boatIt.next();
			currBoat.move();
			couldCreateWave(currBoat);	
		}
		
		//moves turtles around screen
		Iterator<Turtle> turtleIt = board1.getCurrTurtles().iterator();
		while (turtleIt.hasNext()){
			Turtle turtle = turtleIt.next();
			turtle.move();
			turtle.setFramesLeft(turtle.getFramesLeft()-1);
			if (turtle.getGotToOcean()){
				turtleIt.remove();
			}
		}
		
		bView.frame.repaint();
		if (board1.checkLost()){
			hasLost=true;
			if (board1.getIsShoreDestroyed())
				JOptionPane.showMessageDialog(bView.frame, "Sorry, you lost :( The shore receded too much.");
			else
				JOptionPane.showMessageDialog(bView.frame, "Sorry, you lost :( The turtle wasn't able to make it to the ocean.");
			this.dispose();
		}
	}

	@Override
	public void dispose() {
		bView.frame.dispose();
	}
	
	public int getFrameCount(){
		return frameCounter;
	}

}
