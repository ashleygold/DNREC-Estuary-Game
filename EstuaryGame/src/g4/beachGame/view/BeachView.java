package g4.beachGame.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import g4.beachGame.model.Board;
import g4.beachGame.model.Boat;
import g4.beachGame.model.CruiseLiner;
import g4.beachGame.model.Sailboat;
import g4.beachGame.model.Turtle;
import g4.beachGame.model.User;
import g4.beachGame.model.Wave;
import g4.mainController.MainMenu;

public class BeachView extends JPanel{
	/**
	 * Default Serializable required since it implements Serializable
	 */
	private static final long serialVersionUID = 1L;

	/**Beach game's window */
	public JFrame frame;

	/**Board for the game*/
	private Board board;
	
	/**User for the game*/
	private User user;

	//index for the image's location in turtle[i]
	
	/**locations of blue crab images*/
	private static final String[] crabImagesLoc = {"bluecrab_0.png",
	"bluecrab_1.png", "bluecrab_2.png"};
	
	/** horseshoe crab image locations */
	private static final String[] horseshoeImagesLoc = {"horseshoe_east.png", 
			"horseshoe_north.png",
			"horseshoe_south.png", "horseshoe_west.png", 
			"horseshoe_northeast.png", "horseshoe_northwest.png",
			"horseshoe_southeast.png", "horseshoe_southwest.png", 
			"horseshoe_east.png"};
	
	/**locations of left facing turtle's images */
	private static final String[] leftTurtleImagesLoc = {"bogturtle_left_0.png",
		"bogturtle_left_1.png", "bogturtle_left_2.png"};
	
	/**locations of right facing turtle's images*/
	private static final String[] rightTurtleImagesLoc = {"bogturtle_right_0.png",
		"bogturtle_right_1.png", "bogturtle_right_2.png"};
	
	/** locations of protector images*/
	private static final String[] protectorsLoc = {"grass.png", 
		"oyster_clipart.png", "concrete-block-clipart.png"};
	
	/**locations of shore images */
	private static final String[] shoreImagesLoc = {
			"tile_sand_center.png", "tile_water_C.png", "tile_grass_north_deteriorated.png",
			"tile_grass_north.png", "seawall.png", "gabion.png", "gabion_l.png", "gabion_2l.png",
			"tile_sand_north.png","tile_water_S.png"};
	
	/**locations of boat images */
	private static final String[] boatImagesLoc = {"cruiseliner.png", "cruiselinerLeft.png", "sailboat.png",
		"sailboatRight.png", "speedboat.png", "speedboatLeft.png"};
	
	/** images for horseshoe crab */
	private BufferedImage[] horseshoeImages = new BufferedImage[horseshoeImagesLoc.length];
	
	/**locations of wind images */
	private static final String[] windImagesLoc = {"windLeft.png", "windRight.png"};
	
	/**array of blue crab images */
	private BufferedImage[] crabImages = new BufferedImage[crabImagesLoc.length];
	
	/**array of protector images */
	private BufferedImage[] protectors = new BufferedImage[protectorsLoc.length];
	
	/**array of left facing turtle images */
	private BufferedImage[] leftTurtleImages = new BufferedImage[leftTurtleImagesLoc.length];
	
	/**array of right facing turtle images */
	private BufferedImage[] rightTurtleImages = new BufferedImage[rightTurtleImagesLoc.length];
	
	/**array of shore images */
	private BufferedImage[] shoreImages = new BufferedImage[shoreImagesLoc.length];
	
	/**array of boat images */
	private BufferedImage[] boatImages = new BufferedImage[boatImagesLoc.length];
	
	/**array of wind images */
	private BufferedImage[] windImages = new BufferedImage[windImagesLoc.length];
	
	/**the  background image*/
	private BufferedImage background = createImage("background.png");
	
	/**tutorial images*/
	BufferedImage title = createImage("Title.png"),tut1 = createImage("Tut2.png"),tut2 = createImage("Tut3.png"),
			tut3 = createImage("Tut4.png"), tut4 = createImage("Tut5.png"),tut5 = createImage("Tut6.png"),
			arrowDown = createImage("arrowDown.png"),
			
	/**wave image*/		
	waveImage;
	
	/**the height of the display*/
	private int displayHeight;
	
	/** */
	JLabel time; 
	
