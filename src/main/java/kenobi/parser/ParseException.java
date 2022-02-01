package kenobi.parser;

public class ParseException extends Exception {
    public ParseException(String msg) {
        super("Whoopsie-Daisy :( " + msg);
    }
}
