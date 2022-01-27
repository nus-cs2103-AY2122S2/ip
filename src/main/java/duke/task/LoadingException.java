package duke.task;

/**
 * Represents an exception when reading the saved data file, caused by the data being in the wrong format.
 * A <code>LoadingException</code> is represented by a String message.
 */
public class LoadingException extends duke.task.DukeException {
    /**
     * Returns a new instance of <code>LoadingException</code> with the specified Exception message.
     * @param message The error message of the exception.
     */
    public LoadingException(String message) {
        super(message);
    }

    /**
     * Returns a String representation of the Exception, which notifies the user of the cause and solution.
     */
    @Override
    public String toString() {
        return "UH-OH!! seems like the file is not in the right format... (⊙.⊙) \n" +
                "don't worry! I'll start a new file for you!";
    }
}
