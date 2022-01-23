package exception;

/** Planned exceptions thrown by bot. */
public class DukeException extends Exception {
    public DukeException(String errorMessage) {
        super("____________________________________________________________\n" +
                "☹ OOPS!!! " + errorMessage +
                "\n____________________________________________________________");
    }
}