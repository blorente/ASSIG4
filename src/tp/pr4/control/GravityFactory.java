package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.*;

public class GravityFactory implements GameTypeFactory {

	private int sizeX, sizeY;
	
	public Move createMove(int col, int row, Counter colour) {
		Move move = new GravityMove(col, row, colour);
		return move;
	}

	
	public Player createHumanPlayerAtConsole(Scanner in) {
		Player newPlayer = new HumanGravityPlayer(in);
		return newPlayer;
	}

	
	public Player createRandomPlayer() {
		Player player = new RandomGravityPlayer();
		return player;
	}

	public GameRules createRules() {
		GameRules rules = new GravityRules(this.sizeX, this.sizeY);
		return rules;
	}
	
	public GravityFactory(int col, int row) {
		if(col < 1 || row < 1) {
			this.sizeX = 1;
			this.sizeY = 1;
		} else {
			this.sizeX = col;
			this.sizeY = row;
		}
	}	

}
