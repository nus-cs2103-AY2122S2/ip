package duke;

/**
 * Represents an exception that occurs as a result of no backup file found when starting the Duke application
 */
public class DukeFileNotFoundException extends DukeException {

    /**
     * Representation of a <code>DukeFileNotFoundException</code> object
     * @return String representation corresponding to the <code>DukeFileNotFoundException</code> object
     */
    @Override
    public String toString() {
        return "Backup file is not found.";
    }
}
