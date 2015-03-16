package tp.pr4.control;

import java.util.Scanner;
import tp.pr4.logic.*;

public class HumanConnect4Player extends HumanPlayer {

	public HumanConnect4Player(Scanner in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public Move getMove(Board board, Counter colour) {
		Move newMove = new Connect4Move(readColumn("column"), colour);
		return newMove;
	}

	

}
