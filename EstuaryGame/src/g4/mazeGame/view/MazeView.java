package g4.mazeGame.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import g4.mainController.MainMenu;
import g4.mazeGame.controller.MazeCont;
import g4.mazeGame.model.Board;
import g4.mazeGame.model.Predator;

public class MazeView extends JPanel{
	
	/** the board to be displayed */
	private Board board;
	
	/** the width/height in pixels of each square of the maze */ 
	public final int SLOT_SPACE;
	
	/** the buffer on the edge of the screen for progress bars */
	private final int BAR_BUFFER;
	
	/** height of progress bars (salinity & food) */
	private final int BAR_HEIGHT;
	
	/** width of each block in the salinity bar */
	private final int SALINITY_CHUNK_WIDTH;
	
	/** default left corner of salinity bar */
	private final int SALINITY_LEFT_CORNER;
	
	/** blue crab image locations */
	private static final String[] crabImagesLoc = {"bluecrab_0.png",
			"bluecrab_1.png", "bluecrab_2.png"};
	
	/** horseshoe crab image locations */
	private static final String[] horseshoeImagesLoc = {"horseshoe_east.png", "horseshoe_north.png",
			"horseshoe_south.png", "horseshoe_west.png", 
			"horseshoe_northeast.png", "horseshoe_northwest.png",
			"horseshoe_southeast.png", "horseshoe_southwest.png", "horseshoe_west.png"};
	
	/** turtle image locations */
	private static final String[] turtleImagesLoc = {"turtle_east.png", "turtle_north.png",
			"turtle_northeast.png", "turtle_northwest.png", "turtle_south.png", "turtle_southeast.png",
			"turtle_southwest.png", "turtle_west.png"};
	
	/** food image locations **/
	private static final String[] foodImgLoc = {"algae_good.png",
			"algae_medium.png", "fish_eggs.png", "zebramussel.png"};
	
	/** images for blue crab */
	private final Image[] crabImages = new Image[crabImagesLoc.length];
	
	/** images for turtles */
	private final Image[] turtleImages = new Image[turtleImagesLoc.length];
	
	/** images for horseshoe crab */
	private final Image[] horseshoeImages = new Image[horseshoeImagesLoc.length];
	
	/** image of water tiles */
	private final Image water;
	
	/** image of wall tiles */
	private final Image seaWall;
	
	/** image of goal tiles */
	private final Image winGateUp;
	
	/** images of food tiles */
	private final Image[] foodTiles = new Image[foodImgLoc.length];
	
	/** font used for game logo*/
	private final Font logoF;
	
	/** font used for bar labels*/
	private final Font labelF;
	
	/** font used for tutorial text and messages */
	private final Font textF;
	
	/** color used for logo & tutorial text */
	private final Color textColor = new Color(0,105,172);
	
	/** images for tutorial */
	private BufferedImage tut3 = createImage("Tut3.png");
	private BufferedImage tut4 = createImage("Tut4.png");
	private BufferedImage tut5 = createImage("Tut5.png");
	private BufferedImage tut6 = createImage("Tut6.png");
	private BufferedImage arrowUp;
	private BufferedImage arrowRight;
	private BufferedImage arrowDown;
			
