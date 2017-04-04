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

public class View {
	BufferedImage[][] pics;
	int blankSpace=10;
	List<Orc> orcs = new ArrayList<Orc>();
	
    public void paint(Graphics g) {
    	Iterator<Orc> it = orcs.iterator();
    	while (it.hasNext()){
    		Orc curOrc = it.next();
    	}
    	picNum = (picNum + 1) % frameCount;
    	if (picDirection == SE)
    		g.drawImage(pics[SE][picNum], xloc+=xIncr, yloc+=yIncr, Color.gray, this);
    	else if (picDirection == SW)
    		g.drawImage(pics[SW][picNum], xloc-=xIncr, yloc+=yIncr, Color.gray, this);
    	else if (picDirection == NE)
    		g.drawImage(pics[NE][picNum], xloc+=xIncr, yloc-=yIncr, Color.gray, this);
    	else
    		g.drawImage(pics[NW][picNum], xloc-=xIncr, yloc-=yIncr, Color.gray, this);
    	
    	if (xloc>=frameWidth-imgWidth+blankSpace){
    		switch(picDirection){
    			case SE:
    				picDirection=SW;
    				break;
    			case NE:
    				picDirection=NW;
    				break;
    		}
    	}
    	else if (xloc<=-blankSpace){
    		switch(picDirection){
			case SW:
				picDirection=SE;
				break;
			case NW:
				picDirection=NE;
				break;
    		}
    	}
    	else if (yloc>=frameHeight-imgHeight+blankSpace){
    		switch(picDirection){
			case SW:
				picDirection=NW;
				break;
			case SE:
				picDirection=NE;
				break;
    		}
    	}
    	else if (yloc<=-blankSpace){
    		switch(picDirection){
			case NE:
				picDirection=SE;
				break;
			case NW:
				picDirection=SW;
				break;
    		}
    	}
    	// TODO: Keep the orc from walking off-screen, turn around when bouncing off walls.
		//Be sure that animation picture direction matches what is happening on screen.
    }
}
