package duke.exceptions;

/**
 * Representing an Event exception.
 */
public class EventException extends DukeException {
    public EventException() {
        super("Invalid format for event. \n" +
                "Correct format: event <name> /at <Start YYYY-MM-DD> <Start HHMM> <End YYYY-MM-DD> <End HHMM>\n" +
                "Example: event housewarming /at 2012-03-04 1200 2012-03-04 1700\n"
        );
    }
}
