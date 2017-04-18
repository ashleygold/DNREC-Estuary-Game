package g4.mazeGame.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Board {
	static Random rand; // Global variable
	public final int HEIGHT= 17;
	public final int WIDTH= 19;
	int goalFood=10;
	int totalFood;
	private String startboard=
			 "#########*#########\n"
			+"#.....#.....#.....#\n"
			+"####........#..####\n"
			+"#.......#...#..#..#\n"
			+"#..###..#.........#\n"
			+"##......#.....#####\n"
			+"#..#...####.......#\n"
			+"#..#........#.##..#\n"
			+"#######.....#.....#\n"
			+"#........##########\n"
			+"#####.............#\n"
			+"#...#.....######..#\n"
			+"##..###........#..#\n"
			+"#..............#..#\n"
			+"########...########\n"
			+"#.................#\n"
			+"###################\n";
	
	private ArrayList<ArrayList<Character>> board = new ArrayList<ArrayList<Character>>();
	
	public Board(){
		String new_start=generateFood();
		for(String row:new_start.split("\n")){
			ArrayList<Character> r= new ArrayList<Character>();
			for(int i=0;i<row.length();i++){
				r.add(row.charAt(i));
			}
			board.add(r);
		}
	}
	
	public int getHeight() {
		return HEIGHT;
	}

	public int getWidth() {
		return WIDTH;
	}

	public char getCell(int x, int y) {	
		return board.get(y).get(x);
	}

	public static int randomItem(ArrayList<Integer> mylist) {
	    rand = new Random(); 
	    Integer randomInt = mylist.get(rand.nextInt(mylist.size()));
	    mylist.remove(randomInt);
	    return randomInt;
	}
	
	public String generateFood(){
		char[] board = startboard.toCharArray();
		int[] random = new int[goalFood];
		ArrayList<Integer> possibilities = new ArrayList<Integer>();
		for (int y=0; y<board.length;y++){
			if (board[y]=='.'){
				possibilities.add(y);
			}
		}
		for (int j=0; j<goalFood; j++){
			random[j]=randomItem(possibilities);
		}
        for (int i=0; i<board.length; i++) {
            if (board[i]=='.'){
            	for (int k=0; k<random.length;k++){
	            	if (i==random[k]){
	            		board[i]='o';
	            	}
	            }
            }
        }
        String newstartboard = String.valueOf(board);
        return newstartboard;
	}
	
	public boolean isEmpty(double x, double y){
		return (getCell((int)x, (int)y) == '.' || getCell((int)x, (int)y) == 'o');
	}
	
	protected boolean eatFood(double x, double y){
		if (getCell((int)x, (int)y) == 'o'){
			board.get((int)y).set((int)x, '.');
			return true;
		} else {
			return false;
		}
	}
	
	public boolean endgame() {
		return false;
	}
	
}
