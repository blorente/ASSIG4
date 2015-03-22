package tp.pr4.logic;

public interface GameObserver {
	
	/**
	 * When the game is reset, either with a new game or simply 
	 * restarting the current game, the observer receives a 
	 * notification through this method.
	 * 	
	 * @param board
	 * 			The corresponding board (read only)
	 * @param player
	 * 			The player who should play next
	 * @param undoPossible
	 * 			true if there are moves that can be undone, false otherwise
	 */
	void reset(ReadOnlyBoard board, Counter player, Boolean undoPossible);

	/**
	 * When the game finishes, the observer receives 
	 * a notification through this method.
	 * 
	 * @param board
	 * 			The corresponding board (read only)
	 * @param winner
	 * 			The winning player. Counter.EMPTY means a draw.
	 */
	void onGameOver(ReadOnlyBoard board, Counter winner);
	
	/**
	 * When the execution of a move finishes, 
	 * the observer receives a notification through this method.
	 * 
	 * @param board
	 * 			The corresponding board (read only)
	 * @param player
	 * 			The player of the corresponding move.
	 * @param nextPlayer
	 * 			The player who plays next.
	 */
	void moveExecFinished(ReadOnlyBoard board, Counter player, Counter nextPlayer);
	
	/**
	 * Move errors are reported to observers through this method.
	 * 
	 * @param msg
	 * 			An informative error message.
	 */
	void onMoveError(String msg);
	
	/**
	 * When the execution of undo finishes, 
	 * the observer receives a notification through this method.
	 * 
	 * @param board
	 * 			The corresponding board (read only).
	 * @param nextPlayer
	 * 			The player who plays next.	
	 * @param undoPossible
	 * 			true if there are moves that can be undone, otherwise false
	 */
	void onUndo(ReadOnlyBoard board, Counter nextPlayer, boolean undoPossible);
	
	/**
	 * When the undo fails, because it is not possible, 
	 * observers are notified through this method.
	 */
	void onUndoNotPossible();

    /**
     * NOT IN JAVADOC
     * Method to call to get the initial state of the game,
     * when the observer is firstt attached.
     *
     * @param board
     *          Read only board to display it for the first time.
     */
    void onAttachToObserved(ReadOnlyBoard board, Counter turn);
}
