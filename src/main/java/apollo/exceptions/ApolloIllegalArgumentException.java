package apollo.exceptions;

import apollo.messages.Messages;

public class ApolloIllegalArgumentException extends ApolloException{

    public ApolloIllegalArgumentException(String message) {
        super(message);
    }

    public ApolloIllegalArgumentException() {
        this(Messages.ILLEGAL_ARGUMENT_EXCEPTION);
    }
}
