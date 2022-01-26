package duke.exceptions;

public class DukeException extends Exception {

    public DukeException() {
    }

    @Override
    public String toString() {
        return "OOPS!!! You gave an invalid command!";
    }
}
