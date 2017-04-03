package Model;

public class Orc {
	
	final static int imgWidth = 165;
    final static int imgHeight = 165;
	
	int xloc = 0;
    int yloc = 0;
    int picNum = 0;
    final int xIncr = 8;
    final int yIncr = 2;
    
    int picDirection = SE;
    
  //Directions, index for the image's location in pics[i]
  	final static int NE = 2; 
  	final static int NW = 3;
  	final static int SE = 5;
  	final static int SW = 6;

	
	public Orc(){
		xloc=(int) Math.random();
		yloc=(int) Math.random();
		
	}
}
