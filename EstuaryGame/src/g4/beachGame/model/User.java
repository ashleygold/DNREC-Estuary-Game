package g4.beachGame.model;

import g4.mainController.MainMenu;

public class User {
	private final int CRAB_HEIGHT=115;
	private final int CRAB_WIDTH=245;
	
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
	
	
	/*checks to see if the user is on the shore*/
	public boolean isShore(double x, double y){
		return (x>0 && x<Board.WIDTH -CRAB_WIDTH && y>Board.shoreline && y<Board.HEIGHT-CRAB_HEIGHT);
	}
	
	public int getPicNum(){
		return (int) picNum;
	}
	
	public void move() {
		//checks nested in the interest of efficiency
		picNum = (picNum + .2) % frameCount;
		switch(direction) {
			case LEFT:
				if (isShore(xLoc - XINCR, yLoc))
					xLoc-=XINCR;
				break;
			case RIGHT:
				if (isShore(xLoc + XINCR, yLoc))
					xLoc+=XINCR;
				break;
			case UP:
				if (isShore(xLoc, yLoc - YINCR))
					yLoc-=YINCR;
				break;
			case DOWN:
				if (isShore(xLoc, yLoc+YINCR))
					yLoc+=YINCR;
				break;
			case UP_RIGHT:
				if (isShore(xLoc + XINCR, yLoc-YINCR)){
					xLoc+=XINCR;
					yLoc-=YINCR;
				}
				break;
			case UP_LEFT:
				if (isShore(xLoc-XINCR,yLoc-YINCR)){
					xLoc-=XINCR;
					yLoc-=YINCR;
				}
				break;
			case DOWN_RIGHT:
				if (isShore(xLoc + XINCR, yLoc + YINCR)){
					xLoc+=XINCR;
					yLoc+=YINCR;
				}
				break;
			case DOWN_LEFT:
				if (isShore(xLoc - XINCR, yLoc + YINCR)){
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
