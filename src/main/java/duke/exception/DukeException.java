package duke;

public class DukeException extends RuntimeException {

    /**
     * Initialises a new instance of DukeException.
     *
     * @param errorMessage The error message that is related to the exception.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

class InvalidCommand extends DukeException {

    private final String errorMessage;
    /**
     * Initialises a new instance of InvalidCommand.
     *
     * @param errorMessage The error message that is related to the exception.
     */
    public InvalidCommand(String errorMessage) {
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

class InvalidIndex extends DukeException {

    private final String errorMessage;
    /**
     * Initialises a new instance of InvalidIndex.
     *
     * @param errorMessage The error message that is related to the exception.
     */
    public InvalidIndex(String errorMessage) {
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

class InvalidDateTime extends DukeException {
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