package paggro.exception;

/**
 * This class encapsulates an exception thrown during the execution of PaggroBot.
 */
public class PaggroException extends Exception {
    /**
     * Constructor of PaggroException.
     *
     * @param msg String of error message related to exception.
     */
    public PaggroException(String msg) {
        super(msg);
    }
}
