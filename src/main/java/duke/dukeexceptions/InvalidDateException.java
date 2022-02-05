package duke.dukeexceptions;

/**
 * The InvalidDateException exception occurs when the users enters a date format other than dd/mm/yyyy HHmm.
 */
public final class InvalidDateException extends DukeExceptions {
    public InvalidDateException() {
        super("Sorry! You have entered the wrong date!");
    }
}
