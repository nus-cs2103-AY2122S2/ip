package duke.exception;

public class InvalidDateTime extends DukeException {
    private final String errorMessage;

    /**
     * Initialises a new instance of InvalidDateTime.
     *
     * @param errorMessage The error message that is related to the exception.
     */
    public InvalidDateTime(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Creates an error message to be displayed to the user.
     *
     * @return String containing the output message.
     */
    @Override
    public String toString() {
        return "OOPS!!! " + this.errorMessage;
    }
}