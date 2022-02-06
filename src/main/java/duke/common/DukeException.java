package duke.common;

/**
 * This is a custom exception class for Duke that extends the Exception class.
 */
public class DukeException extends Exception {
    /**
     * Creates a DukeException object that receives a string for when
     * a DukeException is thrown and to be displayed to the user.
     * 
     * @param error Error contains the message passed by a function that throws DukeException.
     */
    public DukeException(String error) {
        super(error);

        assert error != null : "DukeException[DukeException] error cannot be null.";
        assert error.length() > 0 : "DukeException[DukeException] error must not be empty.";
    }
}
