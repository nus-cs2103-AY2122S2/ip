package apollo.exceptions;

import apollo.messages.Messages;

public class ApolloOutOfBoundsException extends ApolloException {

    public ApolloOutOfBoundsException(String message) {
        super(message);
    }

    public ApolloOutOfBoundsException() {
        this(Messages.OUT_OF_BOUNDS_EXCEPTION);
    }
}
