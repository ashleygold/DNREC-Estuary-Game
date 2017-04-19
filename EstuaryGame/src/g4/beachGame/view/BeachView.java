package g4.beachGame.view;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import g4.beachGame.model.Board;
import g4.beachGame.model.User;
import g4.mainController.MainMenu;

public class BeachView extends JPanel{
	//This game's window
	public JFrame frame;	
	private final int USER_WIDTH = 100;
	private final int USER_HEIGHT = 100;
	private final int IMG_WIDTH = 100;
	private final int IMG_HEIGHT = 100;
	private User user;

	//Dimensions & locations of images
	private static final String[] crabImagesLoc = {"images/BeachImages/bluecrab_0.png",
	"images/BeachImages/bluecrab_1.png", "images/BeachImages/bluecrab_2.png"};
	
	private static final String[] turtleImagesLoc = {"images/BeachImages/bogturtle_left_0.png",
		"images/BeachImages/bogturtle_left_1.png", "images/BeachImages/bogturtle_left_2.png",
		"images/BeachImages/bogturtle_right_0.png", "images/BeachImages/bogturtle_right_1.png",
		"images/BeachImages/bogturtle_right_2.png"};
	
	private static final String[] protectorsLoc = {"images/BeachImages/grass.png", 
			"images/BeachImages/oysters.png", "images/BeachImages/seawall.png"};
	
	private BufferedImage[] crabImages = new BufferedImage[crabImagesLoc.length];
	private JLabel[] protectors = new JLabel[protectorsLoc.length];
	private BufferedImage[] turtleImages = new BufferedImage[turtleImagesLoc.length];
	
	//converts filename to buffered image
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
	
	public BeachView(Board b){
		int frame_width = b.getWidth();
		int frame_height = b.getHeight();
		user = b.user;
		//loads images into imageIcons
		for (int i = 0; i < protectors.length; i++){
			protectors[i] = new JLabel();
			protectors[i].setIcon(new ImageIcon(protectorsLoc[i]));
		}
		for (int i = 0; i < crabImages.length; i++)
			crabImages[i] = createImage(crabImagesLoc[i]);
		for (int i = 0; i < turtleImages.length; i++)
			turtleImages[i] = createImage(turtleImagesLoc[i]);
		BufferedImage beachBackground = createImage("images/BeachImages/beach.png");
		//creates Jlabels to create background in two parts
		JLabel wavesImage = new JLabel();
		JLabel sandImage = new JLabel();
	
		//sets up frame
		frame = new JFrame();
		frame.getContentPane().add(this);
		//frame.setContentPane(new ImagePanel(beachBackground));
		
		
		//adds background images
		this.add(wavesImage);
		wavesImage.setBounds(0, 0, frame_width, frame_height/2);
		wavesImage.setIcon(new ImageIcon(new ImageIcon("images/BeachImages/waves.png").getImage().getScaledInstance(wavesImage.getWidth(),wavesImage.getHeight(), Image.SCALE_SMOOTH)));
		this.add(sandImage);
		sandImage.setBounds(0, frame_height/2, frame_width, frame_height/2);
		sandImage.setIcon(new ImageIcon(new ImageIcon("images/BeachImages/sand.png").getImage().getScaledInstance(sandImage.getWidth(),sandImage.getHeight(), Image.SCALE_DEFAULT)));
		
		frame.setLayout(null);
		
		int space = 50;
		for (int i = 0; i < protectors.length; i++){
			frame.add(protectors[i]);
			protectors[i].setBounds(frame_width - IMG_WIDTH - space, frame_height/4 +IMG_HEIGHT/2 + 
					(int)(i*1.2*IMG_HEIGHT), IMG_WIDTH, IMG_HEIGHT);
			protectors[i].setSize(IMG_WIDTH, IMG_HEIGHT);
		}
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(frame_width, frame_height);
		//makes frame visible and sets no layout
		frame.setVisible(true);
		
		
	}
	public void paint(Graphics g){
		g.drawImage(crabImages[user.getPicNum()], user.getxLoc(), user.getyLoc(), null, this);
	}
}

