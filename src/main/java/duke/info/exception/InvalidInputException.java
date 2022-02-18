package duke.info.exception;

public class InvalidInputException extends DukeException {

    /**
     * Constructs an InvalidInputException with the specified
     * detail message
     * @param str - the detail message
     */

    public InvalidInputException(String str) {
        super(str);
    }
}
