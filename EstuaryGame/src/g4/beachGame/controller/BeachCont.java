package g4.beachGame.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import g4.beachGame.model.Board;
import g4.beachGame.model.User;
import g4.beachGame.view.BeachView;
import g4.mainController.MiniGameController;


public class BeachCont implements MiniGameController{
	
	
	private BeachView bView = new BeachView();
	private Board b1 = new Board();
	private User user = new User();
	private boolean hasWon=false;
	private boolean hasLost=false;
	


	public BeachCont() {
		bView.frame.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}			
		});
		bView.frame.setLayout(null);
		bView.frame.getContentPane().add(bView);
		bView.frame.setSize(bView.FRAME_WIDTH(), bView.FRAME_HEIGHT());
		bView.frame.setVisible(true);
		bView.addKeyListener(new Listener(user));
	} 


	@Override
	public void update() {
		b1.user.move();
		bView.frame.repaint();
		if (hasWon==false){
			user.move();
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
