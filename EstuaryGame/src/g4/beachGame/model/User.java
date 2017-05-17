package g4.beachGame.model;

public class User {
	
	/**height of the crab*/
	public final static int CRAB_HEIGHT=50;
	
	/**width of the crab*/
	public final static int CRAB_WIDTH=70;
	
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
	
	/**number of frames available for crab images */
	public int frameCount = 3;
	
	/**possible directions of user */
	public static final int RIGHT = 0, UP = 1, DOWN = 2, LEFT = 3, UP_RIGHT = 4,
			UP_LEFT = 5, DOWN_RIGHT = 6, DOWN_LEFT = 7;
	
	/**reference to possible state of when the user is still*/
	public static int STILL=8; 
	
	/**direction of user */
	private int direction = STILL;
	
	
	/**
	 * Constructor to create a user 
	 */
	public User(){
		this.xLoc = DEFAULTX;
		this.yLoc = DEFAULTY;
	}
	
	/**
	 * Returns if the user is on the shore based on its coordinates
	 * @param x xLocation of the user
	 * @param y yLocation of the user
	 * @return boolean whether or not the user is on the shore
	 */
	protected boolean isShore(double x, double y){
		return (x>=0 && x<Board.SHORE_WIDTH && y>Board.SHORE_HEIGHT && y<Board.HEIGHT-CRAB_HEIGHT);
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
				if (isShore(xLoc - XINCR, yLoc))
					xLoc-=XINCR;
				break;
			case RIGHT:
				if (isShore(xLoc + XINCR, yLoc) &&
						isShore(xLoc + XINCR+CRAB_WIDTH, yLoc))
					xLoc+=XINCR;
				break;
			case UP:
				if (isShore(xLoc, yLoc - YINCR)
						&& isShore(xLoc+CRAB_WIDTH, yLoc - YINCR))
					yLoc-=YINCR;
				break;
			case DOWN:
				if (isShore(xLoc, yLoc+YINCR)) 
					yLoc+=YINCR;
				break;
			case UP_RIGHT:
				if (isShore(xLoc + XINCR+CRAB_WIDTH, yLoc-YINCR)){
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
				if (isShore(xLoc + XINCR+CRAB_WIDTH, yLoc + YINCR)){
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
	
	/**
	 * Returns integer representation of picNum
	 * @return integer representation of picNum
	 */
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
	
	public int getDirection(){
		return direction;
	}

}
