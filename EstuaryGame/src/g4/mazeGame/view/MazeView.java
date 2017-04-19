package g4.mazeGame.view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import g4.mazeGame.model.Board;
import g4.mazeGame.model.Predator;

public class MazeView extends JPanel{
	
	private Board board;
	public final int SLOT_SPACE=40;
	
	
	public MazeView(Board board) {
		this.board=board;
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
		g.setColor(Color.LIGHT_GRAY);
		for(Predator x : board.getPredator()){
			g.fillOval((int)(x.getXLoc()*SLOT_SPACE), 
					(int)(x.getYLoc()*SLOT_SPACE), SLOT_SPACE, SLOT_SPACE);
		}
		
		g.setColor(Color.RED);
		g.fillOval((int)(board.getUser().getXLoc()*SLOT_SPACE), 
				(int)(board.getUser().getYLoc()*SLOT_SPACE), SLOT_SPACE, SLOT_SPACE);
		
		g.setColor(Color.GRAY);
		g.fillRect(5, 5, 200, 25);
		
		g.setColor(Color.GREEN);
		g.fillRect(5, 5, board.getUser().getFoodCount()*20, 25);
		
		g.setColor(Color.WHITE);
		g.drawRect(5, 5, 200, 25);
		
		g.setColor(Color.BLUE);
		g.fillRect(-305+board.getWidth()*SLOT_SPACE, 5, 300, 25);
		
		g.setColor(Color.WHITE);
		g.fillRect(-305+board.getWidth()*SLOT_SPACE + 
				(board.MAX_SALINITY - board.getSalinity())*100,
				5, board.getSalinity()*100, 25);
		
		g.setColor(Color.GREEN);
		g.drawRect(-305+board.getWidth()*SLOT_SPACE, 5, 300, 25);
	}
}
