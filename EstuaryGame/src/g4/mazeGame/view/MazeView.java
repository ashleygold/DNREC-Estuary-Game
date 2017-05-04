package g4.mazeGame.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import g4.mazeGame.model.Board;
import g4.mazeGame.model.Predator;

public class MazeView extends JPanel{
	
	private Board board;
	public final int SLOT_SPACE=40;
	private static final String[] crabImagesLoc = {"bluecrab_0.png",
			"bluecrab_1.png", "bluecrab_2.png"};
	private static final String[] turtleImagesLoc = {"turtle_east.png", "turtle_north.png",
			"turtle_northeast.png", "turtle_northwest.png", "turtle_south.png", "turtle_southeast.png",
			"turtle_southwest.png", "turtle_west.png"};
	private BufferedImage[] crabImages = new BufferedImage[crabImagesLoc.length];
	private BufferedImage[] turtleImages = new BufferedImage[turtleImagesLoc.length];
	BufferedImage water = createImage("waterblock.jpg");
	BufferedImage seaWall = createImage("seaweed2.jpg");
	BufferedImage winGateUp = createImage("uparrow.png");
	BufferedImage winGateLeft = createImage("leftarrow.png");
	BufferedImage food = createImage("food.png");
	BufferedImage salinity = createImage("salinity.png");
			
	
	public MazeView(Board board) {
		this.board = board;
		setSize(board.getWidth()*SLOT_SPACE,board.getHeight()*SLOT_SPACE);
		setFocusable(true);
		for (int i = 0; i < crabImages.length; i++)
			crabImages[i] = createImage(crabImagesLoc[i]);
		for (int i = 0; i < turtleImages.length; i++)
			turtleImages[i] = createImage(turtleImagesLoc[i]);
	}
	
	public void changeBoard(Board board){
		this.board = board;
		setSize(board.getWidth()*SLOT_SPACE,board.getHeight()*SLOT_SPACE);
	}
	
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
						g.setColor(Color.BLACK);
						g.fillOval(x*SLOT_SPACE+(SLOT_SPACE/2 - 5), y*SLOT_SPACE+(SLOT_SPACE/2 - 5), 10, 10);
						break;
					case 'W':
						g.drawImage(water, x*SLOT_SPACE, y*SLOT_SPACE, null, this);
						g.drawImage(winGateUp, x*SLOT_SPACE, y*SLOT_SPACE, null, this);
				}
			}
		}
		
		//spawning all predators
		for(Predator x : board.getPredator()){
			if (x.getDirection()==1){
				g.drawImage(turtleImages[7], (int)(x.getXLoc()*SLOT_SPACE + SLOT_SPACE/12), (int)(x.getYLoc()*SLOT_SPACE + SLOT_SPACE/12), null, this);
			}
			if (x.getDirection()==2){
				g.drawImage(turtleImages[0], (int)(x.getXLoc()*SLOT_SPACE + SLOT_SPACE/12), (int)(x.getYLoc()*SLOT_SPACE + SLOT_SPACE/12), null, this);
			}
			if (x.getDirection()==3){
				g.drawImage(turtleImages[1], (int)(x.getXLoc()*SLOT_SPACE + SLOT_SPACE/12), (int)(x.getYLoc()*SLOT_SPACE + SLOT_SPACE/12), null, this);
			}
			if (x.getDirection()==4){
				g.drawImage(turtleImages[4], (int)(x.getXLoc()*SLOT_SPACE + SLOT_SPACE/12), (int)(x.getYLoc()*SLOT_SPACE + SLOT_SPACE/12), null, this);
			}
			if (x.getDirection()==5){
				g.drawImage(turtleImages[2], (int)(x.getXLoc()*SLOT_SPACE + SLOT_SPACE/12), (int)(x.getYLoc()*SLOT_SPACE + SLOT_SPACE/12), null, this);
			}
			if (x.getDirection()==6){
				g.drawImage(turtleImages[3], (int)(x.getXLoc()*SLOT_SPACE + SLOT_SPACE/12), (int)(x.getYLoc()*SLOT_SPACE + SLOT_SPACE/12), null, this);
			}
			if (x.getDirection()==7){
				g.drawImage(turtleImages[5], (int)(x.getXLoc()*SLOT_SPACE + SLOT_SPACE/12), (int)(x.getYLoc()*SLOT_SPACE + SLOT_SPACE/12), null, this);
			}
			if (x.getDirection()==8){
				g.drawImage(turtleImages[6], (int)(x.getXLoc()*SLOT_SPACE + SLOT_SPACE/12), (int)(x.getYLoc()*SLOT_SPACE + SLOT_SPACE/12), null, this);
			}
		}
		
		//spawning user
		g.drawImage(crabImages[board.getUser().getPicNum()], (int)(board.getUser().getXLoc()*SLOT_SPACE + SLOT_SPACE/8), (int)(board.getUser().getYLoc()*SLOT_SPACE + SLOT_SPACE/8), null, this);
		
		//creating food bar
		g.setColor(Color.GRAY);
		g.fillRect(5, 5, board.getGoalFood()*20, 25);
		
		g.setColor(Color.GREEN);
		g.fillRect(5, 5, board.getUser().getFoodCount()*20, 25);
		
		g.setColor(Color.WHITE);
		g.drawRect(5, 5, board.getGoalFood()*20, 25);
		
		g.drawImage(food, 5, 5, null, this);
		
		//creating salinity bar
		g.setColor(Color.BLUE);
		g.fillRect(-305+board.getWidth()*SLOT_SPACE, 5, 300, 25);
		
		g.setColor(Color.WHITE);
		g.fillRect(-305+board.getWidth()*SLOT_SPACE + 
				(board.MAX_SALINITY - board.getSalinity())*100,
				5, board.getSalinity()*100, 25);
		
		g.setColor(Color.GREEN);
		g.drawRect(-305+board.getWidth()*SLOT_SPACE, 5, 300, 25);
		
		g.drawImage(salinity, 530, 6, null, this);
		
		
	}
}
