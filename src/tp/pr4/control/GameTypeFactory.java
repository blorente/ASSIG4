package tp.pr4.control;

import tp.pr4.logic.*;

public interface GameTypeFactory {

	GameRules createRules();
	
	Move createMove(int col, int row, Counter colour);

	Player createHumanPlayerAtConsole(java.util.Scanner in);

	Player createRandomPlayer();
}
