package g4.beachGame.model;

public class Speedboat extends Boat{

	public Speedboat(){
		//this.yloc = 0 + (int)(Math.random() * Board.SHORELINE); 
		if (Math.random() < 0.5 == true){
			this.xloc=0;
		}
		else{
			this.yloc=Board.WIDTH;
		}
		
	}
}
