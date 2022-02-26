package sparrow.commons.exceptions;

/**
 * Represents a parse encountered by the parser.
 */
public class ParseException extends Exception {
    public ParseException(String message) {
        super(message);
    }
}
