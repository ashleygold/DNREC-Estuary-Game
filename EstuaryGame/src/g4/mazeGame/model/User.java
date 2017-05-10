package g4.mazeGame.model;

import g4.mainController.MainMenu;

public class User {
	
	/** creating a reference to a board object */
	private final Board board;
	
	/**boolean to dispose the entire game on winning the third stage */
	private boolean dispose;
	
	/**picks which buffered image of the user is used*/
	private double picNum = 0;
	
	/** the frame count of the user's images*/
	int frameCount = 3;
	
	/**the x location of the user*/
	private double xLoc=15;
	
	/** the y location of the user*/
	private double yLoc=15;
	
	/**the move speed of the user when moving up, down, left or right*/
	private static final double MOVE_SPEED = 5.5/MainMenu.MAZE_FPS;
	
	/** the move speed of the user when moving diagonally*/
	private static final double DIAG_MOVE_SPEED = Math.sqrt(.5*Math.pow(MOVE_SPEED, 2));
	
	/** references to possible directions the user can go when moving*/
	public static final int RIGHT = 0, UP = 1, UP_RIGHT = 2, UP_LEFT = 3, DOWN = 4, 
			 DOWN_RIGHT = 5, DOWN_LEFT = 6, LEFT = 7;

	/**reference to possible state of when the user is still*/
	public static int STILL=8; 
	
	/** direction of the user*/
	private int direction = STILL;
	
	/**center of the user image- used for collision detection */
	public static double CENTER_IMG=0.5;
	
	/**hit box buffer*/
	private static double BUFFER=0.37;
	
	/**diagonal hit box buffer*/
	private static final double DIAG_BUFFER = Math.sqrt(.5*Math.pow(BUFFER, 2));
	
	/** number of food the user has collected in the stage*/
	private int foodCount;
	
	/**true if the win gate is open, false otherwise*/
	private boolean gateOpened = false;
	
	/**
	 * Constructor for user
	 * @param b: takes in the board that the user is on
	 */
	public User(Board b){
		board = b;
	}
	
	/**
	 * checks if a space on the board is food, if so the user "eats" the food
	 */
	public void checkFood(){
		if (board.eatFood(xLoc + CENTER_IMG, yLoc + CENTER_IMG)){
			foodCount++;
		}
	}
	
