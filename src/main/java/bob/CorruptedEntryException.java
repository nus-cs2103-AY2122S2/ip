package bob;

/**
 * Is thrown when a Duke saved file cannot be parsed correctly.
 */

public class CorruptedEntryException extends Exception {

    public CorruptedEntryException(String message, Throwable error) {
        super(message, error);
    }

    public CorruptedEntryException(Throwable error) {
        super(error);
    }

    public CorruptedEntryException(String message) {
        super(message);
    }
}
