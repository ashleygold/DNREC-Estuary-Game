package g4.beachGame.model;

public class User {
	
	/**height of the crab*/
	public final static int CRAB_HEIGHT=50;
	
	/**width of the crab*/
	public final static int CRAB_WIDTH=70;
	
	/**hit box buffer for collision detection*/
	private final static int BUFFER = 30;
	
	/**starting x location of user*/
	private final int DEFAULTX = 100;
	
	/**starting y location of user*/
	private final int DEFAULTY = 450;
	
	/**x increment changed when user moves*/
	private final int XINCR = 12;
	
	/**y increment changed when user moves*/
	private final int YINCR = 8;
	
	/**x location of user*/
	private int xLoc;
	
	/**y location of user*/
	private int yLoc;
	
	/**picture number of current graphic */
	private double picNum = 0;
	
	/** */
	int frameCount = 3;
	
	/**tells whether the user is in the ocean */
	public boolean isInOcean = false;
	
	/**tells whether the user is on the edge of the screen so they can't walk
	 * off the screen*/
	private boolean isOnEdge = false;
	
	/**board that the user is on */
	private final Board board;
	
	/**possible directions of user */
	public final static int STILL = 0, LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4,
			UP_RIGHT = 5, UP_LEFT = 6, DOWN_RIGHT = 7, DOWN_LEFT = 8;
	
	/**direction of user */
	private int direction = STILL;
	
	
	/**
	 *Constructor to create a user 
	 * @param b: the board that the user is on
	 */
	public User(Board b){
		this.board = b;
		this.xLoc = DEFAULTX;
		this.yLoc = DEFAULTY;
	}
	
	/**
	 * Moves the user by changing the direction of the user
	 */
	public void move() {
		//checks nested in the interest of efficiency
		if(direction!=STILL)
			picNum = (picNum + .2) % frameCount;
		switch(direction) {
			case LEFT:
				if (board.isShore(xLoc - XINCR, yLoc) || isInOcean || (isOnEdge && xLoc > 0))
					xLoc-=XINCR;
				break;
			case RIGHT:
				if ((board.isShore(xLoc + XINCR+CRAB_WIDTH, yLoc) 
						&& board.isShore(xLoc + XINCR, yLoc)) || isInOcean
						|| (isOnEdge && xLoc < Board.SHORE_WIDTH))
					xLoc+=XINCR;
				break;
			case UP:
				if ((board.isShore(xLoc, yLoc - YINCR)
						&& board.isShore(xLoc+CRAB_WIDTH, yLoc - YINCR))
						|| isInOcean || isOnEdge)
					yLoc-=YINCR;
				break;
			case DOWN:
				if (board.isShore(xLoc, yLoc+YINCR) || isInOcean) 
					yLoc+=YINCR;
				break;
			case UP_RIGHT:
				if (board.isShore(xLoc + XINCR+CRAB_WIDTH, yLoc-YINCR)
						|| isInOcean){
					xLoc+=XINCR;
					yLoc-=YINCR;
				}
				break;
			case UP_LEFT:
				if (board.isShore(xLoc-XINCR,yLoc-YINCR) || isInOcean){
					xLoc-=XINCR;
					yLoc-=YINCR;
				}
				break;
			case DOWN_RIGHT:
				if (board.isShore(xLoc + XINCR+CRAB_WIDTH, yLoc + YINCR) 
						|| isInOcean){
					xLoc+=XINCR;
					yLoc+=YINCR;
				}
				break;
			case DOWN_LEFT:
				if (board.isShore(xLoc - XINCR, yLoc + YINCR) || isInOcean){
					xLoc-= XINCR;
					yLoc+= YINCR;
				}
				break;
		}
		if (isInOcean && board.isShore(xLoc, yLoc)){
			isInOcean=false;
		}
	}
	
	public int getPicNum(){
		return (int) picNum;
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
