package g4.beachGame.model;

public class User {
	public final static int CRAB_HEIGHT=50;
	public final static int CRAB_WIDTH=70;
	private final static int BUFFER = 30;
	
	private final int DEFAULTX = 15;
	private final int DEFAULTY = 450;
	private final int XINCR = 12;
	private final int YINCR = 8;
	private int xLoc;
	private int yLoc;
	private double picNum = 0;
	int frameCount = 3;
	
	public boolean isInOcean = false;
	
	private boolean isOnEdge = false;
	
	private final Board board;
	
	public final static int STILL = 0, LEFT = 1, RIGHT = 2, UP = 3, DOWN = 4,
			UP_RIGHT = 5, UP_LEFT = 6, DOWN_RIGHT = 7, DOWN_LEFT = 8;
	private int direction = STILL;
	
	
	public User(Board b){
		this.board = b;
		this.xLoc = DEFAULTX;
		this.yLoc = DEFAULTY;
	}
	
	public int getPicNum(){
		return (int) picNum;
	}
	
	public void move() {
		//checks nested in the interest of efficiency
		picNum = (picNum + .2) % frameCount;
		// System.out.println(isInOcean);
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
				if (board.isShore(xLoc, yLoc+YINCR) || isInOcean || isOnEdge) 
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
						|| isInOcean || isOnEdge){
					xLoc+=XINCR;
					yLoc+=YINCR;
				}
				break;
			case DOWN_LEFT:
				if (board.isShore(xLoc - XINCR, yLoc + YINCR) || isInOcean || isOnEdge){
					xLoc-= XINCR;
					yLoc+= YINCR;
				}
				break;
		}
		if (isInOcean && board.isShore(xLoc, yLoc)){
			isInOcean=false;
		}
		isOnEdge = (yLoc >= board.getHeight() - CRAB_HEIGHT - 10);
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
