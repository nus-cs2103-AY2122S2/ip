package seedu.duke.exceptions;

public class LoadingException extends DukeException {
    public LoadingException() {
        super("Looks like there is a problem with creating or retrieving the files for tasks");
    }
}