package g4.mazeGame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	
	private User user;
	public boolean eaten=false;
	static Random rand; // Global variable
	public final int HEIGHT = 17;
	public final int WIDTH = 19;
	private final int[] goalFood = {10, 7, 5};
	private int salinity = 3;
	public static final int MAX_SALINITY = 3;
	private final int[] NUM_PREDATORS = {3, 2, 1};
	int totalFood;
	private String startboard1 =
			 "##########*########\n"
			+"###...#...^.#.....#\n"
			+"#...........#..#.##\n"
			+"#...##..#...#..#..#\n"
			+"#...##..#.........#\n"
			+"#.......#.....###.#\n"
			+"#..#...###........#\n"
			+"#..#........#..#..#\n"
			+"#..####.....#.....#\n"
			+"#........#######..#\n"
			+"#####.............#\n"
			+"#.........######..#\n"
			+"##..###........#..#\n"
			+"#..............#..#\n"
			+"#..#####...#####..#\n"
			+"#.................#\n"
			+"###################\n";
	private String startboard2 =
			 "#########*#########\n"
			+"####.....^.....####\n"
			+"##....#######...###\n"
			+"##..###.....#....##\n"
			+"#...........#.....#\n"
			+"####...###........#\n"
			+"###..#......####.##\n"
			+"####...##........##\n"
			+"#......#######..###\n"
			+"#....#......#.....#\n"
			+"#..#...#....#..####\n"
			+"#..#...###........#\n"
			+"#..#.#.###...#..###\n"
			+"#..#####....##..###\n"
			+"#..........###....#\n"
			+"#..####...........#\n"
			+"###################\n";
	private String startboard3 =
			 "#########*#########\n"
			+"#.....#..^..#.....#\n"
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
	
	private String[] startBoards = {startboard3, startboard2, startboard1};
	
	private ArrayList<ArrayList<Character>> boardArr = new ArrayList<ArrayList<Character>>();
	
	private List<Predator> hunters = new ArrayList<Predator>();
	
	public Board(int s, int deaths){
		salinity = s;
		String new_start = generateFood();
		for(String row : new_start.split("\n")){
			ArrayList<Character> r= new ArrayList<Character>();
			for(int i=0;i<row.length();i++){
				r.add(row.charAt(i));
			}
			boardArr.add(r);
		}
		
		user = new User(this);
		
		for(int i = 0; i < NUM_PREDATORS[salinity - 1] - deaths/2; i++){
			int yTest = (int) (Math.random()*(WIDTH-1)+1);
			int xTest = (int) (Math.random()*(HEIGHT-6)+1);
			while(getCell(yTest, xTest) != '.') {
				yTest = (int) (Math.random()*(WIDTH-1)+1);
				xTest = (int) (Math.random()*(HEIGHT-6)+1);
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
		return boardArr.get(y).get(x);
	}

	public static int randomItem(ArrayList<Integer> mylist) {
	    rand = new Random(); 
	    Integer randomInt = mylist.get(rand.nextInt(mylist.size()));
	    mylist.remove(randomInt);
	    return randomInt;
	}
	
	private String generateFood() {
		char[] board = startBoards[salinity - 1].toCharArray();
		ArrayList<Integer> possibilities = new ArrayList<Integer>();
		for (int y=0; y<board.length;y++){
			if (board[y]=='.'){
				possibilities.add(y);
			}
		}
		
		for (int j=0; j<goalFood[salinity - 1]; j++) {
			board[randomItem(possibilities)] = 'o';
		}
		
        return String.valueOf(board);
	}
	
	public boolean isEmpty(double x, double y){
		return (getCell((int)x, (int)y) == '.' || getCell((int)x, (int)y) == 'o' || getCell((int)x, (int)y) == 'W' || getCell((int)x, (int)y) == '^');
	}
	
	public boolean isEmptyPred(double x, double y){
		return (getCell((int)x, (int)y) == '.' || getCell((int)x, (int)y) == 'o' || getCell((int)x, (int)y) == '^');
	}
	
	protected boolean eatFood(double x, double y){
		if (getCell((int)x, (int)y) == 'o'){
			boardArr.get((int)y).set((int)x, '.');
			return true;
		} else {
			return false;
		}
	}
	
	protected boolean winGame(double x, double y){
		if (getCell((int)x, (int)y) == 'W' || getCell((int)x, (int)y) == 'E' ){
			return true;
		} else {
			return false;
		}
	}
	
	public void update(){
		user.update();
		for(Predator x : hunters){
			x.move();
		}
	}
	
	protected void openGate(){
		int x = 0;
		int y = 0;
		while (x < HEIGHT){
			y = 0;
			while (y < WIDTH){
				if (boardArr.get(x).get(y) == '*' || boardArr.get(x).get(y) == '^' ) {
					boardArr.get(x).set(y, 'W');
				}
				y++;
			}
			x++;
		}
	}
	
	public int getGoalFood(){
		return goalFood[salinity - 1];
	}
	
	public int getSalinity(){
		return salinity;
	}
	
}
