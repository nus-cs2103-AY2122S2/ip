package src.main.java.duke;

/**
 * DukeException is an Exception unique to Duke.
 */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("☹ OOPS!!! " + errorMessage);
    }
}
