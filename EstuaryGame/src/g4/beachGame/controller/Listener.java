package g4.beachGame.controller;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import g4.beachGame.model.Board;
import g4.beachGame.model.User;


public class Listener implements KeyListener {
	
	private boolean[] keysDown = new boolean[4];
	private final int UP_ARROW = 0, RIGHT_ARROW = 1, DOWN_ARROW = 2, LEFT_ARROW = 3;
	private final int[] ARROW_KEYS = {KeyEvent.VK_UP, 
			KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT};
	
	private int SPACEBAR = KeyEvent.VK_SPACE;
	private User user;
	public Listener(User user) {
		this.user = user;
	}

	//@Override
	public void keyPressed(KeyEvent ke) {
		int code = ke.getKeyCode();
		int loc = 0;
		while (loc < ARROW_KEYS.length && ARROW_KEYS[loc] != code)
			loc++;
		if (loc < ARROW_KEYS.length)
			keysDown[loc] = true;
		setUserDirection();
	}

	//@Override
	public void keyReleased(KeyEvent ke) {
		int code = ke.getKeyCode();
		int loc = 0;
		while (loc < ARROW_KEYS.length && ARROW_KEYS[loc] != code)
			loc++;
		if (loc < ARROW_KEYS.length)
			keysDown[loc] = false;
		if (code == SPACEBAR)
			Board.getProtector();
		setUserDirection();
	}

	//@Override
	public void keyTyped(KeyEvent arg0) {}
	
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