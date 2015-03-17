package tp.pr4.control;

import tp.pr4.logic.*;

import java.util.Random;

import tp.pr4.Util;

public class RandomConnect4Player implements Player {

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Random rand = new Random();
		int col;
		do {
			col = rand.nextInt(board.getWidth()) + Board.getMinwidth();
		} while (Util.firstEmptyPosition(board, col) < Util.ERRORTHRESHOLD); // In this while I search for and non full random column in the board
		
		Move newMove = new Connect4Move(col, colour); // Then generate the move and return
		
		return newMove;
	}

}