	/**
	 * Converts file name/location to Buffered Image
	 * @param fileName: filename/location of the image to be created 
	 * @return: a BufferedImage of the input file
	 */
	private BufferedImage createImage(String fileName){ 
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File("images/BeachImages/"+fileName));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *Constructor to create a new beachView 
	 * @param b: board for the current game
	 */
	public BeachView(Board b){
		board = b;
		this.setFocusable(true);
		user = board.user;
		//loads images into imageIcons
		for (int i = 0; i < protectors.length; i++){
			protectors[i] = createImage(protectorsLoc[i]);
		}
		for (int i = 0; i < crabImages.length; i++)
			crabImages[i] = createImage(crabImagesLoc[i]);
		
		for (int i = 0; i < leftTurtleImages.length; i++)
			leftTurtleImages[i] = createImage(leftTurtleImagesLoc[i]);
		
		for (int i = 0; i < rightTurtleImages.length; i++)
			rightTurtleImages[i] = createImage(rightTurtleImagesLoc[i]);
		
		for (int i = 0; i < shoreImages.length; i++)
			shoreImages[i] = createImage(shoreImagesLoc[i]);
		
		for (int i = 0; i < boatImages.length; i++)
			boatImages[i] = createImage(boatImagesLoc[i]);
		
		for (int i = 0; i < windImages.length; i++)
			windImages[i] = createImage(windImagesLoc[i]);
		
		for (int i = 0; i < horseshoeImages.length; i++)
			horseshoeImages[i] = createImage(horseshoeImagesLoc[i]);
		waveImage = createImage("wave.png");
		
		//sets up frame
		frame = new JFrame("Minigame 2: Shore Defense");
		frame.getContentPane().add(this);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(Board.getWidth(), b.getHeight() + 100);
		//makes frame visible and sets no layout
		frame.setVisible(true);
		frame.setLayout(null);
		
		displayHeight = b.getHeight()+100;
	}
	
