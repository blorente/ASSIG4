package tp.pr4.views.console;

import tp.pr4.control.ConsoleController;
import tp.pr4.logic.Counter;
import tp.pr4.logic.GameObserver;
import tp.pr4.logic.Observable;
import tp.pr4.logic.ReadOnlyBoard;

public class ConsoleView implements GameObserver {

	private ConsoleController controller;

	public ConsoleView(Observable<GameObserver> g, ConsoleController c) {
		g.addObserver(this);
		this.controller = c;
	}

	@Override
	public void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible) {
		// TODO Auto-generated method stub
		System.out.println("Game restarted.");
	}

	@Override
	public void onGameOver(ReadOnlyBoard board, Counter winner) {
		// TODO Auto-generated method stub
		System.out.println(board);
		if (winner == Counter.EMPTY) {
			System.out.println("Game over. Game ended in a draw");
		} else {
			System.out.println("Game over. "
					+ this.convertTurnFirstUpper(winner.toString()) + " wins");
		}
		System.out.println("Closing the game... ");
	}

	@Override
	public void moveExecFinished(ReadOnlyBoard board, Counter player,
			Counter nextPlayer) {
		// TODO Auto-generated method stub
		System.out.println(board);
		System.out.println(convertTurnFirstUpper(nextPlayer.toString())
				+ " to move");
	}

	@Override
	public void onMoveError(String msg) {
		// TODO Auto-generated method stub
		System.err.println(msg);
	}

	@Override
	public void onUndo(ReadOnlyBoard board, Counter nextPlayer,
			boolean undoPossible) {
		// TODO Auto-generated method stub
		System.out.println(board);
		System.out.println(convertTurnFirstUpper(nextPlayer.toString())
				+ " to move");

	}

	@Override
	public void onUndoNotPossible() {
		System.err.println("Nothing to undo.");
	}

	private String convertTurnFirstUpper(String currentTurn) {
		String out;
		if (currentTurn.equals("WHITE")) {
			out = "White";
		} else {
			out = "Black";
		}
		return out;
	}

}
