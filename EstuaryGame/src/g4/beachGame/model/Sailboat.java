package g4.beachGame.model;

public class Sailboat extends Boat{
	
	final int SAILBOAT_SPEED =3;

	/*constructor for a Sailboat*/
	public Sailboat(){
		//this.yloc = 0 + (int)(Math.random() * Board.SHORELINE); 
		if (Math.random() < 0.5 == true){
			this.xloc=0;
			this.direction=true;
			
		}
		else{
			this.yloc=Board.WIDTH;
			this.direction=false;
		}
		this.hasEmittedWave=false; 
		this.speed=SAILBOAT_SPEED;
		
	}


}
