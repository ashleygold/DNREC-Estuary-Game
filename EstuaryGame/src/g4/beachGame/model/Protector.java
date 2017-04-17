package g4.beachGame.model;

public abstract class Protector {
	int life;
	int xloc1;
	int xloc2;
	
	public void remove(){
		
	}
	public void loseLife(){
		life -= 1;
	}
}
