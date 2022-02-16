package spike.exception;

/**
 * Class for all Spike specific exceptions
 */
public class SpikeException extends Exception {
    /**
     * Constructor using error message.
     *
     * @param str
     */
    public SpikeException(String str) {
        super(str);
    }
}
