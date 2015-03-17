package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Game;

public class ConsoleController extends Controller {

	private GameTypeFactory factory;
	private Game game;
	private Scanner in;
	
	public ConsoleController(GameTypeFactory factory, Game g) {		
		// TODO Auto-generated constructor stub
		this.game = g;
		this.factory = factory;
		this.in = new java.util.Scanner(System.in);
	}

	@Override
	void run() {
		// TODO Auto-generated method stub
		
	}

}
