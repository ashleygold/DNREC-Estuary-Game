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


import g4.mainController.MainMenu;

public class BeachView extends JPanel{
	//This game's window
	public JFrame frame;
	public final int FRAME_WIDTH = 1100;
	public final int FRAME_HEIGHT = 930;
	
	private final int USER_WIDTH = 100;
	private final int USER_HEIGHT = 100;
	private final int IMG_WIDTH = 150;
	private final int IMG_HEIGHT = 150;

	//Dimensions & locations of images
	private static final String[] crabImagesLoc = {"images/BeachImages/bluecrab_0.png",
	"images/BeachImages/bluecrab_1.png", "images/BeachImages/bluecrab_2.png",
	"images/BeachImages/bluecrab_back.png"};
	
	private static final String[] turtleImagesLoc = {"images/BeachImages/bogturtle_left_0.png",
		"images/BeachImages/bogturtle_left_1.png", "images/BeachImages/bogturtle_left_2.png",
		"images/BeachImages/bogturtle_right_0.png", "images/BeachImages/bogturtle_right_1.png",
		"images/BeachImages/bogturtle_right_2.png"};
	
	private static final String[] protectorsLoc = {"images/BeachImages/grass.png", 
			"images/BeachImages/oysters.png", "images/BeachImages/seawall.png"};
	
	private final BufferedImage[] crabImages = new BufferedImage[crabImagesLoc.length];
	private final JLabel[] protectors = new JLabel[protectorsLoc.length];
	private final BufferedImage[] turtleImages = new BufferedImage[turtleImagesLoc.length];
	
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
	
	public BeachView(int width, int height, int crabx, int craby){
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
		frame.add(wavesImage);
		wavesImage.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT/2);
		wavesImage.setIcon(new ImageIcon(new ImageIcon("images/BeachImages/waves.png").getImage().getScaledInstance(wavesImage.getWidth(),wavesImage.getHeight(), Image.SCALE_SMOOTH)));
		frame.add(sandImage);
		sandImage.setBounds(0, FRAME_HEIGHT/2, FRAME_WIDTH, FRAME_HEIGHT/2);
		sandImage.setIcon(new ImageIcon(new ImageIcon("images/BeachImages/sand.png").getImage().getScaledInstance(sandImage.getWidth(),sandImage.getHeight(), Image.SCALE_DEFAULT)));
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//makes frame visible and sets no layout
		frame.setVisible(true);
		frame.setLayout(null);
		int space = 50;
		for (int i = 0; i < protectors.length; i++){
			frame.add(protectors[i]);
			protectors[i].setBounds(FRAME_WIDTH - IMG_WIDTH - space, FRAME_HEIGHT/4 +IMG_HEIGHT/2 + 
					(int)(i*1.2*IMG_HEIGHT), IMG_WIDTH, IMG_HEIGHT);
			protectors[i].setSize(IMG_WIDTH, IMG_HEIGHT);
		}
	}
	private class ImagePanel extends JComponent{
		private BufferedImage image;
		public ImagePanel(BufferedImage beachBackground){
			this.image = beachBackground;
		}
		@Override
		protected void paintComponent(Graphics g){
			super.paintComponent(g);
			g.drawImage(image, 0, 0, this);
		}
	}
}

