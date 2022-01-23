package duke;

/**
 * Represents an exception that occurs as a result of an invalid task number keyed in by the user when adding, removing
 * , marking on unmarking tasks
 */
public class DukeTaskNotFoundException extends DukeException {

    /**
     * Representation of a <code>DukeTaskNotFoundException</code> object
     * @return String representation corresponding to the <code>DukeTaskNotFoundException</code> object
     */
    @Override
    public String toString() {
        return "The task number keyed in is invalid.";
    }
}
