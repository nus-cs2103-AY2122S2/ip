package apollo.exceptions;

import static apollo.messages.Messages.ILLEGAL_ARGUMENT_EXCEPTION;

public class ApolloIllegalArgumentException extends ApolloException{

    public ApolloIllegalArgumentException(String message) {
        super(message);
    }

    public ApolloIllegalArgumentException() {
        this(ILLEGAL_ARGUMENT_EXCEPTION);
    }
}
