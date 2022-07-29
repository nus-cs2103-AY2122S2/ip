package duke;

/**
 * Exception class for Duke to handle Exceptions
 */

public class DukeException extends Exception {

    private String message;
    public DukeException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}
