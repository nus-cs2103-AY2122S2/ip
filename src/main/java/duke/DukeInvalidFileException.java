package duke;

/**
 * Represents an exception that occurs as a result of an invalid backup file found while starting the Duke application
 */
public class DukeInvalidFileException extends DukeException {

    /**
     * Representation of a <code>DukeInvalidFileException</code> object
     * @return String representation corresponding to the <code>DukeInvalidFileException</code> object
     */
    @Override
    public String toString() {
        return "Backup file cannot be read.";
    }
}
