package tp.pr4.control;

/**
 * Created by Kerith on 27/02/2015.
 */
public class ArgumentInterpreter {

    private String[] tokens;

    public ArgumentInterpreter(String[] tokens) {
        this.tokens = tokens;
        //TODO: Puede que haya que pasarlo a uppercase
    }

    //Precondition: in Main we know that tokens[0] is -g or --game
    public GameType getGame() {

        GameType type = GameType.CONNECT4;

       if(tokens[1].equals("gr")){
           type = GameType.GRAVITY;
       } else if (tokens[1].equals("c4")) {
           type = GameType.CONNECT4;
       } else if (tokens[1].equals("co")) {
           type = GameType.COMPLICA;
       } else {
           type = GameType.ERROR;
       }
        return type;
    }

    public String getHelp() {
        return "usage: tp.pr3.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]" + "\n" +
                " -g,--game <game>           Type of game (c4, co, gr). By default, c4." + "\n"  +
                " -h,--help                  Displays this help." + "\n" +
                " -x,--dimX <columnNumber>   Number of columns on the board (Gravity only)." + "\n" +
                "                            By default, 10." + "\n" +
                " -y,--dimY <rowNumber>      Number of rows on the board (Gravity only). By" + "\n" +
                "                            default, 10.";
    }

    //Main recognizes if tokens[pos - 1] equals -x, -y, --sizeX or --sizeY
    public int getSize(int pos) {
        return Integer.parseInt(tokens[pos]);
    }

}
