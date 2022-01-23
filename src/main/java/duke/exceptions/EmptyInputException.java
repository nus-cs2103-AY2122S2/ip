package duke.exceptions;

public class EmptyInputException extends DukeException {
    public EmptyInputException() {
        super("     ☹ You cannot enter an empty command! Please enter a command.");
    }
}
