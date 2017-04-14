package g4.mazeGame.model;

public class Wall {
	
	//dimensions
	final int LEFT;   //x
	final int RIGHT;  //x
	final int TOP;    //y
	final int BOTTOM; //y
	
	public Wall(int l, int r, int t, int b){
		LEFT = l;
		RIGHT = r;
		TOP = t;
		BOTTOM = b;
	}
	
	public boolean checkColl(int x, int y){
		return (x >= LEFT && x <= RIGHT && y >= TOP && y <= BOTTOM);
	}
	
}
