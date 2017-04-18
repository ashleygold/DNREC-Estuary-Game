package g4.beachGame.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import g4.beachGame.model.Board;
import g4.beachGame.view.BeachView;
import g4.mainController.MiniGameController;
import g4.mazeGame.controller.Listener;
import g4.mazeGame.model.User;
import g4.mazeGame.view.MazeView;

public class BeachCont implements MiniGameController{
	final static double NANOSECOND_PER_SECOND=1000000000.0;
	final static long START_TIME= System.nanoTime(); 
	double elapsedTime;
	
	private BeachView bView = new BeachView();
	private JFrame application = new JFrame ("Minigrame 2: Beach");
	
	Board b1 = new Board();
	
	/*returns how much time has elapsed in the game in seconds*/
	public double updateElapsedTime(){
		long currTime=System.nanoTime();
		this.elapsedTime = (currTime-START_TIME)/NANOSECOND_PER_SECOND;
		return this.elapsedTime;
	}


	public BeachCont() {
		application.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}			
		});
		application.setLayout(null);
		application.getContentPane().add(bView);
		//app.pack();
		application.setSize(15+(b1.getWidth(), (1+board.getHeight()));
		
		app.setVisible(true);
		
		screen.addKeyListener(new Listener(user));
	}
	//public void 


	@Override
	public void update() {
		b1.user.move();
		app.repaint();

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
