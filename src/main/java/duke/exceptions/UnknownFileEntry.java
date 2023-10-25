package duke.exceptions;

/**
 * Exception thrown when an unknown line is read in a file.
 */
public class UnknownFileEntry extends Throwable {

    /**
     * Create an instance of UnknownFileEntry exception.
     *
     * @param message
     */
    public UnknownFileEntry(String message) {
        super(message);
    }
}