	/**
	 * Paints all graphics to the screen
	 */
	public void paint(Graphics g){
		//drawing board

		g.drawImage(background, 0, 0, Board.getWidth(),displayHeight,this);
		g.drawImage(shoreImages[8], 0, board.getHeight()/2, Board.getWidth(), board.getHeight()/6, null, this); //top layer of sand
		BufferedImage image = null;
		for (int row = 0; row < board.beach.length; row++){
			for (int col = 0; col < board.beach[0].length; col++){
				if (board.beach[row][col] == Board.WATER) //probably have to pass these numbers in via controller
					image = shoreImages[Board.WATER];
				else if (board.beach[row][col] >= Board.GRASS && board.beach[row][col] <= Board.GABION_2L)
					image = shoreImages[board.beach[row][col]];
				if (board.beach[row][col] != Board.SHORE)
					g.drawImage(image, col*(Board.getWidth() - 100)/12 -1, board.posArr[row] - 1, (Board.getWidth() - 100)/12 +2, board.getHeight()/6 +2, null, this);
			}
		}
		
		//draws protectors
			for (int i = 0; i < protectors.length; i++){
				g.drawImage(protectors[i], Board.getWidth()-100, (3+i)*board.getHeight()/6 - 10*i, 85, board.getHeight()/7, null, this);
			}
		
		//if user is holding a protector, draw it attached to user
		if (board.getProtector()!= -1){
			switch(board.getProtector()){
			case Board.GABION_2L:
				image = shoreImages[Board.GABION_2L];
				break;
			case Board.GRASS_L:
				image = shoreImages[Board.GRASS_L];
				break;
			case Board.WALL:
				image = shoreImages[Board.WALL];
				break;
			}
			g.drawImage(image, user.getxLoc(), user.getyLoc()-30, User.CRAB_WIDTH, 40, null, this);
		}
		
		//drawing user
		if (MainMenu.getCurState()==MainMenu.DE){
			if(user.getDirection()!=-1){
				horseshoeImages[8]=horseshoeImages[user.getDirection()];
			}
			g.drawImage(horseshoeImages[user.getDirection()],
					user.getxLoc(), user.getyLoc(), User.CRAB_WIDTH, 
					User.CRAB_HEIGHT, null, this);
		}
		else{
			g.drawImage(crabImages[user.getPicNum()],user.getxLoc(),
					user.getyLoc(), User.CRAB_WIDTH, User.CRAB_HEIGHT, 
					null, this);
		}
		
		//drawing the win timer
		g.setColor(Color.GRAY);
		g.fillRect(0, board.getHeight() - Board.RAISE*2, Board.getWidth(), 100); //goal to protect
		g.setColor(Color.GREEN);
		g.fillRect(0, board.getHeight() - Board.RAISE*2, (int) (Board.getWidth()*((double)(board.TOTAL_HOURS-board.hoursLeft)/board.TOTAL_HOURS)), 100);
		
		//drawing turtles
		Iterator<Turtle> turtleIt = board.getCurrTurtles().iterator();
		while (turtleIt.hasNext()){
			Turtle turtle = turtleIt.next();
			if (turtle.getDirection() == 0)
				g.drawImage(leftTurtleImages[turtle.getPicNum()], turtle.getxLoc(), turtle.getyLoc(), turtle.getWidth(), turtle.getHeight(), null, this);
			else
				g.drawImage(rightTurtleImages[turtle.getPicNum()], turtle.getxLoc(), turtle.getyLoc(), turtle.getWidth(), turtle.getHeight(), null, this);
			g.setColor(Color.GRAY);
			g.fillRect(turtle.getxLoc()+turtle.getWidth(), turtle.getyLoc(), turtle.DEFAULTFRAMES/60, 10);
			
			g.setColor(Color.GREEN);
			g.fillRect(turtle.getxLoc()+turtle.getWidth(), turtle.getyLoc(), turtle.getFramesLeft()/60, 10);
		}
		
		//drawing tutorial graphics
		if(user.getxLoc()==100 && user.getyLoc()==450){
			g.drawImage(tut1, 20, 360, Color.DARK_GRAY, this);
		}
		if(board.hoursLeft>=24){
			g.drawImage(title, 400, 75, Color.DARK_GRAY, this);
		}
		else if(board.hoursLeft>=23){
			g.drawImage(tut2, 300, 400, Color.DARK_GRAY, this);
			g.drawImage(arrowDown, 430, 510, null, this);
		}
		else if(board.hoursLeft>=21){
			g.drawImage(tut3, 690, 200, Color.DARK_GRAY, this);
			g.setColor(Color.DARK_GRAY);
			g.fillRect(1000, 247, 50, 40);
			g.drawImage(arrowDown, 1000, 250, Color.DARK_GRAY, this);
		}
		else if(board.hoursLeft>=20){
			g.drawImage(tut4, 300, 350, Color.DARK_GRAY, this);
		}
		else if(board.hoursLeft>=18){
			g.drawImage(tut5, 400, 350, Color.DARK_GRAY, this);
		}
		else{
			Iterator<Boat> boatIt = board.getCurrBoats().iterator();
			BufferedImage boatImage = null;
			while (boatIt.hasNext()){
				Boat currBoat = boatIt.next();
				if (currBoat instanceof CruiseLiner){
					if (currBoat.getDirection())
						boatImage = boatImages[0];
					else
						boatImage = boatImages[1];
				}
				else if (currBoat instanceof Sailboat){
					if (currBoat.getDirection())
						boatImage = boatImages[3];
					else
						boatImage = boatImages[2];
				}
				else{
					if (currBoat.getDirection())
						boatImage = boatImages[4];
					else
						boatImage = boatImages[5];
				}
				g.drawImage(boatImage, currBoat.getXLoc()+10, currBoat.getYLoc()+10, 60, 50, null, this);
			}
			
			Iterator<Wave> wavesIt = board.getCurrWaves().iterator();
			while (wavesIt.hasNext()){
				Wave currWave = wavesIt.next();
				g.drawImage(waveImage, currWave.getX()+10, currWave.getY()+10, currWave.getLength(), 30, null, this);
			}
			Iterator<Wave> splitWavesIt = board.getSplitWaves().iterator();
			while (splitWavesIt.hasNext()){
				Wave sWave = splitWavesIt.next();
				g.fillRect(sWave.getX()+10, sWave.getY()+10, sWave.getLength(), 10);
			}
			
			if (Wave.getWindFace()==1)
				g.drawImage(windImages[1], 0, 0, 70, 60, null, this);
			else if (Wave.getWindFace()==-1)
				g.drawImage(windImages[0], 1010, 0, 70, 60, null, this);
		}
		
	}
	
}

