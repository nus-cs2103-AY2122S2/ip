package duke.dukeexceptions;

/**
 * The InvalidDate exception occurs when the users enters a date format other than dd/mm/yyyy HHmm.
 */
public final class InvalidDate extends DukeExceptions {
    public InvalidDate() {
        super("Sorry! You have entered the wrong date!");
    }
}
