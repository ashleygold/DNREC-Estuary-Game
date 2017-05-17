package g4.mazeGame.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Board {
	
	/** the maximum allowable salinity, equivalently the number of levels */
	public static final int MAX_SALINITY = 3;
	
	/** the height of the board */
	public final int HEIGHT = 17;
	
	/** the width of the board */
	public final int WIDTH = 19;
	
	/** the goal food for the various levels */
	private final int[] goalFood = {10, 7, 5, 5};
	
	/** the number of predators for the various levels */
	private final int[] NUM_PREDATORS = {3, 2, 1, 0};
	
	/** the base board for the tutorial level */
	private final String startboard0 =
			 "#########*#########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#########..########\n"
			+"#.................#\n"
			+"###################\n";
	
	/**the base board for the first level */
	private final String startboard1 =
			 "##########*########\n"
			+"###...#.....#.....#\n"
			+"#...........#..#..#\n"
			+"#...##..#...#..#..#\n"
			+"#...##..#.........#\n"
			+"#.......#....####.#\n"
			+"#..#...###........#\n"
			+"#..#........#..#..#\n"
			+"#...###.....#.....#\n"
			+"#........#######..#\n"
			+"#####.............#\n"
			+"#.................#\n"
			+"##..###...######..#\n"
			+"#..............#..#\n"
			+"#..#####...#####..#\n"
			+"#.................#\n"
			+"###################\n";
	
	/** the base board for the second level */
	private final String startboard2 =
			 "#########*#########\n"
			+"####...........####\n"
			+"##....#######...###\n"
			+"##..###.....#....##\n"
			+"#.................#\n"
			+"####...###........#\n"
			+"###.........###..##\n"
			+"####...##........##\n"
			+"#......######...###\n"
			+"#...#.......#.....#\n"
			+"#......#....#..####\n"
			+"#..#...###........#\n"
			+"#..#...###...#....#\n"
			+"#...####....##..###\n"
			+"#..........###....#\n"
			+"#..###............#\n"
			+"###################\n";
	
	/** the base board for the third level */
	private final String startboard3 =
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
	
	/** the base boards for the various levels */
	private final String[] startBoards = {startboard3, startboard2, startboard1, startboard0};
	
	/** if the user has been eaten */
	private boolean isEaten=false;
	
	/** the random generation for board */
	private static Random rand; // Global variable
	
	/** the user in the board */
	private User user;
	
	/** representation of the actual board */
	private ArrayList<ArrayList<Character>> boardArr = new ArrayList<ArrayList<Character>>();
	
	/** all of the Predators on the board */
	private List<Predator> hunters = new ArrayList<Predator>();
	
	/** the salinity (level) for the board */
	private int salinity;
	
	/**
	 * Constructs a board with the specified level (salinity) and difficulty (deaths).
	 * @param s salinity (level) of the board
	 * @param deaths difficulty. The number of times the user has died on this level.  
	 */
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

	/**
	 * Selects and removes a random item from the list.  Used by generateFood()
	 * @param mylist the list of integer locations 
	 * @return a random (valid) location
	 */
	private static int randomItem(ArrayList<Integer> mylist) {
	    rand = new Random(); 
	    Integer randomInt = mylist.get(rand.nextInt(mylist.size()));
	    mylist.remove(randomInt);
	    return randomInt;
	}
	
	/**
	 * Loads the correct base maze string and adds the correct amount of food bits.    
	 * @return string representation of the board, with food
	 */
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
	
	/**
	 * Checks if the space is open for the user to move to.  A space is open if it is not a wall.  
	 * @param x the x-location
	 * @param y the y-location
	 * @return if the space is empty
	 */
	protected boolean isEmpty(double x, double y){
		return (getCell((int)x, (int)y) == '.' ||
				getCell((int)x, (int)y) == 'o' ||
				getCell((int)x, (int)y) == 'W');
	}
	
	/**
	 * Checks if the space is open for a predator to move to.  A space is open if it is not a wall or the exit.
	 * @param x the x-location
	 * @param y the y-location
	 * @return if the space is empty
	 */
	protected boolean isEmptyPred(double x, double y){
		return (getCell((int)x, (int)y) == '.' ||
				getCell((int)x, (int)y) == 'o');
	}
	
	/**
	 * Tries to eat the food at a location.  If the location is food, the food is removed and true is returned.  
	 * Otherwise false is returned.  
	 * @param x the x-location
	 * @param y the y-location
	 * @return if food was successfully eaten
	 */
	protected boolean eatFood(double x, double y){
		if (getCell((int)x, (int)y) == 'o'){
			boardArr.get((int)y).set((int)x, '.');
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Checks if the user has won the game.  The user wins if the location is the goal.  
	 * @param x the x-location
	 * @param y the y-location
	 * @return if the user has won the game.  
	 */
	protected boolean winGame(double x, double y){
		if (getCell((int)x, (int)y) == 'W'){
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Updates the board by a frame
	 */
	public void update(){
		user.update();
		for(Predator x : hunters){
			 if (x.move()){
				 isEaten = true;
			 }
		}
	}
	/**
	 * Opens the goal to allow the user to win.  Called when the user collects all the food.
	 * @return true when gate is opened.  
	 */
	protected boolean openGate(){
		int x = 0;
		int y = 0;
		while (x < HEIGHT){
			y = 0;
			while (y < WIDTH){
				if (boardArr.get(x).get(y) == '*' ) {
					boardArr.get(x).set(y, 'W');
					return true;
				}
				y++;
			}
			x++;
		}
		return false;
	}
	
	/**
	 * Used only in testing, adds food to the bottom right corner
	 */
	protected void setFoodAtCorner(){
		boardArr.get(15).set(17, 'o');
	}
	
	/**
	 * Returns the character value of the specified location
	 * @param x the x-location
	 * @param y the y-location
	 * @return the character cell at the specified location
	 */
	public char getCell(int x, int y) {	
		return boardArr.get(y).get(x);
	}
	
	public int getGoalFood(){
		return goalFood[salinity - 1];
	}
	
	public int getSalinity(){
		return salinity;
	}
	
	public User getUser() {
		return user;
	}
	
	public List<Predator> getPredator() {
		return hunters;
	}
	
	public boolean getIsEaten(){
		return isEaten;
	}
	
}
