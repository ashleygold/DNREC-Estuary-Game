package g4.mainController;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import g4.beachGame.controller.BeachCont;
import g4.mazeGame.controller.MazeCont;
import g4.storyGame.controller.StoryCont;

public class MainMenu extends JPanel{
	
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
	private static final int DE = 0;
	/** State for Texas images */
	private static final int TX = 1;
	/** State for Florida images */
	private static final int FL = 2;
	
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
		//create and set up frame defaults
		JFrame frame = new JFrame();
		frame.getContentPane().add(new MainMenu());
		frame.setBackground(BACKGROUND_BLUE);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setSize(660, 420);
		
		//create buttons
		JButton[] choices = new JButton[4];
		for (int i = 0; i < choices.length; i++)
		choices[0] = new JButton("Maze Game");
		choices[1] = new JButton("Beach Game");
		choices[2] = new JButton("Story Cube Game");
		choices[3] = new JButton("Exit");
		
		JButton[] states = new JButton[3];
		states[0] = new JButton("Delaware");
		states[1] = new JButton("Texas");
		states[2] = new JButton("Florida");
		
		//make buttons look nice :)
		for (JButton b : choices) {
			b.setBackground(SEA_GREEN);
			b.setForeground(TEXT_BROWN);
		}
		for (JButton b : states) {
			b.setBackground(TEXT_BROWN);
			b.setForeground(SEA_GREEN);
		}
		
		//creates action on click for each button
		choices[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(game != null)
					game.dispose();
				JOptionPane.showMessageDialog(null, "You are a crab trying to grow bigger and stronger!"
						+ "\nCollect food (algae, fish eggs, and mussels) and progress "
						+ "\nthrough the three stages to go from areas of high salinity "
						+ "\nto lower salinity.\n"
						+ "Don't get eaten by predators!");
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
		//create label
		JLabel state = new JLabel(LABEL_NAMES[curState]);
		
		frame.add(state);
		state.setBounds(340, 30, 300, 75);
		state.setSize(300, 75);
		state.setFont(new Font("SansSerif", Font.BOLD, 50));
		state.setForeground(SEA_GREEN);
		
		//creates action on click for each state button
		states[0].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				curState = DE;
			}
		});
		states[1].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				curState = TX;
			}
		});
		states[2].addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				curState = FL;
			}
		});
		
		//game loop
		//repaints on tick		
		while(!exitFlag){
			long current = System.currentTimeMillis();
			
			state.setText(LABEL_NAMES[curState]);
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
}
