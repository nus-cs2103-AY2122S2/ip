package duke.exceptions;

public class DukeException extends Exception {

    public DukeException(String x) {
        super("\nDuke: " + x + "\n");
    }
}
