package g4.mazeGame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	
	private User user;
	
	static Random rand; // Global variable
	public final int HEIGHT= 17;
	public final int WIDTH= 19;
	int goalFood=10;
	private final int NUM_PREDATORS = 3; 
	int totalFood;
	private String startboard =
			 "##########*########\n"
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
	
	private List<Predator> hunters = new ArrayList<Predator>();
	
	public Board(){
		String new_start = generateFood();
		for(String row:new_start.split("\n")){
			ArrayList<Character> r= new ArrayList<Character>();
			for(int i=0;i<row.length();i++){
				r.add(row.charAt(i));
			}
			board.add(r);
		}
		
		user = new User(this);
		
		for(int i = 0; i < NUM_PREDATORS; i++){
			int yTest = (int) (Math.random()*(HEIGHT-3)+1);
			int xTest = (int) (Math.random()*(WIDTH-3)+1);
			while(getCell(yTest, xTest) != '.') {
				yTest = (int) (Math.random()*(HEIGHT-3)+1);
				xTest = (int) (Math.random()*(WIDTH-3)+1);
			}
			hunters.add(new Predator(this, user, yTest, xTest));
		}
	}
	
	public int getHeight() {
		return HEIGHT;
	}

	public int getWidth() {
		return WIDTH;
	}
	
	public User getUser() {
		return user;
	}
	
	public List<Predator> getPredator() {
		return hunters;
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
	
	private String generateFood() {
		char[] board = startboard.toCharArray();
		ArrayList<Integer> possibilities = new ArrayList<Integer>();
		for (int y=0; y<board.length;y++){
			if (board[y]=='.'){
				possibilities.add(y);
			}
		}
		
		for (int j=0; j<goalFood; j++) {
			board[randomItem(possibilities)] = 'o';
		}
		
        String newstartboard = String.valueOf(board);
        return newstartboard;
	}
	
	public boolean isEmpty(double x, double y){
		return (getCell((int)x, (int)y) == '.' || getCell((int)x, (int)y) == 'o' || getCell((int)x, (int)y) == 'W');
	}
	
	protected boolean eatFood(double x, double y){
		if (getCell((int)x, (int)y) == 'o'){
			board.get((int)y).set((int)x, '.');
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean winGame(double x, double y){
		if (getCell((int)x, (int)y) == 'W'){
			return true;
		} else {
			return false;
		}
	}
	
	public void update(){
		user.move();
		for(Predator x : hunters){
			x.move();
		}
	}
	
	protected void openGate(){
		board.get(0).set(9, 'W');
	}
	
	public boolean endgame() {
		return false;
	}
	
}
