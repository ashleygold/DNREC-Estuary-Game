package g4.storyGame.view;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import g4.mainController.MainMenu;
import g4.storyGame.model.Table;

public class StoryView extends JPanel{

	private final Table refTable;
	private final JFrame frame;
	
	private final int FRAME_WIDTH = 1100;
	private final int FRAME_HEIGHT = 600;
	
	private final int IMG_WIDTH = 100;
	private final int IMG_HEIGHT = 100;
	
	private static final String[] imagesLoc = {"images/StoryImages/TestImage.png",
			"images/StoryImages/TestImage2.png"};
	//The number of possible sides of cubes (total number of images)
	public static final int NUM_SIDES = imagesLoc.length;
	
	private final BufferedImage[] images = new BufferedImage[imagesLoc.length];
	
	private final JButton[] cubes;
	private List<BufferedImage> finalized = new ArrayList<BufferedImage>();
	
	public StoryView(Table t){
		for (int i = 0; i < imagesLoc.length; i++){
			images[i] = createImage(imagesLoc[i]);
		}
		
		refTable = t;
		
		frame = new JFrame();
		frame.getContentPane().add(this);
		frame.setBackground(MainMenu.BACKGROUND_BLUE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		//makes frame visible and sets no layout
		frame.setVisible(true);
		frame.setLayout(null);
		
		cubes = new JButton[refTable.NUM_DICE];
		
		//Iterator<Cube> cubesItr = refTable.getCubeIterator();
		for (int i = 0; i < refTable.NUM_DICE; i++){
			cubes[i] = new JButton(new ImageIcon(images[refTable.getCubeAt(i).getImg()]));
			cubes[i].setBackground(MainMenu.BACKGROUND_BLUE);
			cubes[i].addActionListener(new CubeActionListener(i));
			frame.add(cubes[i]);
			cubes[i].setBounds(IMG_WIDTH/2 + (int)(i*1.2*IMG_WIDTH), IMG_HEIGHT/2, 100, 100);
			cubes[i].setSize(100, 100);
		}
		
		System.out.println("herelol");
	}
	
	private class CubeActionListener implements ActionListener{
		private final int cubeNum; 
		CubeActionListener(int i){
			cubeNum = i;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			clicked(cubeNum);
		}
	}
	
	private void clicked(int i) {
		if (!refTable.getCubeAt(i).isFixed())
			//if not fixed
			refTable.getCubeAt(i).fix();
		else if (!refTable.getCubeAt(i).isMoved()){
			//if not moved
			//move
			refTable.getCubeAt(i).move();
			//and disable the button
			cubes[i].setEnabled(false);
			
			//add to lower list of displayed cubes
			finalized.add(images[refTable.getCubeAt(i).getImg()]);
			
			//If everything has been added, display a close button
			if (finalized.size() == refTable.NUM_DICE){
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
	
	@Override
	public void paint(Graphics g) {
		System.out.println("test");
		for (int i = 0; i < cubes.length; i++){
			cubes[i].setIcon(new ImageIcon(images[refTable.getCubeAt(i).getImg()]));
		}
		for (int i = 0; i < finalized.size(); i++){
			g.drawImage(finalized.get(i),IMG_WIDTH/2 + (int)(i*1.2*IMG_WIDTH), 2*IMG_HEIGHT, null, this);
		}
	}
	
	public void dispose() {
		frame.dispose();
	}

	public void update() {
		frame.repaint();
	}
}
