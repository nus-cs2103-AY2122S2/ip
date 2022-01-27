package duke;

/**
 * Class used to handle exceptions/errors during runtime
 */
public class DukeException extends Exception {
    private String errorMsg;

    /**
     * Constructor for DukeException
     * @param msg Message to pass into the exception
     */
    public DukeException(String msg) {
        this.errorMsg = msg;
    }

    /**
     * Gets the error message assigned to DukeException
     * @return The error message
     */
    public String getErrorMsg() {
        return errorMsg;
    }
}
