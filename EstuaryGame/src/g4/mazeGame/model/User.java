package g4.mazeGame.model;

import g4.mainController.MainMenu;
import g4.mazeGame.view.MazeView;

public class User {
	//reference to board
	private final Board board;
	public boolean isFood=true;
	
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
	
	public boolean isEmpty(double x, double y){
		if ((board.getCell((int)x, (int)y) == '.') || (board.getCell((int)x, (int)y) == 'o')){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isFood(double x, double y){
		return (board.getCell((int)x, (int)y) == 'o');
	}
	
	public void checkFood(){
		if (isFood(xLoc - MOVE_SPEED + CENTER_IMG - BUFFER, 
				yLoc + CENTER_IMG)){
			isFood = false;
		}
	}


	public void move() {
		//checks nested in the interest of efficiency
		switch(direction) {
			case LEFT:
				if (isEmpty(xLoc - MOVE_SPEED + CENTER_IMG - BUFFER, 
						yLoc + CENTER_IMG)){
					xLoc-=MOVE_SPEED;
				}
				break;
			case RIGHT:
				if (isEmpty(xLoc + MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + CENTER_IMG)){
					xLoc+=MOVE_SPEED;
				}
				break;
			case UP:
				if (isEmpty(xLoc + CENTER_IMG,
						yLoc - MOVE_SPEED + CENTER_IMG - BUFFER)){
					yLoc-=MOVE_SPEED;
				}
				break;
			case DOWN:
				if (isEmpty(xLoc + CENTER_IMG,
						yLoc + MOVE_SPEED + CENTER_IMG + BUFFER)){
					yLoc+=MOVE_SPEED;
				}
				break;
			case UP_RIGHT:
				if (isEmpty(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
				}
				break;
			case UP_LEFT:
				if (isEmpty(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
				}
				break;
			case DOWN_RIGHT:
				if (isEmpty(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
				}
				break;
			case DOWN_LEFT:
				if (isEmpty(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
				}
				break;
		}
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
