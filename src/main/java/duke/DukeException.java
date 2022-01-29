package duke;

public class DukeException extends RuntimeException {
    /**
     * Constructor for a new instance of DukeException.
     *
     * @param e The error message of the exception.
     */
    public DukeException(String e) {
        super(e);
    }
}

class UnknownCommandException extends DukeException {
    private final String msg;

    /**
     * Constructor for a new instance of UnknownCommandException.
     *
     * @param e The error message of the exception.
     */
    public UnknownCommandException(String e) {
        super(e);
        this.msg = e;
    }

    /**
     * Returns a string of the exception message to be displayed.
     *
     * @return string of the exception message to be displayed
     */
    @Override
    public String toString() {
        return this.msg;
    }
}

class InvalidInputException extends DukeException {
    private final String msg;

    /**
     * Constructor for a new instance of InvalidInputException.
     *
     * @param e The error message of the exception.
     */
    public InvalidInputException(String e) {
        super(e);
        this.msg = e;
    }

    /**
     * Returns a string of the exception message to be displayed.
     *
     * @return string of the exception message to be displayed
     */
    @Override
    public String toString() {
        return this.msg;
    }
}

class EmptyDescException extends DukeException {
    private final String msg;

    /**
     * Constructor for a new instance of EmptyDescException.
     *
     * @param e The error message of the exception.
     */
    public EmptyDescException(String e) {
        super(e);
        this.msg = e;
    }

    /**
     * Returns a string of the exception message to be displayed.
     *
     * @return string of the exception message to be displayed
     */
    @Override
    public String toString() {
        return this.msg;
    }
}