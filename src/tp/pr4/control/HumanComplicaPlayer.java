package tp.pr4.control;

import java.util.Scanner;
import tp.pr4.logic.*;

public class HumanComplicaPlayer extends HumanPlayer{

	public HumanComplicaPlayer(Scanner in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Move newMove = new ComplicaMove(readColumn("column"), colour);
		return newMove;
	}
	
}
