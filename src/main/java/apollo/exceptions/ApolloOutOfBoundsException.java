package apollo.exceptions;

import static apollo.messages.Messages.OUT_OF_BOUNDS_EXCEPTION;

/**
 * Thrown when attempting to access indexes outside of bounds.
 * Extends {@code ApolloException} superclass.
 */
public class ApolloOutOfBoundsException extends ApolloException {

    /**
     * Constructor for {@code ApolloOutOfBoundsException}.
     *
     * @param message Describes exception.
     */
    public ApolloOutOfBoundsException(String message) {
        super(message);
    }

    /**
     * Constructor with default error message.
     */
    public ApolloOutOfBoundsException() {
        this(OUT_OF_BOUNDS_EXCEPTION);
    }
}
