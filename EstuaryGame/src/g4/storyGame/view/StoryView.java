package g4.storyGame.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import g4.mainController.MainMenu;
import g4.mazeGame.controller.MazeCont;
import g4.storyGame.model.Cube;
import g4.storyGame.model.Table;

public class StoryView extends JPanel{

	private final Table refTable;
	private final JFrame frame;
	
	private final int FRAME_WIDTH = 1000;
	private final int FRAME_HEIGHT = 600;
	
	private final int IMG_WIDTH = 100;
	private final int IMG_HEIGHT = 100;
	
	private final String[] imagesLoc = {"images/StoryImages/TestImage.png",
			"images/StoryImages/TestImage2.png"};
	private final BufferedImage[] images = new BufferedImage[imagesLoc.length];
	
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
		
		JButton[] cubes = new JButton[refTable.NUM_DICE];
		
		//Iterator<Cube> cubesItr = refTable.getCubeIterator();
		for (int i = 0; i < refTable.NUM_DICE; i++){
			cubes[i] = new JButton(new ImageIcon(images[refTable.getCubeAt(i).getImg()]));
			cubes[i].setBackground(MainMenu.SEA_GREEN);
			cubes[i].setForeground(MainMenu.TEXT_BROWN);
			cubes[i].addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					clicked(i);
				}
			});
		}
		
		//makes frame visible and sets no layout
		frame.setVisible(true);
		frame.setLayout(null);
	}
	
	protected void clicked(int i) {
		// TODO Auto-generated method stub
		
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
		g.drawImage(images[0], 100, 100, null, this);
	}
	
	public void dispose() {
		frame.dispose();
	}

	public void update() {
		frame.repaint();		
	}
}
