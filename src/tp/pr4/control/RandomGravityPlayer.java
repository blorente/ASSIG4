package tp.pr4.control;

import java.util.Random;
import tp.pr4.logic.*;

public class RandomGravityPlayer implements Player {

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Random rand = new Random();
		int col, row;
		do {
			col = rand.nextInt(board.getWidth()) + Board.getMinwidth();
			row = rand.nextInt(board.getHeight()) + Board.getMinheight();
		} while (board.getPosition(col, row) != Counter.EMPTY); // In this while I search for a random column and row
																// such that there is an empty counter
		
		Move newMove = new GravityMove (col, row, colour); // Then generate the move and return
		return newMove;
	}

}
