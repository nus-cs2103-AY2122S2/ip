package duke;

public class DukeException extends RuntimeException {

    /**
     * Constructs a DukeException instance.
     *
     * @param s The error message.
     */
    public DukeException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "☹ OOPS!!! " + this.getMessage();
    }
}
