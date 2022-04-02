package tsundere;

/**
 * Exception class mostly used for incorrect string format.
 */
public class TsundereException extends Exception {

    /**
     * Creates a new TsundereException.
     *
     * @param errorMsg string of the error message.
     */
    public TsundereException(String errorMsg) {
        super(errorMsg);
    }
}
