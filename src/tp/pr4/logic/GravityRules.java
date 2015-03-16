package tp.pr4.logic;

import tp.pr4.DirectionX;
import tp.pr4.DirectionY;
import tp.pr4.Util;

public class GravityRules implements GameRules {

    private int dimX;
    private int dimY;
    private static final Counter INITIALPLAYER = Counter.WHITE;

    public GravityRules(int numCols, int numRows) {
    	this.dimX = numCols;
    	this.dimY = numRows;
    }

    public Board newBoard() {
        return new Board(this.dimX, this.dimY);
    }

    public Counter initialPlayer() {
        return GravityRules.INITIALPLAYER;
    }

    public Counter winningMove(Move lastMove, Board b) {
        boolean end = false;
        int x = Board.MINWIDTH;
        int y = b.getHeight();
        Counter winner = Counter.EMPTY;

        while ((y >= Board.MINHEIGHT) && !end) {
            while ((x <= b.getWidth() && !end)) {
                // Check up
                end = Util.checkCellInDirection(b, x, y, DirectionX.NOTHING,
                        DirectionY.UP);
                // Check right
                if (!end)
                    end = Util.checkCellInDirection(b, x, y, DirectionX.RIGHT,
                            DirectionY.NOTHING);
                // Check up-left diagonal
                if (!end)
                    end = Util.checkCellInDirection(b, x, y, DirectionX.LEFT,
                            DirectionY.UP);
                // Check up-right diagonal
                if (!end)
                    end = Util.checkCellInDirection(b, x, y, DirectionX.RIGHT,
                            DirectionY.UP);
                // Increase x counter
                if (!end)
                    x++;
            }
            if (end) {
                winner = b.getPosition(x, y);
            }
            x = Board.MINWIDTH;
            y--;
        }
        return winner;
    }

    public boolean isDraw(Counter lastPlaced, Board b) {
        boolean full = true;
        int x = Board.MINWIDTH, y = b.getHeight();
        while ((y >= Board.MINHEIGHT) && full) {
            while ((x <= b.getWidth() && full)) {
                if (b.getPosition(x, y) == Counter.EMPTY) {
                    full = false;
                }
                x++;
            }
            x = Board.MINWIDTH;
            y--;
        }
        return full;
    }

    public Counter nextTurn(Counter lastPlaced, Board b) {
        Counter next;
        if (lastPlaced == Counter.BLACK) {
            next = Counter.WHITE;
        } else {
            next = Counter.BLACK;
        }
        return next;
    }
    
}
