package duke;

/**
 * Represents an exception that occurs as a result of an inability to save user tasks to file while exiting the
 * application
 */
public class DukeInvalidFileSaveException extends DukeException {

    /**
     * Representation of a <code>DukeInvalidFileSaveException</code> object
     * @return String representation corresponding to the <code>DukeInvalidFileSaveException</code> object
     */
    @Override
    public String toString() {
        return "File cannot be saved.";
    }
}
