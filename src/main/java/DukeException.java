public class DukeException extends Exception {

    /** The original message sent. */
    private String msg;

    public DukeException(String exn) {
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
        switch(msg) {
            case "todo":
                return "Hey! This todo can't be empty nyaan~!";
            case "deadline":
                return "Hey! This deadline can't be empty nyaan~!";
            case "event":
                return "Hey! This event can't be empty nyaan~!";
            default:
                return "Chi-san doesn't understand that nyaan~!";
        }
    }

    // TBD - More errors will be added to handle other potential invalid inputs by the user
}
