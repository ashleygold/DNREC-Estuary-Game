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
	public static final int RIGHT = 0, UP = 1, DOWN = 2, LEFT = 3, UP_RIGHT = 4, 
			UP_LEFT = 5, DOWN_RIGHT = 6, DOWN_LEFT = 7; 
	
	/** direction of the predator */
	private int direction = RIGHT;
	
	/** center of the user image- used for collision detection */
	private final double CENTER_IMG = 0.5;
	
	/** hit box buffer */
	private final double BUFFER = 0.37;
	
	/**diagonal hit box buffer*/
	private final double DIAG_BUFFER = Math.sqrt(.5*Math.pow(BUFFER, 2));
	
	/** array which handles vector movement checks **/
	private final double[][] MOVE_VECTOR = {
			{1,  0}, 	//RIGHT
			{0 , -1},		//UP
			{0 , 1},		//DOWN
			{-1, 0},		//LEFT
			
			{1, -1},			//UP_RIGHT
			{-1, -1},			//UP_LEFT
			{1, 1},			//DOWN_RIGHT
			{-1, 1}			//DOWN_LEFT
			};
	
	/**
	 * creates a predator at the specified location 
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
		if (direction < 4){
			//cardinal direction
			if (board.isEmptyPred(xLoc + CENTER_IMG + MOVE_VECTOR[direction][0]*(MOVE_SPEED + BUFFER), 
					yLoc + CENTER_IMG + MOVE_VECTOR[direction][1]*(MOVE_SPEED + BUFFER))){
				xLoc += MOVE_VECTOR[direction][0]*MOVE_SPEED;
				yLoc += MOVE_VECTOR[direction][1]*MOVE_SPEED;
			} else {
				newDirection();
			}
		} else {
			//diagonal direction
			if (board.isEmptyPred(xLoc + CENTER_IMG + MOVE_VECTOR[direction][0]*(DIAG_MOVE_SPEED + DIAG_BUFFER), 
					yLoc + CENTER_IMG + MOVE_VECTOR[direction][1]*(DIAG_MOVE_SPEED + DIAG_BUFFER))){
				xLoc += MOVE_VECTOR[direction][0]*DIAG_MOVE_SPEED;
				yLoc += MOVE_VECTOR[direction][1]*DIAG_MOVE_SPEED;
			} else {
				newDirection();
			}
		}
		//checks if the predator is close enough to eat the user
		return (Math.abs(xLoc - user.getXLoc()) < 0.5 && Math.abs(yLoc - user.getYLoc()) < 0.5);
	}
	
	/**
	 * sets the predator to face a random direction
	 */
	private void newDirection(){
		direction = (int)(8*Math.random());
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

	protected void setDirection(int dir) {
		direction = dir;
	}

}
