package duke.exception;

/**
 * The superclass of all custom exceptions.
 */
public abstract class DukeException extends Exception {

    private String errorMessage;

    /**
     * Constructor for custom exception.
     * @param errorMessage descriptor for the exception.
     */
    public DukeException(String errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }

    /**
     * Getter for error message.
     * @return errorMessage - string descriptor for the exception.
     */
    @Override
    public String toString() {
        return errorMessage;
    }

}
