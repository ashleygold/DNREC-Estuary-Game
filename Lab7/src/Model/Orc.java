package Model;

//Orc Class
public class Orc {
	
	//dimensions of each orc
	final static int imgWidth = 165;
    final static int imgHeight = 165;
	
    //location
	int xloc = 0;
    int yloc = 0;
    //current frame
    int picNum = 0;
    //movement speed
    final int xIncr = 6;
    final int yIncr = 2;
    //current action and direction
    int action;
    int direction;
    
    //Actions
    final static int run = 0;
    final static int jump = 1;
    final static int shoot = 2;
    
    final static int totalNumActions = 3;
    
    //Directions, index for the image's location in pics[i]
  	final static int NE = 2; 
  	final static int NW = 3;
  	final static int SE = 5;
  	final static int SW = 6;
	
  	public Orc(){
  		action = run;
  		//move to a random location
		xloc = (int) (Math.random()*(Board.frameWidth - imgWidth));
		yloc = (int) (Math.random()*(Board.frameHeight - imgHeight));
		//face a random direction
		int[] dirArr = {NE, NW, SE, SW};
		direction = dirArr[(int) (Math.random()*dirArr.length)];
	}
  	
  	public int getPicNum(){
		return picNum;
	}
  	
  	public void updateOrc(int frameCount, int blankSpace){
  		//update frame
		picNum = (picNum + 1) % frameCount;
		//move unless the orc is shooting
		if (action != shoot){
			if (direction == SE){
	    		xloc+=xIncr;
	    		yloc+=yIncr;
			} else if (direction == SW) {
	    		xloc-=xIncr;
				yloc+=yIncr;
			} else if (direction == NE){
	    		xloc+=xIncr;
				yloc-=yIncr;
			} else {
				xloc-=xIncr;
				yloc-=yIncr;
			}
		}
		
		//if an orc reaches an edge
		if (xloc >= Board.frameWidth-imgWidth+blankSpace){
    		switch(direction){
    			case SE:
    				direction=SW;
    				break;
    			case NE:
    				direction=NW;
    				break;
    		}
    	}
    	else if (xloc <= -blankSpace){
    		switch(direction){
			case SW:
				direction=SE;
				break;
			case NW:
				direction=NE;
				break;
    		}
    	}
    	else if (yloc >= Board.frameHeight-imgHeight+blankSpace){
    		switch(direction){
			case SW:
				direction=NW;
				break;
			case SE:
				direction=NE;
				break;
    		}
    	}
    	else if (yloc <= -blankSpace){
    		switch(direction){
			case NE:
				direction=SE;
				break;
			case NW:
				direction=SW;
				break;
    		}
    	}
	}
  	
  	public int getDirection(){
		return direction;
	}
	
	public void setDirection(int d){
		direction = d;
	}

	public int getX() {
		return xloc;
	}

	public int getY() {
		return yloc;
	}
	
	public int getAction(){
		return action;
	}
	
	public void nextAction(){
		action = (action + 1) % totalNumActions;
	}
}