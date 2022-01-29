package duke.exceptions;

public class WrongFormatException extends DukeException {

    public WrongFormatException() {
        super("Command is in the wrong format!");
    }
}