	/**
	 * Constructs a MazeView object set up to display the parameter board
	 * @param board the board which the view displays
	 */
	public MazeView(Board board) {
		this.board = board;
		
		//set display constants
		SLOT_SPACE = (int)((MazeCont.screenSize.getHeight() - 55)/board.getHeight());
		BAR_BUFFER = SLOT_SPACE/8;
		BAR_HEIGHT = SLOT_SPACE/2 + BAR_BUFFER;
		SALINITY_CHUNK_WIDTH = BAR_HEIGHT*4;
		
		SALINITY_LEFT_CORNER = -(SALINITY_CHUNK_WIDTH*Board.MAX_SALINITY + BAR_BUFFER)
				+ board.getWidth()*SLOT_SPACE;
		
		//set font sizes
		labelF = new Font("Findet Nemo", Font.PLAIN, 3*SLOT_SPACE/5);
		logoF = new Font("Findet Nemo", Font.PLAIN, 3*SLOT_SPACE/2);
		textF = new Font(Font.SANS_SERIF, Font.BOLD, SLOT_SPACE/2);
		
		//setup size
		setSize(board.getWidth()*SLOT_SPACE,board.getHeight()*SLOT_SPACE);
		setFocusable(true);
		
		//load images
		for (int i = 0; i < horseshoeImages.length; i++)
			horseshoeImages[i] = createImage(horseshoeImagesLoc[i]).getScaledInstance(
					SLOT_SPACE, SLOT_SPACE, Image.SCALE_SMOOTH);
		for (int i = 0; i < crabImages.length; i++)
			crabImages[i] = createImage(crabImagesLoc[i]).getScaledInstance(
					SLOT_SPACE, SLOT_SPACE, Image.SCALE_SMOOTH);
		for (int i = 0; i < turtleImages.length; i++)
			turtleImages[i] = createImage(turtleImagesLoc[i]).getScaledInstance(
					SLOT_SPACE, SLOT_SPACE, Image.SCALE_SMOOTH);
		for (int i = 0; i < foodImgLoc.length; i++)
			foodTiles[i] = createImage(foodImgLoc[i]).getScaledInstance(
					SLOT_SPACE, SLOT_SPACE, Image.SCALE_SMOOTH);
		
		water = createImage("waterblock.jpg").getScaledInstance(
				SLOT_SPACE, SLOT_SPACE, Image.SCALE_SMOOTH);
		
		seaWall = createImage("seaweed2.jpg").getScaledInstance(
				SLOT_SPACE, SLOT_SPACE, Image.SCALE_SMOOTH);
		
		winGateUp = createImage("uparrow.png").getScaledInstance(
				SLOT_SPACE, SLOT_SPACE, Image.SCALE_SMOOTH);
		
		arrowUp = createImage("arrowUp.png");
		arrowRight = createImage("arrowRight.png");	
		arrowDown = createImage("arrowDown.png");	
	}
	
	/**
	 * Changes the board that this object is set up to display
	 * @param board the board which the view should display
	 */
	public void changeBoard(Board board){
		this.board = board;
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
	 * Draw a String centered in the middle of a Rectangle.
	 *
	 * @param g The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 */
	private void drawCenteredLine(Graphics g, String text, Rectangle rect) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(g.getFont());
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text 
	    //(note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Draw the String
	    g.drawString(text, x, y);
	}
	
	/**
	 * Draw a text paragraph with the first line centered in the middle of a Rectangle.
	 * Please note that the rectangle parameter is modified by this function.  
	 *
	 * @param g The Graphics instance.
	 * @param text The String to draw.
	 * @param rect The Rectangle to center the text in.
	 */
	private void drawCenteredParagraph(Graphics g, String text, Rectangle rect){
		// Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(g.getFont());
	    //adjust first line upwards
	    rect.setLocation(rect.x, rect.y - rect.height/2);
	    String[] lines = text.split("\n");
	    for (int i = 0; i < lines.length; i++) {
	    	rect.setLocation(rect.x, rect.y + 2*metrics.getHeight()/3);
	    	drawCenteredLine(g, lines[i], rect);
	    }
	}
	
