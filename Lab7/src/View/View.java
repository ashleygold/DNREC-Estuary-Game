package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Orc;
import Model.Board;

public class View extends JPanel {
	final int frameCount = 10;
    final int numAni = 8;
	BufferedImage[][] pics;
	int blankSpace = 10;
	List<Orc> orcs = new ArrayList<Orc>();
	
	//Make frame, loop on repaint and wait
    public static void main(String[] args) {
    	JFrame frame = new JFrame();
    	frame.getContentPane().add(new View());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(Board.frameWidth, Board.frameHeight);
    	frame.setVisible(true);
    	for(int i = 0; i < 1000; i++){
    		frame.repaint();
    		try {
    			Thread.sleep(50);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }
	
	public View(){
    	pics = new BufferedImage[numAni][frameCount];
    	//this array holds the file locations of the 8 orc images 
    	String[] directory = {"images/orc/orc_forward_southeast.png",
    			"images/orc/orc_forward_south.png",
    			"images/orc/orc_forward_southwest.png",
    			"images/orc/orc_forward_west.png",
    			"images/orc/orc_forward_northwest.png",
    			"images/orc/orc_forward_north.png",
    			"images/orc/orc_forward_northeast.png",
    			"images/orc/orc_forward_east.png"};
    	//this loop makes sure each set of images is loaded
    	for (int dir = 0; dir < numAni; dir++) {
    		BufferedImage img = createImage(directory[dir]);
    		for(int i = 0; i < frameCount; i++)
    			pics[dir][i] = img.getSubimage(Orc.imgWidth*i, 0, Orc.imgWidth, Orc.imgHeight);
    	}
    	for (int i = 0; i < 5; i++)
    		orcs.add(new Orc());
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }  
	
    public void paint(Graphics g) {
    	Iterator<Orc> it = orcs.iterator();
    	while (it.hasNext()){
    		Orc curOrc = it.next();
    		curOrc.updateOrc(frameCount, blankSpace);
    		g.drawImage(pics[curOrc.getDirection()][curOrc.getPicNum()], curOrc.getX(), 
    				curOrc.getY(), Color.gray, this);
    	}
    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
    }
    
    //Read image from file and return
    //now accepts the file location as a parameter
    private BufferedImage createImage(String file){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(file));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps
    }
}
