package g4.mazeGame.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import g4.mainController.MainMenu;
import g4.mazeGame.controller.MazeCont;
import g4.mazeGame.model.Board;
import g4.mazeGame.model.Predator;

public class MazeView extends JPanel{
	
	/**
	 * Default Serializable required since it implements Serializable
	 */
	private static final long serialVersionUID = 1L;

	/** the board to be displayed */
	private Board board;
	
	/** the width/height in pixels of each square of the maze */ 
	public final int SLOT_SPACE;
	
	/** the buffer on the edge of the screen for progress bars */
	private final int BAR_BUFFER;
	
	/** height of progress bars (salinity and food) */
	private final int BAR_HEIGHT;
	
	/** width of each block in the salinity bar */
	private final int SALINITY_CHUNK_WIDTH;
	
	/** default left corner of salinity bar */
	private final int SALINITY_LEFT_CORNER;
	
	/** control codes which handle transition buttons */
	public static final byte MESSAGE_TUTORIAL = 0, MESSAGE_STAGE = 1, 
			MESSAGE_VICTORY = 2, MESSAGE_FAILURE = 3;
	
	/** strings for transition buttons */
	private final String[] transitionText = {
			"Great job!<br><br>Click to start the first stage",
			"Great job!<br><br>The salinity has decreased by 33%<br>Click to advance to the next stage",
			"You win!<br><br>You made it to an area of <br>lower salinity, so now you<br>can grow big and strong!",
			"You were eaten!<br><br>Click to restart the<br>stage you were on"
	};
	
	/** knows if the transition JButton has been pressed */
	private boolean isTransitionActive = false;
	
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
			"turtle_south.png", "turtle_west.png", "turtle_northeast.png", "turtle_northwest.png",
			"turtle_southeast.png",	"turtle_southwest.png"};
	
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
	
	/** font used for bar labels*/
	private final Font labelF;
	
	/** images for tutorial */
	private BufferedImage tut1, tut2, tut3, tut4, tut5, tut6, arrowUp, arrowRight;
	
	/**
	 * Constructs a MazeView object set up to display the parameter board
	 * @param board the board which the view displays
	 */
	public MazeView(Board board) {
		this.board = board;
		
		//set display constants
		SLOT_SPACE = (int)((MazeCont.screenSize.getHeight() - 55) / board.HEIGHT);
		BAR_BUFFER = SLOT_SPACE/8;
		BAR_HEIGHT = SLOT_SPACE/2 + BAR_BUFFER;
		SALINITY_CHUNK_WIDTH = BAR_HEIGHT*4;
		
		SALINITY_LEFT_CORNER = -(SALINITY_CHUNK_WIDTH * Board.MAX_SALINITY + BAR_BUFFER)
				+ board.WIDTH*SLOT_SPACE;
		
		//set font sizes
		labelF = new Font("Findet Nemo", Font.PLAIN, 3*SLOT_SPACE/5);
		
		//setup size
		setSize(board.WIDTH * SLOT_SPACE,board.HEIGHT * SLOT_SPACE);
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
		
		tut1 = createImage("Tut1.png");
		tut2 = createImage("Tut2.png");
		tut3 = createImage("Tut3.png");
		tut4 = createImage("Tut4.png");
		tut5 = createImage("Tut5.png");
		tut6 = createImage("Tut6.png");
		
		arrowUp = createImage("arrowUp.png");
		arrowRight = createImage("arrowRight.png");
	}
	
	/**
	 * Changes the board that this object is set up to display
	 * @param board the board which the view should display
	 */
	public void changeBoard(Board board){
		this.board = board;
		setSize(board.WIDTH * SLOT_SPACE, board.HEIGHT * SLOT_SPACE);
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
	 * This adds a transition text JButton to the screen when an event occurs
	 * @param code the transition menu identification key
	 */
	public void messageInterrupt(byte code){
		isTransitionActive = true;
		JButton message = new JButton("<html><center>" + transitionText[code] + "</center></html>");
		message.setBounds(0,0,getWidth(),getHeight());
		message.setFont(new Font("Findet Nemo", Font.PLAIN, 40));
		message.setBackground(MainMenu.SEA_GREEN);
		message.setForeground(MainMenu.TEXT_BROWN);
		message.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				handleMessageClick(message);
			}
		});
		add(message);
	}
	
	/**
	 * This function removes transition JButtons from the screen when they are clicked
	 * @param mes the JButton which triggered the event
	 */
	private void handleMessageClick(JButton mes){
		this.remove(mes);
		isTransitionActive = false;
	}
	
	
	/**
	 * Draws all the information from the board onto the screen, called once per tick
	 * @param g object which does the actual painting
	 */
	@Override
	public void paint(Graphics g)
	{
		if (!isTransitionActive){
			//fix antialiasing for text
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			for(int x = 0; x < board.WIDTH; x++){
				for(int y = 0; y < board.HEIGHT; y++)
				{
					char c = board.getCell(x,y);
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
			if (MainMenu.getCurState() == MainMenu.DE){
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
				g.drawImage(tut1, SLOT_SPACE, SLOT_SPACE*9, Color.DARK_GRAY, this);
				g.setColor(Color.DARK_GRAY);
				
				if(board.getUser().getXLoc() == 15 && board.getUser().getYLoc()==15){
					//first instructions (movement)
					g.drawImage(tut2, SLOT_SPACE*13, 13*SLOT_SPACE, Color.DARK_GRAY, this);
				} else if(board.getUser().getFoodCount()<board.getGoalFood()-2){
					//food instructions
					g.drawImage(tut3, SLOT_SPACE/2, 5*SLOT_SPACE/2, Color.DARK_GRAY, this);
					g.drawImage(arrowUp, SLOT_SPACE, SLOT_SPACE, null, this);
				}
				else if(board.getUser().getFoodCount()>=board.getGoalFood()-2 &&
						board.getUser().getFoodCount()<board.getGoalFood()){
					//salinity instructions
					g.drawImage(tut4, 23*SLOT_SPACE/2, 5*SLOT_SPACE/2, Color.DARK_GRAY, this);
					g.drawImage(arrowUp, 29*SLOT_SPACE/2, SLOT_SPACE, null, this);
					g.drawImage(tut5, 12*SLOT_SPACE, 7*SLOT_SPACE, Color.DARK_GRAY, this);
				}
				else if(board.getUser().getFoodCount()==board.getGoalFood()){
					//exit instructions
					g.drawImage(tut6, 7*SLOT_SPACE/2, SLOT_SPACE/2, Color.DARK_GRAY, this);
					g.drawImage(arrowRight, 15*SLOT_SPACE/2, SLOT_SPACE/3, null, this);
				}
				
			}
		} else {
			//repaints JButton
			super.paint(g);
		}
	}
	
	public boolean getIsTransitionActive(){
		return isTransitionActive;
	}
}
