package g4.mazeGame.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import g4.mainController.MiniGameController;
import g4.mazeGame.model.Board;
import g4.mazeGame.model.User;
import g4.mazeGame.view.MazeView;


public class MazeCont implements MiniGameController {
	
	private Board board=new Board();
	private User user = new User(board);
	private JFrame app=new JFrame("Minigame 1: Maze");
	private MazeView screen=new MazeView(board, user);
	
	public MazeCont() {
		app.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}			
		});
		app.setLayout(null);
		app.getContentPane().add(screen);
		//app.pack();
		app.setSize(15+(board.getWidth()*screen.SLOT_SPACE), (1+board.getHeight())*screen.SLOT_SPACE);
		
		app.setVisible(true);
		
		screen.addKeyListener(new Listener(user));
	}

	@Override
	public void update() {
		user.move();
		app.repaint();
	}

	@Override
	public void dispose() {
		app.dispose();
	}
}

