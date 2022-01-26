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
        if (msg.equals("todo") || msg.equals("deadline") || msg.equals("event")) {
            return "Hey! This " + msg + " can't be empty nyaan~!";
        } else if (msg.equals("mark") || msg.equals("unmark") || msg.equals("delete")) {
            return "Hey! Please choose something to " + msg + " nyaan~!";
        } else {
            return msg;
        }
    }

    // TBD - More errors will be added to handle other potential invalid inputs by the user
}
