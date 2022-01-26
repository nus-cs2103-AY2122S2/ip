package funbox.exception;

/**
 * FunBoxExceptions is exceptions used by FunBoxGear for error handling
 */
public class FunBoxExceptions extends Exception {

    /**
     * Constructor for FunBoxExceptions
     *
     * @param errorMessage The error message to be displayed
     */
    public FunBoxExceptions(String errorMessage) {
        super(errorMessage);
    }
}
