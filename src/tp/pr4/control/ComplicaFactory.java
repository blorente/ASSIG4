package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.ComplicaMove;
import tp.pr4.logic.ComplicaRules;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.Move;


public class ComplicaFactory implements GameTypeFactory {

	
	public GameRules createRules() {
		GameRules rules = new ComplicaRules();
		return rules;
	}

	
	public Player createHumanPlayerAtConsole(Scanner in) {
		Player newPlayer = new HumanComplicaPlayer(in);
		return newPlayer;
	}

	
	public Player createRandomPlayer() {
		Player player = new RandomComplicaPlayer();
		return player;
	}


	public Move createMove(int col, int row, Counter colour) {
		Move move = new ComplicaMove(col, colour);
		return move;
	}

}
