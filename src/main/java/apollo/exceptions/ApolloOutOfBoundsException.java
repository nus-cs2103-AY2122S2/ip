package apollo.exceptions;

import static apollo.messages.Messages.OUT_OF_BOUNDS_EXCEPTION;

public class ApolloOutOfBoundsException extends ApolloException {

    public ApolloOutOfBoundsException(String message) {
        super(message);
    }

    public ApolloOutOfBoundsException() {
        this(OUT_OF_BOUNDS_EXCEPTION);
    }
}
