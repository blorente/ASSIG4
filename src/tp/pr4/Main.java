package tp.pr4;

import java.util.Scanner;

import tp.pr4.logic.Game;
import tp.pr4.control.*;

public class Main {

	public static void main(String[] args) {
        //Interpret command-line alrguments
        ArgumentInterpreter interpreter = new ArgumentInterpreter(args);

        GameType type = GameType.CONNECT4;
        int sizeX = 10;
        int sizeY = 10;

        int index = 0;
        boolean stop = false;
        boolean helpAsked = false;
        boolean wrongCommand = false;

        while (index < args.length && !stop) {
            if (args[index].equals("-g") || args[index].equals("--game")) {
                type = interpreter.getGame();
                index++;
                if (type == GameType.ERROR) {
                    System.err.println("Incorrect use: Game '" + args[index] + "' incorrect.\n" +
                            "For more details, use -h|--help.");                    
                    stop = true;
                    System.exit(1);
                }
                index++;
            } else if (type == GameType.GRAVITY) {
                if (args[index].equals("-x") || args[index].equals("--dimX")) {
                    try {                    	
                        sizeX = interpreter.getSize(index + 1);
                        index++;
                    } catch (Exception e) {
                        //TODO: Puede que tengas que outputear algo aqui                    	
                        stop = true;
                    }
                } else if (args[index].equals("-y") || args[index].equals("--dimY")) {
                    try {
                    	sizeY = interpreter.getSize(index + 1);
                        index++;
                    } catch (Exception e) {
                        //TODO: Puede que tengas que outputear algo aqui                    	
                        stop = true;
                    }
                }
                index++;
            } else if ((args[index].equals("-h") || args[index].equals("--help")) && index == 0) {
                System.out.println(interpreter.getHelp());
                helpAsked = true;
                index++;
            } else {
                char ch = args[index].charAt(0);
                if (ch == '-') {
                    System.err.println("Incorrect use: Unrecognized option: " + args[index] + "\n" +
                                        "For more details, use -h|--help.");
                    wrongCommand = true;
                    System.exit(1);
                }

                stop = true;
            }
        }

        if (stop && type != GameType.ERROR && !wrongCommand) {
            String s = "";
            while (index < args.length - 1) {
                s += args[index] + " ";
                index++;
            }
            s += args[index];
            System.err.println("Incorrect use: Illegal arguments: " + s + "\n" +
                                "For more details, use -h|--help.");

            System.exit(1);
        } else if (type != GameType.ERROR && !helpAsked && !wrongCommand){

            GameTypeFactory factory = null;
            Game game = null;
            Scanner in = new Scanner(System.in);

            switch (type) {
                case GRAVITY:
                    factory = new GravityFactory(sizeX, sizeY);
                    game = new Game(factory.createRules());
                    break;
                case CONNECT4:
                    factory = new Connect4Factory();
                    game = new Game(factory.createRules());
                    break;
                case COMPLICA:
                    factory = new ComplicaFactory();
                    game = new Game(factory.createRules());
                    break;
			default:
				break;
            }

            Controller controller = new ConsoleController(factory, game);

            controller.run();
        }
	}

}