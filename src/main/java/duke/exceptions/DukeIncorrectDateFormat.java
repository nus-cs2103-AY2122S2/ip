package duke.exceptions;

public class DukeIncorrectDateFormat extends DukeException {

    public DukeIncorrectDateFormat() {
        super("This is an incorrect format, the correct format is YYYY-MM-DD TT");
    }
}
