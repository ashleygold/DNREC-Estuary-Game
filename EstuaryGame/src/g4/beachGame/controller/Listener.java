package g4.beachGame.controller;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import g4.beachGame.model.Board;
import g4.beachGame.model.User;


public class Listener implements KeyListener {
	
	/** stores which arrow keys are down*/
	private boolean[] keysDown = new boolean[4];
	
	/** index of specified arrow key */
	private final int UP_ARROW = 0, RIGHT_ARROW = 1, DOWN_ARROW = 2, LEFT_ARROW = 3;
	
	/** stores arrow key event numbers */
	private final int[] ARROW_KEYS = {KeyEvent.VK_UP, KeyEvent.VK_RIGHT, 
			KeyEvent.VK_DOWN, KeyEvent.VK_LEFT};
	
	/** stores the space bar event number */
	private int SPACEBAR = KeyEvent.VK_SPACE;
	
	/** the user associated to the listener */
	private User user;
	
	/**The board associated with the listener*/
	private Board board;
	
	/**
	 * Constructs a listener associated to the board
	 * @param board: the board that the listener should modify
	 */
	public Listener(Board board) {
		this.user = board.user;
		this.board = board;
	}



	
	/**
	 * runs when a key is pressed, updates user's direction through setUserDirection()
	 * @param ke contains data of key pressed
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		int code = ke.getKeyCode();
		int loc = 0;
		while (loc < ARROW_KEYS.length && ARROW_KEYS[loc] != code)
			loc++;
		if (loc < ARROW_KEYS.length)
			keysDown[loc] = true;
		setUserDirection();
	}

	/**
	 * runs when a key is released, updates user's direction through setUserDirection()
	 * @param ke contains data of key released
	 */
	@Override
	public void keyReleased(KeyEvent ke) {
		int code = ke.getKeyCode();
		int loc = 0;
		while (loc < ARROW_KEYS.length && ARROW_KEYS[loc] != code)
			loc++;
		if (loc < ARROW_KEYS.length)
			keysDown[loc] = false;
		if (code == SPACEBAR && board.getProtector() ==-1)
			board.chooseProtector();
		else if (code == SPACEBAR && board.getProtector() != -1)
			board.placeProtector();
		setUserDirection();
	}

	
	/**
	 * Override normal key-typed functionality
	 * @param arg0 ignored
	 */
	//@Override
	public void keyTyped(KeyEvent arg0) {}
	
	/** sets the user direction based on keystrokes*/
	public void setUserDirection(){
		if (keysDown[UP_ARROW] && keysDown[LEFT_ARROW])
			user.setDirection(User.UP_LEFT);
		else if (keysDown[DOWN_ARROW] && keysDown[LEFT_ARROW])
			user.setDirection(User.DOWN_LEFT);
		else if (keysDown[UP_ARROW] && keysDown[RIGHT_ARROW])
			user.setDirection(User.UP_RIGHT);
		else if (keysDown[DOWN_ARROW] && keysDown[RIGHT_ARROW])
			user.setDirection(User.DOWN_RIGHT);
		else if (keysDown[UP_ARROW])
			user.setDirection(User.UP);
		else if (keysDown[DOWN_ARROW])
			user.setDirection(User.DOWN);
		else if (keysDown[LEFT_ARROW])
			user.setDirection(User.LEFT);
		else if (keysDown[RIGHT_ARROW])
			user.setDirection(User.RIGHT);
		else
			user.setDirection(User.STILL);
	}
	
}