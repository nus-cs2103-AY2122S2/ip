package Main;

/**
 * Exception class mostly used for incorrect string format
 */
public class TsundereException extends Exception{

    /**
     * create a new TsundereException
     *
     * @param errorMsg string of the error message
     */
    public TsundereException(String errorMsg) {
        super(errorMsg);
    }
}
