package exceptions;

/**
 * Exception to represent the case when there is no description given
 */
public class NoDescException extends Exception{
    public NoDescException(String errorMessage) {
        super(errorMessage);
    }
}
