package g4.mazeGame.model;

import g4.mainController.MainMenu;

public class Predator {
	/** the board that the predator is on*/
	private final Board board;
	
	/** the user this predator tries to eat*/
	private final User user;
	
	/** x-location of the predator */
	private double xLoc;
	
	/** y-location of the predator */
	private double yLoc;
	
	/** the move speed of the predator when moving up, down, left or right */
	private final double MOVE_SPEED = 3.5/MainMenu.MAZE_FPS;
	
	/** the move speed of the predator when moving diagonally */
	private final double DIAG_MOVE_SPEED = Math.sqrt(.5*Math.pow(MOVE_SPEED, 2));
	
	/** references to possible directions the predator can go */
	public static final int RIGHT = 0, UP = 1, UP_RIGHT = 2, UP_LEFT = 3, DOWN = 4, 
			 DOWN_RIGHT = 5, DOWN_LEFT = 6, STILL = 8, LEFT = 7; 
	
	/** direction of the predator */
	private int direction = STILL;
	
	/** center of the user image- used for collision detection */
	public static final double CENTER_IMG = 0.5;
	
	/** hit box buffer */
	public final static double BUFFER = 0.37;
	
	/**diagonal hit box buffer*/
	private static final double DIAG_BUFFER = Math.sqrt(.5*Math.pow(BUFFER, 2));
	
	/**
	 * creates a predator at the apecifed location 
	 * @param b the board the predator should be placed on
	 * @param u the user the predator wants to eat
	 * @param x the initial x-coordinate for the user
	 * @param y the initial y-coordinate for the user
	 */
	public Predator(Board b, User u, int x, int y){
		board = b;
		user = u;
		xLoc = x;
		yLoc = y;
		newDirection();
	}

	/**
	 * moves the predator, or turns if it cannot go forward
	 * @return true if the predator eats the user, false otherwise
	 */
	public boolean move() {
		//checks nested in the interest of efficiency
		switch(direction) {
			case LEFT:
				if (board.isEmptyPred(xLoc - MOVE_SPEED + CENTER_IMG - BUFFER, 
						yLoc + CENTER_IMG)){
					xLoc-=MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case RIGHT:
				if (board.isEmptyPred(xLoc + MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + CENTER_IMG)){
					xLoc+=MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case UP:
				if (board.isEmptyPred(xLoc + CENTER_IMG,
						yLoc - MOVE_SPEED + CENTER_IMG - BUFFER)){
					yLoc-=MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case DOWN:
				if (board.isEmptyPred(xLoc + CENTER_IMG,
						yLoc + MOVE_SPEED + CENTER_IMG + BUFFER)){
					yLoc+=MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case UP_RIGHT:
				if (board.isEmptyPred(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + DIAG_BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - DIAG_BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case UP_LEFT:
				if (board.isEmptyPred(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - DIAG_BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - DIAG_BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case DOWN_RIGHT:
				if (board.isEmptyPred(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + DIAG_BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + DIAG_BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case DOWN_LEFT:
				if (board.isEmptyPred(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - DIAG_BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + DIAG_BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
		}
		return checkEat();
	}
	
	/**
	 * sets the predator to face a random direction
	 */
	private void newDirection(){
		direction = (int)(8*Math.random());
	}
	
	/**
	 * checks if the predator is close enough to eat the user
	 * @return true if the predator has eaten the user, false otherwise
	 */
	boolean checkEat(){
		return (Math.abs(xLoc - user.getXLoc()) < 0.5 && Math.abs(yLoc - user.getYLoc()) < 0.5);
	}
	
	public int getDirection(){
		return direction;
	}
	
	public double getXLoc(){
		return xLoc;
	}
	
	public double getYLoc(){
		return yLoc;
	}

	public void setDirection(int dir) {
		direction = dir;
	}

}
