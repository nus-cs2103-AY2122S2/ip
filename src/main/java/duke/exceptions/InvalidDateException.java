package duke.exceptions;

public class InvalidDateException extends DukeException {

    public InvalidDateException() {
        super("Date is invalid! Try yyyy-mm-dd");
    }
}
