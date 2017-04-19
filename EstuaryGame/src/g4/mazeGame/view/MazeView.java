package g4.mazeGame.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import g4.mazeGame.model.Board;
import g4.mazeGame.model.User;

public class MazeView extends JPanel{
	
	private Board board;
	private User user;
	public final int SLOT_SPACE=40;
	
	
	public MazeView(Board board, User u) {
		this.board=board;	
		user = u;
		setSize(board.getWidth()*SLOT_SPACE,board.getHeight()*SLOT_SPACE);
		setFocusable(true);
	}
	
	@Override
	public void paint(Graphics g)
	{
		for(int x=0; x<board.getWidth();x++){
			for(int y=0;y<board.getHeight();y++)
			{
				char c=board.getCell(x,y);
				switch(c)
				{
					case '.': 
						g.setColor(Color.CYAN);
						g.fillRect(x*SLOT_SPACE, y*SLOT_SPACE, SLOT_SPACE, SLOT_SPACE);
						break;
					case '#':
						g.setColor(Color.DARK_GRAY);
						g.fillRect(x*SLOT_SPACE, y*SLOT_SPACE, SLOT_SPACE, SLOT_SPACE);
						break;
					case '*': 
						g.setColor(Color.DARK_GRAY);
						g.fillRect(x*SLOT_SPACE, y*SLOT_SPACE, SLOT_SPACE, SLOT_SPACE);
						break;
					case 'o':
						g.setColor(Color.CYAN);
						g.fillRect(x*SLOT_SPACE, y*SLOT_SPACE, SLOT_SPACE, SLOT_SPACE);
						g.setColor(Color.BLACK);
						g.fillOval(x*SLOT_SPACE+(SLOT_SPACE/2), y*SLOT_SPACE+(SLOT_SPACE/2), 10, 10);
						break;
					case 'W':
						g.setColor(Color.YELLOW);
						g.fillRect(x*SLOT_SPACE, y*SLOT_SPACE, SLOT_SPACE, SLOT_SPACE);
				}
			}
		}
		
		g.setColor(Color.RED);
		g.fillOval((int)(user.getXLoc()*SLOT_SPACE), (int)(user.getYLoc()*SLOT_SPACE), SLOT_SPACE, SLOT_SPACE);

	}
}
