package g4.beachGame.model;

import g4.mainController.MainMenu;

public class User {
	public final static int CRAB_HEIGHT=50;
	public final static int CRAB_WIDTH=70;
	
	private final int DEFAULTX = 15;
	private final int DEFAULTY = 450;
	private final int XINCR = 8;
	private final int YINCR = 8;
	private int xLoc;
	private int yLoc;
	private double picNum = 0;
	int frameCount = 3;
	
	public final static int STILL = 0, LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4,
			UP_RIGHT = 5, UP_LEFT = 6, DOWN_RIGHT = 7, DOWN_LEFT = 8;
	private int direction = STILL;
	
	
	public User(){
		this.xLoc = DEFAULTX;
		this.yLoc = DEFAULTY;
	}
	
	public int getPicNum(){
		return (int) picNum;
	}
	
	public void move() {
		//checks nested in the interest of efficiency
		picNum = (picNum + .2) % frameCount;
		switch(direction) {
			case LEFT:
				if (Board.isShore(xLoc - XINCR, yLoc))
					xLoc-=XINCR;
				break;
			case RIGHT:
				if (Board.isShore(xLoc + XINCR+CRAB_WIDTH, yLoc))
					xLoc+=XINCR;
				break;
			case UP:
				if (Board.isShore(xLoc, yLoc - YINCR))
					yLoc-=YINCR;
				break;
			case DOWN:
				if (Board.isShore(xLoc, yLoc+YINCR))
					yLoc+=YINCR;
				break;
			case UP_RIGHT:
				if (Board.isShore(xLoc + XINCR+CRAB_WIDTH, yLoc-YINCR)){
					xLoc+=XINCR;
					yLoc-=YINCR;
				}
				break;
			case UP_LEFT:
				if (Board.isShore(xLoc-XINCR,yLoc-YINCR)){
					xLoc-=XINCR;
					yLoc-=YINCR;
				}
				break;
			case DOWN_RIGHT:
				if (Board.isShore(xLoc + XINCR+CRAB_WIDTH, yLoc + YINCR)){
					xLoc+=XINCR;
					yLoc+=YINCR;
				}
				break;
			case DOWN_LEFT:
				if (Board.isShore(xLoc - XINCR, yLoc + YINCR)){
					xLoc-= XINCR;
					yLoc+= YINCR;
				}
				break;
		}
	}

	public int getxLoc() {
		return xLoc;
	}
	public void setxLoc(int x) {
		this.xLoc = x;
	}
	public int getyLoc() {
		return yLoc;
	}
	public void setyLoc(int y) {
		this.yLoc = y;
	}
	
	public void setDirection(int direct){
		this.direction = direct;
	}

}
