package g4.mazeGame.model;

import javax.swing.JOptionPane;

import g4.mainController.MainMenu;

public class Predator {
	//reference to board & user
	private final Board board;
	private final User user;
	
	//movement variables
	private double xLoc, yLoc;
	private final double MOVE_SPEED = 4.5/MainMenu.MAZE_FPS;
	private final double DIAG_MOVE_SPEED = Math.sqrt(.5*Math.pow(MOVE_SPEED, 2));
	public final static int STILL = 0, LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4,
			UP_RIGHT = 5, UP_LEFT = 6, DOWN_RIGHT = 7, DOWN_LEFT = 8;
	private int direction = STILL;
	
	//use the center of the user for wall detection
	public final double CENTER_IMG = 0.5;
	
	//hitbox buffer
	public final double BUFFER = 0.45;
	
	public Predator(Board b, User u, int x, int y){
		board = b;
		user = u;
		xLoc = x;
		yLoc = y;
		newDirection();
	}

	public void move() {
		//checks nested in the interest of efficiency
		switch(direction) {
			case LEFT:
				if (board.isEmpty(xLoc - MOVE_SPEED + CENTER_IMG - BUFFER, 
						yLoc + CENTER_IMG)){
					xLoc-=MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case RIGHT:
				if (board.isEmpty(xLoc + MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + CENTER_IMG)){
					xLoc+=MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case UP:
				if (board.isEmpty(xLoc + CENTER_IMG,
						yLoc - MOVE_SPEED + CENTER_IMG - BUFFER)){
					yLoc-=MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case DOWN:
				if (board.isEmpty(xLoc + CENTER_IMG,
						yLoc + MOVE_SPEED + CENTER_IMG + BUFFER)){
					yLoc+=MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case UP_RIGHT:
				if (board.isEmpty(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case UP_LEFT:
				if (board.isEmpty(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER,
						yLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc-=DIAG_MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case DOWN_RIGHT:
				if (board.isEmpty(xLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER)){
					xLoc+=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
			case DOWN_LEFT:
				if (board.isEmpty(xLoc - DIAG_MOVE_SPEED + CENTER_IMG - BUFFER,
						yLoc + DIAG_MOVE_SPEED + CENTER_IMG + BUFFER)){
					xLoc-=DIAG_MOVE_SPEED;
					yLoc+=DIAG_MOVE_SPEED;
				} else {
					newDirection();
				}
				break;
		}
		checkEat();
	}
	
	private void newDirection(){
		direction = (int)(1 + 8*Math.random());
	}
	
	private boolean checkEat(){
		if (Math.abs(xLoc - user.getXLoc()) < 0.5 && Math.abs(yLoc - user.getYLoc()) < 0.5){
			JOptionPane.showMessageDialog(null, "You lose :(");
			board.eaten=true;
			return true;
		}
		else{
			return false;
		}
	}
		
	
	public double getXLoc(){
		return xLoc;
	}
	
	public double getYLoc(){
		return yLoc;
	}
}
