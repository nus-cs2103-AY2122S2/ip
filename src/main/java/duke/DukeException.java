package duke;

/**
* Represents the errors Duke might face during its operation.
*/
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }
}
