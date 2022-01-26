package duke.exceptions;

/**
 * A throwable object to indicate an invalid operation.
 */
public class InvalidOperationException extends Exception {
    private String error;

    /**
     * Constructs the InvalidOperationException Object.
     *
     * @param s String containing the invalid operation
     */
    public InvalidOperationException(String s) {
        this.error = s;
    }

    /**
     * @return a String representation of the error message
     */
    @Override
    public String toString() {
        return "This task is already " + error;
    }

}
