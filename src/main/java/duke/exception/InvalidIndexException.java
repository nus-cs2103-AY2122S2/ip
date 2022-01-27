package duke.exception;

public class InvalidIndexException extends DukeException {
    public InvalidIndexException() {
        super("Sorry, your argument has invalid Index. Hint: Use \"list\" command to help you");
    }
}
