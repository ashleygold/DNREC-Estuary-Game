package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Orc;
import Model.Board;

public class View extends JPanel{
	static List<Orc> orcs = new ArrayList<Orc>();
	int blankSpace = 10;
	final int numAni = 8; //number of directions
	final int[] frameCount = {10,8,4}; //array of frames for each action
	final static int imgWidth = 165;
	final static int imgHeight = 165;
	final static int numActions = 3; //forward, jump, fire
	BufferedImage[][] forwardPics;
	BufferedImage[][] jumpPics;
	BufferedImage[][] firePics;
	private BufferedImage createImage(String fileName){ //converts filename to buffered image
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(new File(fileName));
			return bufferedImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

		// TODO: Change this method so you can load other orc animation bitmaps
	}
	public View(){
		//initializes all file names into string arrays
		String[] forwardFileName = {"images/orc/orc_forward_east.png",
				"images/orc/orc_forward_north.png",
				"images/orc/orc_forward_northeast.png",
				"images/orc/orc_forward_northwest.png",
				"images/orc/orc_forward_south.png",
				"images/orc/orc_forward_southeast.png",
				"images/orc/orc_forward_southwest.png",
				"images/orc/orc_forward_west.png"};
		String[] jumpFileName = {"images/orc/orc_jump_east.png", 
				"images/orc/orc_jump_north.png",
				"images/orc/orc_jump_northeast.png",
				"images/orc/orc_jump_northwest.png",
				"images/orc/orc_jump_south.png",
				"images/orc/orc_jump_southeast.png",
				"images/orc/orc_jump_southwest.png",
				"images/orc/orc_jump_west.png"};
		String[] fireFileName = {"images/orc/orc_fire_east.png",
				"images/orc/orc_fire_north.png",
				"images/orc/orc_fire_northeast.png",
				"images/orc/orc_fire_northwest.png",
				"images/orc/orc_fire_south.png",
				"images/orc/orc_fire_southeast.png",
				"images/orc/orc_fire_southwest.png",
				"images/orc/orc_fire_west.png"};
		//changes string array into buffered image array
		BufferedImage[] imgForward = new BufferedImage[numAni];
		BufferedImage[] imgJump = new BufferedImage[numAni];
		BufferedImage[] imgFire = new BufferedImage[numAni];
		for (int i =0; i<numAni; i++){
			imgForward[i]=createImage(forwardFileName[i]);
			imgJump[i]=createImage(jumpFileName[i]);
			imgFire[i]=createImage(fireFileName[i]);
		}
		//creates 2D array to change image array into sub-image array
		forwardPics = new BufferedImage[numAni][frameCount[0]];
		jumpPics = new BufferedImage[numAni][frameCount[1]];
		firePics = new BufferedImage[numAni][frameCount[2]];
		for (int i = 0; i< numActions; i++){
			for (int j=0; j<numAni;j++){
				for(int k = 0; k < frameCount[i]; k++) {
					switch(i){
					case 0:
						forwardPics[j][k] = imgForward[j].getSubimage(imgWidth*k, 0, imgWidth, imgHeight);
						break;
					case 1:
						jumpPics[j][k] = imgJump[j].getSubimage(imgWidth*k, 0, imgWidth, imgHeight);
						break;
					case 2:
						firePics[j][k] = imgFire[j].getSubimage(imgWidth*k, 0, imgWidth, imgHeight);
						break;
					}
				}
			}
		}
    	orcs.add(new Orc());
	}
	public void paint(Graphics g) {
		Iterator<Orc> it = orcs.iterator();
		while (it.hasNext()){
			Orc curOrc = it.next();
			curOrc.updateOrc(frameCount[curOrc.getAction()], blankSpace);
			BufferedImage[][] pics = null;
			switch (curOrc.getAction()){
			case 0:
				pics = forwardPics;
				break;
			case 1:
				pics = jumpPics;
				break;
			case 2:
				pics = firePics;
				break;
			}
			g.drawImage(pics[curOrc.getDirection()][curOrc.getPicNum()], curOrc.getX(), 
				curOrc.getY(), null, this);
		}
		
		// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new View());
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Board.frameWidth, Board.frameHeight);
		JButton addOrc = new JButton("Add an Orc");
		JButton killOrc = new JButton("Kill an Orc");
		JButton changeAction = new JButton("Change Orcs' Actions");
		addOrc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				addOrc();	
			}
		});
		killOrc.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				removeOrc();	
			}
		});
		changeAction.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				changeAnimation();	
			}
		});
		frame.setVisible(true);
		frame.setLayout(null);
		frame.add(addOrc);
		addOrc.setSize(100, 20);
		frame.add(killOrc);
		killOrc.setBounds(300, 0, 100, 100);
		killOrc.setSize(100, 20);
		frame.add(changeAction);
		changeAction.setBounds(100, 0, 100, 100);
		changeAction.setSize(200, 20);
		for(int i = 0; i < 1000; i++){
			frame.repaint();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void addOrc(){
		orcs.add(new Orc());
	}
	public static void removeOrc(){
		Random rand = new Random();
		int  x = rand.nextInt(orcs.size());
		orcs.remove(x);
	}
	public static void changeAnimation(){
		Iterator<Orc> it = orcs.iterator();
		while (it.hasNext()){
			Orc curOrc = it.next();
			curOrc.nextAction(numActions);
		}
		
	}
	
}