package common;

/** An exception to be thrown when the user input is not valid.  */
public class NoGoodException extends Exception {
    public NoGoodException(String errorMessage) {
        super(errorMessage);
    }
}
