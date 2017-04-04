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
	BufferedImage[][] pics;
	int blankSpace=10;
	List<Orc> orcs = new ArrayList<Orc>();
	
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
}
