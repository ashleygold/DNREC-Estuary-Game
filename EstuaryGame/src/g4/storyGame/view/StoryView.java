package g4.storyGame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import g4.mainController.MainMenu;
import g4.storyGame.model.Table;

public class StoryView extends JPanel{
	
	/**
	 * Default Serializable required since it implements Serializable
	 */
	private static final long serialVersionUID = 1L;

	/** dimension of the user's screen*/
	private final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	/** pointer to Model, used to get data */
	private final Table refTable;
	
	/** Window for this game */
	private final JFrame frame;
	
	/** Width of the window */
	private final int FRAME_WIDTH = screenSize.width;
	
	/** Offset from bottom of screen */
	private final int OFFSET = 30;
	
	/** Height of the window */
	private final int FRAME_HEIGHT = screenSize.height - OFFSET;
	
	/** Width of images */
	private final int IMG_WIDTH = FRAME_WIDTH/12;
	
	/** Height of images */
	private final int IMG_HEIGHT = IMG_WIDTH;
	
	/** directory of images */
	private static final String imgDir = "images/StoryImages/";	
	
	/** file names of images */
	private static final String[] imagesLoc = {
			"algae_good.png",
			"blackduck_right.png",
			"bluecrab_0.png",
			"bogturtle_left_0.png",
			"butterfly_right.png",
			"denrec_standing.png",
			"estuary_meadow_with_river.jpg",
			"fish_bass_right.png",
			"fish_eggs.png",
			"fisherman_walk_left_2.png",
			"fullHeart.png",
			"grass.png",
			"horseshoe_northeast.png",
			"hotrod_vessel.png",
			"mittencrab_0.png",
			"mittencrabs_spawn_2.png",
			"researcher_withClipboard.png",
			"sailboat.png",
			"speedboat.png",
			"turtle.png",
			"vessel.png",
			"volunteer_blue.png",
			"volunteer_red.png",
			"windRight.png",
			"zebramussel.png"
			};
	
	/**tutorial images*/
	private final Image title = createImage("Title.png");
	private final Image tut1 = createImage("Tut1.png");
	private final Image tut2 = createImage("Tut2.png");
	private final Image tut3 = createImage("Tut3.png");
	
	/** The number of possible sides of cubes (total number of images) */
	public static final int NUM_SIDES = imagesLoc.length;
	
	/** Every possible image */
	private final Image[] images = new Image[imagesLoc.length];
	
	/** Every possible imageIcon */
	private final ImageIcon[] icons = new ImageIcon[imagesLoc.length];
	
	/** Buttons representing Cubes */
	private final JButton[] cubes;
	
	/** Text field for story input*/
	private JTextArea storyBox = new JTextArea();
	
	/** Scrollable text box */
	private JScrollPane scrollPane = new JScrollPane(storyBox);
	
