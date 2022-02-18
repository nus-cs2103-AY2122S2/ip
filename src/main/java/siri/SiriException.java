package siri;

/**
 * This class represents an exception that is thrown by the program. Exceptions thrown are exclusive to the program.
 */
class SiriException extends RuntimeException {
    /**
     * Constructor for the class.
     *
     * @param errMsg message to describe the exception faced.
     */
    public SiriException(String errMsg) {
        super(errMsg);
    }
}
