package arthur.exceptions;

/**
 * Custom exception for arthur.Arthur.
 * Thrown when supported instruction doesn't have description.
 */
public class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String e) {
        super("The description of " + e + " cannot be empty");
    }
}
