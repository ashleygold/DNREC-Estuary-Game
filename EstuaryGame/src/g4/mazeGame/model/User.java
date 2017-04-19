package g4.mazeGame.model;

import javax.swing.JOptionPane;

import g4.mainController.MainMenu;
import g4.mazeGame.view.MazeView;

public class User {
	//reference to board
	private final Board board;
	
	//movement variables
	private double xLoc=15, yLoc=15;
	private final double MOVE_SPEED = 4.5/MainMenu.MAZE_FPS;
	private final double DIAG_MOVE_SPEED = Math.sqrt(.5*Math.pow(MOVE_SPEED, 2));
	public final static int STILL = 0, LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4,
			UP_RIGHT = 5, UP_LEFT = 6, DOWN_RIGHT = 7, DOWN_LEFT = 8;
	private int direction = STILL;
	
	//use the center of the user for wall detection
	public final double CENTER_IMG = 0.5;
	
	//hitbox buffer
	public final double BUFFER = 0.45;
	
	private int foodCount;
	
	public User(Board b){
		board = b;
	}
	
	public void checkFood(){
		//unfinished
		if (board.eatFood(xLoc + CENTER_IMG, yLoc + CENTER_IMG)){
			foodCount++;
		}
	}
	
	public boolean checkWin(){
		if (this.foodCount==board.goalFood){
			board.openGate();
		}
		if (board.winGame(xLoc + CENTER_IMG, yLoc + CENTER_IMG)){
			JOptionPane.showMessageDialog(null, "You win! Press OK to advance to Stage 2 (when it exists).");
			return true;
		}
		else{
			return false;
		}
	}
	

	public void move() {
		//checks nested in the interest of efficiency
		switch(direction) {
			case LEFT:
				if (board.isEmpty(xLoc - MOVE_SPEED + CENTER_IMG - BUFFER, 
						yLoc + CENTER_IMG)){
					xLoc-=MOVE_SPEED;
				}
				break;
			case RIGHT:
				if (board.isEmpty(xLoc + MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + CENTER_IMG)){
					xLoc+=MOVE_SPEED;
				}
				break;
			case UP:
				if (board.isEmpty(xLoc + CENTER_IMG,
						yLoc - MOVE_SPEED + CENTER_IMG - BUFFER)){
					yLoc-=MOVE_SPEED;
				}
				break;
			case DOWN:
				if (board.isEmpty(xLoc + CENTER_IMG,
						yLoc + MOVE_SPEED + CENTER_IMG + BUFFER)){
					yLoc+=MOVE_SPEED;
				}
				break;
			case UP_RIGHT:
				if (board.isEmpty(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
				}
				break;
			case UP_LEFT:
				if (board.isEmpty(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
				}
				break;
			case DOWN_RIGHT:
				if (board.isEmpty(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
				}
				break;
			case DOWN_LEFT:
				if (board.isEmpty(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
				}
				break;
		}
		checkFood();
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
}
