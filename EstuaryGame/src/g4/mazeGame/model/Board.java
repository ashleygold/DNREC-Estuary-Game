package g4.mazeGame.model;

<<<<<<< HEAD
import java.util.ArrayList;
import java.util.List;

public class Board {
	 
	final static int WIDTH = 600; 
	final static int HEIGHT = 600; 
	int goalFood;
	int totalFood;
	List<FoodParticle> food = new ArrayList<FoodParticle>();
	
	public Board(){
		for (int i =0; i < totalFood; i++){ //placing all food on the board
			food.add(new FoodParticle());
		}
	}
=======
public class Board {
>>>>>>> 93e848245dbbb7d5c435292455502838f9856833
	
}
