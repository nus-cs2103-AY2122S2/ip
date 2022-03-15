package duke;

/**
 * The DukeException class, which entails UnknownCommandException, EmptyDescriptionException and InvalidInputException
 *
 * @author Benjamin Koh
 */

public class DukeException extends RuntimeException {

    /**
     * Constructor for a new instance of DukeException
     * @param e Exception error message
     */

    public DukeException(String e) {
        super(e);
    }
}


class UnknownCommandException extends DukeException {
    private final String message;

    /**
     * Constructor for a new instance of UnknownCommandException
     *
     * @param e Exception error message
     */

    public UnknownCommandException(String e) {
        super(e);
        this.message = e;
    }

    /**
     * Returns a string of the exception message to be displayed.
     *
     * @return string of the exception message
     */

    @Override
    public String toString() {
        return "    OH NO! :(" + this.message;
    }
}

class EmptyDescriptionException extends DukeException {
    private final String message;

    /**
     * Constructor for a new instance of EmptyDescriptionException
     *
     * @param e Exception error message
     */

    public EmptyDescriptionException(String e) {
        super(e);
        this.message = e;
    }

    /**
     * Returns a string of the exception message to be displayed.
     *
     * @return string of the exception message
     */

    @Override
    public String toString() {
        return "    OH NO! :(" + this.message;
    }
}

class InvalidInputException extends DukeException {
    private final String message;

    /**
     * Constructor for a new instance of InvalidInputException
     *
     * @param e Exception error message
     */

    public InvalidInputException(String e) {
        super(e);
        this.message = e;
    }

    /**
     * Returns a string of the exception message to be displayed.
     *
     * @return string of the exception message
     */

    @Override
    public String toString() {
        return "    OH NO! :(" + this.message;
    }
}
