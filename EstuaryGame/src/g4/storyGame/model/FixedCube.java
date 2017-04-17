package g4.storyGame.model;

public class FixedCube {

	public final int IMAGE_NUM;
	
	public FixedCube(int i){
		IMAGE_NUM = i;
	}
	
	public FixedCube(Cube x){
		IMAGE_NUM = x.getImg();
	}
}
