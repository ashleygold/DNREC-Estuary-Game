package g4.mazeGame.controller;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import g4.mainController.MiniGameController;
import g4.mazeGame.model.Board;
import g4.mazeGame.model.User;
import g4.mazeGame.view.MazeView;


public class MazeCont implements KeyListener, MiniGameController {
	
	User user = new User();
	
	public MazeCont() {
		Frame app=new Frame("Minigame 1: Maze");
		app.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
			}			
		});
		Board board=new Board();
		User user= new User();
		MazeView screen=new MazeView(board);		
		app.add(screen);
		app.pack();
		app.setVisible(true);
		
		screen.addKeyListener(new Listener(board));
	}

	//@Override
	public void keyPressed(KeyEvent ke) {
		int code= ke.getKeyCode();
		if(code==KeyEvent.VK_LEFT){
			user.direction=User.left;
		}
		else if(code==KeyEvent.VK_RIGHT){
			user.direction=User.right;
		}
		else if(code==KeyEvent.VK_UP){
			user.direction=User.up;
		}
		else if(code==KeyEvent.VK_DOWN){
			user.direction=User.down;
		}
	}

	//@Override
	public void keyReleased(KeyEvent arg) {
		user.direction=User.still;
	}

	//@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}

