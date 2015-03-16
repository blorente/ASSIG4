package tp.pr4.logic;

import tp.pr4.Util;

public class Connect4Move extends Move { // Connect4Movement is a subclass of
											// Move

	public Connect4Move(int moveColumn, Counter moveColour) {
		setCol(moveColumn);
		setPlayer(moveColour);
	}

	public void executeMove(Board board) throws InvalidMove {
			int h = Util.firstEmptyPosition(board, this.getCol());
			// Check whether column is valid
			if (!Util.isColumnValid(board, this.getCol())) {
				throw new InvalidMove("Invalid move: column number "+ this.getCol() +" is not on the board.");
			} else if (h < Util.ERRORTHRESHOLD) {
				throw new InvalidMove("Invalid move: column number "+ this.getCol() +" is already full.");
			} else {
				board.setPosition(this.getCol(),
								  Util.firstEmptyPosition(board, this.getCol()),
								  this.getPlayer());	
			}
	}

	public void undo(Board board){
		// Searches desired column from top to bottom
		int h = Util.firstEmptyPosition(board, this.getCol());
		if (h != board.getHeight()) {
			if (h < Util.ERRORTHRESHOLD) {
				h = Board.MINHEIGHT - 1;
			}
			board.setPosition(this.getCol(), h + 1, Counter.EMPTY);
		} 
	}
}
