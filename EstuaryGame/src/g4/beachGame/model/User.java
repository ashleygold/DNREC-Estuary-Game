package g4.beachGame.model;

public class User {
	private final int DEFAULTX = 15;
	private final int DEFAULTY = 305;
	private final int XINCR = 8;
	private final int YINCR = 8;
	private int xLoc = DEFAULTX;
	private int yLoc = DEFAULTY;
	private int foodCount = 0;
	private String userImage;
	
	public void place(){
		
	}
	public void moveLeft(){
		xLoc -= XINCR;
	}
	public void moveRight(){
		xLoc += XINCR;
	}
	public void moveUp(){
		yLoc -= YINCR;
	}
	public void moveDown(){
		yLoc += YINCR;
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
}
