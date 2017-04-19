package g4.beachGame.model;

public class CruiseLiner extends Boat{
	
	final int CRUISELINER_SPEED=10;
	
	public CruiseLiner(){
		//this.yloc = 0 + (int)(Math.random() * Board.SHORELINE); 
		if (Math.random() < 0.5 == true){
			this.xloc=0;
		}
		else{
			this.yloc=Board.WIDTH;
		}
		this.speed=CRUISELINER_SPEED; 
		this.hasEmittedWave=false;
		
	}
	
	public void move(){
		
	}
}
