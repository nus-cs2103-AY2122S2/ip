package apollo.exceptions;

/**
 * Thrown when I/O operations cannot be completed.
 * Extends {@code ApolloException} superclass.
 */
public class ApolloIoException extends ApolloException {

    /**
     * Constructor for {@code ApolloIoException}.
     *
     * @param message Describes exception.
     */
    public ApolloIoException(String message) {
        super(message);
    }
}
