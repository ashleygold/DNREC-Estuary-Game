package g4.mazeGame.model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class User {
	public float xLoc=15, yLoc=15;
	public final static int still=0, left =1, right =2, up=3, down =4;
	public int direction = still;
	public int time=0;
	public Image image;
	public int foodCount;
	
	public User(){
		//Image image= ImageIO.read(new File("screenshots/screengrab.png"));
		xLoc=15;
		yLoc=15;
		direction=still;
	}


	public void move()
	{
		time+=1;
		if(direction==left){
			xLoc-=.1;
		}
		else if(direction == right){
			xLoc+=.1;
		}
		else if (direction==up){
			yLoc-=.1;
		}
		else if(direction==down){
			yLoc+=.1;
		}
	}
	
	public float getXLoc(){
		return xLoc;
	}
	
	public float getYLoc(){
		return yLoc;
	}
	
	
	public int getFoodCount() {
		return foodCount;
	}
	public void setFoodCount(int foodCount) {
		this.foodCount = foodCount;
	}
	public void increaseFood(){
		setFoodCount(foodCount+1);
	}
}