	/**
	 * Constructor which initializes a View to display the model parameter
	 * @param t The model which this View should display
	 */
	public StoryView(Table t){
		//create all images
		for (int i = 0; i < imagesLoc.length; i++){
			images[i] = createImage(imagesLoc[i])
					.getScaledInstance(IMG_WIDTH, IMG_HEIGHT, Image.SCALE_SMOOTH);
			
			icons[i] = new ImageIcon(images[i]);
		}
		
		//set reference
		refTable = t;
		
		//set up window
		frame = new JFrame("Minigame 3: Story Cubes");
		frame.getContentPane().add(this);
		frame.setBackground(MainMenu.BACKGROUND_BLUE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(0,0,FRAME_WIDTH, FRAME_HEIGHT);
		//makes frame visible and sets no layout
		frame.setVisible(true);
		frame.setLayout(null);
		
		//create JButtons for Cubes
		cubes = new JButton[refTable.NUM_DICE];
		
		for (int i = 0; i < refTable.NUM_DICE; i++){
			cubes[i] = new JButton(icons[i]);
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
	 * Runs when a Cube's JButton is clicked, activates the Cube and checks if
	 * a text box and exit and submit buttons should be displayed
	 * @param i the index of the JButton was clicked
	 */
	private void clicked(int i) {
		refTable.activateCube(i);
		if (refTable.getCubeAt(i, true).getIsMoved()){
			//if moved, remove the button
			frame.remove(cubes[i]);
			
			//If everything has been added, display close and submit buttons
			if (refTable.areAllMoved()){
				//quit button
				JButton quit = new JButton("Return to Menu");
				quit.setFont(new Font("Findet Nemo", Font.PLAIN, 40));
				quit.setBackground(MainMenu.SEA_GREEN);
				quit.setForeground(MainMenu.TEXT_BROWN);
				quit.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				frame.add(quit);
				quit.setBounds(IMG_WIDTH/2, FRAME_HEIGHT - IMG_HEIGHT, 3*IMG_WIDTH, IMG_HEIGHT/2);
				quit.setSize(3*IMG_WIDTH, IMG_HEIGHT/2);
				
				//submit
				JButton submit = new JButton("Submit");
				submit.setFont(new Font("Findet Nemo", Font.PLAIN, 40));
				submit.setBackground(MainMenu.SEA_GREEN);
				submit.setForeground(MainMenu.TEXT_BROWN);
				submit.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.remove(submit);
						handleText();
					}
				});
				frame.add(submit);
				submit.setBounds(4*IMG_WIDTH, FRAME_HEIGHT - IMG_HEIGHT, 3*IMG_WIDTH, IMG_HEIGHT/2);
				submit.setSize(3*IMG_WIDTH, IMG_HEIGHT/2);
				
				//add text box
				scrollPane.setBounds(IMG_WIDTH/2, IMG_HEIGHT/2,
						FRAME_WIDTH - IMG_WIDTH, FRAME_HEIGHT - 7*IMG_HEIGHT/2);
				storyBox.setBounds(IMG_WIDTH/2, IMG_HEIGHT/2,
						FRAME_WIDTH - IMG_WIDTH, FRAME_HEIGHT - 7*IMG_HEIGHT/2);
				frame.add(scrollPane);
			}
		}
	}

	/**
	 * Handles removing the text box and instead displaying the user's input as a JLabel.  
	 * Called when the submit button is pressed.  Also writes story to a file for extraction by 
	 * game operators.  
	 */
	private void handleText() {
		String userInput = storyBox.getText();
		
		//change endline characters for UTF-8 printing
		String filePrint = userInput.replaceAll("\n", "\r\n");

		//print to file
		try {
		    Files.write(Paths.get("Stories.txt"),
		    		(filePrint + "\r\n=========\r\n").getBytes(StandardCharsets.UTF_8),
		    		StandardOpenOption.CREATE,
		    		StandardOpenOption.APPEND);
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		//convert to html for JLabel
		String jLabelPrint = "<html>"
				+ userInput.replaceAll("<","&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") 
				+ "</html>";
		JLabel storyText = new JLabel(jLabelPrint);
		frame.remove(scrollPane);
		
		//add JLabel
		storyText.setBounds(IMG_WIDTH/2, IMG_HEIGHT/4,
				FRAME_WIDTH - IMG_WIDTH, FRAME_HEIGHT - 6*IMG_HEIGHT/2);
		storyText.setFont(new Font("SansSerif", Font.BOLD, 25));
		storyText.setForeground(Color.WHITE);
		
		frame.add(storyText);
	}

	/**
	 * Creates an image from a file path to the requested image
	 * @param fileName path to the wanted image
	 * @return the image object of the requested image
	 */
	private BufferedImage createImage(String fileName){ //converts filename to buffered image
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(imgDir + fileName));
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
			cubes[i].setIcon(icons[refTable.getCubeAt(i, true).getImg()]);
		}
		
		//draw finalized images
		for (int i = 0; i < refTable.getFinishedSize(); i++){
			g.drawImage(images[refTable.getCubeAt(i, false).getImg()],
					IMG_WIDTH/2 + (int)(i*1.2*IMG_WIDTH), FRAME_HEIGHT - 5*IMG_HEIGHT/2,
					null, this);
		}
		
		//if all cubes have been clicked
		if (refTable.areAllMoved()) {
			g.drawImage(tut3, 15*IMG_WIDTH/2, FRAME_HEIGHT - IMG_HEIGHT, Color.DARK_GRAY, this);
		} else {
			g.drawImage(title, IMG_HEIGHT, 2*IMG_HEIGHT, Color.DARK_GRAY, this);
			if (refTable.areAllFixed()){
				//movement prompt
				g.drawImage(tut2, 4*IMG_HEIGHT, 2*IMG_HEIGHT, Color.DARK_GRAY, this);
			} else {
				//rolling prompt
				g.drawImage(tut1, 4*IMG_HEIGHT, 2*IMG_HEIGHT, Color.DARK_GRAY, this);
			}
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
