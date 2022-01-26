package duke;

/**
 * Custom exception class for any Duke specific exceptions
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}

