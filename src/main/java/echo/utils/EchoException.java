package echo.utils;

/**
 * EchoException class that handles exceptions related to user inputs.
 */
public class EchoException extends Exception {

    /**
     * Constructor for the EchoException class.
     *
     * @param e Exception message.
     */
    public EchoException(String e) {
        super(e);
    }
}
