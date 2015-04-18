package tp.pr4.control;

import tp.pr4.logic.*;

import java.util.Scanner;

public class Connect4Factory implements GameTypeFactory {

	public GameRules createRules() {
		GameRules rules = new Connect4Rules();
		return rules;
	}


	public Move createMove(int col, int row, Counter colour) {
		Move move = new Connect4Move(col, colour);
        return move;
	}

	public Player createHumanPlayerAtConsole(Scanner in) {
		Player newPlayer = new HumanConnect4Player(in);
		return newPlayer;
	}

	
	public Player createRandomPlayer() {
		Player player = new RandomConnect4Player();
		return player;
	}

}
