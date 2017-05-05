package g4.beachGame.model;

import java.util.Random;

//FIGURE OUT HOW TO DO CORNERS
//MAKE TURTLE DIE IF NO WAY OUT

public class Turtle {
	private final int TURTLE_HEIGHT=115;
	private final int TURTLE_WIDTH=245;
	
	private final int DEFAULTX = (int)(Math.random() * Board.WIDTH);
	private final int DEFAULTY = Board.HEIGHT;
	private final int XINCR = 1;
	private final int YINCR = 1;
	private final int[] directs = {0,1}; // left = 0, right = 1
	private int direction = directs[new Random().nextInt(directs.length)];
	private int xLoc;
	private int yLoc;
	private boolean gotToOcean = false;
	private int picNum = 0;
	private int numPics = 3;
	
	public Turtle(){
		xLoc = DEFAULTX;
		yLoc = DEFAULTY;
	}
	public void move(){
		picNum = (picNum+1)%numPics;
		if (direction == 0){
			if (isShore(xLoc-XINCR,yLoc-YINCR)){
				xLoc-=XINCR;
				yLoc-=YINCR;
			}
			else if (xLoc-XINCR<0)
				direction = 1;
			else if (isWater(xLoc-XINCR,yLoc-YINCR))
				gotToOcean = true;
			else
				xLoc-=XINCR;
		}
		else if (direction == 1){
			if (isShore(xLoc+XINCR, yLoc-YINCR)){
				xLoc+= XINCR;
				yLoc-= YINCR;
			}
			else if (xLoc+XINCR > Board.WIDTH-TURTLE_WIDTH)
				direction = 0;
			else if (isWater(xLoc+XINCR, yLoc-YINCR))
				gotToOcean= true;
			else
				xLoc+=XINCR;
		}
	}
	/*checks to see if the turtle is on the shore*/
	public boolean isShore(double xLoc, double yLoc){
		int x = (int) xLoc*12/Board.SHORELINE_WIDTH;
		int y = (int) Math.ceil(yLoc*6/Board.HEIGHT);
		int cell;
		System.out.println("x is " + x + ", y is " + y);
		try{
			cell = Board.beach[y-4][x];
		}catch(ArrayIndexOutOfBoundsException e){
			return false;
		}
		return (xLoc>0 && xLoc<Board.WIDTH-TURTLE_WIDTH && cell == Board.SHORE);
	}
	public boolean isWater(double xLoc, double yLoc){
		int x = (int) xLoc*12/Board.SHORELINE_WIDTH;
		int y = (int) Math.ceil(yLoc*6/Board.HEIGHT);
		int cell = 0;
		try{
			cell = Board.beach[y-4][x];
		}catch(ArrayIndexOutOfBoundsException e){
			if (y==3)
				return true;
		}
		return (cell == Board.WATER);
	}
	public boolean isProtector(double xLoc, double yLoc){
		int x = (int) xLoc*12/Board.SHORELINE_WIDTH;
		int y = (int) Math.ceil(yLoc*6/Board.HEIGHT);
		int cell = Board.beach[y-4][x];
		return (cell == Board.GRASS || cell == Board.GRASS_L || cell == Board.GABION || cell == Board.GABION_L ||
			cell ==	Board.GABION_2L || cell == Board.WALL);
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
	public boolean getGotToOcean() {
		return gotToOcean;
	}
	public int getDirection(){
		return direction;
	}
	public int getPicNum(){
		return picNum;
	}
}
