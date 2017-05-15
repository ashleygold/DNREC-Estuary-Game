package g4.beachGame.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
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

public class BeachView extends JPanel{
	/**Beach game's window */
	public JFrame frame;

	/**Board for the game*/
	private Board board;
	
	/**User for the game*/
	private User user;

	//index for the image's location in turtle[i]
	
	/**locations of blue crab images*/
	private static final String[] crabImagesLoc = {"images/BeachImages/bluecrab_0.png",
	"images/BeachImages/bluecrab_1.png", "images/BeachImages/bluecrab_2.png"};
	
	/**locations of left facing turtle's images */
	private static final String[] leftTurtleImagesLoc = {"images/BeachImages/bogturtle_left_0.png",
		"images/BeachImages/bogturtle_left_1.png", "images/BeachImages/bogturtle_left_2.png"};
	
	/**locations of right facing turtle's images*/
	private static final String[] rightTurtleImagesLoc = {"images/BeachImages/bogturtle_right_0.png",
		"images/BeachImages/bogturtle_right_1.png", "images/BeachImages/bogturtle_right_2.png"};
	
	/** locations of protector images*/
	private static final String[] protectorsLoc = {"images/BeachImages/grass.png", 
		"images/BeachImages/oysters.png", "images/BeachImages/seawall.png"};
	
	/**locations of shore images */
	private static final String[] shoreImagesLoc = {"images/BeachImages/tile_sand_north.png",
		"images/BeachImages/tile_sand_center.png", "images/BeachImages/tile_water_S.png", 
		"images/BeachImages/tile_water_C.png", "images/BeachImages/tile_grass_north.png"};
	
	/**locations of boat images */
	private static final String[] boatImagesLoc = {"images/BeachImages/cruiseliner.png", "images/BeachImages/cruiselinerLeft.png", "images/BeachImages/sailboat.png",
		"images/BeachImages/sailboatRight.png", "images/BeachImages/speedboat.png", "images/BeachImages/speedboatLeft.png"};
	
	/**locations of wind images */
	private static final String[] windImagesLoc = {"images/BeachImages/windLeft.png", "images/BeachImages/windRight.png"};
	
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
	private BufferedImage background = createImage("images/BeachImages/background.png");
	
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
			bufferedImage = ImageIO.read(new File(fileName));
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
		
		//sets up frame
		frame = new JFrame();
		frame.getContentPane().add(this);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(b.getWidth(), b.getHeight() + 100);
		//makes frame visible and sets no layout
		frame.setVisible(true);
		frame.setLayout(null);
		
		displayHeight = b.getHeight()+100;
	}
	
	/**
	 * Paints all graphics to the screen
	 */
	public void paint(Graphics g){
		g.drawImage(background, 0, 0, board.getWidth(),displayHeight,this);
		g.drawImage(shoreImages[0], 0, board.getHeight()/2, board.getWidth(), board.getHeight()/6, null, this); //top layer of sand
		g.setColor(Color.darkGray);
		g.fillRect(0, board.getHeight() - Board.RAISE*2, board.getWidth(), 100); //goal to protect
		g.setColor(Color.GREEN);
		g.fillRect(0, board.getHeight() - Board.RAISE*2, (int) (board.getWidth()*((double)(board.TOTAL_HOURS-board.hoursLeft)/board.TOTAL_HOURS)), 100);
		BufferedImage image = null;
		for (int row = 0; row < board.beach.length; row++){
			for (int col = 0; col < board.beach[0].length; col++){
				if (board.beach[row][col] == Board.WATER) //probably have to pass these numbers in via controller
					image = shoreImages[3];
				else if (board.beach[row][col] == Board.GRASS || board.beach[row][col]==Board.GRASS_L)
					image = shoreImages[4];
				else if (board.beach[row][col] >= Board.GABION && board.beach[row][col] <= Board.GABION_2L)
					image = protectors[1];
				else if (board.beach[row][col] == Board.WALL)
					image = protectors[2];

				if (board.beach[row][col] != Board.SHORE)
					g.drawImage(image, col*(board.getWidth() - 100)/12 -1, board.posArr[row] - 1, (board.getWidth() - 100)/12 +2, board.getHeight()/6 +2, null, this);
			}
		}
		
		//draws protectors
		for (int i = 0; i < protectors.length; i++){
			g.drawImage(protectors[i], board.getWidth()-100, (3+i)*board.getHeight()/6 - 15*i, 100, board.getHeight()/6, null, this);
		}
		
		g.drawImage(crabImages[user.getPicNum()], user.getxLoc(), user.getyLoc(), User.CRAB_WIDTH, User.CRAB_HEIGHT, null, this);
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
		
		g.setColor(Color.CYAN);
		Iterator<Wave> wavesIt = board.getCurrWaves().iterator();
		while (wavesIt.hasNext()){
			Wave currWave = wavesIt.next();
			g.fillRect(currWave.getX()+10, currWave.getY()+10, currWave.getLength(), 10);
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

