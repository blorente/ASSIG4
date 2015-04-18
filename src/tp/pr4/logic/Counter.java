package tp.pr4.logic;

public enum Counter {
    BLACK, EMPTY, WHITE;

    public String toString(Counter counter) {
        String word;
        switch (counter) {
            case BLACK:
                word = "Black";
                break;
            case WHITE:
                word = "White";
                break;
            default:
                word = "Empty";
                break;
        }
        return word;
    }
}