package g4.mazeGame.view;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import g4.mazeGame.controller.*;
import g4.mazeGame.model.*;

public class MazeView extends JPanel{
	
	private Board board;
	private User user;
	public final int t=40;
	
	
	
	public MazeView(Board board, User u) {
		this.board=board;	
		user = u;
		setSize(board.getWidth()*t,board.getHeight()*t);
	}
	
	/*
	@Override
	public void update(Graphics g)
	{
		System.out.println("MV update");
		Image im = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		paint(im.getGraphics());
		g.drawImage(im, 0, 0, null);		
		
	}*/
	
	@Override
	public void paint(Graphics g)
	{
		System.out.println("MV paint");
		for(int x=0; x<board.getWidth();x++){
			for(int y=0;y<board.getHeight();y++)
			{
				char c=board.getCell(x,y);
				switch(c)
				{
					case '.': 
						g.setColor(Color.CYAN);
						g.fillRect(x*t, y*t, t, t);
						break;
					case '#':
						g.setColor(Color.DARK_GRAY);
						g.fillRect(x*t, y*t, t, t);
						break;
					case '*': 
						g.setColor(Color.YELLOW);
						g.fillRect(x*t, y*t, t, t);
						break;
					default : System.err.println(c+" Not found");
				}
			}
		}
		
		g.setColor(Color.RED);
		g.fillOval((int)(user.getXLoc()*t-10), (int)(user.getYLoc()*t-10), t-10, t-10);
		g.fillRect(board.getWidth()*t-25,board.getHeight()*t-50, 10, 10);
		g.fillRect(0,0, 10, 10);

	}
}
