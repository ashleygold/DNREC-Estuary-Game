package g4.mazeGame.model;

import javax.swing.JOptionPane;

import g4.mainController.MainMenu;

public class User {
	//reference to board
	private final Board board;
	private boolean dispose;
	private double picNum = 0;
	int frameCount = 3;
	
	//movement variables
	private double xLoc=15, yLoc=15;
	private static final double MOVE_SPEED = 5.5/MainMenu.MAZE_FPS;
	private static final double DIAG_MOVE_SPEED = Math.sqrt(.5*Math.pow(MOVE_SPEED, 2));
	public final static int STILL = 0, LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4,
			UP_RIGHT = 5, UP_LEFT = 6, DOWN_RIGHT = 7, DOWN_LEFT = 8;
	private int direction = STILL;
	
	//use the center of the user for wall detection
	public static final double CENTER_IMG = 0.5;
	
	//hitbox buffer
	private static final double BUFFER = 0.27;
	private static final double DIAG_BUFFER = Math.sqrt(.5*Math.pow(BUFFER, 2));
	
	private int foodCount;
	private boolean gateOpened = false;
	
	public User(Board b){
		board = b;
	}
	
	public int getPicNum(){
		return (int) picNum;
	}
	
	public void checkFood(){
		//unfinished
		if (board.eatFood(xLoc + CENTER_IMG, yLoc + CENTER_IMG)){
			foodCount++;
		}
	}
	
	
	public boolean checkWin(){
		if (this.foodCount==board.getGoalFood() && !gateOpened){
			board.openGate();
			gateOpened = true;
		}
		if (board.winGame(xLoc + CENTER_IMG, yLoc + CENTER_IMG) && board.getGoalFood()<10){
			JOptionPane.showMessageDialog(null, "Great job! The salinity has decreased by 25%. Press OK to advance to the next stage.");
			return true;
		}
		else if ((board.winGame(xLoc + CENTER_IMG, yLoc + CENTER_IMG) && board.getGoalFood()==10)){
			JOptionPane.showMessageDialog(null, "You win! You made it to an area of lower salinity, so now you can grow big and strong!");
			dispose = true;
			return true;
		}
		else{
			return false;
		}
	}
	

	public void update() {
		tryMove(direction);
		checkFood();
	}
	
	private boolean tryMove(int tryDir){
		//checks nested in the interest of efficiency
		picNum = (picNum + .2) % frameCount;
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
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	public void increaseFood(){
		setFoodCount(foodCount+1);
	}
	
	public boolean getDispose(){
		return dispose;
	}
}
