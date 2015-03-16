package tp.pr4.logic;

import tp.pr4.DirectionX;
import tp.pr4.DirectionY;
import tp.pr4.Util;

public class GravityMove extends Move {

    /*Inherited from Move
    private Counter player;
    private int col;
    */
    private int row;

    public GravityMove(int moveColumn, int moveRow, Counter moveColour) {
        this.setCol(moveColumn);
        this.setPlayer(moveColour);
        this.setRow(moveRow);
    }
	
	public void executeMove(Board board) throws InvalidMove {
        DirectionX dirX = DirectionX.NOTHING;
        DirectionY dirY = DirectionY.NOTHING;

        int distRight = board.getWidth() - this.getCol() + 1;
        int distUp = this.getRow();
        int distDown = board.getHeight() - this.getRow() + 1;
        int distLeft = this.getCol();

        int[] distances = {distLeft, distRight, distDown, distUp};

        if (!(Util.isCellEmpty(board, this.getCol(), this.getRow()))) {
        	throw new InvalidMove("Invalid move: position ("+ this.getCol() +", " + this.getRow() + ") is already occupied.");
        } else if (!(Util.isRowValid(board, this.getRow()) && 
        			Util.isColumnValid(board, this.getCol()))) {
        	throw new InvalidMove("Invalid move: position ("+ this.getCol() +", " + this.getRow() + ") is not on the board.");
        } else { // The move is valid

        //Decide the direction in which the counter must move

            //Decide dirX
            if (!Util.thereIsSmaller(distLeft, distances) && (distLeft != distRight)) {
                dirX = DirectionX.LEFT;
            } else if (!Util.thereIsSmaller(distRight, distances) && (distLeft != distRight)) {
                dirX = DirectionX.RIGHT;
            }
            //Decide dirY
            if (!Util.thereIsSmaller(distUp, distances) && (distUp != distDown)) {
                dirY = DirectionY.UP;
            } else if (!Util.thereIsSmaller(distDown, distances) && (distUp != distDown)) {
                dirY = DirectionY.DOWN;
            }
             //Otherwise, it's in the center
            
            //If the counter is not in the center
            if (dirX != DirectionX.NOTHING || dirY != DirectionY.NOTHING) {
	           //Look for the right empty position (modifies fields directly!)
	            while ((board.getPosition(this.getCol() + Util.convertDirX(dirX),
	                                    this.getRow() + Util.convertDirY(dirY)) == Counter.EMPTY) && 
	                                    Util.isColumnValid(board, this.getCol()  + Util.convertDirX(dirX)) &&
	                                    Util.isRowValid(board, this.getRow() + Util.convertDirY(dirY))) {
	                this.setCol(this.getCol() + Util.convertDirX(dirX));
	                this.setRow(this.getRow() + Util.convertDirY(dirY));	
	            }
            }
            //Place the counter
            board.setPosition(this.getCol(), this.getRow(), this.getPlayer());

        } 
    }
	
	public void undo(Board board) {
        board.setPosition(this.getCol(), this.getRow(), Counter.EMPTY);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }
}
