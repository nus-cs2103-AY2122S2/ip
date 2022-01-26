package duke.exception;

public class ChiException extends Exception {

    /** The original message sent. */
    private String msg;

    public ChiException(String exn) {
        super(exn);
        this.msg = exn;
    }

    /**
     * Returns an error message based on the message sent.
     * @return String of error message.
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
