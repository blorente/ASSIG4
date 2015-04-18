package tp.pr4;

import java.util.Scanner;

import tp.pr4.logic.Game;
import tp.pr4.control.*;

public class Main {

	public static void main(String[] args) {
        //Interpret command-line arguments
        ArgumentInterpreter interpreter = new ArgumentInterpreter(args);

        GameType type = GameType.CONNECT4;
        UIType uitype = UIType.CONSOLE;
        int sizeX = 10;
        int sizeY = 10;

        int index = 0;
        boolean stop = false;
        boolean helpAsked = false;
        boolean wrongCommand = false;

        while (index < args.length && !stop) {
        	//Recognize help type
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
                        stop = true;
                    }
                } else if (args[index].equals("-y") || args[index].equals("--dimY")) {
                    try {
                    	sizeY = interpreter.getSize(index + 1);
                        index++;
                    } catch (Exception e) {                                     	
                        stop = true;
                    }
                }
                index++;                
            } else if ((args[index].equals("-h") || args[index].equals("--help")) && index == 0) {//Recognize help
                System.out.println(interpreter.getHelp());
                helpAsked = true;
                index++;
            } else if ((args[index].equals("-u") || args[index].equals("--ui"))) { //Recognize ui type
            	uitype = interpreter.getUI(index);
            	index += 2;
            } else { //There is an error
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
        //Check the correctness of the message and initialize the game if necessary.
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
        	//Everything went right
            GameTypeFactory factory = null;
            Game game = null;
            
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

            //Select the appropriate controller
            Controller controller = null;
            if(uitype == UIType.CONSOLE) {
            	controller = new ConsoleController(factory, game);
            } else if (uitype == UIType.WINDOW) {
            	controller = new WindowController(factory, game);
            }        
            //Run the game
            controller.run();

            controller = new ConsoleController(factory, game);
            controller.run();
        }
	}

}