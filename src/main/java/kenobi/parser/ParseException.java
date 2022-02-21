package kenobi.parser;

public class ParseException extends Exception {
    public ParseException(String msg) {
        super("There were problems in the archives! " + msg);
    }
}
