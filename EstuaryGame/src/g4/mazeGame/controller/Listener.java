package g4.mazeGame.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import g4.mazeGame.model.User;


public class Listener implements KeyListener {
	
	/** stores which arrow keys are down*/
	private boolean[] keysDown = new boolean[4];
	/** index of specified arrow key */
	private final int UP_ARROW = 0, RIGHT_ARROW = 1, DOWN_ARROW = 2, LEFT_ARROW = 3;
	/** stores arrow key event numbers */
	private final int[] ARROW_KEYS = {KeyEvent.VK_UP, 
			KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT};
	
	/** the user associated to the listener */
	private User user;
	
	/**
	 * contructs a listener associated to the user
	 * @param user the user that the listener should modify
	 */
	public Listener(User user) {
		this.user = user;
	}
	
	/**
	 * resets the listener to refer to new user
	 * @param user the user that the listener should modify
	 */
	public void reset(User user) {
		this.user = user;
		keysDown = new boolean[4];
	}

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

	@Override
	public void keyReleased(KeyEvent ke) {
		int code = ke.getKeyCode();
		int loc = 0;
		while (loc < ARROW_KEYS.length && ARROW_KEYS[loc] != code)
			loc++;
		if (loc < ARROW_KEYS.length)
			keysDown[loc] = false;
		setUserDirection();
	}

	//@Override
	public void keyTyped(KeyEvent arg0) {}
	
	/**
	 * sets the direction of the associated user to the correct direction based on which keys are depressed 
	 */
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
