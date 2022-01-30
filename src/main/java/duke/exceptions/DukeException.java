package duke.exceptions;

public class DukeException extends Exception {
    /**
     * Display exception message when a DukeException is thrown
     *
     * @param message Message from method where the DukeException is thrown
     */
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
