package g4.mazeGame.controller;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import g4.mainController.MiniGameController;
import g4.mazeGame.model.Board;
import g4.mazeGame.view.MazeView;


public class MazeCont implements MiniGameController {
	
	private int level = 0;
	private int deaths = 0;
	private Board board=new Board(3 - level, deaths);
	private JFrame app=new JFrame("Minigame 1: Maze");
	private MazeView screen=new MazeView(board);
	private boolean checkWin=false;
	private Listener listen = new Listener(board.getUser());
	
	public MazeCont() {
		app.setLayout(null);
		app.getContentPane().add(screen);
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//app.pack();
		app.setSize(15+(board.getWidth()*screen.SLOT_SPACE), (1+board.getHeight())*screen.SLOT_SPACE);
		
		app.setVisible(true);
		
		screen.addKeyListener(listen);
	}
	
	private void nextGame(){
		deaths = 0;
		level++;
		board = new Board(3 - level, deaths);
		screen.changeBoard(board);
		listen.reset(board.getUser());
	}
	
	private void lowerDifficulty(){
		deaths++;
		board = new Board(3 - level, deaths);
		screen.changeBoard(board);
		listen.reset(board.getUser());
	}

	@Override
	public void update() {
		if (checkWin==false){
			board.update();
			app.repaint();
			if (board.getUser().checkWin()){
				if (level < 2) {
					nextGame();
				} else {
					checkWin = true;
				}
			} else if (board.getIsEaten()){
				lowerDifficulty();
			}
		} else if (board.getUser().getDispose()) {
			dispose();
		}
	}

	@Override
	public void dispose() {
		app.dispose();
	}
}