	/**
	 * checking to see if the user has won the game
	 * @return true if the user has collected all of the food on the board
	 * and gone through the gate
	 */
	public boolean checkWin(){
		if (this.foodCount==board.getGoalFood() && !gateOpened){
			board.openGate();
			gateOpened = true;
		}
		if (board.winGame(xLoc + CENTER_IMG, yLoc + CENTER_IMG)){
			if (board.getSalinity() == 1)
				dispose = true;
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * updates user once per frame
	 */
	public void update() {
		if (tryMove(direction))
			picNum = (picNum + .2) % frameCount;
		checkFood();
	}
	
	/**
	 * allows the user to move about the maze
	 * @param tryDir : takes in the direction of the key pressed
	 * @return true if the user is currently moving and not hitting a wall, false otherwise
	 */
	private boolean tryMove(int tryDir){
		//checks nested in the interest of efficiency
		switch(tryDir) {
			case LEFT:
				if (board.isEmpty(xLoc - MOVE_SPEED + CENTER_IMG - BUFFER, 
						yLoc + CENTER_IMG + BUFFER) &&
						board.isEmpty(xLoc - MOVE_SPEED + CENTER_IMG - BUFFER, 
						yLoc + CENTER_IMG - BUFFER)){
					xLoc-=MOVE_SPEED;
					return true;
				}
				break;
			case RIGHT:
				if (board.isEmpty(xLoc + MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + CENTER_IMG + BUFFER) &&
						board.isEmpty(xLoc + MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + CENTER_IMG - BUFFER)){
					xLoc+=MOVE_SPEED;
					return true;
				}
				break;
			case UP:
				if (board.isEmpty(xLoc + CENTER_IMG + BUFFER,
						yLoc - MOVE_SPEED + CENTER_IMG - BUFFER) &&
						board.isEmpty(xLoc + CENTER_IMG - BUFFER,
						yLoc - MOVE_SPEED + CENTER_IMG - BUFFER)){
					yLoc-=MOVE_SPEED;
					return true;
				}
				break;
			case DOWN:
				if (board.isEmpty(xLoc + CENTER_IMG + BUFFER,
						yLoc + MOVE_SPEED + CENTER_IMG + BUFFER) &&
						board.isEmpty(xLoc + CENTER_IMG - BUFFER,
						yLoc + MOVE_SPEED + CENTER_IMG + BUFFER)){
					yLoc+=MOVE_SPEED;
					return true;
				}
				break;
			case UP_RIGHT:
				if (board.isEmpty(xLoc + MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + CENTER_IMG) &&
						board.isEmpty(xLoc + CENTER_IMG,
						yLoc - MOVE_SPEED + CENTER_IMG - BUFFER) &&
						board.isEmpty(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + DIAG_BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - DIAG_BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
					return true;
					//try moving in one diagonal component
				} else if (tryMove(UP)) {
					return true;
				} else if (tryMove(RIGHT)){
					return true;
					//see if x/y can be slightly changed to allow movement
				} else if (xLoc - (int)xLoc < 1 -(yLoc - (int)yLoc)){
					xLoc -= DIAG_MOVE_SPEED;
					return tryMove(UP_RIGHT);
				} else {
					yLoc += DIAG_MOVE_SPEED;
					return tryMove(UP_RIGHT); 
				}
				//break; //unreachable
			case UP_LEFT:
				if (board.isEmpty(xLoc - MOVE_SPEED + CENTER_IMG - BUFFER,
						yLoc + CENTER_IMG) &&
						board.isEmpty(xLoc + CENTER_IMG,
						yLoc - MOVE_SPEED + CENTER_IMG - BUFFER) &&
						board.isEmpty(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - DIAG_BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - DIAG_BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
					return true;
					//try moving in one diagonal component
				} else if (tryMove(UP)) {
					return true;
				} else if (tryMove(LEFT)) {
					return true;
					//see if x/y can be slightly changed to allow movement
				} else if ((xLoc - (int)xLoc) > (yLoc - (int)yLoc)){
					xLoc += DIAG_MOVE_SPEED;
					return tryMove(UP_LEFT);
				} else {
					yLoc += DIAG_MOVE_SPEED;
					return tryMove(UP_LEFT);
				}
				//break; //unreachable
			case DOWN_RIGHT:
				if (board.isEmpty(xLoc + MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + CENTER_IMG) &&
						board.isEmpty(xLoc + CENTER_IMG,
						yLoc + MOVE_SPEED + CENTER_IMG + BUFFER) &&
						board.isEmpty(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + DIAG_BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + DIAG_BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
					return true;
					//try moving in one diagonal component
				} else if (tryMove(DOWN)) {
					return true;
				} else if (tryMove(RIGHT)){
					return true;
					//see if x/y can be slightly changed to allow movement
				} else if ((xLoc - (int)xLoc) > (yLoc - (int)yLoc)){
					yLoc -= DIAG_MOVE_SPEED;
					return tryMove(DOWN_RIGHT);
				} else {
					xLoc -= DIAG_MOVE_SPEED;
					return tryMove(DOWN_RIGHT);
				}
				//break; //unreachable
			case DOWN_LEFT:
				if (board.isEmpty(xLoc - MOVE_SPEED + CENTER_IMG - BUFFER,
						yLoc + CENTER_IMG) &&
						board.isEmpty(xLoc + CENTER_IMG,
						yLoc + MOVE_SPEED + CENTER_IMG + BUFFER) &&
						board.isEmpty(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - DIAG_BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + DIAG_BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
					return true;
					//try moving in one diagonal component
				} else if (tryMove(DOWN)) {
					return true;
				} else if (tryMove(LEFT)){
					return true;
					//see if x/y can be slightly changed to allow movement
				} else if (xLoc - (int)xLoc < 1 -(yLoc - (int)yLoc)){
					yLoc -= DIAG_MOVE_SPEED;
					return tryMove(DOWN_LEFT);
				} else {
					xLoc += DIAG_MOVE_SPEED;
					return tryMove(DOWN_LEFT);
				}
				//break; //unreachable
		}
		return false;
	}
	
	public int getPicNum(){
		return (int) picNum;
	}
	
	public double getXLoc(){
		return xLoc;
	}
	
	public double getYLoc(){
		return yLoc;
	}
	
	public void setDirection(int d){
		direction = d;
	}
	
	public int getFoodCount() {
		return foodCount;
	}
	
	
	public boolean getDispose(){
		return dispose;
	}

	public int getDirection() {
		return direction;
	}
}
