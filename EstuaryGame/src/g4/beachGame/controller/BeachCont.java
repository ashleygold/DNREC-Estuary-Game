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
	
<<<<<<< HEAD
	private BeachView bView = new BeachView(b1.getWidth(),b1.getHeight(), user.getxLoc(), user.getyLoc());
=======
	private BeachView bView = new BeachView(b1.getWidth(),b1.getHeight(), b1.user.getxLoc(), b1.user.getyLoc(), b1.user);
>>>>>>> 0d5abcab8c86a5bf413fcdd744dc9665dcaaa6bc
	
	private boolean hasWon=false;
	private boolean hasLost=false;
	
	final int timeBetweenBoats= 6;
	
	public BeachCont() {
		bView.frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}			
		});
		bView.frame.setLayout(null);
		bView.frame.getContentPane().add(bView);
		bView.frame.setSize(b1.getWidth(), b1.getHeight());
		bView.frame.setVisible(true);
		bView.addKeyListener(new Listener(b1.user));
	} 


	@Override
	public void update() {
		b1.user.move();
		Iterator<Wave> wavesIt = b1.getCurrWaves().iterator();
		while (wavesIt.hasNext()){
			Wave currWave = wavesIt.next();
			currWave.moveWave();
		}
		Iterator<Boat> boatIt = b1.getCurrBoats().iterator();
		while (boatIt.hasNext()){
			Boat currBoat = boatIt.next();
			currBoat.moveBoat();
		}
//		if (b1.elapsedTime%1000==0){
//			b1.createBoat();
//		}
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
