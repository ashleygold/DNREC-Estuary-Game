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
	private final int FRAME_COUNT = 3;
	
	/**the x location of the user*/
	private double xLoc=15;
	
	/** the y location of the user*/
	private double yLoc=15;
	
	/**the move speed of the user when moving up, down, left or right*/
	private static final double MOVE_SPEED = 5.5/MainMenu.MAZE_FPS;
	
	/** the move speed of the user when moving diagonally*/
	private static final double DIAG_MOVE_SPEED = Math.sqrt(.5*Math.pow(MOVE_SPEED, 2));
	
	/** references to possible directions the user can go when moving*/
	public static final int RIGHT = 0, UP = 1, DOWN = 2, LEFT = 3, UP_RIGHT = 4,
			UP_LEFT = 5, DOWN_RIGHT = 6, DOWN_LEFT = 7;
	
	/**reference to possible state of when the user is still*/
	public static int STILL=8; 
	
	/** array which handles vector movement checks **/
	private final int[][] MOVE_VECTOR = {
			{+1,  0, 0, 1}, 	//RIGHT
			{0 , -1, 1, 0},		//UP
			{0 , +1, 1, 0},		//DOWN
			{-1,  0, 0, 1},		//LEFT
			
			{+1, -1, 0, 1},			//UP_RIGHT
			{-1, -1, 3, 1},			//UP_LEFT
			{+1, +1, 0, 2},			//DOWN_RIGHT
			{-1, +1, 2, 3}			//DOWN_LEFT
			};
	
	/** direction of the user*/
	private int direction = STILL;
	
	/**center of the user image- used for collision detection */
	private final static double CENTER_IMG=0.5;
	
	/**hit box buffer*/
	private final static double BUFFER=0.375;
	
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
	private void checkFood(){
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
			picNum = (picNum + .2) % FRAME_COUNT;
		checkFood();
	}
	
	/**
	 * allows the user to move about the maze
	 * @param tryDir : takes in the direction of the key pressed
	 * @return true if the user is currently moving and not hitting a wall, false otherwise
	 */
	private boolean tryMove(int tryDir){
		
		if (tryDir <= 3){
			//movement in a cardinal direction
			if (board.isEmpty(xLoc + (MOVE_SPEED + BUFFER) * MOVE_VECTOR[tryDir][0] 
							+ CENTER_IMG + BUFFER * MOVE_VECTOR[tryDir][2], 
						yLoc + (MOVE_SPEED + BUFFER) * MOVE_VECTOR[tryDir][1]
							+ CENTER_IMG + BUFFER * MOVE_VECTOR[tryDir][3]) &&
					board.isEmpty(xLoc + (MOVE_SPEED + BUFFER) * MOVE_VECTOR[tryDir][0] 
							+ CENTER_IMG - BUFFER * MOVE_VECTOR[tryDir][2], 
						yLoc + (MOVE_SPEED + BUFFER) * MOVE_VECTOR[tryDir][1] 
							+ CENTER_IMG - BUFFER * MOVE_VECTOR[tryDir][3])){
				xLoc += MOVE_SPEED * MOVE_VECTOR[tryDir][0];
				yLoc += MOVE_SPEED * MOVE_VECTOR[tryDir][1];
				return true;
			}
		} else if (tryDir <= 7) {
			if (board.isEmpty(xLoc + CENTER_IMG + (MOVE_SPEED + BUFFER) * MOVE_VECTOR[tryDir][0],
						yLoc + CENTER_IMG) &&
					board.isEmpty(xLoc + CENTER_IMG,
							yLoc + CENTER_IMG + (BUFFER + MOVE_SPEED) * MOVE_VECTOR[tryDir][1]) &&
					board.isEmpty(xLoc + CENTER_IMG + (DIAG_MOVE_SPEED + DIAG_BUFFER) * MOVE_VECTOR[tryDir][0],
							yLoc + CENTER_IMG + (DIAG_BUFFER + DIAG_MOVE_SPEED) * MOVE_VECTOR[tryDir][1])){
				xLoc += DIAG_MOVE_SPEED * MOVE_VECTOR[tryDir][0];
				yLoc += DIAG_MOVE_SPEED * MOVE_VECTOR[tryDir][1];
				return true;
				//try moving in one diagonal component
			} else if (tryMove(MOVE_VECTOR[tryDir][2])) {
				return true;
			} else if (tryMove(MOVE_VECTOR[tryDir][3])){
				return true;
				//see if x/y can be slightly changed to allow movement
			} else if (MOVE_VECTOR[tryDir][0] * (2 * (xLoc - (int)xLoc) - 1) <
						MOVE_VECTOR[tryDir][1] * (2 * (yLoc - (int)yLoc) - 1)){
				xLoc -= DIAG_MOVE_SPEED * MOVE_VECTOR[tryDir][0];
				return tryMove(tryDir);
			} else {
				yLoc -= DIAG_MOVE_SPEED * MOVE_VECTOR[tryDir][1];
				return tryMove(tryDir); 
			}
		}
		//no movement
		return false;		
	}
	
	protected void setXLoc(double x){
		xLoc=x;
	}
	
	protected void setYLoc(double y){
		yLoc=y;
	}
	
	/**
	 * Get the current frame image for the user
	 * @return integer of the image
	 */
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
	
	protected void setFoodCount(int food) {
		foodCount = food;
	}
	
	public boolean getDispose(){
		return dispose;
	}

	public int getDirection() {
		return direction;
	}
}
