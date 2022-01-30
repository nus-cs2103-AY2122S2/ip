package apollo.exceptions;


/**
 * Thrown when arguments supplied are illegal.
 * Extends {@code ApolloException} superclass.
 */
public class ApolloIllegalArgumentException extends ApolloException{

    /**
     * Constructor for {@code ApolloIllegalArgumentException}.
     *
     * @param message Describes exception.
     */
    public ApolloIllegalArgumentException(String message) {
        super(message);
    }
}
