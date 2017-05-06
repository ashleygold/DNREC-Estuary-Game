package g4.mainController;

public interface MiniGameController {
	/**
	 * called once per tick; repaints the JFrame and advance the model
	 */
	public void update();
	
	/**
	 * safely kills the JFrame when the user switches game. 
	 */
	public void dispose();
}
