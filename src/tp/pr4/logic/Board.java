package tp.pr4.logic;

import tp.pr4.Util;

public class Board {

	public static final int MINWIDTH = 1;
	public static final int MINHEIGHT = 1;
	private int tx, ty;
	private Counter[][] brd;

	public Board(int tx, int ty) {
		if (tx > Board.MINWIDTH) {
			this.tx = tx;			
		} else {
			this.tx = Board.MINWIDTH;			
		}	
		
		if (ty > Board.MINHEIGHT) {
			this.ty = ty;
		} else {
			this.ty = Board.MINHEIGHT;
		}
		this.brd = new Counter[this.tx][this.ty];
		reset();
	}

	public Board() {
		this.tx = Board.MINWIDTH;
		this.ty = Board.MINHEIGHT;
		this.brd = new Counter[tx][ty];
		reset();
	}

	public int getHeight() {
		return this.ty;
	}

	public int getWidth() {
		return this.tx;
	}

	public Counter getPosition(int x, int y) {
		Counter ctr = Counter.EMPTY;
		if (Util.isColumnValid(this, x) && Util.isRowValid(this, y)) {
			ctr = this.brd[x - 1][y - 1];
		} else {
			//throw new InvalidMove("Invalid move: position ("+ x +" ," + y + ") is not on the board.");
        }
		return ctr;
	}

	public void setPosition(int x, int y, Counter colour) {
		if (Util.isColumnValid(this, x)) {
			this.brd[x - 1][y - 1] = colour;
		}
	}

	public void reset() {
		for (int i = 0; i < tx; i++) {
			for (int j = 0; j < ty; j++) {
				this.brd[i][j] = Counter.EMPTY;
			}
		}
	}

	public String toString() {
		String board = "";
		for (int i = 1; i <= this.ty; i++) {
			board = board + "|";
			for (int j = 1; j <= this.tx; j++) {
				board = board + this.counterToString(getPosition((j), (i)));
			}
			board = board + "|" + "\n";
		}
		board = board + "+";
		for (int h = 1; h <= this.tx; h++) {
			board = board + "-";
		}
		board = board + "+" + "\n";
		board = board + " ";
		for (int h = 1; h <= this.tx; h++) {
			board = board + (h % 10); //Changed to mod10 to only display (0...9) single digit
		}
		board = board + "\n";
		return board;
	}

//	public void printBoard() {
//		System.out.print(this.toString());
//	}

	public static int getMinwidth() {
		return MINWIDTH;
	}

	public static int getMinheight() {
		return MINHEIGHT;
	}

	public String counterToString(Counter counter) {
		String cell = " ";
		switch (counter) {
		case BLACK:
			cell = "X";
			break;
		case WHITE:
			cell = "O";
			break;
		default:
			cell = " ";
		}
		return cell;
	}

}