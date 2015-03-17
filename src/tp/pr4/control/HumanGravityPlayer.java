package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.*;

public class HumanGravityPlayer extends HumanPlayer {

	public HumanGravityPlayer(Scanner in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	public Move getMove(ReadOnlyBoard board, Counter colour) {
		Move newMove = new GravityMove(readColumn("column"),readColumn("row"), colour );
		return newMove;
	}

	

}
