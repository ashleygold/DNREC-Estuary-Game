package g4.mazeGame.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import g4.mazeGame.model.User;


public class Listener implements KeyListener {
	
	User user;
	public Listener(User user) {
		this.user = user;
	}

	//@Override
	public void keyPressed(KeyEvent ke) {
		int code= ke.getKeyCode();
		System.out.println("Key pressed");
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
}
