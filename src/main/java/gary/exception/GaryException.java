package gary.exception;

import java.lang.Exception;

/**
 * Represents Exceptions that will be encountered by Gary
 * Invoked by invalid user input
 */
public class GaryException extends Exception {
    private String msg;

    /**
     * Constructs a new Gary Exception with given message
     *
     * @param msg invalid user input
     */
    public GaryException(String msg) {
       this.msg = msg;
    }

    /**
     * Prints out error message
     */
    public String garyError() {
        assert !msg.isBlank();
        return "Sorry, what is " + this.msg;
    }
}
