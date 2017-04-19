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
	
	private Board b1 = new Board();

	private BeachView bView = new BeachView(b1);
	private boolean hasWon=false;
	private boolean hasLost=false;
	private int frameCounter;
	
	final int timeBetweenBoats= 6;
	
	public BeachCont() {
		bView.frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}			
		});
		bView.addKeyListener(new Listener(b1.user));
		frameCounter=0;
	} 

	public void couldCreateWave(Boat boat){
		if (!boat.getHasEmittedWave()){
			if (boat.getDirection() && boat.getXLoc()>=boat.getWaveLocation()){
				b1.createWave(boat);
			}
		}
	}

	@Override
	public void update() {
		frameCounter++;
		b1.user.move();
		if (frameCounter==180){
			b1.createBoat();
			frameCounter=0;
			
		}
		Iterator<Wave> wavesIt = b1.getCurrWaves().iterator();
		while (wavesIt.hasNext()){
			Wave currWave = wavesIt.next();
			currWave.move();
			b1.checkWaves(currWave);
		}
		Iterator<Boat> boatIt = b1.getCurrBoats().iterator();
		while (boatIt.hasNext()){
			Boat currBoat = boatIt.next();
			currBoat.move();
			couldCreateWave(currBoat);	
		}
		bView.frame.repaint();
		if (hasWon==false){
			b1.user.move();
			bView.frame.repaint();
			if (b1.checkLost()){
				hasLost=true;
				this.dispose();
			}
		}
	}

	@Override
	public void dispose() {
		bView.frame.dispose();
	}

}
