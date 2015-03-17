
package tp.pr4.logic;

public interface ReadOnlyBoard {

	/**
	 * Accessor method which returns the height of the board.
	 * 
	 * @return
	 * 			Number of rows of the board.
	 */
	int getHeight();
	
	/**
	 * Accessor method which returns the width of the board.
	 * 
	 * @return
	 * 			Number of columns of the board.	
	 */
	int getWidth();
	
	/**
	 * Accessor method which returns the state of a particular
	 * position on the board 
	 * (i.e. the value of the counter at that position).
	 * 
	 * @param x
	 * 			Column number (1..width)
	 * @param y
	 * 			Row number (1..height)
	 * @return
	 * 			State of the position on the board. 
	 * 			If the position is invalid, returns Counter.EMPTY
	 */
	Counter getPosition(int x,       int y);
}
