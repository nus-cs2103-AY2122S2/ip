package kenobi.parser;

public class ParseException extends Exception {
    public ParseException(String msg) {
        super("Sorry, I don't understand this " + msg);
    }
}
