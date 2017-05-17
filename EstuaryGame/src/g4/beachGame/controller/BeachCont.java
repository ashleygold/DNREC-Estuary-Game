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

	/**Creating an instance of a board for the beach game*/
	private Board board1 = new Board();
	
	/**Creating an instance of beach view*/
	private BeachView bView;
	
	/**Variable to declare if the game is over*/
	private boolean isGameOver = false;
	
	/**The frame counter for the entire game*/
	private int frameCounter;
	
	/**The frame counter for the wind */
	private static int frameCounterWind;
	
	/**The frame counter for the turtles*/
	private int frameCounterTurtles;
	
	/**The frame counter for the frames between boat instances */
	private int framesBetweenBoats;
	
	/**The frame counter for the frames between turtle instances*/
	private int framesBetweenTurtles;
	
	/**The frame counter for the frames between wind instances */
	private int framesBetweenWind;

	/**The frame counter for the turtle's life bar */
	private int frameCounterTurtleLife;
	
	
	/**
	 * a constructor that creates a new Beach controller
	 */
	public BeachCont() {
		bView = new BeachView(board1);
		bView.addKeyListener(new Listener(board1));
		frameCounter=0;
		setFrameCounterWind(0);
		frameCounterTurtles=0;
		setFrameCounterTurtleLife(0);
		framesBetweenBoats=230;
		framesBetweenTurtles=605;
		framesBetweenWind=600;
	} 
	
	/**
	 * Checks to see if boat is in a location where they are able to create a new wave
	 * @param boat is the boat we check to see if it can create a boat
	 */
	private void couldCreateWave(Boat boat){
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
	 * Updates the game each frame
	 */
	@Override
	public void update() {
		if (!isGameOver) {
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
				if (board1.hoursLeft==-1){
					JOptionPane.showMessageDialog(null, "You've protected the shore for a whole day!");
					dispose();
				}
				frameCounter++;
				setFrameCounterWind(getFrameCounterWind() + 1);
				frameCounterTurtles++;
				setFrameCounterTurtleLife(getFrameCounterTurtleLife() + 1);
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
				
				if (getFrameCounterWind() > framesBetweenWind){
					Wave.activateWind(frameCounterTurtles,board1.getCurrWaves());
				}
				
				if (getFrameCounterWind() == framesBetweenWind + 700){
					Wave.ceaseWind();
					setFrameCounterWind(0);
				}
				
				//waves move on screen
				Iterator<Wave> wavesIt = board1.getCurrWaves().iterator();
				while (wavesIt.hasNext()){
					Wave currWave = wavesIt.next();
					if (currWave.getY()<board1.getHeight()/2 || 
							(currWave.getY()<Board.HEIGHT && board1.beach[(int) (Math.ceil(currWave.getY()*6/Board.HEIGHT))-3][currWave.getX()*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]!=Board.SHORE))
						currWave.move();
					if (currWave.isOutOfRange()){
						wavesIt.remove();
					}
					else if (currWave.getY() >= Board.SHORE_HEIGHT && Wave.getDirection()==0){
						if ((int) (Math.ceil(currWave.getY()*6/Board.HEIGHT))-3 <= 2){
							board1.splitWave(currWave);
							wavesIt.remove();
						}
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
						if ((int)(Math.ceil(currWave2.getY()*6/Board.HEIGHT))-3 == 3 || currWave2.getX()+currWave2.getLength()>Board.SHORE_WIDTH ||
								(currWave2.getY() >= Board.SHORE_HEIGHT && board1.beach[(int) (Math.ceil(currWave2.getY()*6/Board.HEIGHT))-3][currWave2.getX()*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]!=Board.WATER)){
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
	 * Overrides the dispose method to dispose the view when game is over
	 */
	@Override
	public void dispose() {
		isGameOver = true;
		bView.frame.dispose();
	}
	
	public int getFrameCount(){
		return frameCounter;
	}

	public static int getFrameCounterWind() {
		return frameCounterWind;
	}

	public static void setFrameCounterWind(int frameCounterWind) {
		BeachCont.frameCounterWind = frameCounterWind;
	}

	public int getFrameCounterTurtleLife() {
		return frameCounterTurtleLife;
	}

	public void setFrameCounterTurtleLife(int frameCounterTurtleLife) {
		this.frameCounterTurtleLife = frameCounterTurtleLife;
	}

}
