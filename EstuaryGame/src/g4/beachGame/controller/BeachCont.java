package g4.beachGame.controller;


import java.util.Iterator;

import javax.swing.JOptionPane;

import g4.beachGame.model.Board;
import g4.beachGame.model.Boat;
import g4.beachGame.model.Turtle;
import g4.beachGame.model.Wave;
import g4.beachGame.view.BeachView;
import g4.mainController.MiniGameController;


public class BeachCont implements MiniGameController{

	/**board for the game*/
	private Board board1 = new Board();
	/**view for the game*/
	private BeachView bView;
	/**true for whether or not the game is over*/
	private boolean isGameOver = false;
	public int frameCounter;
	public static int frameCounterWind;
	public int frameCounterTurtles;
	public int frameCounterTurtleLife;
	private int framesBetweenBoats;
	private int framesBetweenTurtles;
	private int framesBetweenWind;
	final int timeBetweenBoats= 6;
	
	
	/**
	 * a constructor that creates a new Beach controller
	 */
	public BeachCont() {
		bView = new BeachView(board1);
		bView.addKeyListener(new Listener(board1));
		frameCounter=0;
		frameCounterWind=0;
		frameCounterTurtles=0;
		frameCounterTurtleLife = 0;
		framesBetweenBoats=230;
		framesBetweenTurtles=605;
		framesBetweenWind=600;
	} 
	
	/**
	 * checks to see if boat is in a location where they are able to create a new wave
	 * @param boat is the boat we check to see if it can create a boat
	 */
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
	
	/**
	 * everything that changes every frame
	 */
	@Override
	public void update() {
		//System.out.println(board1.getCurrTurtles());
		if (!isGameOver) {
			//System.out.println(board1.getCurrTurtles());
			if (board1.checkLost()){
				if (board1.getIsShoreDestroyed()){
					JOptionPane.showMessageDialog(null, "Sorry, you lost :( The shore receded too much.");
				}
				else{
					JOptionPane.showMessageDialog(null, "Sorry, you lost :( The turtle wasn't able to make it to the ocean.");
				}
				this.dispose();
			}
			else{
				board1.updateElapsedTime();
				//System.out.println(board1.hoursLeft);
				if (board1.hoursLeft==0){
					JOptionPane.showMessageDialog(null, "You've protected the shore for a whole day!");
					dispose();
				}
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
				
				//waves move on screen
				Iterator<Wave> wavesIt = board1.getCurrWaves().iterator();
				while (wavesIt.hasNext()){
					Wave currWave = wavesIt.next();
					currWave.move();
					if (currWave.isOutOfRange()){
						wavesIt.remove();
					}
					else if (currWave.getY() >= Board.SHORE_HEIGHT){
						board1.splitWave(currWave);
						wavesIt.remove();
					}
				}
				
				Iterator<Wave> splitWavesIt = board1.getSplitWaves().iterator();
				while (splitWavesIt.hasNext()){
					Wave wave = splitWavesIt.next();
					board1.getCurrWaves().add(wave);
					splitWavesIt.remove();
				}
				
				Iterator<Wave> wavesIt2 = board1.getCurrWaves().iterator();
				while (wavesIt2.hasNext()){
					Wave currWave2 = wavesIt2.next();
					if (currWave2.isOutOfRange()){
						wavesIt2.remove();
					}
					else{
						if (currWave2.getY() >= Board.SHORE_HEIGHT&&board1.beach[(int) (Math.ceil(currWave2.getY()*6/Board.HEIGHT))-3][currWave2.getX()*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]==Board.SHORE){
							board1.waveHit(currWave2.getX(), currWave2.getX()+currWave2.getLength());
							wavesIt2.remove();
						}
					}
				}
			}
			
			//moves boats across screen
			Iterator<Boat> boatIt = board1.getCurrBoats().iterator();
			while (boatIt.hasNext()){
				Boat currBoat = boatIt.next();
				currBoat.move();
				couldCreateWave(currBoat);	
			}
			board1.checkBoats();
			
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
		}
	}
	
	/**
	 * overriding the dispose method, gets ride of the View.
	 */
	@Override
	public void dispose() {
		isGameOver = true;
		bView.frame.dispose();
	}
	
	public int getFrameCount(){
		return frameCounter;
	}

}
