package g4.mazeGame.controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import g4.mainController.MiniGameController;
import g4.mazeGame.model.Board;
import g4.mazeGame.view.MazeView;


public class MazeCont implements MiniGameController {
	
	private Board board=new Board();
	private JFrame app=new JFrame("Minigame 1: Maze");
	private MazeView screen=new MazeView(board);
	private boolean checkWin=false;
	private boolean checkEat=false;
	
	public MazeCont() {
		app.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}			
		});
		app.setLayout(null);
		app.getContentPane().add(screen);
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//app.pack();
		app.setSize(15+(board.getWidth()*screen.SLOT_SPACE), (1+board.getHeight())*screen.SLOT_SPACE);
		
		app.setVisible(true);
		
		screen.addKeyListener(new Listener(board.getUser()));
	}

	@Override
	public void update() {
		if (checkWin==false && board.eaten==false){
			board.update();
			app.repaint();
			if ((board.getUser().checkWin()) || (board.eaten==true)){
				checkWin=true;
				this.dispose();
			}
		}
	}

	@Override
	public void dispose() {
		app.dispose();
	}
}

