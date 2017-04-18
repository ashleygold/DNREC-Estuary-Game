package g4.beachGame.view;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import com.sun.prism.Image;

import g4.mainController.MainMenu;

public class BeachView extends JPanel{
	private JFrame frame;
	private final int FRAME_WIDTH = 1100;
	private final int FRAME_HEIGHT = 600;
	
	private final int USER_WIDTH = 100;
	private final int USER_HEIGHT = 100;

	private static final String[] crabImagesLoc = {"images/BeachImages/bluecrab_0.png",
	"images/BeachImages/bluecrab_1.png", "images/BeachImages/bluecrab_2.png",
	"images/BeachImages/bluecrab_back.png"};
	
	private static final String[] turtleImagesLoc = {"images/BeachImages/bogturtle_left_0.png",
		"images/BeachImages/bogturtle_left_1.png", "images/BeachImages/bogturtle_left_2.png",
		"images/BeachImages/bogturtle_right_0.png", "images/BeachImages/bogturtle_right_1.png",
		"images/BeachImages/bogturtle_right_2.png"};
	
	private static final String[] protectorsLoc = {"images/StoryImages/TestImage.png",
		"images/StoryImages/TestImage2.png", "images/StoryImages/TestImage2.png"};
	
	private final ImageIcon[] crabImages = new ImageIcon[crabImagesLoc.length];
	private final ImageIcon[] protectors = new ImageIcon[protectorsLoc.length];
	private final ImageIcon[] turtleImages = new ImageIcon[turtleImagesLoc.length];
	
	public BeachView(){
		//loads images into imageIcons
		for (int i = 0; i < protectors.length; i++)
			protectors[i] = new ImageIcon(protectorsLoc[i]);
		for (int i = 0; i < crabImages.length; i++)
			crabImages[i] = new ImageIcon(crabImagesLoc[i]);
		for (int i = 0; i < turtleImages.length; i++)
			turtleImages[i] = new ImageIcon(turtleImagesLoc[i]);
		
		frame = new JFrame();
		frame.getContentPane().add(this);
	}
}
