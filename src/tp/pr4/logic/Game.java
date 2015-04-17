package tp.pr4.logic;

import java.util.ArrayList;
import java.util.List;

public class Game implements Observable<GameObserver> {

	public static final int WINCON = 4;
	public static final int MAX_OBSERVERS = 10;

	// Class Attributes
	private Board board;
	private Counter turn;// This attribute is public static because we need in
							// Movement.java
	private boolean finished;
	private Counter winner;
	private boolean draw;
	private UndoStack undoStack;
	private GameRules rules;
	
	//Observers
	private List<GameObserver> observers;

	// Constructs a new game.
	public Game(GameRules rules) {
		// Initialize game
		this.board = rules.newBoard();
		this.turn = rules.initialPlayer();
		this.finished = false;
		this.winner = Counter.EMPTY;
		this.draw = false;
		this.undoStack = new UndoStack();
		this.rules = rules;
		this.observers = new ArrayList<GameObserver>();
	}

	// Restarts the current game. This operation cannot be undone.
	public void reset(GameRules rules) {
		this.board = rules.newBoard();
		this.turn = rules.initialPlayer();
		this.finished = false;
		this.winner = Counter.EMPTY;
		this.draw = false;
		this.undoStack.clear();
		this.rules = rules;
        for (GameObserver o : this.observers) {
            o.reset(this.board, this.turn, false);
        }
	}

	// Executes the move indicated by the column number provided as argument.
	public void executeMove(Move move) throws InvalidMove {
		// Check whether column is valid
		if (this.turn != move.getPlayer()) {
            //Uncomment in case we need an exception
			//throw new InvalidMove("Invalid move: is not " + move.getPlayer() +"'s turn");

            //Notify observers
            for (GameObserver o : this.observers) {
                o.onMoveError("Invalid move: is not " + move.getPlayer() +"'s turn");
            }
		}
		if (this.draw) {
			throw new InvalidMove("is a draw");
		}
		if (!this.isFinished())  {
			move.executeMove(this.board);
            for (GameObserver o : this.observers) {
                o.moveExecFinished(this.board, this.turn, this.rules.nextTurn(this.turn, this.board));
            }
			// A complete and correct movement is pushed
			this.undoStack.push(move);
			// Update the winner
			this.winner = this.rules.winningMove(move, this.board);
			// Check if the game has ended in a draw
			this.draw = this.rules.isDraw(move.getPlayer(), this.board);
			if (this.draw) {
				this.winner = Counter.EMPTY;
				this.finished = true;
			} else if (this.winner != Counter.EMPTY) {
				this.finished = true;
                //Notify game over
                for (GameObserver o : this.observers) {
                    o.onGameOver(this.board, this.winner);
                }
			}
			if (this.winner == Counter.EMPTY && !this.finished) {
				this.turn = this.rules.nextTurn(this.turn, this.board);
			}
		} else {
            //throw new InvalidMove("Invalid move: The game is already finished");

            //Notify observers
            for (GameObserver o : this.observers) {
                o.onMoveError("Invalid move: The game is already finished");
            }
        }
	}

    // Undo the last movement executed
    public boolean undo() {
        boolean success = false;
        if (!this.undoStack.isEmpty()) {
            Move mov = this.undoStack.getLastElement();
            mov.undo(this.board);
            this.undoStack.pop();
            this.turn = this.rules.nextTurn(this.turn, this.board);
            success = true;

        } else {
            success = false;
            for (GameObserver o : this.observers) {
                o.onUndoNotPossible();
            }
        } 
        //Notify the observers
        for (GameObserver o : this.observers) {
            o.onUndo(this.board, turn, success);
        }

        return success;
    }

	

	// Returns the color of the player whose turn it is.
	public Counter getTurn() {
		return this.turn;
	}

	// Returns the color of the winner. It is only valid if the game has
	// finished (isFinished() == true).
	public Counter getWinner() {
		return this.winner;
	}

	public void setWinner(Counter c) {
		this.winner = c;
	}

	// Returns true if the game has finished and false otherwise.
	public boolean isFinished() {
		return this.finished;
	}

	// Accessor method for the board.
	public ReadOnlyBoard getBoard() {
		return this.board;
	}

//	public void displayBoard() {
//		this.board.printBoard();
//	}

	public boolean isDraw() {
		return draw;
	}

	@Override
	public void addObserver(GameObserver o) {
		this.observers.add(o);
        o.onAttachToObserved(this.board, this.turn);
	}

	@Override
	public void removeObserver(GameObserver o) {
		this.observers.remove(o);
	}
	
	public String toString() {
        return this.board.toString();
    }
}
