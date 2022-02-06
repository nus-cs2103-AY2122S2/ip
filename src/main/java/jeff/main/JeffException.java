package jeff.main;

/**
 * JeffException class is a custom exception class to handle
 * problems that might occur.
 */
public class JeffException extends Exception {

    /**
     * Constructor for the JeffException class.
     *
     * @param errorMessage Exception message.
     */
    public JeffException(String errorMessage) {
        super(errorMessage);
    }

}
