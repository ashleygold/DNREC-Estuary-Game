package g4.mazeGame.controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import g4.mainController.MiniGameController;
import g4.mazeGame.model.Board;
import g4.mazeGame.view.MazeView;


public class MazeCont implements MiniGameController {
	
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	/** current level of the game */
	private int level = 0;
	
	/** number of times the user has died on the current level */
	private int deaths = 0;
	
	/** the board on which the current level occurs */
	private Board board=new Board(4 - level, deaths);
	
	/** swing window which displays the game */
	private JFrame app=new JFrame("Minigame 1: Estuary Maze");
	
	/** view component associated with the maze game */
	private MazeView screen=new MazeView(board);
	
	/** whether the game has been won yet */
	private boolean gameWon=false;
	
	/** the listener used to react to keystrokes */
	private Listener listen = new Listener(board.getUser());
	
	/** 
	 * Constructs a new maze controller object 
	 */
	public MazeCont() {
	    //app.setBounds(0,0,screenSize.width, screenSize.height);
		app.setExtendedState(JFrame.MAXIMIZED_BOTH);
		app.setLayout(null);
		app.getContentPane().add(screen);
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//app.setSize(screenSize.width, screenSize.height);
		//app.setSize(15+(board.getWidth()*screen.SLOT_SPACE), (1+board.getHeight())*screen.SLOT_SPACE);
		
		app.setVisible(true);
		
		screen.addKeyListener(listen);
	}
	
	/**
	 * Advances the game to the next level
	 */
	private void nextGame(){
		if (level==1 || level==2){
			JOptionPane.showMessageDialog(null, "Great job! The salinity has decreased by 33%. Press OK to advance to the next stage.");
		}
		if (level==0){
			JOptionPane.showMessageDialog(null, "Great job! Press okay to start the first stage.");
		}
		deaths = 0;
		level++;
		board = new Board(4 - level, deaths);
		screen.changeBoard(board);
		listen.reset(board.getUser());
	}
	
	/**
	 * Resets the game if the user dies
	 */
	private void lowerDifficulty(){
		JOptionPane.showMessageDialog(null, "You were eaten! You'll now restart the stage you were on.");
		deaths++;
		board = new Board(4 - level, deaths);
		screen.changeBoard(board);
		listen.reset(board.getUser());
	}

	/**
	 * Updates the game, called once per tick
	 */
	@Override
	public void update() {
		if (gameWon==false){
			board.update();
			app.repaint();
			if (board.getUser().checkWin()){
				if (level==0 || level==1 || level==2) {
					nextGame();
				} else if(level==3) {
					gameWon = true;
					JOptionPane.showMessageDialog(null, "You win! You made it to an area of lower salinity, so now you can grow big and strong!");
				}
			} else if (board.getIsEaten()){
				lowerDifficulty();
			}
		} else if (board.getUser().getDispose()) {
			dispose();
		}
	}

	/**
	 * Safely disposes of the game 
	 */
	@Override
	public void dispose() {
		app.dispose();
	}
}

