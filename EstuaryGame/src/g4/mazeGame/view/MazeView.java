package g4.mazeGame.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import g4.mainController.MainMenu;
import g4.mazeGame.model.Board;
import g4.mazeGame.model.Predator;

public class MazeView extends JPanel{
	
	/** the board to be displayed */
	private Board board;
	
	/** the width/height in pixels of each square of the maze */ 
	public final int SLOT_SPACE=40;
	
	/** the buffer on the edge of the screen for progress bars */
	private final int BAR_BUFFER = SLOT_SPACE/8;
	
	/** height of progress bars (salinity & food) */
	private final int BAR_HEIGHT = SLOT_SPACE/2 + BAR_BUFFER;
	
	/** width of each block in the salinity bar */
	private final int SALINITY_CHUNK_WIDTH = BAR_HEIGHT*4;
	
	/** default left corner of salinity bar */
	private final int SALINITY_LEFT_CORNER;
	
	/** blue crab image locations */
	private static final String[] crabImagesLoc = {"bluecrab_0.png",
			"bluecrab_1.png", "bluecrab_2.png"};
	
	/** horseshoe crab image locations */
	private static final String[] horseshoeImagesLoc = {"horseshoe_east.png", "horseshoe_north.png",
			"horseshoe_northeast.png", "horseshoe_northwest.png", "horseshoe_south.png", "horseshoe_southeast.png",
			"horseshoe_southwest.png", "horseshoe_west.png", "horseshoe_west.png"};
	
	/** turtle image locations */
	private static final String[] turtleImagesLoc = {"turtle_east.png", "turtle_north.png",
			"turtle_northeast.png", "turtle_northwest.png", "turtle_south.png", "turtle_southeast.png",
			"turtle_southwest.png", "turtle_west.png"};
	
	/** images for blue crab */
	private BufferedImage[] crabImages = new BufferedImage[crabImagesLoc.length];
	
	/** images for turtles */
	private BufferedImage[] turtleImages = new BufferedImage[turtleImagesLoc.length];
	
	/** images for horseshoe crab */
	private BufferedImage[] horseshoeImages = new BufferedImage[horseshoeImagesLoc.length];
	
	/** image of water tiles */
	private BufferedImage water = createImage("waterblock.jpg");
	
	/** image of wall tiles */
	private BufferedImage seaWall = createImage("seaweed2.jpg");
	
	/** image of goal tiles */
	private BufferedImage winGateUp = createImage("uparrow.png");
	
	/** images of food tiles */
	private BufferedImage[] foodTiles = {createImage("algae_good.png"), 
			createImage("algae_medium.png"),
			createImage("fish_eggs.png"),
			createImage("zebramussel.png")};
	
	/** image of food text label */
	private BufferedImage food = createImage("food.png");
	
	/** image of salinity text label */
	private BufferedImage salinity = createImage("salinity.png");
	
	/** images for tutorial */
	private BufferedImage tut1 = createImage("Tut1.png");
	private BufferedImage tut2 = createImage("Tut2.png");
	private BufferedImage tut3 = createImage("Tut3.png");
	private BufferedImage tut4 = createImage("Tut4.png");
	private BufferedImage tut5 = createImage("Tut5.png");
	private BufferedImage tut6 = createImage("Tut6.png");
	private BufferedImage arrow = createImage("arrow.png");
	private BufferedImage arrowRight = createImage("arrowRight.png");
			
	/**
	 * Constructs a MazeView object set up to display the parameter board
	 * @param board the board which the view displays
	 */
	public MazeView(Board board) {
		this.board = board;
		//double initialX=board.getUser().getXLoc();
		setSize(board.getWidth()*SLOT_SPACE,board.getHeight()*SLOT_SPACE);
		setFocusable(true);
		for (int i = 0; i < horseshoeImages.length; i++)
			horseshoeImages[i] = createImage(horseshoeImagesLoc[i]);
		for (int i = 0; i < crabImages.length; i++)
			crabImages[i] = createImage(crabImagesLoc[i]);
		for (int i = 0; i < turtleImages.length; i++)
			turtleImages[i] = createImage(turtleImagesLoc[i]);
		
		SALINITY_LEFT_CORNER = -(SALINITY_CHUNK_WIDTH*Board.MAX_SALINITY + BAR_BUFFER)
				+ board.getWidth()*SLOT_SPACE;
	}
	
	/**
	 * Changes the board that this object is set up to display
	 * @param board the board which the view should display
	 */
	public void changeBoard(Board board){
		this.board = board;
		setSize(board.getWidth()*SLOT_SPACE,board.getHeight()*SLOT_SPACE);
	}
	
