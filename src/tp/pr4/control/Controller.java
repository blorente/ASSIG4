package tp.pr4.control;

import tp.pr4.logic.*;

import java.util.Scanner;

public abstract class Controller {

	private Game game;
	private Scanner in;
	private GameRules rules;
	private Player[] players;
	
	private GameTypeFactory factory;
	private int numPlayer;
	private String[] tokens;
	/*
	public Controller(GameTypeFactory factory, Game g, java.util.Scanner in) {
		this.game = g;
		this.in = in;
		this.factory = factory;
		this.rules = factory.createRules();
		this.players = new Player[2];
		this.initializePlayers();
		this.numPlayer = 0;
		this.tokens = null;
	}
*/
	public Controller() {}
	
	/**
	 * The main method of the controller.
	 * It is assume to execute the game 
	 * until it finishes 
	 * (depending on the kind of the controller)
	 */
	abstract public void run();
	
	/*public void run() {
		Instruction inst = Instruction.ERROR;
		String currentTurn;

		boolean correctUndo = false;

		while (!this.game.isFinished() && !(inst.equals(Instruction.EXIT))) {
			System.out.println(this.game.getBoard());
			currentTurn = this.game.getTurn().toString();			
			System.out.println(currentTurn + " to move");
		
			
				inst = readInstruction(this.in);
				switch (inst) {
				case MOVE:
					Move mov = players[this.numPlayer].getMove(
							this.game.getBoard(), this.game.getTurn());
					try {
						this.game.executeMove(mov);
						this.numPlayer = this.getNextPlayerIndex(this.game
								.getTurn());
					} catch (InvalidMove e) {
						System.err.println(e.getMessage());
					}
					break;
				case UNDO:
					correctUndo = this.game.undo();
					if (!correctUndo) {
						System.err.println("Nothing to undo.");
					}
					break;
				case RESTART:
					this.game.reset(this.rules);
					System.out.println("Game restarted.");
					break;
				case ERROR:
					// // non existing command
					// System.err.println("Invalid move, please try again.");
					break;
				case EXIT:
					System.out.println("Exit requested. ");
					break;
				case PLAY_C4:
					this.factory = new Connect4Factory();
					this.rules = this.factory.createRules();
					this.game.reset(this.rules);
					initializePlayers();
					System.out.println("Game restarted.");
					break;
				case PLAY_CO:
					this.factory = new ComplicaFactory();
					this.rules = this.factory.createRules();
					this.game.reset(this.rules);
					initializePlayers();
					System.out.println("Game restarted.");
					break;
				case PLAY_G:
						try {
							if (tokens.length == 4) {
								this.factory = new GravityFactory(
										Integer.parseInt(this.tokens[2]),
										Integer.parseInt(this.tokens[3]));							
									this.rules = this.factory.createRules();
							}
						} catch (NumberFormatException e) {
							System.err.println("Invalid row or column number.");
						}
						this.game.reset(this.rules);
						initializePlayers();
						System.out.println("Game restarted.");
					break;
				case PLAYER:
					Counter colour = null;
					try {
						colour = Counter.valueOf(this.tokens[1]);
						if (this.tokens[2].equals("RANDOM")) {
							this.players[this.getNextPlayerIndex(colour)] = factory
									.createRandomPlayer();
							
						} else if (this.tokens[2].equals("HUMAN")) {
							this.players[this.getNextPlayerIndex(colour)] = factory
									.createHumanPlayerAtConsole(this.in);
						}
//						this.game.reset(this.rules);
//						System.out.println("Game restarted.");
					} catch (IllegalArgumentException e) {
						System.err.println("Invalid move, please try again.");
					}
					break;
				case HELP:
					System.out.println(getHelpString());
					break;
				default:
					break;
				}
			
		}
		
		System.out.println("Closing the game... ");
	}	*/

	private Instruction readInstruction(Scanner in) {
		//We initialize to move for the random player
		Instruction inst = Instruction.MOVE;
		String instString = "";
		// Start scanner
		System.out.print("Please enter a command: ");

		instString = in.nextLine();
		String commandRaw = instString;
		instString = instString.toUpperCase();
		String delims = "[ ]+";
		this.tokens = instString.split(delims);
		try { //Catches Exceptions for parseInt and valueOf
			switch (this.tokens.length) {
			case 1:
				break;
			case 2:
				if ((this.tokens[0] + " " + this.tokens[1]).equals("PLAY C4")) {
					instString = "PLAY_C4";
				} else if ((this.tokens[0] + " " + this.tokens[1])
						.equals("PLAY CO")) {
					instString = "PLAY_CO";
				}
				break;
			case 3:
				if ((this.tokens[0] + " " + this.tokens[1] + " " + this.tokens[2])
						.equals("MAKE A MOVE")) {
					instString = "MOVE";
				} else if (this.tokens[0].equals("PLAYER")) {
					instString = "PLAYER";
				}
				break;
			case 4:
				if ((this.tokens[0] + " " + this.tokens[1]).equals("PLAY GR")
						) {
					instString = "PLAY_G";
				}
				break;
			}
		//Transforms the string into an instruction			
			inst = Instruction.valueOf(instString);
		} catch (Exception e) {
			
			System.err.println(commandRaw.toLowerCase()
					+ ": command not understood, please try again");
			inst = Instruction.ERROR;
		}
		
		return inst;
	}

	private int getNextPlayerIndex(Counter colour) {
		int i = 0;
		if (colour == Counter.BLACK) {
			i = 1;
		}
		return i;
	}

	private void initializePlayers() {
		for (int i = 0; i < 2; i++) {
			this.players[i] = this.factory.createHumanPlayerAtConsole(this.in);
		}
	}

	public String[] getTokens() {
		return tokens;
	}

	private String getHelpString() {
		String help = "The available commands are:"
				+ "\n"
				+ "\n"
				+

				"MAKE A MOVE: place a counter on the board."
				+ "\n"
				+ "UNDO: undo the last move of the game."
				+ "\n"
				+ "RESTART: restart the game."
				+ "\n"
				+ "PLAY [c4|co|gr] [dimX dimY]: change the type of game."
				+ "\n"
				+ "PLAYER [white|black] [human|random]: change the type of game."
				+ "\n" + "EXIT: exit the application." + "\n"
				+ "HELP: show this help." + "\n";
		return help;
	}

}
