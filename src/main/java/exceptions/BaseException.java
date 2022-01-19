package exceptions;

public class BaseException extends Exception {
    /**
     * Constructor for BaseException.
     */
    public BaseException() {
        super();
    }

    /**
     * Constructor for BaseException.
     *
     * @param errMsg error message.
     */
    public BaseException(String errMsg) {
        super(errMsg);
    }
}