	/**
	 * Creates an image from a file path to the requested image
	 * @param file path to the wanted image
	 * @return the image object of the requested image
	 */
	private BufferedImage createImage(String file){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File("images/MazeImages/"+file));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    }
	
	/**
	 * Draws all the information from the board onto the screen, called once per tick
	 * @param g object which does the actual painting
	 */
	@Override
	public void paint(Graphics g)
	{
		for(int x=0; x<board.getWidth();x++){
			for(int y=0;y<board.getHeight();y++)
			{
				char c=board.getCell(x,y);
				switch(c)
				{
					case '.':case '^':
						g.drawImage(water, x*SLOT_SPACE, y*SLOT_SPACE, null, this);
						break;
					case '#':case '*':
						g.drawImage(seaWall, x*SLOT_SPACE, y*SLOT_SPACE, null, this);
						break;
					case 'o':
						g.drawImage(water, x*SLOT_SPACE, y*SLOT_SPACE, null, this);
						g.drawImage(foodTiles[(x+y)%foodTiles.length], x*SLOT_SPACE, y*SLOT_SPACE, null, this);
						break;
					case 'W':
						g.drawImage(water, x*SLOT_SPACE, y*SLOT_SPACE, null, this);
						g.drawImage(winGateUp, x*SLOT_SPACE, y*SLOT_SPACE, null, this);
				}
			}
		}
		
		//spawning all predators
		for(Predator x : board.getPredator()){
			g.drawImage(turtleImages[x.getDirection()],
					(int)(x.getXLoc()*SLOT_SPACE),
					(int)(x.getYLoc()*SLOT_SPACE), null, this);
		}
		
		//spawning user
		if (MainMenu.getCurState()==MainMenu.DE){
			if(board.getUser().getDirection()!=-1){
				horseshoeImages[8]=horseshoeImages[board.getUser().getDirection()];
			}
			g.drawImage(horseshoeImages[board.getUser().getDirection()],
					(int)(board.getUser().getXLoc()*SLOT_SPACE),
					(int)(board.getUser().getYLoc()*SLOT_SPACE), null, this);
		}
		else{
			g.drawImage(crabImages[board.getUser().getPicNum()],
					(int)(board.getUser().getXLoc()*SLOT_SPACE),
					(int)(board.getUser().getYLoc()*SLOT_SPACE), null, this);
		}
		
		//creating food bar
		g.setColor(Color.GRAY);
		g.fillRect(BAR_BUFFER, BAR_BUFFER, board.getGoalFood()*SLOT_SPACE/2, BAR_HEIGHT);
		
		g.setColor(Color.GREEN);
		g.fillRect(BAR_BUFFER, BAR_BUFFER, board.getUser().getFoodCount()*SLOT_SPACE/2, BAR_HEIGHT);
		
		g.setColor(Color.WHITE);
		g.drawRect(BAR_BUFFER, BAR_BUFFER, board.getGoalFood()*SLOT_SPACE/2, BAR_HEIGHT);
		
		g.drawImage(food, BAR_BUFFER, BAR_BUFFER, null, this);
		
		//creating salinity bar
		g.setColor(Color.GRAY);
		g.fillRect(SALINITY_LEFT_CORNER, BAR_BUFFER, 
				Board.MAX_SALINITY*SALINITY_CHUNK_WIDTH, BAR_HEIGHT);
		g.setColor(Color.WHITE);
		
		if (board.getGoalFood() != 5){
			g.fillRect(SALINITY_LEFT_CORNER + (Board.MAX_SALINITY - board.getSalinity())*SALINITY_CHUNK_WIDTH,
					BAR_BUFFER, board.getSalinity()*SALINITY_CHUNK_WIDTH, BAR_HEIGHT);
		}
		else{
			g.fillRect(SALINITY_LEFT_CORNER,
					BAR_BUFFER,	SALINITY_CHUNK_WIDTH*Board.MAX_SALINITY, BAR_HEIGHT);
		}
		g.setColor(Color.GREEN);
		g.drawRect(SALINITY_LEFT_CORNER, BAR_BUFFER, Board.MAX_SALINITY*SALINITY_CHUNK_WIDTH, BAR_HEIGHT);
		
		g.drawImage(salinity, SALINITY_LEFT_CORNER + 70, BAR_BUFFER + 1, null, this);
		
		if(board.getSalinity()==4){
			g.drawImage(tut1, 40, 300, Color.DARK_GRAY, this);
			if(board.getUser().getXLoc()==15 && board.getUser().getYLoc()==15){
				g.drawImage(tut2, 490, 520, Color.DARK_GRAY, this);
			}
			else if(board.getUser().getFoodCount()<board.getGoalFood()-2){
				g.drawImage(tut3,20,100, Color.DARK_GRAY, this);
				g.drawImage(arrow,40,40, null, this);
			}
			else if(board.getUser().getFoodCount()>=board.getGoalFood()-2 && board.getUser().getFoodCount()<board.getGoalFood()){
				g.drawImage(tut4,450,100, Color.DARK_GRAY, this);
				g.drawImage(arrow,575,40, null, this);
				g.drawImage(tut5,500,300, Color.DARK_GRAY, this);
			}
			else if(board.getUser().getFoodCount()==board.getGoalFood()){
				g.drawImage(tut6,120,30, Color.DARK_GRAY, this);
				g.drawImage(arrowRight,300,30, null, this);
			}
			
		}
		
	}
}
