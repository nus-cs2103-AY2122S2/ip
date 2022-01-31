package duke.exception;

/**
 * Unique exceptions pertaining to Chi Chat bot.
 */
public class ChiException extends Exception {

    /** Body of exception message */
    private String msg;

    /**
     * Constructor for ChiException.
     * @param exn The exception message when ChiException is thrown.
     */
    public ChiException(String exn) {
        super(exn);
        this.msg = exn;
    }

    /**
     * Returns an error message based on the exception body passed in.
     * @return String of an error message.
     */
    @Override
    public String toString() {
        // Error messages based on message sent
        return this.msg;
    }
}
