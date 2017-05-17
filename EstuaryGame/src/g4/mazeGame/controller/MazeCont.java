package g4.mazeGame.controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

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
		deaths = 0;
		level++;
		board = new Board(4 - level, deaths);
		screen.changeBoard(board);
		listen.reset(board.getUser());
		if (level==2 || level==3){
			screen.messageInterrupt(MazeView.MESSAGE_STAGE);
		}
		if (level==1){
			screen.messageInterrupt(MazeView.MESSAGE_TUTORIAL);
		}
	}
	
	/**
	 * Resets the game if the user dies
	 */
	private void lowerDifficulty(){
		screen.messageInterrupt(MazeView.MESSAGE_FAILURE);
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
			if (board.getUser().checkWin() && !screen.getIsTransitionActive()){
				if (level==0 || level==1 || level==2) {
					nextGame();
				} else if(level==3) {
					gameWon = true;
					screen.messageInterrupt(MazeView.MESSAGE_VICTORY);
				}
			} else if (board.getIsEaten() && !screen.getIsTransitionActive()){
				lowerDifficulty();
			}
			
			if (!screen.getIsTransitionActive())
				board.update();
			app.repaint();
		} else if (board.getUser().getDispose() && !screen.getIsTransitionActive()) {
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

