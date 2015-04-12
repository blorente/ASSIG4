package tp.pr4.control;

import java.util.Scanner;

import tp.pr4.logic.Game;
import tp.pr4.logic.GameRules;
import tp.pr4.logic.InvalidMove;
import tp.pr4.logic.Move;
import tp.pr4.views.console.ConsoleView;
import tp.pr4.views.window.MainWindow;
import tp.pr4.logic.Counter;

public class WindowController extends Controller {
	
	
	private Counter turn;
	private Game game;
	private GameTypeFactory factory;
	private GameRules rules;
	private Player random;
	private MainWindow window;
	
	public WindowController (GameTypeFactory factory, Game game) {
		this.game = game;
		this.factory = factory;
		this.rules = factory.createRules();
		this.window = new MainWindow(game, this);
		this.random = factory.createRandomPlayer();
	}
	
	public void makeMove(int col, int row, Counter colour) { 
		Move move = this.factory.createMove(col, row, colour);
		try { 
			this.game.executeMove(move);
		} catch ( InvalidMove e) { 
		} 
	}
	
	public void reset(GameRules rules) { 
		this.game.reset(rules);
	}
	
	public void undo() {
		this.game.undo();
	}
	
	public void changeGame (Instruction instruction, int width, int height) {
		switch (instruction) {
		//TODO implementa lo que queda de la window
		case PLAY_C4:
			this.factory = new Connect4Factory();
			this.rules = this.factory.createRules();
			this.game.reset(this.rules);
			this.random = this.factory.createRandomPlayer();
			break;
		case PLAY_CO:
			this.factory = new ComplicaFactory();
			this.rules = this.factory.createRules();
			this.game.reset(this.rules);
			this.random = this.factory.createRandomPlayer();
			break;
		case PLAY_G:
			this.factory = new GravityFactory(width, height);
			this.rules = this.factory.createRules();
			this.game.reset(this.rules);
			this.random = this.factory.createRandomPlayer();
			break;
		default:
			break;
		
		}
	}
	
	public void requestQuit() {
		//TODO implementa
	}
	
	public void randomMove(Counter player) {
		Move move = random.getMove(this.game.getBoard(), player);
		try {
			this.game.executeMove(move);
		} catch (InvalidMove e) {
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