	/**
	 * Draws all the information from the board onto the screen, called once per tick
	 * @param g object which does the actual painting
	 */
	@Override
	public void paint(Graphics g)
	{
		long time = System.currentTimeMillis();
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
		
		g.setFont(labelF);
		g.setColor(Color.BLACK);
		drawCenteredLine(g, "F O O D", new Rectangle(BAR_BUFFER,BAR_BUFFER,board.getGoalFood()*SLOT_SPACE/2, BAR_HEIGHT));
		
		//creating salinity bar
		g.setColor(Color.GRAY);
		g.fillRect(SALINITY_LEFT_CORNER, BAR_BUFFER, 
				Board.MAX_SALINITY*SALINITY_CHUNK_WIDTH, BAR_HEIGHT);
		g.setColor(Color.WHITE);
		
		if (board.getGoalFood() != 5){
			g.fillRect(SALINITY_LEFT_CORNER + 
					(Board.MAX_SALINITY - board.getSalinity())*SALINITY_CHUNK_WIDTH,
					BAR_BUFFER, board.getSalinity()*SALINITY_CHUNK_WIDTH, BAR_HEIGHT);
		}
		else{
			g.fillRect(SALINITY_LEFT_CORNER,
					BAR_BUFFER,	SALINITY_CHUNK_WIDTH*Board.MAX_SALINITY, BAR_HEIGHT);
		}
		g.setColor(Color.GREEN);
		g.drawRect(SALINITY_LEFT_CORNER, BAR_BUFFER, Board.MAX_SALINITY*SALINITY_CHUNK_WIDTH, BAR_HEIGHT);
		
		g.setFont(labelF);
		g.setColor(Color.BLACK);
		drawCenteredLine(g, "S A L I N I T Y",
				new Rectangle(SALINITY_LEFT_CORNER, BAR_BUFFER, Board.MAX_SALINITY*SALINITY_CHUNK_WIDTH, BAR_HEIGHT));
		
		//tutorial
		if(board.getSalinity()==4){
			//logo
			g.setColor(Color.DARK_GRAY);
			g.fillRect(SLOT_SPACE/2, SLOT_SPACE*9, SLOT_SPACE*7, SLOT_SPACE*3);
			g.setColor(textColor);
			g.setFont(logoF);
			drawCenteredParagraph(g,"ESTUARY\nMAZE!",
					new Rectangle(SLOT_SPACE/2, SLOT_SPACE*9, SLOT_SPACE*7, SLOT_SPACE*3));
			g.setFont(textF);
			drawCenteredLine(g,"Welcome to the",
					new Rectangle(SLOT_SPACE/2, SLOT_SPACE*9, SLOT_SPACE*7, SLOT_SPACE/2));
			g.setColor(Color.DARK_GRAY);
			
			if(board.getUser().getXLoc()==15 && board.getUser().getYLoc()==15){
				//first instructions (movement)
				g.fillRect(SLOT_SPACE*13, 12*SLOT_SPACE, SLOT_SPACE*5, 5*SLOT_SPACE/3);
				g.setColor(textColor);
				drawCenteredParagraph(g,"This is you, a crab.\nTry moving with\nthe arrow keys.",
						new Rectangle(SLOT_SPACE*13, 12*SLOT_SPACE, SLOT_SPACE*5, SLOT_SPACE));
				g.drawImage(arrowDown, SLOT_SPACE*15, SLOT_SPACE*14, null, this);
			} else if(board.getUser().getFoodCount()<board.getGoalFood()-2){
				//food instructions
				g.fillRect(SLOT_SPACE/2, 5*SLOT_SPACE/2, SLOT_SPACE*5, 4*SLOT_SPACE);
				g.setColor(textColor);
				drawCenteredParagraph(g,"These are food\nsources for your\n"
						+ "crab. Collect them\nall and watch your\nfood bar fill!",
						new Rectangle(SLOT_SPACE/2, 5*SLOT_SPACE/2, SLOT_SPACE*5, SLOT_SPACE));
				for (int i = 0; i < foodTiles.length; i++){
					g.drawImage(foodTiles[i], 2*(i+1)*SLOT_SPACE/2, 11*SLOT_SPACE/2, null, this);
				}
				g.drawImage(arrowUp,SLOT_SPACE,SLOT_SPACE, null, this);
			}
			else if(board.getUser().getFoodCount()>=board.getGoalFood()-2 &&
					board.getUser().getFoodCount()<board.getGoalFood()){
				//salinity instructions
				g.drawImage(tut4,450,100, Color.DARK_GRAY, this);
				g.drawImage(arrowUp,575,40, null, this);
				g.drawImage(tut5,500,300, Color.DARK_GRAY, this);
			}
			else if(board.getUser().getFoodCount()==board.getGoalFood()){
				g.drawImage(tut6,120,30, Color.DARK_GRAY, this);
				g.drawImage(arrowRight,300,30, null, this);
			}
			
		}
		
		System.out.println(System.currentTimeMillis() - time);
		
	}
}
