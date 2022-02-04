package duke.exception;

public class DukeDateTimeFormatException extends DukeException {
    /**
     * Instantiates DukeException using the parent constructor from Exception.
     *
     * @param message String Error message.
     */
    public DukeDateTimeFormatException() {
        super("Date and time must be in the format of yyyy-MM-dd HHmm.");
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
