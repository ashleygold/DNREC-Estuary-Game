package g4.mazeGame.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
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
	
	public Board()
	{
		for(String row:startboard.split("\n")){
			
			ArrayList<Character> r= new ArrayList<Character>();
			for(int i=0;i<row.length();i++)
			{
				r.add(row.charAt(i));
			}
				
			board.add(r);
		}
	}
	
	public int getHeight() {
		return board.size();
	}

	public int getWidth() {
		return board.get(0).size();
	}

	public char getCell(int x, int y) {	
		return board.get(y).get(x);
	}	
	
	public boolean endgame() {
		return false;
	}

	int goalFood;
	int totalFood;
	List<FoodParticle> food = new ArrayList<FoodParticle>();
	
}
