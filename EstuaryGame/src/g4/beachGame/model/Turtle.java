package g4.beachGame.model;

import java.util.Random;

//FIGURE OUT HOW TO DO CORNERS
//MAKE TURTLE DIE IF NO WAY OUT

public class Turtle {
	
	/**Height of the turtle*/
	private final int TURTLE_HEIGHT=50;
	
	/**Width of the turtle*/
	private final int TURTLE_WIDTH=70;
	
	/**Starting x location */
	private final int DEFAULTX = 2+ (int)Math.random() * Board.SHORE_WIDTH;
	
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
	
	/** */
	public final int DEFAULTFRAMES = 1600;
	
	/** */
	private int framesLeft;
	
	/**The board the turtle is on */
	private final Board board;
	
	/**
	 * Constructs a turtle using the given board
	 * @param b - board on which the turtle is placed
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
			//if grass at diagonal location
			else if (board.beach[(int) (Math.ceil((yLoc-YINCR)*6/Board.HEIGHT))-3][(xLoc-XINCR)*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]==Board.GRASS_L ||
					board.beach[(int) (Math.ceil((yLoc-YINCR)*6/Board.HEIGHT))-3][(xLoc-XINCR)*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]==Board.GRASS){
				xLoc-=XINCR;
				yLoc-=YINCR;
			}
			//if this is a protector
			else if (!isShore(xLoc-XINCR,yLoc)){
				//if grass at horizontal location
				if (board.beach[(int) (Math.ceil(yLoc*6/Board.HEIGHT))-3][(xLoc-XINCR)*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]==Board.GRASS_L ||
						board.beach[(int) (Math.ceil(yLoc*6/Board.HEIGHT))-3][(xLoc-XINCR)*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]==Board.GRASS){
					xLoc-=XINCR;
					yLoc-=YINCR;
				}
				else
					yLoc+=YINCR;
			}
			else
				xLoc-=XINCR;
		}
		else if (direction == 1){
			if (isShore(xLoc+XINCR, yLoc-YINCR)){
				xLoc+= XINCR;
				yLoc-= YINCR;
			}
			else if (xLoc+XINCR >=Board.WIDTH-TURTLE_WIDTH-100)
				direction = 0;
			else if (isWater(xLoc+XINCR, yLoc-YINCR))
				gotToOcean= true;
			else if (board.beach[(int) (Math.ceil((yLoc-YINCR)*6/Board.HEIGHT))-3][(xLoc+XINCR)*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]==Board.GRASS_L ||
					board.beach[(int) (Math.ceil((yLoc-YINCR)*6/Board.HEIGHT))-3][(xLoc+XINCR)*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]==Board.GRASS){
				xLoc+= XINCR;
				yLoc-= YINCR;
			}
			else if (!isShore(xLoc+XINCR,yLoc))
				if (board.beach[(int) (Math.ceil(yLoc*6/Board.HEIGHT))-3][(xLoc+XINCR)*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]==Board.GRASS_L ||
						board.beach[(int) (Math.ceil(yLoc*6/Board.HEIGHT))-3][(xLoc+XINCR)*Board.SPACES_OF_SHORE/Board.SHORE_WIDTH]==Board.GRASS){
					xLoc+=XINCR;
					yLoc-=YINCR;
				}
				else
					yLoc+=YINCR;
			else
				xLoc+=XINCR;
		}
	}
	/**
	 * Checks to see if turtle is on the shore
	 * @param xLoc
	 * @param yLoc
	 * @return true if turtle is on shore, false otherwise
	 */
	public boolean isShore(double xLoc, double yLoc){
		int x = (int) xLoc*12/Board.SHORE_WIDTH;
		int y = (int) Math.ceil(yLoc*6/Board.HEIGHT);
		int cell;
		if (x>11 || y>6 || y<=3)
			return false;
		else
			cell = board.beach[y-4][x];
		return (xLoc>0 && xLoc<Board.WIDTH-TURTLE_WIDTH-100 && cell == Board.SHORE);
	}
	
	/**
	 * Checks to see if turtle is on the water
	 * @param xLoc
	 * @param yLoc
	 * @return true if turtle is on the water, false otherwise
	 */
	public boolean isWater(double xLoc, double yLoc){
		int x = (int) xLoc*12/Board.SHORE_WIDTH;
		int y = (int) Math.ceil(yLoc*6/Board.HEIGHT);
		int cell = Board.SHORE;
		if (y<=3 && xLoc>0 && xLoc<Board.WIDTH-TURTLE_WIDTH-100)
			return true;
		else if (x>11 || y>6||x<=0)
			return false;
		else
			cell = board.beach[y-4][x];
		return (xLoc>0 && xLoc<Board.WIDTH-TURTLE_WIDTH-100 && cell == Board.WATER);
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
