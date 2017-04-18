package g4.beachGame.model;

public class Sailboat extends Boat{

	/*constructor for a Sailboat*/
	public Sailboat(){
		this.yloc = 0 + (int)(Math.random() * Board.SHORELINE); 
		if (Math.random() < 0.5 == true){
			this.xloc=0;
		}
		else{
			this.yloc=Board.WIDTH;
		}
		
	}
}
