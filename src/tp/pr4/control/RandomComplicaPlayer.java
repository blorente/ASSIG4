package tp.pr4.control;

import java.util.Random;

import tp.pr4.logic.Board;
import tp.pr4.logic.ComplicaMove;
import tp.pr4.logic.Counter;
import tp.pr4.logic.Move;

public class RandomComplicaPlayer implements Player {

	public Move getMove(Board board, Counter colour) {
		Random rand = new Random();
		int col = rand.nextInt(board.getWidth()) + Board.getMinwidth(); // I generate a random column number for the new move
		Move newMove = new ComplicaMove(col, colour);
		return newMove;
	}
	
}
