package g4.mainController;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import g4.beachGame.controller.BeachCont;
import g4.mazeGame.controller.MazeCont;
import g4.storyGame.controller.StoryCont;

public class MainMenu extends JPanel{
	
	/**
	 * Default Serializable required since it implements Serializable
	 */
	private static final long serialVersionUID = 1L;

	/** Exit program flag */
	private static boolean exitFlag = false;
	
	/** Current MiniGame */
	private static MiniGameController game = null;
	
	/** Current frame rate, Number of times models are updated and frames are repainted per second */
	private static int fps = 24;
	
	/** Frame rates for maze game, allows it to process at different rates */
	public static final int MAZE_FPS = 60;
	
	/** Frame rates for beach game, allows it to process at different rates */
	public static final int BEACH_FPS = 60;
	
	/** Frame rates for story game, allows it to process at different rates */
	public static final int STORY_FPS = 24;
	
	/** Currently selected state */
	private static int curState = 0;
	
	/** State for Delaware images */
	public static final int DE = 0;
	
	/** State for Texas images */
	public static final int TX = 1;
	
	/** State for Florida images */
	public static final int FL = 2;
	
	/** Green color for menus */
	public static final Color SEA_GREEN = new Color(26, 255, 139);
	
	/** Blue color for menus */
	public static final Color BACKGROUND_BLUE = new Color(10,105,148);
	
	/** Brown color for menus */
	public static final Color TEXT_BROWN = new Color(101, 67, 33);
	
	/** Text names of states */
	private static final String[] LABEL_NAMES = {"Delaware", "Texas", "Florida"};
	
	/**
	 * Main method for entire collection of games, includes game loop
	 * @param args line arguments
	 */
	public static void main(String [] args){
		//import fonts
		try {
		    //create the font to use. Specify the size!
		    //Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("Fonts\\custom_font.ttf")).deriveFont(12f);
		    //GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		    //register the font
		    GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(
		    		Font.createFont(Font.TRUETYPE_FONT, new File("fonts/FindetNemo.ttf")));
		} catch (IOException e) {
		    e.printStackTrace();
		} catch(FontFormatException e) {
		    e.printStackTrace();
		}
		
		Font buttonF = new Font("Findet Nemo", Font.PLAIN, 40);
		
		//create and set up frame defaults
		JFrame frame = new JFrame("Main Menu");
		frame.getContentPane().add(new MainMenu());
		frame.setBackground(BACKGROUND_BLUE);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(660, 420);
		
		//create buttons
		JButton[] choices = new JButton[4];
		choices[0] = new JButton("Maze");
		choices[1] = new JButton("<html><center>Shore<br>Defense</center></html>");
		choices[2] = new JButton("<html><center>Story<br>Cubes</center></html>");
		choices[3] = new JButton("Exit");
		
		JButton[] states = new JButton[3];
		states[0] = new JButton("Delaware");
		states[1] = new JButton("Texas");
		states[2] = new JButton("Florida");
		
		//make buttons look nice :)
		for (JButton b : choices) {
			b.setBackground(SEA_GREEN);
			b.setForeground(TEXT_BROWN);
			b.setFont(buttonF);
		}
		for (JButton b : states) {
			b.setBackground(TEXT_BROWN);
			b.setForeground(SEA_GREEN);
			b.setFont(buttonF);
		}
		
		//creates action on click for each button
		choices[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(game != null)
					game.dispose();
				game = new MazeCont();
				fps = MAZE_FPS;
			}
		});
		choices[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(game != null)
					game.dispose();
				game = new BeachCont();
				fps = BEACH_FPS;
			}
		});
		choices[2].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(game != null)
					game.dispose();
				game = new StoryCont();
				fps = STORY_FPS;
			}
		});
		choices[3].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				quit();
			}
		});
		
		
		//makes frame visible and sets no layout
		frame.setVisible(true);
		frame.setLayout(null);
		
		//puts buttons on screen
		choices[0].setBounds(10, 10, 300, 100);
		choices[1].setBounds(10, 120, 300, 100);
		choices[2].setBounds(10, 230, 300, 100);
		
		states[0].setBounds(330, 125, 300, 75);
		states[1].setBounds(330, 210, 300, 75);
		states[2].setBounds(330, 295, 300, 75);
		
		for (int i = 0; i<choices.length - 1; i++){
			frame.add(choices[i]);
			choices[i].setSize(300, 100);
			
			frame.add(states[i]);
			states[i].setSize(300,75);
		}
		
		//quit handled separately
		frame.add(choices[3]);
		choices[3].setBounds(10, 340, 100, 100);
		choices[3].setSize(300, 30);
		
		//Add text Label for current state
		JLabel state = new JLabel(LABEL_NAMES[getCurState()]);
		
		frame.add(state);
		state.setBounds(340, 30, 300, 75);
		state.setSize(300, 75);
		state.setFont(new Font("Findet Nemo", Font.PLAIN, 50));
		state.setForeground(SEA_GREEN);
		
		//creates action on click for each state button
		states[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurState(DE);
			}
		});
		states[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurState(TX);
			}
		});
		states[2].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurState(FL);
			}
		});		
		
		//-------------------------------------------
		//game loop
		//repaints on tick		
		while(!exitFlag){
			long current = System.currentTimeMillis();
			
			state.setText(LABEL_NAMES[getCurState()]);
			if(game != null)
				game.update();
			frame.repaint();
			
			//if there is extra time left in the frame, sleep the thread
			long elapsed = System.currentTimeMillis() - current;
			long timeToWait = 1000/fps - elapsed;
			if (timeToWait > 0)
				try {
					Thread.sleep(timeToWait);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
		if(game != null)
			game.dispose();
		frame.dispose();
	}
	
	/**
	 * Overrides default repainting to ensure menu visibility
	 * g object which does the actual painting
	 */
	@Override
	public void paint(Graphics g) {
		
	}

	/**
	 * Set the game flag to exit the game loop and quit 
	 */
	private static void quit() {
		exitFlag = true;
	}

	public static int getCurState() {
		return curState;
	}

	/**
	 * Sets the current state to the parameter.  Called by JButtons on main menu.
	 * @param newState the state that this should be changed to
	 */
	private static void setCurState(int newState) {
		curState = newState;
	}
}
