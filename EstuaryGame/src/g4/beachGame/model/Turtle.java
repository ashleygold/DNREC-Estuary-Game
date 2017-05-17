package g4.beachGame.model;

import java.util.Random;

//FIGURE OUT HOW TO DO CORNERS

public class Turtle {
	
	/**Height of the turtle*/
	private final int TURTLE_HEIGHT=50;
	
	/**Width of the turtle*/
	private final int TURTLE_WIDTH=70;
	
	/**Starting x location */
	private final int DEFAULTX = 50+ (int)(Math.random() * Board.SHORE_WIDTH-50);
	
	/**Starting y location */
	private final int DEFAULTY = Board.HEIGHT;
	
	/**x-increment that the turtle moves*/
	private final int XINCR = 1;
	
	/**y-increment that the turtle moves*/
	private final int YINCR = 1;
	
	/**possible directions of the turtle, left=0, right=1 */
	private final int[] directs = {0,1}; // left = 0, right = 1
	
	/**current direction of the turtle, randomly selected*/
	private int direction = directs[new Random().nextInt(directs.length)];
	
	/**turtle's x location*/
	private int xLoc;
	
	/**turtle's y location */
	private int yLoc;
	
	/**tells whether the turtle has gotten to the ocean */
	private boolean gotToOcean = false;
	
	/**picture number of current animation */
	private double picNum = 0;
	
	/**number of turtle pictures */
	private int numPics = 3;
	
	/**default number of frames that a turtle is able to be on screen for */
	public final int DEFAULTFRAMES = 1600;
	
	/**number of frames that a turtle has left on screen */
	private int framesLeft;
	
	/**The board the turtle is on */
	private final Board board;
	
	/**
	 * Constructs a turtle using the given board
	 * @param b board on which the turtle is placed
	 */
	public Turtle(Board b){
		board = b;
		xLoc = DEFAULTX;
		yLoc = DEFAULTY;
		setFramesLeft(DEFAULTFRAMES);
	}
	
	/**
	 * Moves the turtle in upward motion and around protectors to get to ocean
	 */
	public void move(){
		picNum = (picNum+.2)%numPics;
		if (direction == 0){ //left
			//if normal land
			if (isShore(xLoc-XINCR,yLoc-YINCR)){
				xLoc-=XINCR;
				yLoc-=YINCR;
			}
			//if you've reached the left edge
			else if (xLoc-XINCR<=0)
				direction = 1;
			//if this is water
			else if (isWater(xLoc-XINCR,yLoc-YINCR))
				gotToOcean = true;
			//if this is a protector
			else if (!isShore(xLoc-XINCR,yLoc))
				yLoc+=YINCR;
			else
				xLoc-=XINCR;
		}
		else if (direction == 1){
			if (isShore(xLoc+XINCR, yLoc-YINCR)){
				xLoc+= XINCR;
				yLoc-= YINCR;
			}
			else if (xLoc+XINCR >=Board.getWidth()-TURTLE_WIDTH-100)
				direction = 0;
			else if (isWater(xLoc+XINCR, yLoc-YINCR))
				gotToOcean= true;
			else if (!isShore(xLoc+XINCR,yLoc))
				yLoc+=YINCR;
			else
				xLoc+=XINCR;
		}
	}
	
	/**
	 * Checks to see if turtle is on the shore
	 * @param xLoc x coordinate to be checked
	 * @param yLoc y coordinate to be checked
	 * @return true if turtle is on shore, false otherwise
	 */
	protected boolean isShore(double xLoc, double yLoc){
		int x = (int) xLoc*12/Board.SHORE_WIDTH;
		int y = (int) Math.ceil(yLoc*6/Board.HEIGHT);
		int cell;
		if (x>11 || y>6 || y<=3)
			return false;
		else
			cell = board.beach[y-4][x];
		return (xLoc>0 && xLoc<Board.getWidth()-TURTLE_WIDTH-100 && (cell == Board.SHORE||cell == Board.GRASS || cell == Board.GRASS_L));
	}
	
	/**
	 * Checks to see if turtle is on the water
	 * @param xLoc x-coordinate to be checked
	 * @param yLoc y-coordinate to be checked
	 * @return true if turtle is on the water, false otherwise
	 */
	protected boolean isWater(double xLoc, double yLoc){
		int x = (int) xLoc*12/Board.SHORE_WIDTH;
		int y = (int) Math.ceil(yLoc*6/Board.HEIGHT);
		int cell = Board.SHORE;
		if (y<=3 && xLoc>0 && xLoc<Board.getWidth()-TURTLE_WIDTH-100)
			return true;
		else if (x>11 || y>6||x<=0)
			return false;
		else
			cell = board.beach[y-4][x];
		return (xLoc>0 && xLoc<Board.getWidth()-TURTLE_WIDTH-100 && cell == Board.WATER);
	} 	
	
	public int getxLoc() {
		return xLoc;
	}
	public int getyLoc() {
		return yLoc;
	}
	public void setxLoc(int x){
		xLoc = x;
	}
	public void setyLoc(int y){
		yLoc = y;
	}
	public boolean getGotToOcean() {
		return gotToOcean;
	}
	public int getDirection(){
		return direction;
	}
	public void setDirection(int x){
		direction = x;
	}
	
	/**
	 * Returns integer representation of picNum
	 * @return picNum as an integer
	 */
	public int getPicNum(){
		return (int)picNum;
	}
	public int getFramesLeft() {
		return framesLeft;
	}
	public void setFramesLeft(int framesLeft) {
		this.framesLeft = framesLeft;
	}
	public int getWidth(){
		return TURTLE_WIDTH;
	}
	public int getHeight(){
		return TURTLE_HEIGHT;
	}
}
