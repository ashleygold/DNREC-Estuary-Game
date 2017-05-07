package g4.storyGame.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import g4.mainController.MainMenu;
import g4.storyGame.model.Table;

public class StoryView extends JPanel{

	/** pointer to Model, used to get data */
	private final Table refTable;
	
	/** Window for this game */
	private final JFrame frame;
	/** Width of the window */
	private final int FRAME_WIDTH = 950;
	/** Height of the window */
	private final int FRAME_HEIGHT = 500;
	
	/** Width of images */
	private final int IMG_WIDTH = 100;
	/** Height of images */
	private final int IMG_HEIGHT = 100;
	/** files locations of images */
	private static final String[] imagesLoc = {"images/StoryImages/TestImage.png",
			"images/StoryImages/TestImage2.png",
			"images/StoryImages/TestImage3.png",
			"images/StoryImages/TestImage4.png"};
	/** The number of possible sides of cubes (total number of images) */
	public static final int NUM_SIDES = imagesLoc.length;
	
	/** Every possible image */
	private final BufferedImage[] images = new BufferedImage[imagesLoc.length];
	
	/** Buttons representing Cubes */
	private final JButton[] cubes;
	
	/**
	 * Constructor which initializes a View to display the model parameter
	 * @param t The model which this View should display
	 */
	public StoryView(Table t){
		//create all images
		for (int i = 0; i < imagesLoc.length; i++){
			images[i] = createImage(imagesLoc[i]);
		}
		
		//set reference
		refTable = t;
		
		//set up window
		frame = new JFrame();
		frame.getContentPane().add(this);
		frame.setBackground(MainMenu.BACKGROUND_BLUE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//makes frame visible and sets no layout
		frame.setVisible(true);
		frame.setLayout(null);
		
		//create JButtons for Cubes
		
		cubes = new JButton[refTable.NUM_DICE];
		
		for (int i = 0; i < refTable.NUM_DICE; i++){
			cubes[i] = new JButton(new ImageIcon(images[refTable.getCubeAt(i, true).getImg()]));
			cubes[i].setBackground(MainMenu.BACKGROUND_BLUE);
			cubes[i].addActionListener(new CubeActionListener(i));
			frame.add(cubes[i]);
			cubes[i].setBounds(IMG_WIDTH/2 + (int)(i*1.2*IMG_WIDTH), IMG_HEIGHT/2, IMG_WIDTH, IMG_HEIGHT);
			cubes[i].setSize(IMG_WIDTH, IMG_HEIGHT);
		}
	}
	
	/** 
	 * This private class allows the Cube JButtons to trigger when clicked
	 * @author Joseph Buxton
	 *
	 */
	private class CubeActionListener implements ActionListener{
		/** the cube which should be activated by this */
		private final int cubeNum; 
		/**
		 * Constructs an object which can activate the cube at the index (parameter)
		 * @param i the index of the cube which should be activated by this
		 */
		CubeActionListener(int i){
			cubeNum = i;
		}
		/**
		 * When the JButton is clicked, this calls the clicked() function to handle this 
		 * @param e the object containing the data of the click
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			clicked(cubeNum);
		}
	}
	
	/**
	 * Runs when a Cube's JButton is clicked, activates the Cube and checks if an Exit button should be displayed
	 * @param i the index of the JButton was clicked
	 */
	private void clicked(int i) {
		refTable.activateCube(i);
		if (refTable.getCubeAt(i, true).isMoved()){
			//if moved, disable the button
			cubes[i].setEnabled(false);
			
			//If everything has been added, display a close button
			if (refTable.areAllMoved()){
				JButton quit = new JButton("Return to Menu");
				quit.setFont(new Font("SansSerif", Font.BOLD, 20));
				quit.setBackground(MainMenu.SEA_GREEN);
				quit.setForeground(MainMenu.TEXT_BROWN);
				quit.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				frame.add(quit);
				quit.setBounds(IMG_WIDTH/2, (int)(3.5*IMG_HEIGHT), 300, 50);
				quit.setSize(300, 50);
			}
		}
	}

	/**
	 * Creates an image from a file path to the requested image
	 * @param fileName path to the wanted image
	 * @return the image object of the requested image
	 */
	private BufferedImage createImage(String fileName){ //converts filename to buffered image
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
	 * Draws all the information from the table onto the screen, called once per tick
	 * @param g object which does the actual painting
	 */
	@Override
	public void paint(Graphics g) {

		//set icons to JButtons
		for (int i = 0; i < cubes.length; i++){
			cubes[i].setIcon(new ImageIcon(images[refTable.getCubeAt(i, true).getImg()]));
		}
		//draw finalized images
		for (int i = 0; i < refTable.getFinishedSize(); i++){
			g.drawImage(images[refTable.getCubeAt(i, false).getImg()],IMG_WIDTH/2 + (int)(i*1.2*IMG_WIDTH), 2*IMG_HEIGHT, null, this);
		}
	}
	
	/**
	 * Disposes of the JFrame which holds the view
	 */
	public void dispose() {
		frame.dispose();
	}

	/**
	 * Updates the view by repainting the JFrame
	 */
	public void update() {
		frame.repaint();
	}
}
