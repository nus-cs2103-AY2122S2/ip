package duke.exceptions;

/**
 * Custom exception to notify when the date keyword is not inputted (eg. /by or /at).
 */
public class NullDateProvidedException extends RuntimeException {
    /**
     * Creates a NullDateProvidedException object with a message.
     *
     * @param str Message.
     */
    public NullDateProvidedException(String str) {
        super(str);
    }

    /**
     * Creates a NullDateProvidedException object.
     */
    public NullDateProvidedException() {
        super();
    }
}
