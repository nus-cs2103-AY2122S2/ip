package apollo.exceptions;

/**
 * Superclass for all exceptions in Apollo.
 * Extends {@code Exception} superclass.
 */
public class ApolloException extends Exception {

    /**
     * Constructor for {@code ApolloException}.
     *
     * @param message Describes exception.
     */
    public ApolloException(String message) {
        super(message);
    }
}
